package com.khaileid.Serviceimpl;

import com.khaileid.Entity.EntityComment;
import com.khaileid.Entity.EntityEvent;
import com.khaileid.Entity.EntityUsers;
import com.khaileid.Repository.RepositoryComment;
import com.khaileid.Repository.RepositoryEvent;
import com.khaileid.Repository.RepositoryUser;
import com.khaileid.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceimpl implements CommentService {

    @Autowired
    private RepositoryComment repositoryComment;
    @Autowired
    private RepositoryEvent repositoryEvent;
    @Autowired
    private RepositoryUser repositoryUser;
    @Override
    public List<EntityComment> findAll() {
        List<EntityComment>entityComments= new ArrayList<>();
        this.repositoryComment.findAll().forEach(entityComments::add);
        return entityComments;
    }


    @Override
    public ResponseEntity addComment(EntityComment entityComment, Long eventid, Long userid) {
        EntityUsers entityUsers = repositoryUser.findById(userid).get();
        EntityEvent entityEvent = repositoryEvent.findById(eventid).get();
        long counter = repositoryComment.countByEventidAndUseridAndDateTimeAfter(entityEvent,entityUsers,LocalDateTime.now().minusSeconds(10));
        if(counter==0) {
            entityComment.setEventid(entityEvent);
            entityComment.setUserid(entityUsers);
            entityComment.setDateTime(LocalDateTime.now());
            entityEvent.getComments().add(entityComment);
            entityComment.setEventname(entityEvent.getNameevent());
            entityComment.setUsername(entityUsers.getUsername());
            return  ResponseEntity.ok(repositoryComment.save(entityComment));
        }
        return new ResponseEntity("please waite 10Sec to comment again", HttpStatus.BAD_REQUEST);


    }

    @Override
    public List<EntityComment> findAllCommentByEventid(Long eventid) {
        return repositoryComment.findAllByEventid(repositoryEvent.findByEventid(eventid));
    }

    @Override
    public List<EntityComment> findAllCommentByUserid(Long userid) {
        return repositoryComment.findByUseridUserid(userid);
    }

    @Override
    public EntityComment findById(Long commentid) {
        return repositoryComment.findByCommentid(commentid);
    }
}
