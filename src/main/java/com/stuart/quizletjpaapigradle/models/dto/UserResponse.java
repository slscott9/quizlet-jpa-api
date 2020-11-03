package com.stuart.quizletjpaapigradle.models.dto;

import com.stuart.quizletjpaapigradle.models.Folder;
import com.stuart.quizletjpaapigradle.models.UserSet;
import org.graalvm.compiler.api.replacements.Fold;
import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.util.Set;

public class UserResponse implements Serializable {



    @Nullable
    public Set<UserSet> userSets;

    @Nullable
    public Set<UserSet> getUserSets() {
        return userSets;
    }

    public void setUserSets(@Nullable Set<UserSet> userSets) {
        this.userSets = userSets;
    }


    @Nullable
    Set<Folder> folders;

    @Nullable
    public Set<Folder> getFolders() {
        return folders;
    }

    public void setFolders(@Nullable Set<Folder> folders) {
        this.folders = folders;
    }


    public UserResponse( Set<Folder> folders, Set<UserSet> userSets){

        this.folders = folders;
        this.userSets = userSets;
    }
}
