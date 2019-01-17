package com.khaileid.Entity;

import javax.persistence.*;

@Entity
@Table (name = "Rate")
public class EntittRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private long attenderRate;
    private boolean rated;
    //**Relation**//
    @ManyToOne
    private EntityTicket tid;



    //**Setter and Getter**//


    public EntityTicket getTid() {
        return tid;
    }

    public void setTid(EntityTicket tid) {
        this.tid = tid;
    }

    public long getAttenderRate() {
        return attenderRate;
    }

    public void setAttenderRate(long attenderRate) {
        this.attenderRate = attenderRate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isRated() {
        return rated;
    }

    public void setRated(boolean rated) {
        this.rated = rated;
    }
}
