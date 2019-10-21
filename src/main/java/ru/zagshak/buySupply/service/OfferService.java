package ru.zagshak.buySupply.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.zagshak.buySupply.domain.Estimate;
import ru.zagshak.buySupply.domain.Offer;
import ru.zagshak.buySupply.repository.offerRepo.OfferRepoImpl;

import java.util.List;

import static ru.zagshak.buySupply.util.ValidationUtil.checkNotFoundWithId;

@Service
public class OfferService {

    @Autowired
    private OfferRepoImpl repo;



    public List<Offer> getAll(){
        return repo.getAll();
    }


    public void update(Offer offer, int userId) {
        Assert.notNull(offer, "offer must not be null");
        checkNotFoundWithId(repo.save(offer, userId), offer.getId());
    }

    public Offer create(Offer offer, int userId) {
        Assert.notNull(offer, "offer must not be null");
        return repo.save(offer, userId);
    }


    public boolean delete(int id, int userId) {
        return repo.delete(id,userId);
    }


    public Offer get(int id) {
        return repo.get(id);
    }
}
