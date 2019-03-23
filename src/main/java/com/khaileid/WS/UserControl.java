package com.khaileid.WS;

import com.khaileid.DTO.UserDTO;
import com.khaileid.Entity.EntityUsers;
import com.khaileid.Repository.RepositoryUser;
import com.khaileid.Service.NotificationService;
import com.khaileid.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api")
public class UserControl {

    @Autowired
    private UserService userService;
    @Autowired
    private RepositoryUser repositoryUser;
    @Autowired
    private NotificationService emailsender;

    @PreAuthorize("(hasRole('ADMIN'))")
    @GetMapping (value = "/users")
    public List<EntityUsers> getAllusers(){
        return this.userService.getAllusers();
    }

//    @PreAuthorize("(hasAnyRole('ADMIN','ORGANIZER'))")
    @GetMapping (value = "/users/{userid}")
    public UserDTO findByUserid(@PathVariable Long userid) {

        return userService.findByUserid(userid);}

    @PreAuthorize("(hasRole('ADMIN'))")
    @GetMapping (value = "/users/firstname/{firstname}")
    public ResponseEntity findByFirstname(@PathVariable String firstname) {
        if (userService.findByFirstname(firstname).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userService.findByFirstname(firstname));
    }

    @PreAuthorize("(hasRole('ADMIN'))")
    @GetMapping (value = "/users/lastname/{lastname}")
    public ResponseEntity findByLastname(@PathVariable String lastname) {
        if (userService.findByLastname(lastname).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userService.findByLastname(lastname));
    }

    // FIXME: 12/1/2018
    @PreAuthorize("(hasAnyRole('ADMIN','ORGANIZER'))")
    @GetMapping (value = "/users/username/{username}")
    public ResponseEntity findUsername(@PathVariable String username) {
        if (userService.findUsername(username)!=null)
        {
            return ResponseEntity.ok(userService.findUsername(username));

        }
            return ResponseEntity.notFound().build();
    }

    @PreAuthorize("(hasRole('ADMIN'))")
    @GetMapping ( value = "/users/gender/{gender}")
    public List<EntityUsers> findByGender(@PathVariable String gender){return userService.findByGender(gender);}

    @PreAuthorize("(hasAnyRole('ADMIN','ORGANIZER'))")
    @GetMapping (value = "/users/mobile/{mobile}")
    public ResponseEntity findByMobile(@PathVariable String mobile) {
        if (userService.findByMobile(mobile) != null) {
            return ResponseEntity.ok(userService.findByMobile(mobile));
        }
            return ResponseEntity.notFound().build();
    }

    @PostMapping (value = "/users/registration/{roles}")
    public ResponseEntity addUser(@RequestBody @Valid UserDTO userDTO,@Valid @PathVariable String roles, BindingResult bindingResult) {

        if (bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        userService.addUser(userDTO,roles);
//        emailsender.notificationAddAtender(entityUsers);
//        return new ResponseEntity("User HAS ADDED", HttpStatus.ACCEPTED);
        return ResponseEntity.created(null).build();
    }

    @PutMapping (value ="/users/update/{userid}")
    public ResponseEntity updateUser (@Valid @RequestBody UserDTO userDTO, @PathVariable Long userid,BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        return ResponseEntity.ok(userService.updateUser(userDTO,userid));}

    // FIXME: 12/6/2018 
    @PreAuthorize("(hasRole('ADMIN'))")
    @DeleteMapping (value = "/users/delete/{userid}")
    public ResponseEntity deleteById(@PathVariable Long userid){
        return ResponseEntity.ok(userService.deletById(userid));
    }

    @PreAuthorize("(hasRole('ADMIN'))")
    @PutMapping (value = "/adminaccess/users/enable/{userid}")
    public ResponseEntity enableById(@PathVariable Long userid) {
        return ResponseEntity.ok(userService.enableById(userid));}

    @PreAuthorize("(hasRole('ADMIN'))")
    @PutMapping (value = "/adminaccess/users/unenable/{userid}")
    public ResponseEntity unEnableById(@PathVariable Long userid) { return ResponseEntity.ok(userService.unEnableById(userid));}

    @PreAuthorize("(hasRole('ADMIN'))")
    @GetMapping (value = "/users/alldeleteusers")
    public List<EntityUsers> findAllDeleted(){return userService.findAllDeleted();}

//    @PreAuthorize("(hasRole('ADMIN'))")
    @GetMapping (value = "/adminaccess/gatallroles/{roles}")
    public List<EntityUsers> findByRoles(@PathVariable String roles){
        return userService.findByRoles(roles);
    }

}
