package com.khaileid.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table (name="Users")
public class EntityUsers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userid;

    @NotNull
    @Column(unique = true)
    @Pattern(regexp = "[^\\s]+", message = "No space please")
    @Pattern(regexp = "[1-2]{1}+[0-9]{9}")
    @Size(min = 10, max = 10, message = "HI")
    private String id;

    @NotNull
    @Pattern(regexp = "[a-zA-Z\\s]{2,10}",message = "can not have DIGET, and size 2-10 ")
    @Size(min =2 , max = 10)
    private String firstname;

    @NotNull
    @Pattern(regexp = "[a-zA-Z\\s]{2,16}",message = "can not have DIGET, and size 2-16 ")
    @Size(min =2 , max = 16)
    private String lastname;
    private String pic;


    @NotNull
    @Column(unique = true)
    @Pattern(regexp = "[^\\s]+", message = "No space please")
    @Pattern(regexp = "[a-z0-9]{4,11}",message = "can not have SYMBOL, and size 4-11 ")
    @Size(min = 4, max = 11, message = "size from 4-11")
    private String username;

    @NotNull
    @Pattern(regexp = "[^\\s]+", message = "No space please")
    @Size(min = 8)
    private String password;

    @NotNull
    @DateTimeFormat
    private String birthday;

    @NotNull
    private String gender;

    @NotNull
    @Column(unique = true)
    @Pattern(regexp = "[^\\s]+", message = "No space please")
    @Email
    @Size(max = 37)
    private String email;

    @NotNull
    @NumberFormat
    @Column(unique = true)
    @Pattern(regexp = "[^\\s]+", message = "No space please")
    @Pattern(regexp ="[5]{1}+[0-9]{8}", message = "size prop")
    @Size(min = 9,max = 9)
    private String mobile;

    @JsonIgnore
    private boolean deleted;

    @JsonIgnore
    private boolean enable;


    //**Relation**//
    @ManyToOne
    @JoinColumn(name = "role", referencedColumnName = "rolename")
    private EntityRoles roles;



    //**Setter and Getter**//
    public EntityUsers() {
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public EntityRoles getRoles() {
        return roles;
    }

    public void setRoles(EntityRoles roles) {
        this.roles = roles;
    }


}