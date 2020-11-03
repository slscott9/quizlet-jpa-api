package com.stuart.quizletjpaapigradle.models.dto;

import com.stuart.quizletjpaapigradle.models.Folder;
import com.stuart.quizletjpaapigradle.models.UserSet;
import org.springframework.lang.Nullable;

import java.util.Set;

public class UserDto {

    private String userName;
    private String email;
    private String password;

    private Set<Folder> folders;

    private Set<UserSet> sets;




    public Set<UserSet> getSets() {
        return sets;
    }

    public void setSets(Set<UserSet> sets) {
        this.sets = sets;
    }


    @Nullable
    public Set<Folder> getFolders() {
        return folders;
    }

    public void setFolders(Set<Folder> folders) {
        this.folders = folders;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
}
