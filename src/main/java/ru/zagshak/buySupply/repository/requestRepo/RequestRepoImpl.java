package ru.zagshak.buySupply.repository.requestRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.zagshak.buySupply.domain.Request;
import ru.zagshak.buySupply.repository.offerRepo.OfferJPARepo;
import ru.zagshak.buySupply.repository.userRepo.UserJpaRepo;

import java.util.List;

@Repository
public class RequestRepoImpl implements RequestRepo {

    @Autowired
    private UserJpaRepo userRepo;

    @Autowired
    private RequestJpaRepo requestRepo;

    @Autowired
    private OfferJPARepo offerRepo;


    @Override
    public Request save(Request request, int offerId, int userId) {
        if (!request.isNew() && get(request.getId(), userId) == null ) {
            return null;
        }
        request.setOffer(offerRepo.getOne(offerId));
        request.setRequester(userRepo.getOne(userId));
        return requestRepo.save(request);
    }

    @Override
    public boolean delete(int id, int userId) {
        return requestRepo.delete(id, userId) != 0;
    }


    @Override
    public Request get(int id, int userId) {
        return requestRepo.findById(id).filter(request -> request.getRequester().getId() == userId).orElse(null);
    }

    @Override
    public Request get(int id) {
        return requestRepo.findById(id).orElse(null);
    }

    @Override
    public List<Request> getAll() {
        return requestRepo.findAll();
    }
}
