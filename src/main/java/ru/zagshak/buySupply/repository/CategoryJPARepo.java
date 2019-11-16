package ru.zagshak.buySupply.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zagshak.buySupply.domain.Category;

public interface CategoryJPARepo extends JpaRepository<Category,Integer> {
    Category findByName(String name);
}
