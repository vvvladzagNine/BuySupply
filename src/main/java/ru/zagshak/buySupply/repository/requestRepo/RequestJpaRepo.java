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
    @Query("SELECT r FROM Request r JOIN r.offer f WHERE f.offerer.id=:userId")
    List<Request> getAllByUser(@Param("userId") int userId);

}
