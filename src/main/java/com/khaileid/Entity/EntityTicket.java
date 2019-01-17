package com.khaileid.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "Ticket")
public class EntityTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ticketid;

    private LocalDate date;

    private boolean ticketcancel;
    private boolean userpresent;
    private String eventname;
    private LocalDate dateevent;
    private String timeevent;
    private int ticketrate;

    //**Relation**//
    @ManyToOne
    private EntityEvent eid;
    @ManyToOne
    private EntityUsers uid;


    //**Setter and Getter**//
    public EntityEvent getEid() {
        return eid;
    }

    public void setEid(EntityEvent eid) {
        this.eid = eid;
    }

    public EntityUsers getUid() {
        return uid;
    }

    public void setUid(EntityUsers uid) {
        this.uid = uid;
    }

    public long getTicketid() {
        return ticketid;
    }

    public void setTicketid(long ticketid) {
        this.ticketid = ticketid;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isTicketcancel() {
        return ticketcancel;
    }

    public void setTicketcancel(boolean ticketcancel) {
        this.ticketcancel = ticketcancel;
    }

    public boolean isUserpresent() {
        return userpresent;
    }

    public void setUserpresent(boolean userpresent) {
        this.userpresent = userpresent;
    }

    public String getEventname() {
        return eventname;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }

    public LocalDate getDateevent() {
        return dateevent;
    }

    public void setDateevent(LocalDate dateevent) {
        this.dateevent = dateevent;
    }

    public String getTimeevent() {
        return timeevent;
    }

    public void setTimeevent(String timeevent) {
        this.timeevent = timeevent;
    }

    public int getTicketrate() {
        return ticketrate;
    }

    public void setTicketrate(int ticketrate) {
        this.ticketrate = ticketrate;
    }
}
