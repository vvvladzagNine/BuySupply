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

    @Column(name = "amount", nullable = false)
    @NotNull
    private Integer amount;

    @Column(name = "offerId", nullable = false)
    @NotNull
    private Integer offerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "requester_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private User requester;

    public Request() {
    }

    public Request(Integer id, @NotNull boolean responced, @NotNull Integer amount, @NotNull Integer offerId, User requester) {
        super(id);
        this.responced = responced;
        this.amount = amount;
        this.offerId = offerId;
        this.requester = requester;
    }

    public boolean isResponced() {
        return responced;
    }

    public void setResponced(boolean responced) {
        this.responced = responced;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getOfferId() {
        return offerId;
    }

    public void setOfferId(Integer offerId) {
        this.offerId = offerId;
    }


    public User getRequester() {
        return requester;
    }

    public void setRequester(User requester) {
        this.requester = requester;
    }

}
