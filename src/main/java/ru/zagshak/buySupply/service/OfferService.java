package ru.zagshak.buySupply.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.zagshak.buySupply.domain.Estimate;
import ru.zagshak.buySupply.domain.Offer;
import ru.zagshak.buySupply.repository.offerRepo.OfferRepoImpl;
import ru.zagshak.buySupply.util.exception.NoAccessException;
import ru.zagshak.buySupply.util.exception.NotFoundException;

import java.util.List;

import static ru.zagshak.buySupply.util.ValidationUtil.checkNotFoundWithAccess;
import static ru.zagshak.buySupply.util.ValidationUtil.checkNotFoundWithId;

@Service
public class OfferService {

    @Autowired
    private OfferRepoImpl repo;



    public List<Offer> getAll(){
        return repo.getAll();
    }


    public void update(Offer offer, int userId) throws NotFoundException, NoAccessException {
        Assert.notNull(offer, "offer must not be null");
        if (offer.getOfferer().getId() != userId) {
            throw new NoAccessException(userId + "doesn't have rights for this object/operation");
        }

//        checkNotFoundWithId(repo.save(offer, userId), offer.getId());
        checkNotFoundWithAccess(repo.save(offer, userId),offer.getId(), offer.getOfferer().getId(), userId);
    }

    public Offer create(Offer offer, int userId) {
        Assert.notNull(offer, "offer must not be null");
        return repo.save(offer, userId);
    }


    public void delete(int id, int userId) throws NotFoundException, NoAccessException {
        checkNotFoundWithId(repo.get(id),id);
        int offererId = repo.get(id).getOfferer().getId();
        checkNotFoundWithAccess(repo.delete(id,userId), id, offererId,userId);
    }


    public Offer get(int id) throws NotFoundException {
        return checkNotFoundWithId(repo.get(id), id);
    }
}
