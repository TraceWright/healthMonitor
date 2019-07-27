package io.api.hypertension;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BloodPressureController {

    @PostMapping("/hypertension")
    public BloodPressure bp(@RequestBody BloodPressure hypertension) {
        return new BloodPressure(
            hypertension.getSysBp(),
            hypertension.getDiaBp(),
            hypertension.getAtDate(),
            hypertension.getClassification()
        );
    }
}
