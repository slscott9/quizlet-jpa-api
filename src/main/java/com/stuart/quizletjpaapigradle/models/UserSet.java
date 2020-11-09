package com.stuart.quizletjpaapigradle.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user_set_table")
public class UserSet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long setId;

    private String setName;


    private String userEmail;

    private Integer termCount;

    private Boolean isSynced;


    @Nullable
    private String folderId;

    @Nullable
    public String getFolderId() {
        return folderId;
    }

    public void setFolderId(@Nullable String folderId) {
        this.folderId = folderId;
    }




    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "email")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User user;

    @OneToMany(mappedBy = "userSet", cascade = CascadeType.ALL)
    private Set<Term> terms = new HashSet<>();



    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Folder parentFolder;



    public Folder getFolder() {
        return parentFolder;
    }

    public void setFolder(Folder folder) {
        this.parentFolder = folder;
    }

    public Set<Term> getTerms() {
        return this.terms;
    }

    public void setTerms(@Nullable Set<Term> terms) {
        this.terms = terms;
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

    public Boolean getIsSynced() {
        return isSynced;
    }

    public void setIsSynced(Boolean isSynced) {
        this.isSynced = isSynced;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
