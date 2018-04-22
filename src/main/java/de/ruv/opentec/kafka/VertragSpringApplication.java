package de.ruv.opentec.kafka;

import de.ruv.opentec.kafka.model.Partner;
import de.ruv.opentec.kafka.model.Vertrag;
import de.ruv.opentec.kafka.repository.VertragRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class VertragSpringApplication {

    @Autowired
    private VertragRepository vertragRepository;

    public static void main(String[] args) {
        SpringApplication.run(VertragSpringApplication.class, args);
    }

    @PostConstruct
    private void loadInitialData() {
        Partner uwe = new Partner();
        uwe.setId(1);
        uwe.setVorname("Uwe");
        uwe.setNachname("Bein");
        Vertrag leben = new Vertrag();
        leben.setId(1);
        leben.setPartner(uwe);
        leben.setSparte("Leben");
        leben.setBeitrag(134.25);
        vertragRepository.save(leben);

        Partner andi = new Partner();
        andi.setId(2);
        andi.setVorname("Andi");
        andi.setNachname("Moeller");
        Vertrag unfall = new Vertrag();
        unfall.setId(2);
        unfall.setPartner(andi);
        unfall.setSparte("Unfall");
        unfall.setBeitrag(16.11);
        vertragRepository.save(unfall);

        Partner tony = new Partner();
        tony.setId(3);
        tony.setVorname("Tony");
        tony.setNachname("Yeboah");
        Vertrag kranken = new Vertrag();
        kranken.setId(3);
        kranken.setPartner(tony);
        kranken.setSparte("Kranken");
        kranken.setBeitrag(22.35);
        vertragRepository.save(kranken);
    }
}