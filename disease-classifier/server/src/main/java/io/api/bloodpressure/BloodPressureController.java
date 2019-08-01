package io.api.bloodpressure;

import javax.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BloodPressureController {

    @PostMapping("/bloodpressure")
    public BloodPressure bp(@Valid @RequestBody BloodPressure hypertension) {
        return new BloodPressure(
            hypertension.getSysBp(),
            hypertension.getDiaBp(),
            hypertension.getAtDate(),
            hypertension.getClassification()
        );
    }
}
