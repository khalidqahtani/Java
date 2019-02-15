package com.khaileid.Serviceimpl;

import com.khaileid.DTO.EventDTO;
import com.khaileid.Entity.EntityEvent;
import com.khaileid.Entity.EntityUsers;
import com.khaileid.Repository.RepositoryEvent;
import com.khaileid.Repository.RepositoryTicket;
import com.khaileid.Repository.RepositoryUser;
import com.khaileid.Service.EventService;
import com.khaileid.config.ObjectMapperUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventServiceimpl implements EventService {

    @Autowired
    private RepositoryEvent repositoryevent;
    @Autowired
    private RepositoryUser repositoryUser;
    @Autowired
    private RepositoryTicket repositoryTicket;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<EntityEvent> getAllevent() {
        List<EntityEvent> entityEvents = repositoryevent.findAll();

        return entityEvents;
    }


    @Override
    public List<EntityEvent> findByEventcity(String eventcity) {
        LocalDate localDate= LocalDate.now();
        return repositoryevent.findAllByEventdateAfterAndEventcityAndApprovalTrueAndEdeleteFalse(localDate,eventcity);
    }

    @Override
    public List<EntityEvent> findByEventcityAndEventdate(String eventcity, LocalDate eventdate) {
        LocalDate localDate= LocalDate.now();
        return repositoryevent.findAllByEventcityAndEventdateAndApprovalTrueAndEdeleteFalse(eventcity,eventdate);
    }

    @Override
    public EventDTO findByEventid(Long eventid) {
        EntityEvent entityEvent = repositoryevent.findByEventid(eventid);
        EventDTO eventDTO = modelMapper.map(entityEvent,EventDTO.class);
        return eventDTO;
    }

    @Override
    public ResponseEntity addEvent(EventDTO eventDTO, Long uid) {
        EntityEvent entityEvent= new EntityEvent();
        EntityUsers entityUsers = repositoryUser.findById(uid).get();
        entityEvent= modelMapper.map(eventDTO, EntityEvent.class);
        if (entityEvent.getEventdate().isAfter(LocalDate.now().minusDays(1))) {
            entityEvent.setOrgnizerID(repositoryUser.findByUserid(uid));
            entityEvent.setAvailable(entityEvent.getCapacity());

            repositoryevent.save(entityEvent);
            return new ResponseEntity("Event Has ADD",HttpStatus.ACCEPTED);
        }
            return new ResponseEntity("EventDate you insert Has old date",HttpStatus.BAD_REQUEST);

    }

    @Override
    public ResponseEntity updateEvent(EventDTO eventDTO, Long eventid) {
        EntityEvent entityEvent= new EntityEvent();
        EntityEvent entityEvent1 = repositoryevent.findByEventid(eventid);
        entityEvent= modelMapper.map(eventDTO, EntityEvent.class);
        entityEvent.setEventid(eventid);
        entityEvent.setOrgnizerID(entityEvent1.getOrgnizerID());
        repositoryevent.save(entityEvent);
        return new ResponseEntity("Event Has Updated",HttpStatus.ACCEPTED);

    }



    @Override
    public ResponseEntity deletByEventid(Long eventid) {
        EntityEvent entityEvent = repositoryevent.findByEventid(eventid);
        entityEvent.setEdelete(true);
        entityEvent.setApproval(false);
        repositoryevent.save(entityEvent);
        return new ResponseEntity("Event Has DELETED", HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity unDeletByEventid(Long eventid) {
        EntityEvent entityEvent = repositoryevent.findByEventid(eventid);
        entityEvent.setEdelete(false);
        repositoryevent.save(entityEvent);
        return new ResponseEntity("Event Has UnDELETED", HttpStatus.ACCEPTED);

    }

    @Override
    public ResponseEntity approvalByEventid(Long eventid) {
        EntityEvent entityEvent = repositoryevent.findByEventid(eventid);
        entityEvent.setApproval(true);
        repositoryevent.save(entityEvent);
        return new ResponseEntity("Event Has approve", HttpStatus.ACCEPTED);

    }

    @Override
    public ResponseEntity disapproveByEventid(Long idevent) {
        EntityEvent entityEvent = repositoryevent.findById(idevent).get();
        entityEvent.setApproval(false);
        repositoryevent.save(entityEvent);
        return new ResponseEntity("Event Has UnaApprove", HttpStatus.ACCEPTED);

    }


    @Override
    public List<EntityEvent> findAllByEventdateAfter(LocalDate eventdate) {
        LocalDate localDate = LocalDate.now();
        return repositoryevent.findAllByEdeleteFalseAndApprovalTrueAndEventdateAfter(localDate);
    }

    @Override
    public List<EntityEvent> findAllByEventdate(LocalDate eventdate) {
        LocalDate localDate=LocalDate.now();
        return repositoryevent.findAllByEdeleteFalseAndApprovalTrueAndEventdate(localDate);
    }

    @Override
    public List<EntityEvent> findAllByGenderevent(String genderevent) {
        LocalDate localDate = LocalDate.from(LocalDate.now());
        return repositoryevent.findAllByEventdateAfterAndGendereventAndEdeleteFalseAndApprovalTrue(localDate,genderevent);
    }


    @Override
    public List<EntityEvent> findAllByEdeleteTrue() {
        return repositoryevent.findAllByEdeleteTrue();
    }

    @Override
    public List<EntityEvent> findAllByEdeleteFalse() {
        return repositoryevent.findAllByEdeleteFalse();
    }

    @Override
    public List<EntityEvent> findAllByApprovalFalse() {
        return repositoryevent.findAllByApprovalFalse();
    }

    @Override
    public List<EntityEvent> findAllByApprovalTrue() {
        return repositoryevent.findAllByApprovalTrue();
    }

    @Override
    public List<EntityEvent>getAlleventActive() {
        LocalDate localDate= LocalDate.now().minusDays(1);
        return repositoryevent.findAllByEdeleteFalseAndApprovalTrueAndEventdateAfter(localDate);
    }

    @Override
    public List<EntityEvent> Top3event() {
        LocalDate localDate= LocalDate.now().minusDays(1);
        return repositoryevent.findTop3ByEventdateAfterAndEdeleteFalseAndApprovalTrueOrderByEventidDesc(localDate);
    }

    @Override
    public List<EntityEvent> trending() {
        LocalDate localDate= LocalDate.now().minusDays(1);
        return repositoryevent.findTop3ByEventdateAfterAndEdeleteFalseAndApprovalTrueAndCounterGreaterThanOrderByCounterDesc(localDate,Long.valueOf(0));
    }
//
    @Override
    public List<EventDTO> findMyEvent(Long Orgnizerid) {
        EntityUsers entityUsers= repositoryUser.findByUserid(Orgnizerid);
        List <EntityEvent> entityEvents= repositoryevent.findByOrgnizerID(entityUsers);
        List<EventDTO> eventDTO = ObjectMapperUtils.mapAll(entityEvents,EventDTO.class);
        return eventDTO;


    }

    @Override
    public EntityEvent search(Long eid, String ename, String etybe, String gender, String city, LocalDate date) {
        return repositoryevent.findByEventidOrNameeventOrTybeeventOrGendereventOrEventcityOrEventdate(eid,ename,etybe,gender,city,date);
    }
}