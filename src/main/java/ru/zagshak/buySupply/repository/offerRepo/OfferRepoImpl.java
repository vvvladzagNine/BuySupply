package ru.zagshak.buySupply.repository.offerRepo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.zagshak.buySupply.domain.Estimate;
import ru.zagshak.buySupply.domain.Offer;
import ru.zagshak.buySupply.repository.CategoryJPARepo;
import ru.zagshak.buySupply.repository.userRepo.UserJpaRepo;

import java.util.List;

@Repository
public class OfferRepoImpl implements OfferRepo {

    @Autowired
    private OfferJPARepo repo;

    @Autowired
    private UserJpaRepo userRepo;

    @Autowired
    private CategoryJPARepo categoryRepo;

    @Override
    public List<Offer> getAll() {
        return repo.findAll();
    }

    @Override
    public Offer save(Offer offer, int offererId,int categoryId) {
        if (!offer.isNew() && get(offer.getId()) == null) {
            return null;
        }
        offer.setOfferer(userRepo.getOne(offererId));
        offer.setCategory(categoryRepo.getOne(categoryId));

        return repo.save(offer);
    }

    @Override
    public boolean delete(int id, int userId) {
        return repo.delete(id,userId)!=0;
    }

    @Override
    public Offer get(int id) {
        return repo.findById(id).orElse(null);
    }


}
