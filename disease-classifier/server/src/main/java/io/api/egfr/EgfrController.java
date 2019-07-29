package io.api.egfr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EgfrController {

    @Autowired 
    private EgfrRepository egfrRepository;

    @PostMapping("/egfr")
    public Egfr EgfrSingle(@RequestBody Egfr egfr) {
        egfrRepository.save(egfr);
        return egfr;
    }

    @GetMapping("/egfr-history")
    public Iterable<Egfr> EgfrHistory() {
        Iterable<Egfr> e = egfrRepository.findTop10ByOrderByAtDateDesc();
        return e;
    }
}