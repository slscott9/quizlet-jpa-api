package com.stuart.quizletjpaapigradle.models;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user_table")
public class User {


    //constructors

    public User() {

    }

    public User(
            String email,
            String userName,
            String password,
            Boolean active,
            String roles,
            @Nullable Set<Folder> folders,
            @Nullable Set<UserSet> sets
    ){
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.active = active;
        this.roles = roles;
        this.folders = folders;
    }


    @Id
    private String email;

    private String userName;
    private String password;
    private boolean active;
    private String roles;

    @Nullable
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Folder> folders = new HashSet<>();

    public Set<Folder> getFolders() {
        return folders;
    }

    public void setFolders(Set<Folder> folders) {
        this.folders = folders;

        for(Folder folder : folders){
            folder.setUser(this);
            folder.setUserEmail(this.email); //sets each folders email property
        }
    }



    @Nullable
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<UserSet> sets = new HashSet<>();

    public Set<UserSet> getSets() {
        return sets;
    }

    public void setSets(@Nullable Set<UserSet> sets) {
        this.sets = sets;

        for(UserSet set : sets){
            set.setUser(this);
            set.setUserEmail(this.email);
        }
    }

    @Nullable
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Term> terms = new HashSet<>();

    public Set<Term> getTerms() {
        return this.terms;
    }

    public void setTerms(@Nullable Set<Term> terms){
        this.terms = terms;

        for(Term term : terms){
            term.setUser(this);
            term.setUserEmail(this.email);
        }
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
