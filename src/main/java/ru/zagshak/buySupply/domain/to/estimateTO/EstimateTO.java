package ru.zagshak.buySupply.domain.to.estimateTO;

import ru.zagshak.buySupply.domain.Estimate;

import java.time.LocalDateTime;

public class EstimateTO {

    private Integer id;

    private String comment;

    private int stars;

    private Integer estimatedId;

    private Integer estimatorId;

    public EstimateTO(int id, String comment, int stars, int estimatedId, int estimatorId) {
        this.id = id;
        this.comment = comment;
        this.stars = stars;
        this.estimatedId = estimatedId;
        this.estimatorId = estimatorId;
    }

    public EstimateTO(Estimate es) {
        this.id = es.getId();
        this.comment = es.getComment();
        this.stars = es.getStars();
        this.estimatedId = es.getEstimated().getId();
        this.estimatorId = es.getEstimator().getId();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public int getEstimatedId() {
        return estimatedId;
    }

    public void setEstimatedId(int estimatedId) {
        this.estimatedId = estimatedId;
    }

    public int getEstimatorId() {
        return estimatorId;
    }

    public void setEstimatorId(int estimatorId) {
        this.estimatorId = estimatorId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
