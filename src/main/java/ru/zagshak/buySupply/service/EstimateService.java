package ru.zagshak.buySupply.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.zagshak.buySupply.domain.Estimate;
import ru.zagshak.buySupply.repository.estimateRepo.EstimateRepoImpl;
import ru.zagshak.buySupply.util.exception.NoAccessException;

import static ru.zagshak.buySupply.util.ValidationUtil.checkNotFoundWithId;


import java.util.List;

@Service
public class EstimateService {

    @Autowired
    private EstimateRepoImpl repo;


    public void update(Estimate estimate, int estimatedId, int estimatorId) {
        Assert.notNull(estimate, "estimate must not be null");
        if (get(estimate.getId()).getEstimator().getId() != estimatorId) {
            throw new NoAccessException("You can't change others estimates");
        }
        checkNotFoundWithId(repo.save(estimate, estimatedId,estimatorId), estimate.getId());
    }

    public Estimate create(Estimate estimate, int estimatedId, int estimatorId) {
        Assert.notNull(estimate, "estimate must not be null");
        return repo.save(estimate, estimatedId,estimatorId);
    }




    public boolean delete(int id,int estimatorId) {
        if (get(id).getEstimator().getId() != estimatorId) {
            throw new NoAccessException("You can't change others estimates");
        }
        return repo.delete(id,estimatorId);
    }


    public Estimate get(int id) {
        return checkNotFoundWithId(repo.get(id), id);
    }


    public List<Estimate> getAll() { return repo.getAll(); }

    public List<Estimate> getAllByEstimator(int estimatorId) {
        return repo.getAllByEstimator(estimatorId);
    }

    public List<Estimate> getAllForEstimated(int estimatedId) {
        return repo.getAllByEstimated(estimatedId);
    }
}

