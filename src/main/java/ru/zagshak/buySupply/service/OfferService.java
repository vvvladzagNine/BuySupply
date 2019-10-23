package ru.zagshak.buySupply.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.zagshak.buySupply.domain.Offer;
import ru.zagshak.buySupply.domain.to.OfferTO.OfferTO;
import ru.zagshak.buySupply.repository.CategoryJPARepo;
import ru.zagshak.buySupply.repository.offerRepo.OfferRepoImpl;

import java.util.List;
import java.util.stream.Collectors;

import static ru.zagshak.buySupply.util.ValidationUtil.checkNotFoundWithId;

@Service
public class OfferService {

    @Autowired
    private OfferRepoImpl repo;

    @Autowired
    private CategoryJPARepo categoryRepo;



    public List<Offer> getAll(){
        return repo.getAll();
    }


    public void update(Offer offer, int userId) {
        Assert.notNull(offer, "offer must not be null");
        checkNotFoundWithId(repo.save(offer, userId,offer.getCategory().getId()), offer.getId());
    }

    public Offer create(Offer offer, int userId, int categoryId) {
        Assert.notNull(offer, "offer must not be null");
        return repo.save(offer, userId,categoryId);
    }

    public List<OfferTO> getAllTO(){
        return repo.getAll()
                .stream()
                .map(o -> new OfferTO(
                    o.isBuyOffer(),
                    o.getDescription(),
                    o.getAmount(),
                    o.getCost(),
                    o.getOfferer().getUsername(),
                    o.getOfferer().getId(),
                    o.getDateTime(),
                    o.getCategory()

                ))
                .collect(Collectors.toList());
    }


    public boolean delete(int id, int userId) {
        return repo.delete(id,userId);
    }


    public Offer get(int id) {
        return repo.get(id);
    }
}
