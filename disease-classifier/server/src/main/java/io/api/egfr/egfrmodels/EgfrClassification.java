package io.api.egfr.egfrmodels;

import io.api.egfr.Egfr;

public class EgfrClassification {
    
    private byte classification;
    
    public EgfrClassification() {}
    public EgfrClassification(Egfr egfr, byte classification) {
       this.classification = classification;
    }

    public byte getClassification() {
        return this.classification;
    }

    public void setClassification(byte classification) {
        this.classification = classification;
    }
   
}