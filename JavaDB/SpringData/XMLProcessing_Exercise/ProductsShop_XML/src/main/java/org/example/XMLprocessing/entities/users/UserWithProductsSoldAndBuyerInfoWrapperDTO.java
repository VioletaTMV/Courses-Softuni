package org.example.XMLprocessing.entities.users;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserWithProductsSoldAndBuyerInfoWrapperDTO implements Serializable {

    @XmlElement(name = "user")
    private List<UserWithProductsSoldAndBuyerInfoDTO> users;

    public UserWithProductsSoldAndBuyerInfoWrapperDTO(){}

    public UserWithProductsSoldAndBuyerInfoWrapperDTO(List<UserWithProductsSoldAndBuyerInfoDTO> users) {
        this.users = users;
    }

    public List<UserWithProductsSoldAndBuyerInfoDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserWithProductsSoldAndBuyerInfoDTO> users) {
        this.users = users;
    }
}
