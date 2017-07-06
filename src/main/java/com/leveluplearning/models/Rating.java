package com.leveluplearning.models;

import javax.persistence.*;

/**
 * Created by renecortez on 7/6/17.
 */

@Entity
@Table(name = "rating")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "rated_by")
    private User ratedBy;

    @Column(name = "rated_to")
    private User ratedTo;

    @Column(name = "rating")
    private int rating;

    @Column(name = "review", columnDefinition = "TEXT")
    private String review;

    public Rating(User ratedBy, User ratedTo, int rating, String review) {
        this.ratedBy = ratedBy;
        this.ratedTo = ratedTo;
        this.rating = rating;
        this.review = review;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getRatedBy() {
        return ratedBy;
    }

    public void setRatedBy(User origin) {
        this.ratedBy = ratedBy;
    }

    public User getRatedTo() {
        return ratedTo;
    }

    public void setRatedTo(User destination) {
        this.ratedTo = ratedTo;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
