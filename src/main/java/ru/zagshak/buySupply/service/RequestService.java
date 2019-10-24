package ru.zagshak.buySupply.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.zagshak.buySupply.domain.Request;
import ru.zagshak.buySupply.domain.to.requestTO.RequestTO;
import ru.zagshak.buySupply.repository.requestRepo.RequestRepo;
import ru.zagshak.buySupply.repository.requestRepo.RequestRepoImpl;
import ru.zagshak.buySupply.util.exception.NoAccessException;

import java.util.List;
import java.util.stream.Collectors;

import static ru.zagshak.buySupply.util.ValidationUtil.checkNotFoundWithId;

@Service
public class RequestService {

    @Autowired
    private RequestRepo requestRepo;

    @Autowired
    private OfferService offerService;

    public Request get(int id, int userId) {
        if (get(id).getRequester().getId() != userId) {
            throw new NoAccessException("You can't access others requests");
        }
        return checkNotFoundWithId(requestRepo.get(id, userId), id);
    }

    public Request getForOfferer(int id, int offererId) {
        return checkNotFoundWithId(requestRepo.getForOfferer(id, offererId), id);
    }

    public Request get(int id) {
        return  checkNotFoundWithId(requestRepo.get(id), id);
    }

    public Request create(Request request, int offerId, int userId) {
        Assert.notNull(request, "request must not be null");
        return requestRepo.save(request, offerId, userId);
    }

    public void delete(int id, int userId) {
        if (get(id).getRequester().getId() != userId) {
            throw new NoAccessException("You can't access others requests");
        }
        checkNotFoundWithId(requestRepo.delete(id, userId), id);
    }

    public void update(Request request, int offerId, int userId) {
        Assert.notNull(request, "request must not be null");
        if (request.getRequester().getId() != userId) {
            throw new NoAccessException("You can't access others requests");
        }
       checkNotFoundWithId(requestRepo.save(request, offerId, userId), offerId);
    }

    public List<Request> getAll(){
        return requestRepo.getAll();
    }

    public List<Request> getAllForOfferer(int offererId){
        return requestRepo.getAllForOfferer(offererId);
    }

    public List<Request> getAllForOffer(int offerId, int offererId) {
        return requestRepo.getAllForOffer(offerId, offererId);
    }

    public List<Request> getAllForRequester(int requesterId){
        return requestRepo.getAllForRequester(requesterId);
    }




}
