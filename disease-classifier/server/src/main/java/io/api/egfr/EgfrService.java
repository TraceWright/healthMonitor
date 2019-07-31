package io.api.egfr;

import java.util.ArrayList;
import org.springframework.stereotype.Service;

@Service
public class EgfrService {

    public EgfrDrop getEgfrDropService(ArrayList<Egfr> egfrLatest, Egfr egfr)
    {
        Boolean hasDropped = false;
        Float maxPercentageDrop = (float)0;
        // this method evaluates the maxPercentageDrop between the current egfr and
        // the highest egfr for the latest date recorded
        // requires list order by DESC
        if (egfrLatest.size() > 0) {
            Float e_egfr = egfr.getEgfr();
            Float el_egfr = egfrLatest.get(0).getEgfr(); // eval highest egfr only
            Float percentageDrop = (el_egfr - e_egfr) / el_egfr * ((float)100);
            if (percentageDrop >= 20) {
                hasDropped = true;
            }
            if (percentageDrop > maxPercentageDrop) {
                maxPercentageDrop = percentageDrop;
            }
        }

        return new EgfrDrop(egfrLatest, egfr, hasDropped, maxPercentageDrop);
    }

}
