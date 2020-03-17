package ru.zagshak.buySupply.domain.to.OfferTO;

import ru.zagshak.buySupply.domain.Category;
import ru.zagshak.buySupply.domain.Offer;
import java.time.LocalDateTime;

public class OfferTO {


    private int id;
    private boolean buyOffer;

    private String description;

    private int amount;

    private int cost;

    private String offererName;

    private int offererId;

    private LocalDateTime dateTime;

    private Category category;



    public OfferTO(int id,boolean buyOffer, String description, int amount, int cost, String offererName, int offererId, LocalDateTime dateTime, Category category) {
        this.id=id;
        this.buyOffer = buyOffer;
        this.description = description;
        this.amount = amount;
        this.cost = cost;
        this.offererName = offererName;
        this.offererId = offererId;
        this.dateTime = dateTime;
        this.category = category;
    }


    public OfferTO(Offer o) {
        this.id=o.getId();
        this.buyOffer = o.isBuyOffer();
        this.description = o.getDescription();
        this.amount = o.getAmount();
        this.cost = o.getCost();
        this.offererName = o.getOfferer().getName();
        this.offererId = o.getOfferer().getId();
        this.dateTime = o.getDateTime();
        this.category = o.getCategory();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getOffererName() {
        return offererName;
    }

    public void setOffererName(String offererName) {
        this.offererName = offererName;
    }

    public int getOffererId() {
        return offererId;
    }

    public void setOffererId(int offererId) {
        this.offererId = offererId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
