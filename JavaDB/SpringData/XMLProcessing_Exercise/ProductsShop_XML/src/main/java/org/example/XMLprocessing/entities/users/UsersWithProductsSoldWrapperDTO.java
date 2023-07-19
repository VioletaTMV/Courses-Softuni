package org.example.XMLprocessing.entities.users;

import jakarta.xml.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsersWithProductsSoldWrapperDTO implements Serializable {

    @XmlAttribute(name = "count")
    private long usersCount;
    @XmlElement(name = "user")
    private List<UsersWithProductsSoldDTO> users;

    public UsersWithProductsSoldWrapperDTO() {
    }

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
