package ru.zagshak.buySupply.domain;

import javax.persistence.*;

@Entity
public class Estimate extends AbstractBaseEntity {

    @Column(name = "comment", nullable = false)
    private String comment;

    @Column(name = "stars", nullable = false)
    private int stars;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "estimated_id", nullable = false)
    private User estimated;


    public Estimate() {
    }


}
