package com.khaileid.Service;

import com.khaileid.Entity.EntityRoles;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RolesService {

    List<EntityRoles> getAllRolename();
    EntityRoles findByRolename(String namerole);
    ResponseEntity addRolename(EntityRoles entityRoles);
    void deleteByRolename (String namerole);

}


