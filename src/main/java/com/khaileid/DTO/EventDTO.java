package com.khaileid.DTO;

import com.khaileid.Entity.EntityComment;
import com.khaileid.Entity.EntityUsers;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

public class EventDTO {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long eventid;

    @NotNull
    @Size(max = 18, message = "character it max 18 ")
    private String nameevent;

    @NotNull
    @Size(max = 18)
    private String tybeevent;

    @NotNull
    @Size(max = 18)
    private String genderevent;

    @Size(max = 20, min = 2)
    private String eventstreet;

    @NotNull
    @Size(max = 18)
    private String eventcity;

    @NotNull
    private LocalDate eventdate;

    @DateTimeFormat
    private String eventtime;

    @NotNull
    @Min(1)
    @Max(300)
    private long capacity;
    private boolean edelete;
    private boolean approval;
    private long counter=0;
    private UserDTO OrgnizerID;
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
        return OrgnizerID;
    }

    public void setOrgnizerID(UserDTO orgnizerID) {
        OrgnizerID = orgnizerID;
    }

    public List<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentDTO> comments) {
        this.comments = comments;
    }
}

