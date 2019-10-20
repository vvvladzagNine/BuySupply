package ru.zagshak.buySupply.repository.estimateRepo;

import ru.zagshak.buySupply.domain.Estimate;

import java.util.List;

public interface EstimateRepo {
    Estimate save(Estimate estimate, int estimatedId, int estimatorId);

    // false if not found
    boolean delete(int id, int userId);

    // null if not found
    Estimate get(int id);

    List<Estimate> getAllByEstimator(int estimatorId);
    List<Estimate> getAllByEstimated(int estimatedId);

}
