package com.khaileid.Serviceimpl;

import com.khaileid.Entity.EntityRoles;
import com.khaileid.Repository.RepositoryRoles;
import com.khaileid.Service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RolesServiceimpl implements RolesService {

    @Autowired
    private RepositoryRoles repositoryroles;

    @Override
    public List<EntityRoles> getAllRolename() {
        List<EntityRoles>entityRoles=new ArrayList<>();
        this.repositoryroles.findAll().forEach(entityRoles::add);
        return entityRoles;
    }



    @Override
    public EntityRoles findByRolename(String namerole) {
        return repositoryroles.findByRolename(namerole);
    }

    @Override
    public ResponseEntity addRolename(EntityRoles entityRoles) {
        repositoryroles.save(entityRoles);
        return new ResponseEntity("Role is ADD", HttpStatus.ACCEPTED);
    }

    @Override
    public void deleteByRolename(String namerole) {
        repositoryroles.deleteByRolename(namerole);
    }


}
