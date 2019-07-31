package io.api.egfr;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EgfrController {

    @Autowired 
    private EgfrRepository egfrRepository;

    // @PostMapping("/egfr")
    // public EgfrQueryDrop Egfr(@RequestBody Egfr egfr) {
    //     Iterator<Egfr> latestEgfr = egfrRepository.findAllEgfrForLatestDate().iterator();
    //     EgfrQueryDrop qd = new EgfrQueryDrop(latestEgfr, egfr, false, (float)0);
    //     EgfrSave(egfr);
    //     return qd;
    // }

    @PostMapping("/egfr")
    public EgfrQueryDrop Egfr(@RequestBody Egfr egfr) {
        ArrayList<Egfr> latestEgfr = egfrRepository.findAllEgfrForLatestDate();
        EgfrQueryDrop qd = new EgfrQueryDrop(latestEgfr, egfr, false, (float)0);
        EgfrSave(egfr);
        return qd;
    }

    public Egfr EgfrSave(@RequestBody Egfr egfr) {
        egfrRepository.save(egfr);
        return egfr;
    }

    @GetMapping("/egfr-history")
    public Iterable<Egfr> EgfrHistory() {
        return egfrRepository.findTop10ByOrderByAtDateDesc();
    }

}