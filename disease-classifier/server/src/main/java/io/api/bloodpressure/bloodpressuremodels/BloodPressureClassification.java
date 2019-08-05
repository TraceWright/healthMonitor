package io.api.bloodpressure.bloodpressuremodels;

import io.api.egfr.egfrmodels.Egfr;

public class BloodPressureClassification {
    
    private byte classification;
    
    public BloodPressureClassification() {}
    public BloodPressureClassification(Egfr egfr, byte classification) {
       this.classification = classification;
    }

    public byte getClassification() {
        return this.classification;
    }

    public void setClassification(byte classification) {
        this.classification = classification;
    }
   
}