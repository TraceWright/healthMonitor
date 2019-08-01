package io.api.egfr.egfrmodels;

import java.util.ArrayList;
import javax.validation.constraints.NotNull;

import io.api.egfr.egfrmodels.Egfr;
import io.api.egfr.egfrmodels.EgfrPercentageDrop;

public class EgfrEvaluate {
    
    @NotNull
    private ArrayList<Egfr> egfrLatest;
    private Egfr egfr;
    private EgfrPercentageDrop evaluatePercentageDrop;
    private byte classification;
    
    public EgfrEvaluate(ArrayList<Egfr> egfrLatest, Egfr egfr, EgfrPercentageDrop evaluatePercentageDrop, byte classification) {
        this.egfrLatest = egfrLatest;
        this.egfr = egfr;
        this.evaluatePercentageDrop = evaluatePercentageDrop;
        this.classification = classification;
    }
    
    public ArrayList<Egfr> getEgfrLatest() {
        return egfrLatest;
    }
    
    public Egfr getEgfr() {
        return egfr;
    }

    public EgfrPercentageDrop getEvaluatePercentageDrop() {
        return evaluatePercentageDrop;
    }

    public byte getClassification() {
        return classification;
    }
}