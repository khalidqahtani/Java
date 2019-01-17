package com.khaileid.Repository;

import com.khaileid.Entity.EntityComment;
import com.khaileid.Entity.EntityEvent;
import com.khaileid.Entity.EntityUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.lang.ref.Reference;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RepositoryComment extends JpaRepository<EntityComment,String> {

    Long countByEventidAndUseridAndDateTimeAfter(EntityEvent eid, EntityUsers uid, LocalDateTime DT);
    List<EntityComment> findAllByEventid(EntityEvent entityEvent);
    List<EntityComment> findAllByUserid(EntityUsers entityUsers);
    List<EntityComment> findByUseridUserid(Long uid);

    EntityComment findByCommentid (Long commentid);
}
