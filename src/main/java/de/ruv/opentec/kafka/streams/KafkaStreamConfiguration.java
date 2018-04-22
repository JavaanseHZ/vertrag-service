package de.ruv.opentec.kafka.streams;

import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import io.confluent.kafka.streams.serdes.avro.GenericAvroSerde;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.util.Utf8;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.processor.WallclockTimestampExtractor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
@EnableKafkaStreams
public class KafkaStreamConfiguration {
	@Value(value = "${kafka.bootstrap.address}")
	private String bootstrapAddress;

	@Value(value = "${kafka.group.id}")
	private String groupId;

	@Value(value = "${kafka.schemaregistry.address}")
	private String registryAddress;

	@Value("${kafka.message.topic.vertrag.saved}")
	private String vertragOutTopic;

	@Value("${kafka.streams.vertrag.topic.saved}")
	private String vertragInTopic;

	@Value("${kafka.streams.vertrag.applicationid}")
	private String applicationid;

	@Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
	public StreamsConfig kStreamsConfigs() {
		Map<String, Object> props = new HashMap<>();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
		props.put(AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, registryAddress);
		props.put(StreamsConfig.APPLICATION_ID_CONFIG, applicationid);
		props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, GenericAvroSerde.class);
		props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, GenericAvroSerde.class);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
		props.put(StreamsConfig.DEFAULT_TIMESTAMP_EXTRACTOR_CLASS_CONFIG, WallclockTimestampExtractor.class.getName());
		props.put("consumer.interceptor.classes",
				"io.confluent.monitoring.clients.interceptor.MonitoringConsumerInterceptor");
		props.put("producer.interceptor.classes",
				"io.confluent.monitoring.clients.interceptor.MonitoringProducerInterceptor");

		return new StreamsConfig(props);
	}


	@Bean
	public KStream<GenericRecord, GenericRecord> kStream(StreamsBuilder streamBuilder) {
		KStream<GenericRecord, GenericRecord> stream = streamBuilder.stream(vertragInTopic);

		KStream<GenericRecord, GenericRecord> filtered = stream.filter((key, value) -> !(((Utf8) value.get("sparte")).toString().contains("Firmen")));

		filtered.to(vertragOutTopic);
		return filtered;
	}

}