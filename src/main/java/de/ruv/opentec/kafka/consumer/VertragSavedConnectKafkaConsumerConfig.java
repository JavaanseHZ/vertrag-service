package de.ruv.opentec.kafka.consumer;

import io.confluent.kafka.schemaregistry.client.CachedSchemaRegistryClient;
import io.confluent.kafka.schemaregistry.client.SchemaRegistryClient;
import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class VertragSavedConnectKafkaConsumerConfig {

    @Value(value = "${kafka.bootstrap.address}")
    private String bootstrapAddress;

    @Value(value = "${kafka.group.id}")
    private String groupId;

    @Value(value = "${kafka.schemaregistry.address}")
    private String registryAddress;

    @Bean
    public ConsumerFactory<Object, Object> vertragSavedConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapAddress);
        props.put(
                ConsumerConfig.GROUP_ID_CONFIG,
                groupId);
        props.put(
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                KafkaAvroDeserializer.class);
        props.put(
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                KafkaAvroDeserializer.class);
        props.put(AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, registryAddress);

        props.put("consumer.interceptor.classes",
                "io.confluent.monitoring.clients.interceptor.MonitoringConsumerInterceptor");

        SchemaRegistryClient client = new CachedSchemaRegistryClient(registryAddress, 10);

        return new DefaultKafkaConsumerFactory<>(props, new KafkaAvroDeserializer(),
                new KafkaAvroDeserializer(client));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<Object, Object>
    vertragSavedKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<Object, Object> factory
                = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(vertragSavedConsumerFactory());
        return factory;
    }

}