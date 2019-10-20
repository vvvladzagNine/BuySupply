package ru.zagshak.buySupply.repository.estimateRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zagshak.buySupply.domain.Estimate;

public interface EstimateJPARepo extends JpaRepository<Estimate,Integer> {
}
