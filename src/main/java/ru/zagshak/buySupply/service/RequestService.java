package ru.zagshak.buySupply.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.zagshak.buySupply.domain.Request;
import ru.zagshak.buySupply.repository.requestRepo.RequestRepo;
import ru.zagshak.buySupply.repository.requestRepo.RequestRepoImpl;

import static ru.zagshak.buySupply.util.ValidationUtil.checkNotFoundWithId;

@Service
public class RequestService {

    @Autowired
    private RequestRepo requestRepo;

    public Request get(int id) {
        return requestRepo.get(id);
    }

    public Request create(Request request, int offerId, int userId) {
        Assert.notNull(request, "request must not be null");
        return requestRepo.save(request, offerId, userId);
    }

    public void delete(int id, int userId) {
        checkNotFoundWithId(requestRepo.delete(id, userId), id);
    }

    public void update(Request request, int offerId, int userId) {
        Assert.notNull(request, "request must not be null");
       checkNotFoundWithId(requestRepo.save(request, offerId, userId), offerId);
    }
}
