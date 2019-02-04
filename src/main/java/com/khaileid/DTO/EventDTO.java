package com.khaileid.DTO;

import com.khaileid.Entity.EntityComment;
import com.khaileid.Entity.EntityUsers;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

public class EventDTO {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long eventid;

    @NotNull
    @Pattern(regexp = "[a-zA-Z0-9]{3,15}",message = "size 3-15 ")
    @Size(min =3 , max = 15)
    private String nameevent;

    @NotNull
    @Pattern(regexp = "[a-zA-Z]{3,15}",message = "size 3-15 ")
    @Size(min =3 , max = 15)
    private String tybeevent;

    @NotNull
    @Pattern(regexp = "[^\\s]+", message = "No space please")
    @Pattern(regexp = "[a-zA-Z]{3,9}",message = "size 3-9 ")
    @Size(min =3 , max = 9)
    private String genderevent;

    @NotNull
    @Pattern(regexp = "[a-zA-Z0-9]{3,15}",message = "size 3-15 ")
    @Size(min =3 , max = 15)
    private String eventstreet;

    @NotNull
    @Pattern(regexp = "[a-zA-Z]{3,15}",message = "size 3-20 ")
    @Size(min =3 , max = 20)
    private String eventcity;

    @NotNull
    private LocalDate eventdate;

    @NotNull
    @DateTimeFormat
    private String eventtime;

    @NotNull
    @Pattern(regexp = "[a-zA-Z0-9]{3,48}",message = "size 3-48 ")
    @Size(min =3 , max = 48)
    private String description;
//
    @NotNull
    private String specialneed;

    @NotNull
    @Min(20)
    @Max(300)
    private long capacity;

    private boolean edelete;
    private boolean approval;
    private long counter=0;
    private UserDTO orgnizerID;
    private List<CommentDTO> comments;




    public long getEventid() {
        return eventid;
    }

    public void setEventid(long eventid) {
        this.eventid = eventid;
    }

    public String getNameevent() {
        return nameevent;
    }

    public void setNameevent(String nameevent) {
        this.nameevent = nameevent;
    }

    public String getTybeevent() {
        return tybeevent;
    }

    public void setTybeevent(String tybeevent) {
        this.tybeevent = tybeevent;
    }

    public String getGenderevent() {
        return genderevent;
    }

    public void setGenderevent(String genderevent) {
        this.genderevent = genderevent;
    }

    public String getEventstreet() {
        return eventstreet;
    }

    public void setEventstreet(String eventstreet) {
        this.eventstreet = eventstreet;
    }

    public String getEventcity() {
        return eventcity;
    }

    public void setEventcity(String eventcity) {
        this.eventcity = eventcity;
    }

    public LocalDate getEventdate() {
        return eventdate;
    }

    public void setEventdate(LocalDate eventdate) {
        this.eventdate = eventdate;
    }

    public String getEventtime() {
        return eventtime;
    }

    public void setEventtime(String eventtime) {
        this.eventtime = eventtime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSpecialneed() {
        return specialneed;
    }

    public void setSpecialneed(String specialneed) {
        this.specialneed = specialneed;
    }

    public long getCapacity() {
        return capacity;
    }

    public void setCapacity(long capacity) {
        this.capacity = capacity;
    }

    public boolean isEdelete() {
        return edelete;
    }

    public void setEdelete(boolean edelete) {
        this.edelete = edelete;
    }

    public boolean isApproval() {
        return approval;
    }

    public void setApproval(boolean approval) {
        this.approval = approval;
    }

    public long getCounter() {
        return counter;
    }

    public void setCounter(long counter) {
        this.counter = counter;
    }


    public UserDTO getOrgnizerID() {
        return orgnizerID;
    }

    public void setOrgnizerID(UserDTO orgnizerID) {
        this.orgnizerID = orgnizerID;
    }

    public List<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentDTO> comments) {
        this.comments = comments;
    }
}

