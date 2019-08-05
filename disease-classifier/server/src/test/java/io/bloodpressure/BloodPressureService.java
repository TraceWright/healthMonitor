package io.bloodpressure;

import java.util.Date;

import org.junit.jupiter.api.Test;

import io.api.bloodpressure.BloodPressureService;
import io.api.bloodpressure.bloodpressuremodels.BloodPressure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotEquals;
import java.text.SimpleDateFormat;

class EgfrServiceTests extends BloodPressureService {

BloodPressure bp_1 = new BloodPressure((short)120,(short)80, new Date());
BloodPressure bp_2 = new BloodPressure((short)1, (short)1, new Date());
BloodPressure bp_3 = new BloodPressure((short)139, (short)89, new Date());
BloodPressure bp_4 = new BloodPressure((short)140, (short)90, new Date());
BloodPressure bp_5 = new BloodPressure((short)159, (short)99, new Date());
BloodPressure bp_6 = new BloodPressure((short)160, (short)100, new Date());
BloodPressure bp_7 = new BloodPressure((short)179, (short)119, new Date());
BloodPressure bp_8 = new BloodPressure((short)180, (short)120, new Date());
BloodPressure bp_9 = new BloodPressure((short)500, (short)500, new Date());


    @Test
    void sysBpEquals() {
        assertEquals((short)120, bp_1.getSysBp());
    }

    @Test
    void diaBpEquals() {
        assertEquals((short)80, bp_1.getDiaBp());
    }

    @Test
    void dateEquals() {
        assertEquals(new SimpleDateFormat("yyyy/MM/dd").format(new Date()), bp_1.getAtDate());
    }

    @Test
    void bpClassificationNotNull() {
        byte ec = EvaluateClassification(bp_1.getSysBp(), bp_1.getDiaBp());
        assertNotNull(ec);
    }

    @Test
    void bpClassificationNotEquals() {
        byte ec = EvaluateClassification(bp_1.getSysBp(), bp_1.getDiaBp());
        assertNotEquals((byte)1, ec);
    }

    @Test
    void bpClassificationNormal() {
        byte ec = EvaluateClassification(bp_1.getSysBp(), bp_1.getDiaBp());
        assertEquals((byte)0, ec);
    }

    @Test
    void bpClassificationNormalLow() {
        byte ec = EvaluateClassification(bp_2.getSysBp(), bp_2.getDiaBp());
        assertEquals((byte)0, ec);
    }

    @Test
    void bpClassificationNormalHigh() {
        byte ec = EvaluateClassification(bp_3.getSysBp(), bp_3.getDiaBp());
        assertEquals((byte)0, ec);
    }

    @Test
    void bpClassificationStage1Low() {
        byte ec = EvaluateClassification(bp_4.getSysBp(), bp_4.getDiaBp());
        assertEquals((byte)1, ec);
    }

    @Test
    void bpClassificationStage1High() {
        byte ec = EvaluateClassification(bp_5.getSysBp(), bp_5.getDiaBp());
        assertEquals((byte)1, ec);
    }

    @Test
    void bpClassificationStage2Low() {
        byte ec = EvaluateClassification(bp_6.getSysBp(), bp_6.getDiaBp());
        assertEquals((byte)2, ec);
    }

    @Test
    void bpClassificationStage2High() {
        byte ec = EvaluateClassification(bp_7.getSysBp(), bp_7.getDiaBp());
        assertEquals((byte)2, ec);
    }

    @Test
    void bpClassificationStage3Low() {
        byte ec = EvaluateClassification(bp_8.getSysBp(), bp_8.getDiaBp());
        assertEquals((byte)3, ec);
    }

    @Test
    void bpClassificationStage3High() {
        byte ec = EvaluateClassification(bp_9.getSysBp(), bp_9.getDiaBp());
        assertEquals((byte)3, ec);
    }
    
}