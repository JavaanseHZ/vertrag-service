package de.ruv.opentec.kafka.repository;

import de.ruv.opentec.kafka.model.Vertrag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VertragRepository extends JpaRepository<Vertrag, Long> {
}
