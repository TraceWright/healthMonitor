package io.api.bloodpressure;

import javax.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import io.api.bloodpressure.bloodpressuremodels.BloodPressureEvaluate;
import io.api.bloodpressure.bloodpressuremodels.BloodPressure;

@RestController
public class BloodPressureController extends BloodPressureService {

    @PostMapping("/bloodpressure")
    public BloodPressureEvaluate bp(@Valid @RequestBody BloodPressure bp) {
        return EvaluateBloodPressure(bp);
    }
}
