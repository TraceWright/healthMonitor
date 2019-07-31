package io.api.egfr;

import java.util.ArrayList;
import javax.validation.constraints.NotNull;

public class EgfrDrop {
    
    @NotNull
    private ArrayList<Egfr> egfrLatest;
    private Egfr egfr;
    private Boolean hasDropped;
    private Float maxPercentageDrop;
    
    public EgfrDrop(ArrayList<Egfr> egfrLatest, Egfr egfr, Boolean hasDropped, Float maxPercentageDrop) {
        this.egfrLatest = egfrLatest;
        this.egfr = egfr;
        this.hasDropped = hasDropped;
        this.maxPercentageDrop = maxPercentageDrop;
    }
    
    public ArrayList<Egfr> getEgfrLatest() {
        return egfrLatest;
    }
    
    public Egfr getEgfr() {
        return egfr;
    }
    
    public Boolean getHasDropped() {
        return hasDropped;
    }

    public Float getMaxPercentageDrop() {
        return maxPercentageDrop;
    }
}