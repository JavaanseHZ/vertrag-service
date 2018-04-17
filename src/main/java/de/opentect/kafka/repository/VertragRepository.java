package de.opentect.kafka.repository;

import de.opentect.kafka.model.Vertrag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VertragRepository extends JpaRepository<Vertrag, Long> {
    Optional<Vertrag> findByVertragUUID(String vertragUUID);
}
