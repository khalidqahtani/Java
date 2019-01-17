package com.khaileid.Serviceimpl;

import com.khaileid.Entity.EntittRate;
import com.khaileid.Entity.EntityTicket;
import com.khaileid.Repository.RepositoryRate;
import com.khaileid.Repository.RepositoryTicket;
import com.khaileid.Service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RateServiceimpl implements RateService {

    @Autowired
    private RepositoryRate repositoryRate;
    @Autowired
    private RepositoryTicket repositoryTicket;


    @Override
    public List<EntittRate> getAllusers() {
        List<EntittRate> entittRates = new ArrayList<>();
        this.repositoryRate.findAll().forEach(entittRates::add);
        return entittRates;
    }

    @Override
    public List<EntittRate> findById(Long attenderRate) {
        List<EntittRate> entittRates= new ArrayList<>();
       repositoryRate.findById(attenderRate).get();
       return entittRates;
    }


    @Override
    public EntittRate reviewEvent(Long tid, int rate) {
        EntittRate entittRate= new EntittRate();
        EntityTicket entityTicket= repositoryTicket.findById(tid).get();
        if(repositoryRate.countByTidTicketid(tid)==0) {
            if (entityTicket.isUserpresent() && !entityTicket.isTicketcancel()) {
                entityTicket.setTicketrate(rate);
                entittRate.setAttenderRate(rate);
                entittRate.setTid(entityTicket);
                entittRate.setRated(true);
//            entittRate.setAttenderRate(entittRate.getAttenderRate());
                repositoryRate.save(entittRate);
                return entittRate;
            }
        }
        return null;
    }

    @Override
    public List<EntittRate> findAllrateForticket(Long entityTicket) {
        return repositoryRate.findAllByTidTicketid(entityTicket);
    }

}
