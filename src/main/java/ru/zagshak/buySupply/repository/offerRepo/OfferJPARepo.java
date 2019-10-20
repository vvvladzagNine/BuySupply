package ru.zagshak.buySupply.repository.offerRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.zagshak.buySupply.domain.Offer;

public interface OfferJPARepo extends JpaRepository<Offer,Integer> {


    @Modifying
    @Transactional
    @Query("DELETE FROM Offer m WHERE m.id=:id AND m.offerer.id=:userId")
    int delete(@Param("id") int id, @Param("userId") int userId);
}
