package ru.zagshak.buySupply.util;

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

    public static RequestTO makeTO(Request r) {
        return new RequestTO(r);
    }
}
