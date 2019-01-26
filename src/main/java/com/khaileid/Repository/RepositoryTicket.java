package com.khaileid.Repository;

import com.khaileid.Entity.EntityEvent;
import com.khaileid.Entity.EntityTicket;
import com.khaileid.Entity.EntityUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RepositoryTicket extends JpaRepository<EntityTicket,Long> {

//    EntityEvent findByTicketid(Long ticketid);

    List<EntityTicket> findAllByUidAndTicketcancelFalse(EntityUsers entityUsers);
    List<EntityTicket> findAllByEid(EntityEvent entityEvent);
    List<EntityTicket> findAllByEidAndUserpresentTrueAndTicketcancelFalse(EntityEvent entityEvent);
    List<EntityTicket> findAllByEidAndUserpresentFalseAndTicketcancelTrue(EntityEvent entityEvent);
    List<EntityTicket> findAllByEidAndUserpresentFalseAndTicketcancelFalse(EntityEvent entityEvent);

    List<EntityTicket> findAllByUserpresentTrue();
    List<EntityTicket> findAllByUserpresentFalse();
    List<EntityTicket> findAllByTicketcancelTrue();
    List<EntityTicket> findAllByTicketcancelFalse();
    List<EntityTicket> findByEidAndTicketcancelFalse(EntityEvent entityEvent);
    EntityTicket findByUidAndEid (EntityUsers uid, EntityEvent eid);
    Long countByEidAndUidAndDateAfter (EntityEvent eid, EntityUsers uid, LocalDate DT);
    EntityTicket findByTicketid (Long ticketid);

    List<EntityTicket> findAllByEidEventid(Long eid);
    List<EntityTicket> findByEid(EntityEvent eid);





}
