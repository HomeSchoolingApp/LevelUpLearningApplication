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
    private int id;//would this be idMessenger

    @Column(name = "sender")
    private int sender;

    @Column(name = "reciever")
    private int reciever;

    @Column(name = "message")
    private Text message;

    @Column(name = "date_sent")
    private Date dateSent;

    public Messenger(int sender, int reciever, Text message, Date dateSent) {
        this.sender = sender;
        this.reciever = reciever;
        this.message = message;
        this.dateSent = dateSent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSender() {
        return sender;
    }

    public void setSender(int sender) {
        this.sender = sender;
    }

    public int getReciever() {
        return reciever;
    }

    public void setReciever(int reciever) {
        this.reciever = reciever;
    }

    public Text getMessage() {
        return message;
    }

    public void setMessage(Text message) {
        this.message = message;
    }

    public Date getDateSent() {
        return dateSent;
    }

    public void setDateSent(Date dateSent) {
        this.dateSent = dateSent;
    }
}
