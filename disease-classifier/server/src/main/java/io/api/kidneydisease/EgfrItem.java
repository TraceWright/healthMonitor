package io.api.kidneydisease;

import java.util.Date;

// import javax.validation.constraints.NotNull;
// import javax.validation.constraints.Size;

public class EgfrItem {

    private short egfr;
    private Date atDate;
    
    public EgfrItem() {}
    public EgfrItem(short egfr, Date atDate) {
        this.egfr = egfr;
        this.atDate = atDate;
    }

    public short getEgfr() {
        return egfr;
    }

    public Date getAtDate() {
        return atDate;
    }

}