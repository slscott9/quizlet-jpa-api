package com.stuart.quizletjpaapigradle.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user_set_table")
public class UserSet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long setId;

    private String setName;

    private String folderId;

    private String userEmail;

    private Integer termCount;

    private Long timeStamp;



    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "email")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User user;

    @Nullable
    @OneToMany(mappedBy = "userSet", cascade = CascadeType.ALL)
    private Set<Term> terms = new HashSet<>();

    public Set<Term> getTerms() {
        return this.terms;
    }

    public void setTerms(@Nullable Set<Term> terms) {
        this.terms = terms;

        for(Term term : terms) {
            term.setSetId(this.setId);
        }
    }



    public Long getId() {
        return setId;
    }

    public void setId(Long id) {
        this.setId = id;
    }

    public String getSetName() {
        return setName;
    }

    public void setSetName(String setName) {
        this.setName = setName;
    }

    public String getFolderId() {
        return folderId;
    }

    public void setFolderId(String folderId) {
        this.folderId = folderId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Integer getTermCount() {
        return termCount;
    }

    public void setTermCount(Integer termCount) {
        this.termCount = termCount;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
