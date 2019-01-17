package com.khaileid.Repository;

import com.khaileid.Entity.EntityRoles;
import com.khaileid.Entity.EntityUsers;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryRoles extends CrudRepository<EntityRoles,String> {

    List<EntityRoles> findAll();
    EntityRoles findByRolename(String namerole);
    void deleteByRolename(String namerole);
//    Long countByUserid();

}
