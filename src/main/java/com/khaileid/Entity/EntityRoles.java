package com.khaileid.Entity;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "Roles")
public class EntityRoles {

    @Id
    private String rolename;

    public EntityRoles() {
    }


    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

}