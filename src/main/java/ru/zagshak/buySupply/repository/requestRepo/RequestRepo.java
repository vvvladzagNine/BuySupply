package ru.zagshak.buySupply.repository.requestRepo;

import org.springframework.data.repository.query.Param;
import ru.zagshak.buySupply.domain.Request;

import java.util.List;

public interface RequestRepo {

    Request save(Request request, int offerId, int userId);

    boolean delete(int id, int userId);

    Request get(int id, int userId);

    Request getForOfferer(int id, int offererId);

    Request get(int id);

    List<Request> getAll();
    List<Request> getAllForOfferer(int offererId);
    List<Request> getAllForRequester(int requesterId);

    List<Request> getAllForOffer(int offerId, int offererId);

}
