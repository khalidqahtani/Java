package com.khaileid.WS;

import com.khaileid.DTO.LoginBody;
import com.khaileid.DTO.UserDTO;
import com.khaileid.Service.UserService;
import com.khaileid.config.TheUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping
public class LoginControl {

//    @Autowired
//    private TheUserDetails theUserDetails;
    @Autowired
    private UserService userService;

//    @GetMapping (value = "/userData")
//    public ResponseEntity login(Principal principal){
////TODO
//        Map<String,String> map = new HashMap<>();
//        map.put("userid", String.valueOf(userService.findUsername(principal.getName()).getUserid()));
//        map.put("username", String.valueOf(userService.findUsername(principal.getName()).getUsername()));
//        map.put("firstname", String.valueOf(userService.findUsername(principal.getName()).getFirstname()));
//        map.put("lastname", String.valueOf(userService.findUsername(principal.getName()).getLastname()));
//        map.put("role",userService.findUsername(principal.getName()).getRoles().getRolename());
//        map.put("id", String.valueOf(userService.findUsername(principal.getName()).getId()));
//        map.put("email", String.valueOf(userService.findUsername(principal.getName()).getEmail()));
//        map.put("birthday", String.valueOf(userService.findUsername(principal.getName()).getBirthday()));
//        map.put("gender", String.valueOf(userService.findUsername(principal.getName()).getGender()));
//        map.put("mobile", String.valueOf(userService.findUsername(principal.getName()).getMobile()));
//        map.put("pic", String.valueOf(userService.findUsername(principal.getName()).getPic()));
//
//
//        return ResponseEntity.ok(map);
//    }

    @PostMapping("/userData")
    public ResponseEntity login(@RequestBody LoginBody loginBody){
        if (!userService.existsByUsernameAndAndEnableTrue(loginBody.getUsername())){
            throw new RuntimeException("User Login Fsiled");
        }
        UserDTO userDTO = userService.findUsername(loginBody.getUsername());
        if (userService.isUserEnabled(userDTO.getUserid()) && new BCryptPasswordEncoder().matches(loginBody.getPassword(),userDTO.getPassword())){
            Map<String, Object> map = new  HashMap<>();
            map.put("userid", userDTO.getUserid());
            map.put("firstname", userService.findUsername(loginBody.getUsername()).getFirstname());
            map.put("lastname", userService.findUsername(loginBody.getUsername()).getLastname());
            map.put("role", userService.findUsername(loginBody.getUsername()).getRoles().getRolename());
            return ResponseEntity.ok(map);
        }
        else {
            throw new RuntimeException("Password incorrect");
        }
    }
}
