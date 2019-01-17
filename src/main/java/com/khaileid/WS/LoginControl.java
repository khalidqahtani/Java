package com.khaileid.WS;

import com.khaileid.Service.UserService;
import com.khaileid.config.TheUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping (value = "/userData")
    public ResponseEntity login(Principal principal){
//TODO
        Map<String,String> map = new HashMap<>();
        map.put("userid", String.valueOf(userService.findUsername(principal.getName()).getUserid()));
        map.put("username", String.valueOf(userService.findUsername(principal.getName()).getUsername()));
        map.put("firstname", String.valueOf(userService.findUsername(principal.getName()).getFirstname()));
        map.put("lastname", String.valueOf(userService.findUsername(principal.getName()).getLastname()));
        map.put("role",userService.findUsername(principal.getName()).getRoles().getRolename());
        return ResponseEntity.ok(map);
    }
}
