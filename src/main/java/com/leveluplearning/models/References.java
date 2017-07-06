package com.leveluplearning.models;

import javax.persistence.*;
import javax.xml.soap.Text;

/**
 * Created by renecortez on 7/6/17.
 */

@Entity
@Table(name = "references")
public class References {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;//would this be the ref id?

    @Column(name = "user")
    private User user;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "ref_comment", columnDefinition = "TEXT")
    private String refComment;

    public References(User user, String phoneNumber, String fullName, String refComment) {
        this.user = user;
        this.phoneNumber = phoneNumber;
        this.fullName = fullName;
        this.refComment = refComment;
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
