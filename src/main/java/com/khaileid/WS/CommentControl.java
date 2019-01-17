package com.khaileid.WS;

import com.khaileid.Entity.EntityComment;
import com.khaileid.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentControl {

    @Autowired
    private CommentService commentService;

    @GetMapping(value = "/allcomment")
    public List<EntityComment> findAll(){return commentService.findAll();}

    @PreAuthorize("(hasAnyRole('ADMIN','ORGANIZER','USER'))")
    @PostMapping(value = "/comment/{eventid}/{userid}")
    public ResponseEntity addComment(@Valid @RequestBody EntityComment entityComment, @PathVariable Long eventid, @PathVariable Long userid){
       return commentService.addComment(entityComment,eventid,userid);
    }

    // FIXME: 12/2/2018
    @GetMapping(value = "/comment/buevents/{eventid}")
    public List<EntityComment> findAllCommentByEventid(@PathVariable Long eventid ){
        return commentService.findAllCommentByEventid(eventid);
    }

    @GetMapping(value = "/comment/byuser/{userid}")
    public List<EntityComment> findAllCommentByUserid(@PathVariable Long userid){
        return commentService.findAllCommentByUserid(userid);
    }

    @GetMapping (value = "/comment/by/{commentid}")
    public EntityComment findById(@Valid @PathVariable Long commentid) {return commentService.findById(commentid);}
}
