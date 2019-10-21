package ru.zagshak.buySupply.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "category")
public class Category extends AbstractNamedEntity {

    @Column(name = "type", nullable = false)
    private String typeOfBantch;

    @Column(name = "unit", nullable = false)
    private String unit;




    public Category() {
    }


    public Category(String name, String typeOfBantch, String unit) {
        this.name=name;
        this.typeOfBantch = typeOfBantch;
        this.unit = unit;
    }
}
