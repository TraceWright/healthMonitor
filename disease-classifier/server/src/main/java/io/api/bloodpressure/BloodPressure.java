package io.api.bloodpressure;

import java.util.Date;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import org.hibernate.validator.constraints.Range;

public class BloodPressure {

    @Range(min = 1, max = 500, message = "{validation.input.generic}")
    private final short sysBp;

    @Range(min = 1, max = 500, message = "{validation.input.generic}")
    private final short diaBp;

    @NotNull
    @PastOrPresent(message = "{validation.date.pastorpresent}")
    private final Date atDate;
    
    public BloodPressure(short sysBp, short diaBp, Date atDate, byte classification) {
        this.sysBp = sysBp;
        this.diaBp = diaBp;
        this.atDate = atDate;
    }

    public short getSysBp() {
        return sysBp;
    }

    public short getDiaBp() {
        return diaBp;
    }

    public Date getAtDate() {
        return atDate;
    }

    public byte getClassification() {
        byte classification;
        if (sysBp >= 180 || diaBp >= 120) {
            classification = 3;
        } else if (sysBp >= 160 || diaBp >= 100) {
            classification = 2;
        } else if (sysBp >= 140 || diaBp >= 90) {
            classification = 1;
        } else {
            classification = 0;
        }
        return classification; 
    }
}