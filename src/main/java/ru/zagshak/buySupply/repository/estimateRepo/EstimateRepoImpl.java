package ru.zagshak.buySupply.repository.estimateRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.zagshak.buySupply.domain.Estimate;
import ru.zagshak.buySupply.repository.userRepo.UserJpaRepo;

import java.util.List;

@Repository
public class EstimateRepoImpl implements EstimateRepo {


    @Autowired
    private EstimateJPARepo repo;

    @Autowired
    private UserJpaRepo userRepo;

    @Override
    public Estimate save(Estimate estimate, int estimatedId,int estimatorId) {
        if (!estimate.isNew() && get(estimate.getId()) == null) {
            return null;
        }
        estimate.setEstimator(userRepo.getOne(estimatorId));
        estimate.setEstimated(userRepo.getOne(estimatedId));
        return repo.save(estimate);
    }

    @Override
    public boolean delete(int id,int estimatorId) {
        return repo.delete(id,estimatorId)!=0;
    }

    @Override
    public Estimate get(int id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<Estimate> getAllByEstimator(int estimatorId) {
        return repo.getAllByEstimatorId(estimatorId);
    }

    @Override
    public List<Estimate> getAllByEstimated(int estimatedId) {
        return repo.getAllByEstimatedId(estimatedId);
    }
}
