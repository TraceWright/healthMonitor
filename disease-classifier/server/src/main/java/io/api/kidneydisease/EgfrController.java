package io.api.kidneydisease;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class EgfrController {

    @Autowired 
    
	private EgfrRepository egfrRepository;

    @PostMapping("/egfr")
    public Egfr EgfrSingle(@RequestBody Egfr egfr) {
        egfrRepository.save(egfr);
        return egfr;
    }

    @PostMapping("/egfr-list")
    public EgfrList EgfrList(@RequestBody List<Egfr> egfr) {
        EgfrList k = new EgfrList(egfr);
        return k;
    }
}