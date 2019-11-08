package ru.zagshak.buySupply.util;

import ru.zagshak.buySupply.domain.Estimate;
import ru.zagshak.buySupply.domain.to.estimateTO.EstimateTO;

import java.util.List;
import java.util.stream.Collectors;

public class EstimateUtil {

    public static List<EstimateTO> makeTO(List<Estimate> estimates) {
        return estimates.stream()
                .map(EstimateTO::new)
                .collect(Collectors.toList());
    }

    public static EstimateTO makeTO(Estimate estimate) {
        return new EstimateTO(estimate);
    }
}
