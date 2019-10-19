package ru.zagshak.buySupply.domain;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Request extends AbstractBaseEntity {

    @Column(name = "responced", nullable = false)
    @NotNull
    private boolean responced;

    @Column(name = "message", nullable = false)
    private String message;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "offer_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Offer offer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "requester_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User requester;

    public Request() {
    }

    public Request(Integer id, @NotNull boolean responced, String message, Offer offer, User requester) {
        super(id);
        this.responced = responced;
        this.message = message;
        this.offer = offer;
        this.requester = requester;
    }

    public boolean isResponced() {
        return responced;
    }

    public void setResponced(boolean responced) {
        this.responced = responced;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(Integer amount) {
        this.message = message;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public User getRequester() {
        return requester;
    }

    public void setRequester(User requester) {
        this.requester = requester;
    }

}
