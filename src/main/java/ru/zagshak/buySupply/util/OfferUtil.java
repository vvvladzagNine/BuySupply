package ru.zagshak.buySupply.util;

import org.springframework.web.bind.annotation.RequestParam;
import ru.zagshak.buySupply.domain.Offer;
import ru.zagshak.buySupply.domain.to.OfferTO.OfferTO;

import java.util.List;
import java.util.stream.Collectors;

public class OfferUtil {

    public static List<OfferTO> filterOfferTO(
            List<OfferTO> original,
            String categoryName,
            Boolean isBuyOffer,
            Integer pricePerUnitFrom,
            Integer pricePerUnitTo,
            String fragment,
            String offererName
    ){
        return original
                .stream()
                .filter( o -> categoryName==null || categoryName.equals(o.getCategory().getName()))
                .filter( o -> isBuyOffer==null || !Boolean.logicalXor(isBuyOffer,o.isBuyOffer()))
                .filter( o -> pricePerUnitFrom==null || (o.getCost()/o.getAmount())>=pricePerUnitFrom)
                .filter( o -> pricePerUnitTo==null || (o.getCost()/o.getAmount())<=pricePerUnitTo)
                .filter( o -> fragment==null || fragment.length()<=2 ||  (o.getDescription().contains(fragment)))
                .filter( o -> offererName==null ||  (o.getOffererName().contains(offererName)))
                .collect(Collectors.toList());
    }
}
