package com.stuart.quizletjpaapigradle.models;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "term_table")
public class Term {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long parentSetId;

    private String question;

    private String answer;

    private String userEmail;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "email")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User user;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "setId")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private UserSet userSet;

    public UserSet getUserSet() {
        return userSet;
    }

    public void setUserSet(UserSet userSet) {
        this.userSet = userSet;
    }


    public Long getSetId() {
        return parentSetId;
    }

    public void setSetId(Long setId) {
        this.parentSetId = setId;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
