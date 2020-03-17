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
            String offererName,
            String city
    ){
        return
        original.stream()
                .filter( f -> categoryName==null || categoryName.isEmpty() || categoryName.equals(f.getCategory().getName()))
                .filter( i -> isBuyOffer==null || !Boolean.logicalXor(isBuyOffer,i.isBuyOffer()))
                .filter( l -> pricePerUnitFrom==null || (l.getCost()/l.getAmount())>=pricePerUnitFrom)
                .filter( t -> pricePerUnitTo==null || (t.getCost()/t.getAmount())<=pricePerUnitTo)
                .filter( e -> fragment==null || fragment.isEmpty() || fragment.length()<=2 ||  (e.getDescription().toLowerCase().contains(fragment.toLowerCase())))
                .filter( r -> offererName==null || offererName.isEmpty() ||  (r.getOffererName().toLowerCase().contains(offererName.toLowerCase())))

                .collect(Collectors.toList());
    }


    public static List<Offer> filterOffer(
            List<Offer> original,
            String categoryName,
            Boolean isBuyOffer,
            Integer pricePerUnitFrom,
            Integer pricePerUnitTo,
            String fragment,
            String offererName,
            String city
    ){
        return original
                .stream()
                .filter( f -> categoryName==null || categoryName.isEmpty() || categoryName.equals(f.getCategory().getName()))
                .filter( i -> isBuyOffer==null || !Boolean.logicalXor(isBuyOffer,i.isBuyOffer()))
                .filter( l -> pricePerUnitFrom==null ||  (l.getCost()/l.getAmount())>=pricePerUnitFrom)
                .filter( t -> pricePerUnitTo==null || (t.getCost()/t.getAmount())<=pricePerUnitTo)
                .filter( e -> fragment==null ||  fragment.isEmpty() || fragment.length()<=2 ||  (e.getDescription().contains(fragment)))
                .filter( r -> offererName==null ||  offererName.isEmpty() || (r.getOfferer().getName().contains(offererName)))
                .filter( r -> city==null ||  city.isEmpty() || (r.getOfferer().getCity().equals(city)))
                .collect(Collectors.toList());
    }
}
