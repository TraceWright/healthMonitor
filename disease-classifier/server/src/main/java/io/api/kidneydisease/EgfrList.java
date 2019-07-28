package io.api.kidneydisease;

import java.util.List;
import javax.validation.constraints.NotNull;

public class EgfrList {

    @NotNull
    private final List<Egfr> egfr;
    public EgfrList(List<Egfr> egfr) {
        this.egfr = egfr;
    }
    public List<Egfr> getEgfrList() {
        return egfr;
    }
}
