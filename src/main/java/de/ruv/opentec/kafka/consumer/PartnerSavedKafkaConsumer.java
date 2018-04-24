package de.ruv.opentec.kafka.consumer;


import de.ruv.opentec.kafka.model.Partner;
import de.ruv.opentec.kafka.repository.VertragRepository;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
public class PartnerSavedKafkaConsumer {

    @Autowired
    private VertragRepository vertragRepository;

    @KafkaListener(topics = "${kafka.message.topic}")
    public void receive(ConsumerRecord<Long, Partner> consumerRecord, Acknowledgment ack) {
        Partner partner = consumerRecord.value();
        vertragRepository.findById(consumerRecord.key()).ifPresent(v -> {
            v.setPartner(partner);
            vertragRepository.save(v);
            ack.acknowledge();
        });
    }
}