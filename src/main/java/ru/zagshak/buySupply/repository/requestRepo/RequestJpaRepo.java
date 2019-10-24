package ru.zagshak.buySupply.repository.requestRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.zagshak.buySupply.domain.Request;

import java.util.List;

public interface RequestJpaRepo extends JpaRepository<Request, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Request r WHERE r.id=:id AND r.requester.id=:userId")
    int delete(@Param("id") int id, @Param("userId") int userId);

    @Override
    @Transactional
    Request save(Request request);

    @Transactional
    @Query("SELECT r FROM Request r JOIN r.offer f WHERE r.id=:id AND f.offerer.id=:offererId")
    Request getForOfferer(@Param("id") int id, @Param("offererId") int offererId);

    @Transactional
    @Query("SELECT r FROM Request r JOIN r.offer f WHERE f.id=:offerId AND f.offerer.id=:offererId")
    List<Request> getAllForOffer(@Param("offerId") int offerId, @Param("offererId") int offererId);


    @Transactional
    @Query("SELECT r FROM Request r JOIN r.offer f WHERE f.offerer.id=:offererId")
    List<Request> getAllForOfferer(@Param("offererId") int offererId);


    @Transactional
    @Query("SELECT r FROM Request r WHERE r.requester.id=:requesterId")
    List<Request> getAllForRequester(@Param("requesterId") int requesterId);


}
