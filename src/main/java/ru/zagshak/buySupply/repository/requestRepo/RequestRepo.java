package ru.zagshak.buySupply.repository.requestRepo;

import ru.zagshak.buySupply.domain.Request;

import java.util.List;

public interface RequestRepo {

    Request save(Request request, int offerId, int userId);

    boolean delete(int id, int userId);

    Request get(int id);
    List<Request> getAll();
}
