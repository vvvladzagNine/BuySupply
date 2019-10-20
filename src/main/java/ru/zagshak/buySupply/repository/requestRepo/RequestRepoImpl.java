package ru.zagshak.buySupply.repository.requestRepo;

import org.springframework.beans.factory.annotation.Autowired;
import ru.zagshak.buySupply.domain.Request;
import ru.zagshak.buySupply.repository.userRepo.UserJpaRepo;

public class RequestRepoImpl implements RequestRepo {

    @Autowired
    private UserJpaRepo userRepo;

    @Autowired
    private RequestJpaRepo requestRepo;


    @Override
    public Request save(Request request, int offerId, int userId) {
        if (!request.isNew() && get(request.getId()) == null ) {
            return null;
        }
        request.setRequester(userRepo.getOne(userId));
        request.setOffer(null);
        return requestRepo.save(request);
    }

    @Override
    public boolean delete(int id, int userId) {
        return requestRepo.delete(id, userId) != 0;
    }

    @Override
    public Request get(int id) {
        return requestRepo.findById(id).orElse(null);
    }
}
