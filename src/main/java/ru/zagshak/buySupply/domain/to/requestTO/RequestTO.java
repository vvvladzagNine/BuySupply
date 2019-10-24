package ru.zagshak.buySupply.domain.to.requestTO;

import ru.zagshak.buySupply.domain.Request;

public class RequestTO {

    private int id;

    private boolean responced;

    private String message;

    private int offerId;

    private String requesterName;

    private int requesterId;

    public RequestTO(int id, boolean responced, String message, int offerId, String requesterName, int requesterId) {
        this.id = id;
        this.responced = responced;
        this.message = message;
        this.offerId = offerId;
        this.requesterName = requesterName;
        this.requesterId = requesterId;
    }

    public RequestTO(Request r) {
        this.id = r.getId();
        this.responced = r.isResponced();
        this.message = r.getMessage();
        this.offerId = r.getOffer().getId();
        this.requesterName = r.getRequester().getName();
        this.requesterId = r.getRequester().getId();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setMessage(String message) {
        this.message = message;
    }

    public int getOfferId() {
        return offerId;
    }

    public void setOfferId(int offerId) {
        this.offerId = offerId;
    }

    public String getRequesterName() {
        return requesterName;
    }

    public void setRequesterName(String requesterName) {
        this.requesterName = requesterName;
    }

    public int getRequesterId() {
        return requesterId;
    }

    public void setRequesterId(int requesterId) {
        this.requesterId = requesterId;
    }

    @Override
    public String toString() {
        return "RequestTO{" +
                "id=" + id +
                ", responced=" + responced +
                ", message='" + message + '\'' +
                ", offerId=" + offerId +
                ", requesterName='" + requesterName + '\'' +
                ", requesterId=" + requesterId +
                '}';
    }
}
