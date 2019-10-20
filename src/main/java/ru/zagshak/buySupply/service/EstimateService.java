package ru.zagshak.buySupply.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.zagshak.buySupply.domain.Estimate;
import ru.zagshak.buySupply.repository.estimateRepo.EstimateRepoImpl;
import static ru.zagshak.buySupply.util.ValidationUtil.checkNotFoundWithId;


import java.util.List;

@Service
public class EstimateService {

    @Autowired
    private EstimateRepoImpl repo;


    public void update(Estimate estimate, int estimatedId, int estimatorId) {
        Assert.notNull(estimate, "estimate must not be null");
        checkNotFoundWithId(repo.save(estimate, estimatedId,estimatorId), estimate.getId());
    }

    public Estimate create(Estimate estimate, int estimatedId, int estimatorId) {
        Assert.notNull(estimate, "estimate must not be null");
        return repo.save(estimate, estimatedId,estimatorId);
    }




    public boolean delete(int id,int estimatorId) {
        return repo.delete(id,estimatorId);
    }


    public Estimate get(int id) {
        return repo.get(id);
    }


    public List<Estimate> getAllByEstimator(int estimatorId) {
        return repo.getAllByEstimator(estimatorId);
    }


    public List<Estimate> getAllByEstimated(int estimatedId) {
        return repo.getAllByEstimated(estimatedId);
    }
}

