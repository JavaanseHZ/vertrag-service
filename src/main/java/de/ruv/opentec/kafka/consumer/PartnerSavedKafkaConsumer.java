package de.ruv.opentec.kafka.consumer;


import de.ruv.opentec.kafka.model.Partner;
import de.ruv.opentec.kafka.repository.VertragRepository;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PartnerSavedKafkaConsumer {

    @Value("${kafka.message.topic}")
    private String topic;

    @Autowired
    private VertragRepository vertragRepository;

    @KafkaListener(topics = "${kafka.message.topic}")
    public void receive(ConsumerRecord<Long, Partner> consumerRecord) {
        Partner partner = consumerRecord.value();
        vertragRepository.findAll().forEach(e -> {
            if (e.getPartner().getId() == consumerRecord.key()) {
                e.setPartner(partner);
                vertragRepository.save(e);
            }
        });
    }
}