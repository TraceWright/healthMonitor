package io.api.egfr;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EgfrController extends EgfrService {

    @Autowired 
    private EgfrRepository egfrRepository;

    @PostMapping("/egfr")
    public EgfrDrop Egfr(@RequestBody Egfr egfr) {
        ArrayList<Egfr> latestEgfr = egfrRepository.findAllEgfrForLatestDate();
        egfrRepository.save(egfr);
        return EvalEgfrDropService(latestEgfr, egfr);
    }

    @GetMapping("/egfr-history")
    public Iterable<Egfr> EgfrHistory() {
        return egfrRepository.findTop10ByOrderByAtDateDesc();
    }

}