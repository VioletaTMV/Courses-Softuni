package org.example.JSONprocessing.entities.users;

import java.util.List;

public class UsersWithProductsSoldWrapperDTO {
    private long usersCount;
    private List<UsersWithProductsSoldDTO> users;

    public UsersWithProductsSoldWrapperDTO(List<UsersWithProductsSoldDTO> users) {
        this.users = users;
        this.usersCount = users.size();
    }

    public long getUsersCount() {
        return usersCount;
    }

    public void setUsersCount(long usersCount) {
        this.usersCount = usersCount;
    }

    public List<UsersWithProductsSoldDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UsersWithProductsSoldDTO> users) {
        this.users = users;
    }
}
