package de.opentect.kafka.endpoint;

import de.opentect.kafka.model.Vertrag;
import de.opentect.kafka.repository.VertragRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

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


    @DeleteMapping("/delete/{vertragUUID}")
    public void deleteVertrag(@PathVariable String vertragUUID) {
        vertragRepository.delete(vertragRepository.findByVertragUUID(vertragUUID).get());
    }

    @PostMapping("/save")
    public Vertrag createVertrag(@RequestBody Vertrag vertrag) {
        vertrag.setLastChanged(new Date());
        if (StringUtils.isEmpty(vertrag.getVertragUUID())) {
            vertrag.setVertragUUID(UUID.randomUUID().toString());
        }
        vertragRepository.save(vertrag);
        return vertrag;
    }

}