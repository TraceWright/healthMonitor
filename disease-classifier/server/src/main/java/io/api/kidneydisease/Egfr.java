package io.api.kidneydisease;

import java.util.List;

import javax.validation.constraints.NotNull;

public class Egfr {

    @NotNull
    private final List<EgfrItem> egfr;
    public Egfr(List<EgfrItem> egfr) {
        this.egfr = egfr;
    }
    public List<EgfrItem> getEgfrList() {
        return egfr;
    }
}
