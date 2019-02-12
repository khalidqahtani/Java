package com.khaileid.DTO;

import com.khaileid.Entity.EntityTicket;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class RateDTO {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(1)
    @Max(5)
    private long attenderRate;
    private long counter=0;
    private EntityTicket tid;
    private boolean rated;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getAttenderRate() {
        return attenderRate;
    }

    public void setAttenderRate(long attenderRate) {
        this.attenderRate = attenderRate;
    }

    public EntityTicket getTid() {
        return tid;
    }

    public void setTid(EntityTicket tid) {
        this.tid = tid;
    }

    public boolean isRated() {
        return rated;
    }

    public void setRated(boolean rated) {
        this.rated = rated;
    }

    public long getCounter() {
        return counter;
    }

    public void setCounter(long counter) {
        this.counter = counter;
    }
}
