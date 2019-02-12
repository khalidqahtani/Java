package com.khaileid.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "Comment")
public class EntityComment {

    @Id
    @GeneratedValue (strategy =GenerationType.IDENTITY)
    private long commentid;

    @Size(min =3 , max = 24)
    private String comment;

    @DateTimeFormat
    private LocalDateTime dateTime;


    private String eventname;
    private String username;
    private Long eid;



    //**Relation**//
    @ManyToOne
    @JsonIgnore
    private EntityEvent eventid;
    @ManyToOne
    private EntityUsers userid;

    public EntityUsers getUserid() {
        return userid;
    }

    public void setUserid(EntityUsers userid) {
        this.userid = userid;
    }

    public EntityEvent getEventid() { return eventid; }

    public void setEventid(EntityEvent eventid) { this.eventid = eventid; }

    public long getCommentid() {
        return commentid;
    }

    public void setCommentid(long commentid) {
        this.commentid = commentid;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


    public LocalDateTime getDateTime() { return dateTime; }

    public void setDateTime(LocalDateTime dateTime) { this.dateTime = dateTime; }


    public String getEventname() {
        return eventname;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getEid() {
        return eid;
    }

    public void setEid(Long eid) {
        this.eid = eid;
    }
}
