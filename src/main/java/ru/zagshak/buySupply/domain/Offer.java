package ru.zagshak.buySupply.domain;

import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Offer extends AbstractBaseEntity {

    @Column(name = "buy_offer", nullable = false, unique = true)
    private boolean buyOffer;

    @Column(name = "description", nullable = false, unique = true)
    private String description;
    @Column(name = "cost", nullable = false, unique = true)
    private Integer cost;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "offerer_id", nullable = false)
    private User offerer;

    @Column(name = "date_time", nullable = false, unique = true)
    private LocalDateTime dateTime;


    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "offer_categories", joinColumns = @JoinColumn(name = "offer_id"))
    @Column(name = "category")
    @ElementCollection(fetch = FetchType.EAGER)
    @BatchSize(size = 200)
    private Set<Category> categories;


    public Offer() {
    }

    public Offer(Boolean buyOffer, String description, Integer cost, User offerer, LocalDateTime dateTime) {
        this.buyOffer = buyOffer;
        this.description = description;
        this.cost = cost;
        this.offerer = offerer;
        this.dateTime = dateTime;
    }

    public boolean isBuyOffer() {
        return buyOffer;
    }

    public void setBuyOffer(boolean buyOffer) {
        this.buyOffer = buyOffer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public User getOfferer() {
        return offerer;
    }

    public void setOfferer(User offerer) {
        this.offerer = offerer;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
