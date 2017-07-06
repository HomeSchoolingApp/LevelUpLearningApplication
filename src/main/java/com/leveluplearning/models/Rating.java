package com.leveluplearning.models;

import javax.persistence.*;
import javax.xml.soap.Text;

/**
 * Created by renecortez on 7/6/17.
 */

@Entity
@Table(name = "rating")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "origin")//relationship to id?
    private int origin;

    @Column(name = "destination")//relationship to id?
    private int destination;

    @Column(name = "rating")
    private int rating;

    @Column(name = "review")
    private Text review;

    public Rating(int origin, int destination, int rating, Text review) {
        this.origin = origin;
        this.destination = destination;
        this.rating = rating;
        this.review = review;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getOrigin() {
        return origin;
    }

    public void setOrigin(int origin) {
        this.origin = origin;
    }

    public int getDestination() {
        return destination;
    }

    public void setDestination(int destination) {
        this.destination = destination;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Text getReview() {
        return review;
    }

    public void setReview(Text review) {
        this.review = review;
    }
}
