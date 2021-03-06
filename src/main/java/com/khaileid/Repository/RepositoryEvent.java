package com.khaileid.Repository;

import com.khaileid.DTO.EventDTO;
import com.khaileid.Entity.EntityEvent;
import com.khaileid.Entity.EntityUsers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
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
    List<EntityEvent> findLast3ByEventdateAfterAndEdeleteFalseAndApprovalTrue(LocalDate eventdate);
    List<EntityEvent> EventdateAfterAndEdeleteFalseAndApprovalTrue(LocalDate eventdate);
//    List<EntityEvent> getTop3ByEventdateAfterAndEdeleteFalseAndApprovalTrue(LocalDate eventdate);
//    Slice<EntityEvent> findFirst3EventdateOrderBy(LocalDate eventdate);
    List<EntityEvent> findTop3ByEventdateAfterAndEdeleteFalseAndApprovalTrueOrderByEventidDesc(LocalDate eventdate);

    List<EntityEvent> findTop3ByEventdateAfterAndEdeleteFalseAndApprovalTrueAndCounterGreaterThanOrderByCounterDesc(LocalDate eventdate,long c);





}
