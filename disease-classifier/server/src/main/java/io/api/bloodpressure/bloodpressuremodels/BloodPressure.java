package io.api.bloodpressure.bloodpressuremodels;

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
    
    public BloodPressure(short sysBp, short diaBp, Date atDate) {
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

}