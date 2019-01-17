package com.khaileid.Service;

import com.khaileid.DTO.TicketDTO;
import com.khaileid.Entity.EntityTicket;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TicketService {

     List<EntityTicket> getAlltickit();
     EntityTicket findById(Long ticketid);

     List<EntityTicket> findAllTicketByUser(Long uid);
     List<EntityTicket> findAllByEidEventid(Long eid);
     List<EntityTicket> findAllTicketprepresentByEvent(Long eid);
     List<EntityTicket> findfindAllTicketnonprepresentByEvent(Long eid);
     List<EntityTicket> findAllTicketcancelByEvent(Long eid);



     EntityTicket addTicket(Long eid, Long uid);
     ResponseEntity updateTicket(EntityTicket entityTicket, Long ticketid);
     List<EntityTicket> findAllDeleted();
     ResponseEntity UserpresentById(Long ticketid);
     ResponseEntity unUserpresentByTicketid(Long ticketid);
     ResponseEntity TicketcancelByTicketid(Long ticketid);
     ResponseEntity UnTicketcancelByTicketid(Long ticketid);
     List<EntityTicket> findAllByUserpresentTrue();
     List<EntityTicket> findAllByUserpresentFalse();
     List<EntityTicket> findAllByTicketcancelTrue();
     List<EntityTicket> findAllByTicketcancelFalse();

}


