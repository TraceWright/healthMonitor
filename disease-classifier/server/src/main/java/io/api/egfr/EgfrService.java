package io.api.egfr;

import java.util.ArrayList;
import org.springframework.stereotype.Service;

@Service
public class EgfrService {

    // this method evaluates the maxDailyPctDrop between the current egfr and
    // the highest egfr for the latest date, which will flag any egfr drop >= 20
    // for all records on that day (or days if the latest records are for a past day)
    
    // requires list order by DESC

    public EgfrDrop EvalEgfrDropService(ArrayList<Egfr> egfrLatest, Egfr egfr)
    {
        Boolean hasDropped20Pct = false;
        Float maxDailyPctDrop = (float)0;
        if (egfrLatest.size() > 0) {
            Float e_egfr = egfr.getEgfr();
            Float el_egfr = egfrLatest.get(0).getEgfr(); // eval highest egfr only
            Float percentageDrop = (el_egfr - e_egfr) / el_egfr * ((float)100);
            if (percentageDrop >= 20) {
                hasDropped20Pct = true;
            }
            if (percentageDrop > maxDailyPctDrop) {
                maxDailyPctDrop = percentageDrop;
            }
        }

        return new EgfrDrop(egfrLatest, egfr, hasDropped20Pct, maxDailyPctDrop);
    }

}
