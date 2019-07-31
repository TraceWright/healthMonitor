package io.api.egfr;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EgfrRepository extends JpaRepository<Egfr, Integer> {
    Iterable<Egfr> findTop10ByOrderByAtDateDesc();

    
    // -------------------------------------------------------------- //
    // specification limitation:
    // data is stored at yyyy/dd/mm precision so if there's multiple records per day, we need to 
    // return a list rather than just one item.

    @Query(value = "SELECT eg.id, eg.at_date, eg.egfr FROM egfr as eg WHERE eg.at_date IN" + 
        "(SELECT MAX(e.at_date) FROM egfr as e) ORDER BY eg.egfr DESC", nativeQuery = true) // order by DESC required for EvalEgfrDropService
    public ArrayList<Egfr> findAllEgfrForLatestDate();

    // -------------------------------------------------------------- //

}