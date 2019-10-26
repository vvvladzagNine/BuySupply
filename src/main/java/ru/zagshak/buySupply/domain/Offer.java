package ru.zagshak.buySupply.domain;

import ru.zagshak.buySupply.domain.to.OfferTO.OfferTO;
import ru.zagshak.buySupply.domain.to.OfferTO.OfferTOSave;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Offer extends AbstractBaseEntity {

    @Column(name = "buy_offer", nullable = false)
    private boolean buyOffer;

    @Column(name = "description", nullable = false)
    private String description;


    @Column(name = "amount", nullable = false, unique = true)
    private int amount;

    @Column(name = "cost", nullable = false)
    private Integer cost;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "offerer_id", nullable = false)
    private User offerer;

    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;


    public Offer() {
    }


    public Offer(OfferTOSave to){
        this.buyOffer = to.isBuyOffer();
        this.description = to.getDescription();
        this.cost = to.getCost();
        this.dateTime = to.getDateTime();
        this.amount = to.getAmount();
    }

    public Offer(OfferTOSave to, Integer id){
        this.id=id;
        this.buyOffer = to.isBuyOffer();
        this.description = to.getDescription();
        this.cost = to.getCost();
        this.dateTime = to.getDateTime();
        this.amount = to.getAmount();
    }

    public Offer(Boolean buyOffer, String description, Integer cost, User offerer, LocalDateTime dateTime) {
        this.buyOffer = buyOffer;
        this.description = description;
        this.cost = cost;
        this.buyOffer = buyOffer;
        this.offerer = offerer;
        this.dateTime = dateTime;

    }

    public Offer(Integer id, String description,int amount, Integer cost, boolean buyOffer, Category category, User offerer,  LocalDateTime dateTime) {
        super(id);
        this.description = description;
        this.amount = amount;
        this.cost = cost;
        this.buyOffer = buyOffer;
        this.category = category;
        this.offerer = offerer;
        this.dateTime = dateTime;

    }

    public Offer(Integer id, String description,int amount, Integer cost, boolean buyOffer, Category category, LocalDateTime dateTime) {
        super(id);
        this.description = description;
        this.amount = amount;
        this.cost = cost;
        this.buyOffer = buyOffer;
        this.category = category;
        this.dateTime = dateTime;

    }

    public Offer(Offer offer) {
        this(offer.getId(), offer.getDescription(), offer.getAmount(), offer.getCost(), offer.isBuyOffer(), offer.getCategory(), offer.getOfferer(), offer.getDateTime());
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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
