package ru.zagshak.buySupply.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "category")
public class Category extends AbstractNamedEntity {

    @Column(name = "type", nullable = false)
    private String typeOfBatch;

    @Column(name = "unit", nullable = false)
    private String unit;




    public Category() {
    }


    public Category(String name, String typeOfBatch, String unit) {
        this.name=name;
        this.typeOfBatch = typeOfBatch;
        this.unit = unit;
    }

    public String getTypeOfBantch() {
        return typeOfBatch;
    }

    public void setTypeOfBantch(String typeOfBantch) {
        this.typeOfBatch = typeOfBantch;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
