package com.khaileid.config;

import com.khaileid.DTO.UserDTO;
import com.khaileid.Entity.EntityUsers;
import com.khaileid.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TheUserDetails implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        UserDTO userDTO = userService.findUsername(s);
        if (userDTO == null){

            throw new UsernameNotFoundException(s);
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(userDTO.getRoles().getRolename()));
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(userDTO.getUsername(),userDTO.getPassword(),authorities);
        return userDetails;
    }
}
