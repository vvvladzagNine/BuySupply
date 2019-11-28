package ru.zagshak.buySupply.repository.estimateRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.zagshak.buySupply.domain.Estimate;

import java.util.List;


public interface EstimateJPARepo extends JpaRepository<Estimate,Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Estimate m WHERE m.id=:id AND m.estimator.id=:userId")
    int delete(@Param("id") int id, @Param("userId") int userId);

    @Transactional
    @Query("SELECT m FROM Estimate m WHERE m.estimator.id=:estimatorId")
    List<Estimate> getAllByEstimatorId(@Param("estimatorId") int estimatorId);

    @Transactional
    @Query("SELECT m FROM Estimate m WHERE m.estimated.id=:estimatedId")
    List<Estimate> getAllByEstimatedId(@Param("estimatedId") int estimatedId);

    @Transactional
    @Query("SELECT m FROM Estimate m WHERE m.estimated.id=:estimatedId AND m.estimator.id=:estimatorId")
    Estimate getAllByEstimatedIdAndEstimatorId(@Param("estimatedId") int estimatedId,@Param("estimatorId") int estimatorId);

}
