package com.khaileid.Service;

import com.khaileid.DTO.UserDTO;
import com.khaileid.Entity.EntityUsers;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService  {

     List<EntityUsers> getAllusers();
     UserDTO findByUserid(Long userid);
     List<EntityUsers> findByFirstname(String firstname);
     List<EntityUsers> findByLastname (String lastname);
     UserDTO findUsername (String username);
     List<EntityUsers> findByGender (String genngder);
     UserDTO findByMobile (String mobile);
     EntityUsers addUser(UserDTO userDTO , String roles);
     EntityUsers updateUser(UserDTO userDTO, Long userid);
     EntityUsers deletById(Long userid);
     ResponseEntity enableById(Long userid);
     ResponseEntity unEnableById(Long userid);
     List<EntityUsers> findAllDeleted();
     List<EntityUsers> findByRoles (String roles);


    }



