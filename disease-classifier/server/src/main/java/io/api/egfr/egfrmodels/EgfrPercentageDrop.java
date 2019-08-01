package io.api.egfr.egfrmodels;

public class EgfrPercentageDrop {
    
    private Boolean hasDropped20Pct = false;
    private Float maxDailyPctDrop = (float)0;
    
    public EgfrPercentageDrop() {}
    public EgfrPercentageDrop(Boolean hasDropped20Pct, Float maxDailyPctDrop) {
        this.hasDropped20Pct = hasDropped20Pct;
        this.maxDailyPctDrop = maxDailyPctDrop;
    }

    public Boolean getHasDropped20Pct() {
        return hasDropped20Pct;
    }

    public void setHasDropped20Pct(Boolean hasDropped20Pct) {
        this.hasDropped20Pct = hasDropped20Pct;
    }

    public Float getMaxDailyPctDrop() {
        return maxDailyPctDrop;
    }

    public void setMaxDailyPctDrop(Float maxDailyPctDrop) {
        this.maxDailyPctDrop = maxDailyPctDrop;
    }
}