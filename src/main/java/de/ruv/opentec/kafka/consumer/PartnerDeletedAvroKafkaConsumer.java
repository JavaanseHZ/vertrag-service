package de.ruv.opentec.kafka.consumer;


import de.ruv.opentec.kafka.repository.VertragRepository;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.util.Utf8;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PartnerDeletedAvroKafkaConsumer {

    @Autowired
    private VertragRepository vertragRepository;

    @KafkaListener(topics = "${kafka.message.topic.deleted}", containerFactory = "partnerDeletedKafkaListenerContainerFactory")
    public void receiveDeleted(GenericRecord genericRecord) {

        long id = (Long) genericRecord.get("id");
        String neuePartnerUid = (String) genericRecord.get("vorname");
        String altePartnerUid = (String) genericRecord.get("nachname");
        vertragRepository.findAll().forEach(e -> {
            if (e.getPartner().getId() == id) {
                vertragRepository.delete(e);
            }
        });
    }
}