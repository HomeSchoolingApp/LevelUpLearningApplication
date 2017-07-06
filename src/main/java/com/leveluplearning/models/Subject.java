package com.leveluplearning.models;

import javax.persistence.*;

/**
 * Created by renecortez on 7/6/17.
 */

@Entity
@Table(name = "subject")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "subject")
    private String subject;

    public Subject(String subject) {
        this.subject = subject;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
