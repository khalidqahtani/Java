package com.khaileid.Serviceimpl;

import com.khaileid.DTO.UserDTO;
import com.khaileid.Entity.EntityRoles;
import com.khaileid.Entity.EntityUsers;
import com.khaileid.Repository.RepositoryEvent;
import com.khaileid.Repository.RepositoryRoles;
import com.khaileid.Repository.RepositoryUser;
import com.khaileid.Service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceimpl implements UserService {


    @Autowired
    private RepositoryUser repositoryuser;
    @Autowired
    private RepositoryRoles repositoryRoles;
    @Autowired
    private RepositoryEvent repositoryEvent;
    @Autowired
    private ModelMapper modelMapper;



    @Override
    public List<EntityUsers> getAllusers() {
        return repositoryuser.findAll();
    }


    @Override
    public UserDTO findByUserid( Long userid) {
        EntityUsers entityUsers = repositoryuser.findByUserid(userid);
        UserDTO userDTO = modelMapper.map(entityUsers,UserDTO.class);
        return userDTO;
    }


    @Override
    public List<EntityUsers> findByFirstname(String firstname) {
        return repositoryuser.findByFirstname(firstname);
    }

    @Override
    public List<EntityUsers> findByLastname(String lastname) {
        return repositoryuser.findByLastname(lastname);
    }

    @Override
    public UserDTO findUsername(String username) {
        EntityUsers entityUsers = repositoryuser.findByUsernameAndDeletedFalseAndEnableTrue(username);
        UserDTO userDTO = modelMapper.map(entityUsers, UserDTO.class);

        return userDTO;
    }

    @Override
    public List<EntityUsers> findByGender(String gender) {
        return repositoryuser.findByGender(gender);
    }

    @Override
    public UserDTO findByMobile(String mobile) {
        EntityUsers entityUsers = repositoryuser.findByMobileAndDeletedFalseAndEnableTrue(mobile);
        UserDTO userDTO = modelMapper.map(entityUsers, UserDTO.class);

        return userDTO;
    }

    @Override
    public EntityUsers addUser(UserDTO userDTO, String roles) {
        if (!repositoryuser.existsById(userDTO.getId()) && !repositoryuser.existsByUsername(userDTO.getUsername()) && !repositoryuser.existsByEmail(userDTO.getEmail())) {
           EntityUsers entityUsers = modelMapper.map(userDTO, EntityUsers.class);
            String encoded = new BCryptPasswordEncoder().encode(entityUsers.getPassword());
            entityUsers.setRoles(repositoryRoles.findByRolename(roles));
            entityUsers.setPassword(encoded);
            entityUsers.setEnable(true);
            repositoryuser.save(entityUsers);
            return entityUsers;
        }
        return null;
    }

    @Override
    public EntityUsers updateUser(UserDTO userDTO, Long userid) {
        EntityUsers entityUsers ;
        EntityUsers entityUsers1 = repositoryuser.findById(userid).get();
        entityUsers = modelMapper.map(userDTO, EntityUsers.class);
        String encoded = new BCryptPasswordEncoder().encode(entityUsers.getPassword());
        entityUsers.setPassword(encoded);
        entityUsers.setUserid(userid);
        entityUsers.setRoles(entityUsers1.getRoles());
        return repositoryuser.save(entityUsers);
    }

    @Override
    public EntityUsers deletById(Long userid) {
        EntityUsers entityUsers=repositoryuser.findById(userid).get();
        entityUsers.setDeleted(true);
        entityUsers.setEnable(false);
        return repositoryuser.save(entityUsers);
    }

    @Override
    public ResponseEntity enableById(Long userid) {
        EntityUsers entityUsers=repositoryuser.findById(userid).get();
        entityUsers.setEnable(true);
        repositoryuser.save(entityUsers);
        return new ResponseEntity("User Has Enable.",HttpStatus.ACCEPTED);


    }

    @Override
    public ResponseEntity unEnableById(Long userid) {
        EntityUsers entityUsers=repositoryuser.findById(userid).get();
        entityUsers.setEnable(false);
        repositoryuser.save(entityUsers);
        return new ResponseEntity("User Has UnEnable.",HttpStatus.ACCEPTED);


    }

    @Override
    public List<EntityUsers> findAllDeleted() {
        return repositoryuser.findAllByDeletedTrue();
    }

    @Override
    public List<EntityUsers> findByRoles(String roles) {
        EntityRoles entityRoles = repositoryRoles.findByRolename(roles);
        return repositoryuser.findByRoles(entityRoles);
    }

    public boolean isUserEnabled(Long id) { return repositoryuser.existsByUseridAndEnableTrue(id);}

    public boolean existsByUsernameAndAndEnableTrue(String username) {
        return repositoryuser.existsByUsernameAndAndEnableTrue(username);
    }


}
