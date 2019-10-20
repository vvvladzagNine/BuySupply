package ru.zagshak.buySupply.repository.requestRepo;

import ru.zagshak.buySupply.domain.Request;

public interface RequestRepo {

    Request save(Request request, int offerId, int userId);

    boolean delete(int id, int userId);

    Request get(int id);
}
