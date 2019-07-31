package io.api.egfr;

import java.util.ArrayList;
import javax.validation.constraints.NotNull;

public class EgfrDrop {
    
    @NotNull
    private ArrayList<Egfr> egfrLatest;
    private Egfr egfr;
    private Boolean hasDropped20Pct;
    private Float maxDailyPctDrop;
    
    public EgfrDrop(ArrayList<Egfr> egfrLatest, Egfr egfr, Boolean hasDropped20Pct, Float maxDailyPctDrop) {
        this.egfrLatest = egfrLatest;
        this.egfr = egfr;
        this.hasDropped20Pct = hasDropped20Pct;
        this.maxDailyPctDrop = maxDailyPctDrop;
    }
    
    public ArrayList<Egfr> getEgfrLatest() {
        return egfrLatest;
    }
    
    public Egfr getEgfr() {
        return egfr;
    }
    
    public Boolean getHasDropped20Pct() {
        return hasDropped20Pct;
    }

    public Float getMaxDailyPctDrop() {
        return maxDailyPctDrop;
    }
}