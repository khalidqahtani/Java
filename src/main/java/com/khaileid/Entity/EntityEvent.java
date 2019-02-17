package com.khaileid.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;


@Entity
@Table (name = "Event")
public class EntityEvent {

    @Id
    @GeneratedValue (strategy =GenerationType.IDENTITY)
    private long eventid;

    @NotNull
    @Column(unique = true)
    @Size(min =3 , max = 33)
    private String nameevent;

    @NotNull
    @Size(min =3 , max = 33)
    private String tybeevent;

    @NotNull
    @Pattern(regexp = "[^\\s]+", message = "No space please")
    @Size(min =3 , max = 9)
    private String genderevent;

    @NotNull
    @Size(min =3 , max = 15)
    private String eventstreet;

    @NotNull
    @Pattern(regexp = "[a-zA-Z\\s]{3,15}",message = "size 3-20 ")
    @Size(min =3 , max = 20)
    private String eventcity;
//
    @NotNull
    private LocalDate eventdate;

    @NotNull
    @DateTimeFormat
    private String eventtime;

    @NotNull
    @Size(min =3 , max = 48)
    private String description;

    @NotNull
    private String specialneed;


    @NotNull
    @Min(20)
    @Max(300)
    private long capacity;
    private String pic;
    private Long available;


    private boolean edelete;
    private boolean approval;
    private long counter=0;


    //**Relation**//
    @ManyToOne
    private EntityUsers orgnizerID;

    @OneToMany
    private List<EntityComment> comments;

    //**Setter and Getter**//


    public EntityUsers getOrgnizerID() {
        return orgnizerID;
    }

    public void setOrgnizerID(EntityUsers orgnizerID) {
        this.orgnizerID = orgnizerID;
    }

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

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
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

    public boolean isApproval() {
        return approval;
    }

    public void setApproval(boolean approval) {
        this.approval = approval;
    }

    public void setEdelete(boolean edelete) {
        this.edelete = edelete;
    }

    public long getCounter() {
        return counter;
    }

    public void setCounter(long counter) {
        this.counter = counter;
    }

    public List<EntityComment> getComments() {
        return comments;
    }

    public void setComments(List<EntityComment> comments) {
        this.comments = comments;
    }

    public Long getAvailable() {
        return available;
    }

    public void setAvailable(Long available) {
        this.available = available;
    }
}

