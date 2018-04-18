package de.opentect.kafka.producer;

import de.opentect.kafka.model.Vertrag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class VertragKafkaSender {

    @Value("${kafka.message.topic}")
    private String topic;

    @Autowired
    private KafkaTemplate<String, Vertrag> kafkaTemplate;

    public void sendEvent(String key, Vertrag vertrag)
    {
        kafkaTemplate.send(topic, key, vertrag);
    }
}
