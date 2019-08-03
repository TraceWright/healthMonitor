package io.api.egfr.egfrmodels;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.PastOrPresent;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.hibernate.validator.constraints.Range;

@Entity
public class Egfr {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Range(min = 1, max = 2000, message = "{validation.input.generic}")
    private Float egfr;
    
    @PastOrPresent(message = "{validation.date.pastorpresent}")
    private Date atDate;
    
    public Egfr() {}
    public Egfr(Float egfr, Date atDate) {
        this.egfr = egfr;
        this.atDate = atDate;
    }

    public Float getEgfr() {
        return egfr;
    }

    public String getAtDate() {
        SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd");
        return f.format(atDate);
    }

    public Integer getId() {
        return id;
    }

}
