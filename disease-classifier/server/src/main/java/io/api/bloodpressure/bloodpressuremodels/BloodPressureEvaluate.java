package io.api.bloodpressure.bloodpressuremodels;

import javax.validation.constraints.NotNull;
import io.api.bloodpressure.bloodpressuremodels.BloodPressure;

public class BloodPressureEvaluate {
    
    @NotNull
    private BloodPressure bp;
    private byte classification;
    
    public BloodPressureEvaluate(BloodPressure bp, byte classification) {
        this.bp = bp;
        this.classification = classification;
    }
    
    public BloodPressure getBp() {
        return bp;
    }

    public byte getClassification() {
        return classification;
    }
}