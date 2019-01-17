package com.khaileid.Service;

import com.khaileid.Entity.EntityComment;
import com.khaileid.Entity.EntityEvent;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CommentService {

    ResponseEntity addComment(EntityComment entityComment, Long eventid, Long userid);
    List<EntityComment> findAll();
    List<EntityComment> findAllCommentByEventid(Long eventid);
    List<EntityComment> findAllCommentByUserid(Long userid);
    EntityComment findById(Long commentid);


}
