package io.api.egfr;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EgfrRepository extends JpaRepository<Egfr, Integer> {
    Iterable<Egfr> findTop10ByOrderByAtDateDesc();
}