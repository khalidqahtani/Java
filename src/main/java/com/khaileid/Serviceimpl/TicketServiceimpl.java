package com.khaileid.Serviceimpl;

import com.khaileid.DTO.EventDTO;
import com.khaileid.DTO.TicketDTO;
import com.khaileid.Entity.EntityEvent;
import com.khaileid.Entity.EntityTicket;
import com.khaileid.Entity.EntityUsers;
import com.khaileid.Repository.RepositoryEvent;
import com.khaileid.Repository.RepositoryTicket;
import com.khaileid.Repository.RepositoryUser;
import com.khaileid.Service.NotificationService;
import com.khaileid.Service.TicketService;
import com.khaileid.config.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TicketServiceimpl implements TicketService {


    @Autowired
    private RepositoryTicket repositoryTicket;
    @Autowired
    private RepositoryUser repositoryUser;
    @Autowired
    private RepositoryEvent repositoryEvent;
    @Autowired
    private NotificationService notificationService;


    @Override
    public List<EntityTicket> getAlltickit() {
        return repositoryTicket.findAll();
    }

    @Override
    public List<EntityTicket> findAllTicketByUser(Long uid) {
        return repositoryTicket.findAllByUidAndTicketcancelFalse(repositoryUser.findByUserid(uid));
    }

    @Override
    public List<EntityTicket> findAllByEidEventid(Long eid) {
        return repositoryTicket.findAllByEidEventid(eid);
    }

    @Override
    public List<EntityTicket> findAllTicketprepresentByEvent(Long eid) {
        return repositoryTicket.findAllByEidAndUserpresentTrueAndTicketcancelFalse(repositoryEvent.findByEventid(eid));

    }

    @Override
    public List<EntityTicket> findfindAllTicketnonprepresentByEvent(Long eid) {
        return repositoryTicket.findAllByEidAndUserpresentFalseAndTicketcancelFalse(repositoryEvent.findByEventid(eid));
    }

    @Override
    public List<EntityTicket> findAllTicketcancelByEvent(Long eid) {
        return repositoryTicket.findAllByEidAndUserpresentFalseAndTicketcancelTrue(repositoryEvent.findByEventid(eid));
    }



    @Override
    public EntityTicket findById(Long ticketid) {

        return repositoryTicket.findById(ticketid).get();
    }
//
//    @Override
//    public ResponseEntity addUser(EntityTicket entityTicket, Long uid, Long eid) {repositoryTicket.save(entityTicket); }

    @Override
    public ResponseEntity addTicket(Long eid, Long uid) {
        EntityTicket entityTicket = new EntityTicket();
        EntityEvent entityEvent = (repositoryEvent.findById(eid).get());
        EntityUsers entityUsers = (repositoryUser.findById(uid).get());
        entityEvent.setCapacity(entityEvent.getCapacity());
        Long counterbook = repositoryTicket.countByEidAndUidAndDateAfter(entityEvent,entityUsers,LocalDate.now().minusDays(1));
        if (entityEvent.getCounter() < entityEvent.getCapacity()&& entityEvent.isApproval() && !entityEvent.isEdelete() && !entityUsers.isDeleted() && entityEvent.getEventdate().isAfter(LocalDate.now()) && counterbook==0 ){
            entityTicket.setEid(repositoryEvent.findById(eid).get());
            entityTicket.setUid(repositoryUser.findById(uid).get());
            entityTicket.setDate(LocalDate.now());
            entityEvent.setCounter(1+entityEvent.getCounter());
            entityEvent.setAvailable(entityEvent.getCapacity()-entityEvent.getCounter());
            entityTicket.setEventname(repositoryEvent.findByEventid(eid).getNameevent());
            entityTicket.setDateevent(repositoryEvent.findByEventid(eid).getEventdate());
            entityTicket.setTimeevent(entityEvent.getEventtime());
            repositoryTicket.save(entityTicket);
            notificationService.notificationBookTicket(eid, uid);
            return new ResponseEntity("Book Ticket",HttpStatus.ACCEPTED);

        }else {
            return null;
        }
    }

    @Override
    public ResponseEntity updateTicket(EntityTicket entityTicket, Long ticketid){
        entityTicket.setTicketid(ticketid);
        repositoryTicket.save(entityTicket);
        return new ResponseEntity("Ticket Has Update",HttpStatus.ACCEPTED);

    }

    @Override
    public ResponseEntity TicketcancelByTicketid(Long ticketid) {
       EntityTicket entityTicket= new EntityTicket();
       entityTicket=repositoryTicket.findById(ticketid).get();
       entityTicket.setTicketcancel(true);
       EntityEvent entityEvent=repositoryTicket.findById(ticketid).get().getEid();
       entityEvent.setCounter(entityEvent.getCounter()-1);
       entityEvent.setAvailable(1+entityEvent.getAvailable());
       repositoryTicket.save(entityTicket);
       notificationService.notificationCancelTicket(ticketid);

       return new ResponseEntity("Ticket Has Deleted",HttpStatus.ACCEPTED);
    }

    @Override
    public List<EntityTicket> findAllDeleted() {
        List<EntityTicket> entityTickets = new ArrayList<>();
        for (EntityTicket entityTicket : repositoryTicket.findAll()) {
            if (entityTicket.isTicketcancel())
            {
                entityTickets.add(entityTicket);
            }
        }
        return entityTickets;
    }

    @Override
    public ResponseEntity UserpresentById(Long ticketid) {
        EntityTicket entityTicket= repositoryTicket.findById(ticketid).get();
        entityTicket.setUserpresent(true);
        repositoryTicket.save(entityTicket);
        return new ResponseEntity("User Has Present",HttpStatus.ACCEPTED);

    }

    @Override
    public ResponseEntity unUserpresentByTicketid(Long ticketid) {
    EntityTicket entityTicket = repositoryTicket.findById(ticketid).get();
    entityTicket.setUserpresent(false);
    repositoryTicket.save(entityTicket);
    return new ResponseEntity("User Has UnPresent",HttpStatus.ACCEPTED);

    }


    @Override
    public ResponseEntity UnTicketcancelByTicketid(Long ticketid) {
    EntityTicket entityTicket = repositoryTicket.findById(ticketid).get();
    entityTicket.setTicketcancel(false);
    repositoryTicket.save(entityTicket);
        return new ResponseEntity("User Has UnCancel",HttpStatus.ACCEPTED);

    }

    @Override
    public List<EntityTicket> findAllByUserpresentTrue() {
        return repositoryTicket.findAllByUserpresentTrue();
    }

    @Override
    public List<EntityTicket> findAllByUserpresentFalse() {
        return repositoryTicket.findAllByUserpresentFalse();
    }

    @Override
    public List<EntityTicket> findAllByTicketcancelTrue() {
        return repositoryTicket.findAllByTicketcancelTrue();
    }

    @Override
    public List<EntityTicket> findAllByTicketcancelFalse() {
        return repositoryTicket.findAllByTicketcancelFalse();
    }

    @Override
    public List<TicketDTO> findallticketbyevent(Long eid) {
        EntityEvent entityEvent= repositoryEvent.findByEventid(eid);
        List<EntityTicket> entityTickets= repositoryTicket.findByEid(entityEvent);
        List<TicketDTO> ticketdto = ObjectMapperUtils.mapAll(entityTickets,TicketDTO.class);
        return ticketdto;
    }
}
