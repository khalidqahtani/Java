package com.khaileid.Service;

import com.khaileid.DTO.EventDTO;
import com.khaileid.Entity.EntityEvent;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface EventService {


     List<EntityEvent> getAllevent();
     List<EntityEvent> findByEventcity(String eventcity);
     List<EntityEvent> findByEventcityAndEventdate(String eventcity,LocalDate eventdate);
     List<EntityEvent> getAlleventActive();
     List<EntityEvent> NewEvent();
     List<EntityEvent> trending();

     EventDTO findByEventid(Long eventid);
//     EventDTO countByLikecounter();
     ResponseEntity addEvent(EventDTO eventDTO, Long uid);
     ResponseEntity updateEvent(EventDTO eventDTO, Long eventid);

     ResponseEntity deletByEventid(Long eventid);
     ResponseEntity unDeletByEventid(Long eventid);
     ResponseEntity approvalByEventid(Long eventid);
     ResponseEntity disapproveByEventid(Long eventid);

     // FIXME: 12/1/2018
     // Search for event available in date after you chose
     List<EntityEvent> findAllByEventdateAfter (LocalDate eventdate);


     // FIXME: 12/1/2018
     // Search for event available in date your chose
     List<EntityEvent> findAllByEventdate (LocalDate eventdate);

     List<EntityEvent> findAllByGenderevent(String genderevent);
     List<EntityEvent> findAllByEdeleteTrue();
     List<EntityEvent> findAllByEdeleteFalse();
     List<EntityEvent> findAllByApprovalFalse();
     List<EntityEvent> findAllByApprovalTrue();
//     List<EntityEvent> findAllCounter();
     List <EventDTO> findMyEvent (Long Orgnizerid);

     EntityEvent search (Long eid, String ename, String etybe, String gender, String city, LocalDate date);


}
