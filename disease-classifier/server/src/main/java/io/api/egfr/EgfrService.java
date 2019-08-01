package io.api.egfr;

import java.util.ArrayList;
import org.springframework.stereotype.Service;

import io.api.egfr.egfrmodels.EgfrClassification;
import io.api.egfr.egfrmodels.EgfrEvaluate;
import io.api.egfr.egfrmodels.EgfrPercentageDrop;
import io.api.egfr.egfrmodels.Egfr;

@Service
public class EgfrService {

    public EgfrEvaluate EvaluateEgfr(ArrayList<Egfr> egfrLatest, Egfr egfr) 
    {
        return new EgfrEvaluate(
            egfrLatest,
            egfr,
            EvaluateEgfrDrop(egfrLatest, egfr),
            EvaluateClassification(egfr.getEgfr())
        );
    }

    public EgfrPercentageDrop EvaluateEgfrDrop(ArrayList<Egfr> egfrLatest, Egfr egfr)

    // this method evaluates the maxDailyPctDrop between the current egfr and
    // the highest egfr for the latest date, which will flag any egfr drop >= 20
    // for all records on that day (or days if the latest records are for a past day)
    
    // requires list order by DESC

    {
        EgfrPercentageDrop epd = new EgfrPercentageDrop();
        if (egfrLatest.size() > 0) {
            Float e_egfr = egfr.getEgfr();
            Float el_egfr = egfrLatest.get(0).getEgfr(); // eval highest egfr only
            Float percentageDrop = (el_egfr - e_egfr) / el_egfr * ((float)100);
            if (percentageDrop >= 20) {
                epd.setHasDropped20Pct(true);
            }
            if (percentageDrop > epd.getMaxDailyPctDrop()) {
                epd.setMaxDailyPctDrop(percentageDrop);
            }
        }
        return epd;
    }

    public byte EvaluateClassification(Float egfr) {
        EgfrClassification c = new EgfrClassification();
        if (egfr >= 90) {
            c.setClassification((byte)0);
        } else if (egfr >= 60) {
            c.setClassification((byte)1);
        } else if (egfr >= 45) {
            c.setClassification((byte)2);
        } else if (egfr >= 30) {
            c.setClassification((byte)3);
        } else if (egfr >= 15) {
            c.setClassification((byte)4);
        } else {
            c.setClassification((byte)5);
        }
        return c.getClassification();
    }
}

