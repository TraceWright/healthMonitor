package io.api.bloodpressure;

import org.springframework.stereotype.Service;

import io.api.bloodpressure.bloodpressuremodels.BloodPressureClassification;
import io.api.bloodpressure.bloodpressuremodels.BloodPressureEvaluate;
import io.api.bloodpressure.bloodpressuremodels.BloodPressure;


@Service
public abstract class BloodPressureService {

    public BloodPressureEvaluate EvaluateBloodPressure(BloodPressure bp) 
    {
        return new BloodPressureEvaluate(bp, EvaluateClassification(bp.getSysBp(), bp.getDiaBp()));
    }

    public byte EvaluateClassification(short sysBp, short diaBp) {
        BloodPressureClassification c = new BloodPressureClassification();
        if (sysBp >= 180 || diaBp >= 120) {
            c.setClassification((byte)3);
        } else if (sysBp >= 160 || diaBp >= 100) {
            c.setClassification((byte)2);
        } else if (sysBp >= 140 || diaBp >= 90) {
            c.setClassification((byte)1);
        } else {
            c.setClassification((byte)0);
        }
        return c.getClassification();
    }
    
  
}

