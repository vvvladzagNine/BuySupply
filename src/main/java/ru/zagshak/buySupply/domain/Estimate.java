package ru.zagshak.buySupply.domain;

import ru.zagshak.buySupply.domain.to.estimateTO.EstimateTO;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Estimate extends AbstractBaseEntity {

    @Column(name = "comment", nullable = false)
    private String comment;

    @Column(name = "stars", nullable = false)
    private int stars;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "estimated_id", nullable = false)
    private User estimated;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "estimator_id", nullable = false)
    private User estimator;

    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;


    public Estimate() {
    }

    public Estimate(Integer id, User estimator, User estimated,  int stars, String comment, LocalDateTime dateTime) {
        super(id);
        this.estimator = estimator;
        this.estimated = estimated;
        this.stars = stars;
        this.comment = comment;
        this.dateTime = dateTime;
    }

    public Estimate(Integer id, int stars, String comment, LocalDateTime dateTime) {
        super(id);
        this.stars = stars;
        this.comment = comment;
        this.dateTime = dateTime;
    }

    public Estimate(Estimate e) {
        this(e.getId(), e.getEstimator(), e.getEstimated(), e.getStars(), e.getComment(), e.getDateTime());
    }

    public Estimate(EstimateTO esTo) {
        this.id = esTo.getId();
        this.stars = esTo.getStars();
        this.comment = esTo.getComment();
        this.dateTime = LocalDateTime.now();
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

    public User getEstimated() {
        return estimated;
    }

    public void setEstimated(User estimated) {
        this.estimated = estimated;
    }

    public User getEstimator() {
        return estimator;
    }

    public void setEstimator(User estimator) {
        this.estimator = estimator;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

}
