package de.ruv.opentec.kafka.endpoint;

import de.ruv.opentec.kafka.model.Vertrag;
import de.ruv.opentec.kafka.repository.VertragRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vertrag")
public class VertragRestController {

    private final VertragRepository vertragRepository;

    @Autowired
    VertragRestController(VertragRepository vertragRepository) {
        this.vertragRepository = vertragRepository;
    }

    @GetMapping("/load")
    @ResponseBody
    public List<Vertrag> getVertraege() {
        return vertragRepository.findAll();
    }
}