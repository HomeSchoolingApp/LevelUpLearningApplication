package com.leveluplearning.models;

import javax.persistence.*;
import javax.xml.soap.Text;
import java.util.Date;

/**
 * Created by renecortez on 7/6/17.
 */

@Entity
@Table(name = "messenger")
public class Messenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private User sender;

    @ManyToOne
    private User reciever;

    @Column(name = "message", columnDefinition = "TEXT")
    private String message;

    @Column(name = "date_sent")
    private Date dateSent;


    public Messenger(User sender, User reciever, String message, Date dateSent) {
        this.sender = sender;
        this.reciever = reciever;
        this.message = message;
        this.dateSent = dateSent;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReciever() {
        return reciever;
    }

    public void setReciever(User reciever) {
        this.reciever = reciever;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDateSent() {
        return dateSent;
    }

    public void setDateSent(Date dateSent) {
        this.dateSent = dateSent;
    }
}
