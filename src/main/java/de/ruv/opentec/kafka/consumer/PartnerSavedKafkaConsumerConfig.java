package de.ruv.opentec.kafka.consumer;

import de.ruv.opentec.kafka.model.Partner;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class PartnerSavedKafkaConsumerConfig {

    @Value(value = "${kafka.bootstrap.address}")
    private String bootstrapAddress;

    @Value(value = "${kafka.group.id}")
    private String groupId;

    @Bean
    public ConsumerFactory<Long, Partner> partnerSavedConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapAddress);
        props.put(
                ConsumerConfig.GROUP_ID_CONFIG,
                groupId);
        props.put(
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                LongDeserializer.class);
        props.put(
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                JsonDeserializer.class);
        props.put("consumer.interceptor.classes",
                "io.confluent.monitoring.clients.interceptor.MonitoringConsumerInterceptor");

        return new DefaultKafkaConsumerFactory<>(props, new LongDeserializer(),
                new JsonDeserializer<>(Partner.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<Long, Partner>
    partnerSavedKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<Long, Partner> factory
                = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(partnerSavedConsumerFactory());
        return factory;
    }

}