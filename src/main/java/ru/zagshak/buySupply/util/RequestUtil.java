package ru.zagshak.buySupply.util;

import org.springframework.web.bind.annotation.RequestParam;
import ru.zagshak.buySupply.domain.Request;
import ru.zagshak.buySupply.domain.to.requestTO.RequestTO;

import java.util.List;
import java.util.stream.Collectors;

public class RequestUtil {
    public static List<RequestTO> makeTO(List<Request> requests) {
        return requests.stream()
                .map(RequestTO::new)
                .collect(Collectors.toList());
    }

    public static RequestTO makeTO( Request r) {
        return new RequestTO(r);
    }

    public static List<RequestTO> filterRequestTO(
            List<Request> requests,
            Boolean responced,
            String message,
            Integer offerId
    ) {
        return makeTO(requests)
                .stream()
                .filter(o -> responced == null || responced.equals(o.isResponced()))
                .filter(o -> message == null || (o.getMessage().contains(message)))
                .filter(o -> offerId == null || (offerId.equals(o.getOfferId())))
                .collect(Collectors.toList());
    }


}
