package com.khaileid.WS;

import com.khaileid.DTO.EventDTO;
import com.khaileid.Entity.EntityEvent;
import com.khaileid.Service.EventService;
import com.khaileid.Service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EventControl {
    @Autowired
    private EventService eventservice;
    @Autowired
    private NotificationService emailsender;


    @PreAuthorize("(hasRole('ADMIN'))")
    @GetMapping(value = "/events")
    public List<EntityEvent> getAllevent(){
        return this.eventservice.getAllevent();
    }

    // FIXME: 11/27/2018
    @GetMapping (value = "/event/ByCity/{eventcity}")
    public ResponseEntity findByEventcity(@PathVariable String eventcity ){
        if(eventservice.findByEventcity(eventcity).isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(eventservice.findByEventcity(eventcity)) ;}


    // FIXME: 11/27/2018
    @RequestMapping (value = "/event/ByDateAndCity/{eventcity}/{eventdate}")
    public ResponseEntity findByEventcityAndEventdate(@PathVariable String eventdate,@PathVariable String eventcity)
    { if (eventservice.findByEventcityAndEventdate(eventcity,LocalDate.parse(eventdate)).isEmpty()){
        return ResponseEntity.notFound().build();
    }
        return ResponseEntity.ok(eventservice.findByEventcityAndEventdate(eventcity,LocalDate.parse(eventdate)));
    }

    @PreAuthorize("(hasAnyRole('ADMIN','ORGANIZER'))")
    @GetMapping(value = "/findbyid/{eventid}")
    public EventDTO findByEventid(@PathVariable Long eventid){
        return eventservice.findByEventid(eventid);
    }


    @PreAuthorize("(hasAnyRole('ADMIN','ORGANIZER'))")
    @PostMapping (value = "/event/{uid}")
    public ResponseEntity addEvent(@Valid @RequestBody EventDTO eventDTO, @Valid @PathVariable Long uid, BindingResult result)
    {
        if (result.hasErrors()){
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        return eventservice.addEvent(eventDTO,uid); }

    @PreAuthorize("(hasAnyRole('ADMIN','ORGANIZER'))")
    @PutMapping (value ="/event/{eventid}")
    public ResponseEntity updateEvent (@Valid @RequestBody EventDTO eventDTO, @Valid @PathVariable Long eventid, BindingResult result) {

        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        emailsender.notificationUpdateEvent(eventid);
        return ResponseEntity.ok(eventservice.updateEvent(eventDTO, eventid));

    }

    @PreAuthorize("(hasAnyRole('ADMIN','ORGANIZER'))")
    @DeleteMapping (value = "/event/delete/{eventid}")
    public ResponseEntity deletByEventid(@PathVariable Long eventid){
        emailsender.notificationEventCancel(eventid);
        return ResponseEntity.ok(eventservice.deletByEventid(eventid));
    }

    @PreAuthorize("(hasAnyRole('ADMIN','ORGANIZER'))")
    @DeleteMapping (value = "/event/undelete/{eventid}")
    public ResponseEntity unDeletByEventid(@PathVariable Long eventid){
        return ResponseEntity.ok(eventservice.unDeletByEventid(eventid));

    }


    @PreAuthorize("(hasAnyRole('ADMIN','ORGANIZER'))")
    @GetMapping (value = "/event/approve/{eventid}")
    public ResponseEntity approvalByEventid(@PathVariable Long eventid){
        return ResponseEntity.ok(eventservice.approvalByEventid(eventid));

    }

    @PreAuthorize("(hasAnyRole('ADMIN','ORGANIZER'))")
    @GetMapping (value = "/event/unapprove/{eventid}")
    public ResponseEntity disapproveByEventid(@PathVariable Long eventid){
        return ResponseEntity.ok(eventservice.disapproveByEventid(eventid));
    }

    @GetMapping (value = "/event/available/{eventdate}")
    public ResponseEntity findAllByEventdateAfter(@PathVariable String eventdate){
        return ResponseEntity.ok(eventservice.findAllByEventdateAfter(LocalDate.parse(eventdate))) ;
    }

    @GetMapping (value = "/event/available/inDate/{eventdate}")
    public ResponseEntity findAllByEventdate(@PathVariable String eventdate){
        return ResponseEntity.ok(eventservice.findAllByEventdate(LocalDate.parse(eventdate)));
    }

    @GetMapping (value = "/useraccess/eventgender/{genderevent}")
    public ResponseEntity findAllByGenderevent(@PathVariable String genderevent){
        return ResponseEntity.ok(eventservice.findAllByGenderevent(genderevent));
    }

    @PreAuthorize("(hasRole('ADMIN'))")
    @GetMapping (value = "/event/delete/eventdelete")
    public List<EntityEvent> findAllByEdeleteTrue(){
        return eventservice.findAllByEdeleteTrue();
    }

    @PreAuthorize("(hasRole('ADMIN'))")
    @GetMapping (value = "/event/undelete/eventdelete")
    public List<EntityEvent> findAllByEdeleteFalse(){ return eventservice.findAllByEdeleteFalse();}

    @PreAuthorize("(hasRole('ADMIN'))")
    @GetMapping (value = "/event/notapprove")
    public List<EntityEvent> findAllByApprovalFalse(){
        return eventservice.findAllByApprovalFalse();
    }

    @PreAuthorize("(hasRole('ADMIN'))")
    @GetMapping (value = "/adminaccess/event/approve")
    public List<EntityEvent> findAllByApprovalTrue(){
        return eventservice.findAllByApprovalTrue();
    }

    @GetMapping(value = "/event/avtivete")
    public List<EntityEvent> getAlleventActive(){
        return eventservice.getAlleventActive();
    }

    @GetMapping(value = "/event/khalid")
    public List<EntityEvent> khalid(){
        return eventservice.khalid();
    }

    @GetMapping(value = "/myevents/{Orgnizerid}")
    public List<EventDTO> findMyEvent(@PathVariable Long Orgnizerid) {
        return eventservice.findMyEvent(Orgnizerid);
    }
//
//    @GetMapping(value = "/khaled/{eid}||{ename}||{etybe}||{gender}||{city}||{date}")
//    public EntityEvent serch(){ return eventservice.search(eid,ename,etybe,gender,city,date);}
}
