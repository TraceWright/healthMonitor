package io.api.egfr;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.util.Date;
import javax.validation.constraints.NotNull;
// import javax.validation.constraints.Size;

@Entity
public class Egfr {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotNull(message = "Please provide an eGFR value")
    private Float egfr;
    private Date atDate;
    
    public Egfr() {}
    public Egfr(Float egfr, Date atDate) {
        this.egfr = egfr;
        this.atDate = atDate;
    }

    public Float getEgfr() {
        return egfr;
    }

    public Date getAtDate() {
        return atDate;
    }

    public Integer getId() {
        return id;
    }

}