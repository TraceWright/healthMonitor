package io.api.egfr;

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
    
    @Range(min = 1, max = 2000, message = "{validation.range}")
    private Float egfr;
    
    @PastOrPresent
    private Date atDate;
    
    public Egfr() {}
    public Egfr(Float egfr, Date atDate, byte classification, Boolean hasDropped) {
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

    public byte getClassification() {
        byte classification;
        if (egfr >= 90) {
            classification = 0;
        } else if (egfr >= 60) {
            classification = 1;
        } else if (egfr >= 45) {
            classification = 2;
        } else if (egfr >= 30) {
            classification = 3;
        } else if (egfr >= 15) {
            classification = 4;
        } else {
            classification = 5;
        }
        return classification; 
    }

}
