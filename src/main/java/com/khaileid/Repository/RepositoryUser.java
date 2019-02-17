package com.khaileid.Repository;


import com.khaileid.DTO.UserDTO;
import com.khaileid.Entity.EntityEvent;
import com.khaileid.Entity.EntityRoles;
import com.khaileid.Entity.EntityUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryUser extends JpaRepository<EntityUsers,Long> {

    EntityUsers findByUserid(Long userid);
    List<EntityUsers> findByFirstname(String firstname);
    List<EntityUsers> findByLastname(String lastname);
    List<EntityUsers> findByGender(String gender);
    List<EntityUsers> findAllByDeletedTrue();
    EntityUsers findByUsernameAndDeletedFalseAndEnableTrue(String username);
    EntityUsers findByMobileAndDeletedFalseAndEnableTrue(String mobile);
    List<EntityUsers> findByRoles (EntityRoles roles);

    boolean existsById(String id);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

    boolean existsByUsernameAndAndEnableTrue(String username);
    boolean existsByUseridAndEnableTrue(Long userid);



}
