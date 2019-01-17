package com.khaileid.WS;

import com.khaileid.Entity.EntityTicket;
import com.khaileid.Service.NotificationService;
import com.khaileid.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TicketControl {

    @Autowired
    private TicketService ticketService;
    @Autowired
    private NotificationService emailsender;

    @PreAuthorize("(hasRole('ADMIN'))")
    @GetMapping (value = "/adminaccess/ticket")
    public List<EntityTicket> getAlltickit(){
        return this.ticketService.getAlltickit();
    }

    @PreAuthorize("(hasAnyRole('ADMIN','ORGANIZER','USER'))")
    @GetMapping (value = "/allticket/foruser/{uid}")
    public List<EntityTicket> findAllTicketByUser(@Valid @PathVariable Long uid){
        return ticketService.findAllTicketByUser(uid);}

    @PreAuthorize("(hasAnyRole('ADMIN','ORGANIZER'))")
    @GetMapping (value = "/allticket/forevent/{eid}")
    public List<EntityTicket> findAllByEidEventid(@Valid @PathVariable Long eid){
        return ticketService.findAllByEidEventid(eid);}

    @PreAuthorize("(hasAnyRole('ADMIN','ORGANIZER'))")
    @GetMapping (value = "/useraccess/allticket/foreventpressent/{eid}")
    public List<EntityTicket> findAllTicketprepresentByEvent(@Valid @PathVariable Long eid){
        return ticketService.findAllTicketprepresentByEvent(eid);
    }

    @PreAuthorize("(hasAnyRole('ADMIN','ORGANIZER'))")
    @GetMapping (value = "/useraccess/allticket/foreventUnpressent/{eid}")
    public List<EntityTicket> findfindAllTicketnonprepresentByEvent(@Valid @PathVariable Long eid){
        return ticketService.findfindAllTicketnonprepresentByEvent(eid);
    }

    @PreAuthorize("(hasAnyRole('ADMIN','ORGANIZER'))")
    @GetMapping (value = "/useraccess/allticket/foreventAtCancel/{eid}")
    public List<EntityTicket> findAllTicketcancelByEvent(@Valid @PathVariable Long eid){
        return ticketService.findAllTicketcancelByEvent(eid);
    }

    @PreAuthorize("(hasRole('ADMIN'))")
    @GetMapping (value = "/ticket/by/{tid}")
    public EntityTicket findById(@Valid @PathVariable Long tid) {return ticketService.findById(tid);}

    //user book ticket eventid/ateenderid
    @PreAuthorize("(hasRole('USER'))")
    @GetMapping (value = "/book/{eid}/{uid}")
    public EntityTicket addTicket(@Valid @PathVariable Long eid, @PathVariable Long uid) {

//        emailsender.notificationBookTicket(eid, uid);
        return ticketService.addTicket(eid, uid);
    }

    @PreAuthorize("(hasAnyRole('ADMIN','ORGANIZER'))")
    @PutMapping (value ="/ticket/{tid}")
    public ResponseEntity updateTicket (@Valid @RequestBody EntityTicket entityTicket, @PathVariable Long tid) {
       return ResponseEntity.ok(ticketService.updateTicket(entityTicket,tid));}

    //attender cansel ticket
//    @PreAuthorize("(hasRole('USER'))")
//    @PutMapping (value = "/useraccess/ticket/delete/{tid}")
//    public void deleteById(@PathVariable Long tid){
//        ticketService.deletById(tid);
//        emailsender.notificationCancelTicket(tid);
//    }

    //admin get all ticket cansel
    @PreAuthorize("(hasRole('ADMIN'))")
    @GetMapping (value = "/adminaccess/ticket/delete/ticketdelete")
    public List<EntityTicket> findAllDeleted(){return ticketService.findAllDeleted();}

    @PreAuthorize("(hasRole('USER'))")
    @DeleteMapping (value = "/ticket/cancel/{tid}")
    public ResponseEntity TicketcancelByTicketid(@PathVariable Long tid){
        return ResponseEntity.ok(ticketService.TicketcancelByTicketid(tid));}

    @PreAuthorize("(hasRole('USER'))")
    @PutMapping (value = "/adminaccess/ticket/uncancel/{tid}")
    public ResponseEntity UnTicketcancelByTicketid(@PathVariable Long tid){
        return ResponseEntity.ok(ticketService.UnTicketcancelByTicketid(tid));}

    @PreAuthorize("(hasAnyRole('ORGANIZER','USER'))")
    @PutMapping (value = "/adminaccess/ticket/present/{tid}")
    public ResponseEntity UserpresentById(@PathVariable Long tid) {
        return ResponseEntity.ok(ticketService.UserpresentById(tid));}

    @PreAuthorize("(hasAnyRole('ORGANIZER','USER'))")
    @PutMapping (value = "/adminaccess/ticket/unpresent/{tid}")
    public ResponseEntity unUserpresentByTicketid(@PathVariable Long tid){
        return ResponseEntity.ok(ticketService.unUserpresentByTicketid(tid));}

    @PreAuthorize("(hasAnyRole('ADMIN','ORGANIZER'))")
    @GetMapping (value = "/adminaccess/ticket/allpresent")
    public ResponseEntity findAllByUserpresentTrue(){
        return ResponseEntity.ok( ticketService.findAllByUserpresentTrue());}

    @PreAuthorize("(hasAnyRole('ADMIN','ORGANIZER'))")
    @GetMapping (value = "/adminaccess/ticket/allunpresent")
    public ResponseEntity findAllByUserpresentFalse(){
        return ResponseEntity.ok(ticketService.findAllByUserpresentFalse());}

    @PreAuthorize("(hasAnyRole('ADMIN','ORGANIZER'))")
    @GetMapping (value = "/adminaccess/ticket/allcancel")
    public ResponseEntity findAllByTicketcancelTrue(){
        return ResponseEntity.ok(ticketService.findAllByTicketcancelTrue());}

    @PreAuthorize("(hasAnyRole('ADMIN','ORGANIZER'))")
    @GetMapping (value = "/adminaccess/ticket/alluncancel")
    public ResponseEntity findAllByTicketcancelFalse(){
        return ResponseEntity.ok(ticketService.findAllByTicketcancelFalse());}
}
