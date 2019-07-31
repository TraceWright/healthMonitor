package io.api.egfr;

import java.util.ArrayList;
// import java.util.Iterator;

import javax.validation.constraints.NotNull;

public class EgfrQueryDrop {
    
    private static final int _20 = 20;
    @NotNull
    // private Iterator<Egfr> egfrLatest;
    private ArrayList<Egfr> egfrLatest;
    private Egfr egfr;
    private Boolean hasDropped;
    private Float maxPercentageDrop;
    
    // public EgfrQueryDrop(Iterator<Egfr> egfrLatest, Egfr egfr, Boolean hasDropped, Float maxPercentageDrop) {
    public EgfrQueryDrop(ArrayList<Egfr> egfrLatest, Egfr egfr, Boolean hasDropped, Float maxPercentageDrop) {
        this.egfrLatest = egfrLatest;
        this.egfr = egfr;
        this.hasDropped = hasDropped;
        this.maxPercentageDrop = maxPercentageDrop;
    }
    
    // returns empty list/iterator
    // public Iterator<Egfr> getEgfrLatest() {
        // while (egfrLatest.hasNext()) {
        //     Float e_egfr = egfr.getEgfr();
        //     Float el_egfr = egfrLatest.next().getEgfr();
        //     Float percentageDrop = (el_egfr - e_egfr) / el_egfr * ((float)100);
        //     if (percentageDrop >= _20) {
        //         this.setHasDropped(true);
        //         if (percentageDrop > this.maxPercentageDrop) {
        //             this.setMaxPercentageDrop(percentageDrop);
        //         }
        //     }
        // }
    //     return egfrLatest;
    // }
    
    public ArrayList<Egfr> getEgfrLatest() {

        // this method evaluates the maxPercentageDrop between the current egfr and
        // the highest egfr for the latest date recorded
        // requires list order by DESC
        if (egfrLatest.size() > 0) {
            Float e_egfr = egfr.getEgfr();
            Float el_egfr = egfrLatest.get(0).getEgfr(); // eval highest egfr only
            Float percentageDrop = (el_egfr - e_egfr) / el_egfr * ((float)100);
            if (percentageDrop >= _20) {
                this.setHasDropped(true);
                this.setMaxPercentageDrop(percentageDrop);
            }
        }

        // // evaluates maxPercentageDrop for all records for queried day
        // egfrLatest.forEach((el) -> {
        //     Float e_egfr = egfr.getEgfr();
        //     Float el_egfr = el.getEgfr();
        //     Float percentageDrop = (el_egfr - e_egfr) / el_egfr * ((float)100);
        //     if (percentageDrop >= _20) {
        //         this.hasDropped = true;
        //         if (percentageDrop > this.maxPercentageDrop) {
        //             this.maxPercentageDrop = percentageDrop;
        //         }
        //     }
        // });

        return egfrLatest;
    }
    
    public Egfr getEgfr() {
        return egfr;
    }

    public void setHasDropped(Boolean hasDropped) {
        this.hasDropped = hasDropped;
    }
    
    public Boolean getHasDropped() {
        return hasDropped;
    }

    public void setMaxPercentageDrop(Float maxPercentageDrop) {
        this.maxPercentageDrop = maxPercentageDrop;
    }

    public Float getMaxPercentageDrop() {
        return maxPercentageDrop;
    }
}