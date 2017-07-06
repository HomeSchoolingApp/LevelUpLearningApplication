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
    private String user;

    @Column(name = "phone_number")
    private int phoneNumber;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "ref_comment")
    private Text refComment;

    public References(String user, int phoneNumber, String fullName, Text refComment) {
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Text getRefComment() {
        return refComment;
    }

    public void setRefComment(Text refComment) {
        this.refComment = refComment;
    }
}
