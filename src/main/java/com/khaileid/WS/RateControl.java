package com.khaileid.WS;

import com.khaileid.Entity.EntittRate;
import com.khaileid.Service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RateControl {

    @Autowired
    private RateService rateService;


    @GetMapping (value = "/adminaccess/rate")
    public List<EntittRate> getAllusers(){
        return this.rateService.getAllusers();
    }

    //View the rate from search
    @PreAuthorize("(hasRole('ADMIN'))")
    @GetMapping (value = "/rate/by/{attenderRate}")
    public List<EntittRate> findById(@Valid @PathVariable Long rate5) {
        return rateService.findById(rate5);}

    //user reate the event from the ticket
    @PreAuthorize("(hasRole('USER'))")
    @GetMapping(value = "/rate/{tid}/{rate}")
    public EntittRate reviewEvent(@Valid @PathVariable Long tid, @PathVariable int rate){
        return rateService.reviewEvent(tid,rate);
    }

    @GetMapping(value = "/allrate/forticket/{tid}")
    public List<EntittRate> findAllrateForticket(@PathVariable Long tid) {
        return rateService.findAllrateForticket(tid);
    }

    @GetMapping(value = "/rated/{id}")
    public String getAverageAge(@PathVariable Long id){
        return rateService.avg(id);
    }
}

