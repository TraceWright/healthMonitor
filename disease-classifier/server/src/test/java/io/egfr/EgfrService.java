package io.egfr;

import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.Test;

import io.api.egfr.EgfrService;
import io.api.egfr.egfrmodels.Egfr;
import io.api.egfr.egfrmodels.EgfrPercentageDrop;

import static org.junit.Assert.assertEquals;
import java.text.SimpleDateFormat;

class EgfrServiceTests extends EgfrService {

Egfr egfr = new Egfr((float)88, new Date());
ArrayList<Egfr> latestEgfr = new ArrayList<Egfr>() {
    {
        add(egfr);
    }
};

    @Test
    void egfrEquals() {
        assertEquals(Float.valueOf(88), egfr.getEgfr());
    }

    @Test
    void dateEquals() {
        assertEquals(new SimpleDateFormat("yyyy/MM/dd").format(new Date()), egfr.getAtDate());
    }

    @Test
    void egfrPercentageDropEquals() {
        EgfrPercentageDrop epd = EvaluateEgfrDrop(latestEgfr, egfr);
        assertEquals(false, epd.getHasDropped20Pct());
    }

    @Test
    void egfrMaxDropEquals() {
        EgfrPercentageDrop epd = EvaluateEgfrDrop(latestEgfr, egfr);
        assertEquals(Float.valueOf(0), epd.getMaxDailyPctDrop());
    }
    
}