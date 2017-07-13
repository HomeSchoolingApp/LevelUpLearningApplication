package com.leveluplearning.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by renecortez on 7/6/17.
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true, length = 45)
    private String username;

    @Column(nullable = false, unique = true, length = 45)
    private String email;

    @Column(nullable = false, length = 300)
    @JsonIgnore
    private String password;

    @Column
    private Date dob;

    @Column
    private String gender;

    @Column(nullable = false)
    private boolean student;

    @Column(nullable = false, length = 45)
    private String firstName;

    @Column(nullable = false, length = 45)
    private String lastName;

    @Column
    private String imgUrl;

    @Column
    private String language;

    @Column(nullable = true, length = 10)
    private Long phoneNumber;

    @Column(columnDefinition = "TEXT")
    private String profSum;

    @Column
    private int eduLevel;

    @Column
    private String certification;

    @Column(length = 45)
    private String city;

    @Column(length = 45)
    private String state;

    @Column(columnDefinition = "TEXT")
    private String aboutMe;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_subject",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "subject_id")}
    )

    private List<Subject> subjects;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "child_parent",
            joinColumns = {@JoinColumn(name = "parent_id")},
            inverseJoinColumns = {@JoinColumn(name = "child_id")}
    )
    private List<User> childParent;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "teacher_parent",
            joinColumns = {@JoinColumn(name = "parent_id")},
            inverseJoinColumns = {@JoinColumn(name = "teacher_id")}
    )
    private List<User> teacherParent;

    @OneToMany(mappedBy = "user")
    @JsonBackReference
    private List<Reference> references;

    public User(String username, String email, String password, Date dob, String gender, boolean student, String firstName, String lastName, String imgUrl, String language, String profSum, int eduLevel, String certification, String city, String state, String aboutMe) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.dob = dob;
        this.gender = gender;
        this.student = student;
        this.firstName = firstName;
        this.lastName = lastName;
        this.imgUrl = imgUrl;
        this.language = language;
        this.profSum = profSum;
        this.eduLevel = eduLevel;
        this.certification = certification;
        this.city = city;
        this.state = state;
        this.aboutMe = aboutMe;
    }

    public User() {

    }

    public User(User user) {
        this.username = user.username;
        this.email = user.email;
        this.password = user.password;
        this.dob = user.dob;
        this.gender = user.gender;
        this.student = user.student;
        this.firstName = user.firstName;
        this.lastName = user.lastName;
        this.imgUrl = user.imgUrl;
        this.language = user.language;
        this.profSum = user.profSum;
        this.eduLevel = user.eduLevel;
        this.certification = user.certification;
        this.city = user.city;
        this.state = user.state;
        this.aboutMe = user.aboutMe;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean isStudent() {
        return student;
    }

    public void setStudent(boolean student) {
        this.student = student;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getProfSum() {
        return profSum;
    }

    public void setProfSum(String profSum) {
        this.profSum = profSum;
    }

    public int getEduLevel() {
        return eduLevel;
    }

    public void setEduLevel(int eduLevel) {
        this.eduLevel = eduLevel;
    }

    public String getCertification() {
        return certification;
    }

    public void setCertification(String certification) {
        this.certification = certification;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public List<Reference> getReferences() {
        return references;
    }

    public void setReferences(List<Reference> references) {
        this.references = references;
    }

}
