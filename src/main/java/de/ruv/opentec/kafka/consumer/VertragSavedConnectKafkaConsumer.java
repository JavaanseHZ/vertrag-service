package de.ruv.opentec.kafka.consumer;


import de.ruv.opentec.kafka.model.Partner;
import de.ruv.opentec.kafka.model.Vertrag;
import de.ruv.opentec.kafka.repository.VertragRepository;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.util.Utf8;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class VertragSavedConnectKafkaConsumer {

    @Autowired
    private VertragRepository vertragRepository;

    @KafkaListener(topics = "${kafka.message.topic.vertrag.saved}", containerFactory = "partnerDeletedKafkaListenerContainerFactory")
    public void receiveDeleted(GenericRecord genericRecord) {
        long id = (Long) genericRecord.get("id");
        String sparte = ((Utf8) genericRecord.get("sparte")).toString();
        double betrag = (Double) genericRecord.get("betrag");
        long partnerid = (Integer) genericRecord.get("partnerid");
        String vorname = ((Utf8) genericRecord.get("vorname")).toString();
        String nachname = ((Utf8) genericRecord.get("nachname")).toString();

        Partner partner = new Partner();
        partner.setId(partnerid);
        partner.setVorname(vorname);
        partner.setNachname(nachname);
        Vertrag vertrag = new Vertrag();
        vertrag.setId(id);
        vertrag.setPartner(partner);
        vertrag.setSparte(sparte);
        vertrag.setBeitrag(betrag);
        vertragRepository.save(vertrag);
    }
}