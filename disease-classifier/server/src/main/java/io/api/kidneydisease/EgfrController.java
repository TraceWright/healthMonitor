package io.api.kidneydisease;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class EgfrController {

    @PostMapping("/egfr")
    public EgfrItem EgfrSingle(@RequestBody List<EgfrItem> egfr) {
        Egfr k = new Egfr(egfr);
        return k.getEgfrList().get(0);
    }

    @PostMapping("/egfr-list")
    public Egfr EgfrList(@RequestBody List<EgfrItem> egfr) {
        Egfr k = new Egfr(egfr);
        return k;
    }
}