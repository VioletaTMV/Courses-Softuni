package org.example.XMLprocessing.entities.users;

import jakarta.xml.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportUserWrapperDTO implements Serializable {

    @XmlElement(name = "user")
    private List<ImportUserDTO> users;

    public ImportUserWrapperDTO(){}

    public ImportUserWrapperDTO(List<ImportUserDTO> users) {
        this.users = users;
    }

    public List<ImportUserDTO> getUsers() {
        return users;
    }
}
