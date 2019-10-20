package ru.zagshak.buySupply.repository.offerRepo;

import ru.zagshak.buySupply.domain.Estimate;
import ru.zagshak.buySupply.domain.Offer;

import java.util.List;

public interface OfferRepo {

    Offer save(Offer estimate, int offererId);

    // false if not found
    boolean delete(int id, int userId);

    // null if not found
    Offer get(int id);


}
