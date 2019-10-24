package ru.zagshak.buySupply.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.zagshak.buySupply.domain.Offer;
import ru.zagshak.buySupply.domain.to.OfferTO.OfferTO;
import ru.zagshak.buySupply.repository.CategoryJPARepo;
import ru.zagshak.buySupply.repository.offerRepo.OfferRepoImpl;
import ru.zagshak.buySupply.util.OfferUtil;
import ru.zagshak.buySupply.util.exception.NoAccessException;
import ru.zagshak.buySupply.util.exception.NotFoundException;

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


    public void update(Offer offer, int userId, int categoryId) throws NotFoundException, NoAccessException {
        Assert.notNull(offer, "offer must not be null");

        if (get(offer.getId()).getOfferer().getId() != userId) {
            throw new NoAccessException("You can't change others offers");
        }
        checkNotFoundWithId(repo.save(offer, userId,categoryId), offer.getId());

    }

    public Offer create(Offer offer, int userId, int categoryId) {
        Assert.notNull(offer, "offer must not be null");
        return repo.save(offer, userId,categoryId);
    }

    public List<OfferTO> getAllTO(){
        return getAll()
                .stream()
                .map(OfferTO::new)
                .collect(Collectors.toList());
    }


    public List<OfferTO> getFilteredTO(
            String categoryName,
            Boolean isBuyOffer,
            Integer pricePerUnitFrom,
            Integer pricePerUnitTo,
            String fragment,
            String offererName){

        return OfferUtil.filterOfferTO(
                getAllTO(),
                categoryName,
                isBuyOffer,
                pricePerUnitFrom,
                pricePerUnitTo,
                fragment,
                offererName

        );
    }


    public void delete(int id, int userId) throws NotFoundException, NoAccessException {
        if (get(id).getOfferer().getId() != userId) {
            throw new NoAccessException("You can't delete others offers");
        }
        checkNotFoundWithId(repo.delete(id, userId), id);
    }


    public Offer get(int id) throws NotFoundException {
        return checkNotFoundWithId(repo.get(id), id);
    }

    public OfferTO getTO(int id) throws NotFoundException {
        return new OfferTO(get(id));
    }


}
