package com.khaileid.Service;

import com.khaileid.Entity.EntittRate;
import com.khaileid.Entity.EntityTicket;

import java.util.List;

public interface RateService {

     List<EntittRate> getAllusers();
     List<EntittRate> findAllrateForticket(Long entityTicket);
     List<EntittRate> findById(Long attenderRate);
     EntittRate reviewEvent(Long tid, int rate);
}
