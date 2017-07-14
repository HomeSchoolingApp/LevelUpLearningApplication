package com.leveluplearning.models;

import javax.persistence.*;

/**
 * Created by renecortez on 7/6/17.
 */

@Entity
@Table(name = "user_references")
public class Reference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;//would this be the ref id?

    @ManyToOne
    private User user;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "ref_comment", columnDefinition = "TEXT")
    private String refComment;

    public Reference(User user, String fullName, String phoneNumber, String refComment) {
        this.user = user;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.refComment = refComment;
    }

    public Reference() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRefComment() {
        return refComment;
    }

    public void setRefComment(String refComment) {
        this.refComment = refComment;
    }
}
