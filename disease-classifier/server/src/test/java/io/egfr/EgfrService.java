package io.egfr;

import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.Test;

import io.api.egfr.EgfrService;
import io.api.egfr.egfrmodels.Egfr;
import io.api.egfr.egfrmodels.EgfrPercentageDrop;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotEquals;
import java.text.SimpleDateFormat;

class EgfrServiceTests extends EgfrService {

Egfr egfr_1 = new Egfr((float)90, new Date());
ArrayList<Egfr> latestEgfr_1 = new ArrayList<Egfr>() {{ add(egfr_1); }};

Egfr egfr_2 = new Egfr((float)72, new Date());
Egfr egfr_3 = new Egfr((float)89, new Date());
Egfr egfr_4 = new Egfr((float)60, new Date());
Egfr egfr_5 = new Egfr((float)59, new Date());
Egfr egfr_6 = new Egfr((float)45, new Date());
Egfr egfr_7 = new Egfr((float)44, new Date());
Egfr egfr_8 = new Egfr((float)30, new Date());
Egfr egfr_9 = new Egfr((float)29, new Date());
Egfr egfr_10 = new Egfr((float)15, new Date());
Egfr egfr_11 = new Egfr((float)14, new Date());
Egfr egfr_12 = new Egfr((float)1, new Date());
Egfr egfr_13 = new Egfr((float)500, new Date());


    @Test
    void egfrEquals() {
        assertEquals(Float.valueOf(90), egfr_1.getEgfr());
    }

    @Test
    void dateEquals() {
        assertEquals(new SimpleDateFormat("yyyy/MM/dd").format(new Date()), egfr_1.getAtDate());
    }

    @Test
    void egfrHasntDropped() {
        EgfrPercentageDrop epd = EvaluateEgfrDrop(latestEgfr_1, egfr_1);
        assertEquals(false, epd.getHasDropped20Pct());
    }

    @Test
    void egfrHasDropped20Pct() {
        EgfrPercentageDrop epd = EvaluateEgfrDrop(latestEgfr_1, egfr_2);
        assertEquals(true, epd.getHasDropped20Pct());
    }

    @Test
    void egfrPercentageDropZero() {
        EgfrPercentageDrop epd = EvaluateEgfrDrop(latestEgfr_1, egfr_1);
        assertEquals(Float.valueOf(0), epd.getMaxDailyPctDrop());
    }

    @Test
    void egfrPercentageDrop20Pct() {
        EgfrPercentageDrop epd = EvaluateEgfrDrop(latestEgfr_1, egfr_2);
        assertEquals(Float.valueOf(20), epd.getMaxDailyPctDrop());
    }

    @Test
    void egfrClassificationNotNull() {
        byte ec = EvaluateClassification(egfr_1.getEgfr());
        assertNotNull(ec);
    }

    @Test
    void egfrClassificationNotEquals() {
        byte ec = EvaluateClassification(egfr_1.getEgfr());
        assertNotEquals((byte)1, ec);
    }

    @Test
    void egfrClassificationNormalHigh() {
        byte ec = EvaluateClassification(egfr_13.getEgfr());
        assertEquals((byte)0, ec);
    }

    @Test
    void egfrClassificationNormal() {
        byte ec = EvaluateClassification(egfr_1.getEgfr());
        assertEquals((byte)0, ec);
    }

    @Test
    void egfrClassificationMildDecreaseHigh() {
        byte ec = EvaluateClassification(egfr_3.getEgfr());
        assertEquals((byte)1, ec);
    }

    @Test
    void egfrClassificationMildDecreaseLow() {
        byte ec = EvaluateClassification(egfr_4.getEgfr());
        assertEquals((byte)1, ec);
    }

    @Test
    void egfrClassificationMildModerateHigh() {
        byte ec = EvaluateClassification(egfr_5.getEgfr());
        assertEquals((byte)2, ec);
    }

    @Test
    void egfrClassificationMildModerateLow() {
        byte ec = EvaluateClassification(egfr_6.getEgfr());
        assertEquals((byte)2, ec);
    }

    @Test
    void egfrClassificationModerateSevereHigh() {
        byte ec = EvaluateClassification(egfr_7.getEgfr());
        assertEquals((byte)3, ec);
    }

    @Test
    void egfrClassificationModerateSevereLow() {
        byte ec = EvaluateClassification(egfr_8.getEgfr());
        assertEquals((byte)3, ec);
    }

    @Test
    void egfrClassificationSevereHigh() {
        byte ec = EvaluateClassification(egfr_9.getEgfr());
        assertEquals((byte)4, ec);
    }

    @Test
    void egfrClassificationSevereLow() {
        byte ec = EvaluateClassification(egfr_10.getEgfr());
        assertEquals((byte)4, ec);
    }

    @Test
    void egfrClassificationKidneyFailureHigh() {
        byte ec = EvaluateClassification(egfr_11.getEgfr());
        assertEquals((byte)5, ec);
    }

    @Test
    void egfrClassificationKidneyFailureLow() {
        byte ec = EvaluateClassification(egfr_12.getEgfr());
        assertEquals((byte)5, ec);
    }
    
}