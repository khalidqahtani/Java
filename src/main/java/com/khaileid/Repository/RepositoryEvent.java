package com.khaileid.Repository;

import com.khaileid.DTO.EventDTO;
import com.khaileid.Entity.EntityEvent;
import com.khaileid.Entity.EntityUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RepositoryEvent extends JpaRepository<EntityEvent,Long> {

    EntityEvent findByEventid(Long eventid);
//    EntityEvent countByLikecounter();
    List<EntityEvent> findAllByEventdateAfterAndEventcityAndApprovalTrueAndEdeleteFalse(LocalDate eventdate, String eventcity);
    List<EntityEvent> findAllByEventcityAndEventdateAndApprovalTrueAndEdeleteFalse(String eventcity,LocalDate eventdate);
    EntityEvent findByEventidOrNameeventOrTybeeventOrGendereventOrEventcityOrEventdate(Long eid, String ename, String etybe, String gender, String city, LocalDate date);
    List<EntityEvent> findAllByEdeleteFalseAndApprovalTrueAndEventdateAfter(LocalDate eventdate);
    List<EntityEvent> findAllByEdeleteFalseAndApprovalTrueAndEventdate(LocalDate eventdate);
    List<EntityEvent> findAllByEdeleteTrue();
    List<EntityEvent> findAllByEdeleteFalse();
    List<EntityEvent> findAllByApprovalFalse();
    List<EntityEvent> findAllByApprovalTrue();
    List<EntityEvent> findAllByEventdateAfterAndGendereventAndEdeleteFalseAndApprovalTrue(LocalDate eventdate ,String genderevent);
    List<EntityEvent> findByOrgnizerID(EntityUsers entityUsers);





}
