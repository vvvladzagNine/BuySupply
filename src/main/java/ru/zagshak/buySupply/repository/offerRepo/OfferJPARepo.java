package ru.zagshak.buySupply.repository.offerRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.zagshak.buySupply.domain.Offer;
import ru.zagshak.buySupply.domain.Request;

import java.util.List;

public interface OfferJPARepo extends JpaRepository<Offer,Integer> {


    @Modifying
    @Transactional
    @Query("DELETE FROM Offer m WHERE m.id=:id AND m.offerer.id=:userId")
    int delete(@Param("id") int id, @Param("userId") int userId);

    @Query("SELECT m FROM Offer m WHERE m.offerer.id=:id")
    List<Offer> getAllByOffereId(@Param("id") int id);



    @Transactional
    @Query("SELECT f FROM Request r JOIN r.offer f WHERE r.requester.id=:requesterId AND r.responced=true AND f.buyOffer=false")
    List<Offer> getOfferByRequestUserBuy(@Param("requesterId") int requesterId);

    @Transactional
    @Query("SELECT f FROM Request r JOIN r.offer f WHERE r.requester.id=:requesterId AND r.responced=true AND f.buyOffer=true")
    List<Offer> getOfferByRequestUserSupply(@Param("requesterId") int requesterId);


    @Transactional
    @Query("SELECT f FROM Request r LEFT JOIN r.offer f WHERE f.offerer.id=:offerrerId AND r.responced=true AND f.buyOffer=false")
    List<Offer> getOfferMyRequestedOffersSupply(@Param("offerrerId") int offerrerId);


    @Transactional
    @Query("SELECT f FROM Request r LEFT JOIN r.offer f WHERE f.offerer.id=:offerrerId AND r.responced=true AND f.buyOffer=true")
    List<Offer> getOfferMyRequestedOffersBuy(@Param("offerrerId") int offerrerId);
}
