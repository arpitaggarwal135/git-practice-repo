package in.ashokit.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import in.ashokit.constants.KafkaConsumerConstants;
import in.ashokit.model.Tracking;

@Configuration
public class KafkaConsumerConfig {
	@Bean
	public ConsumerFactory<String, Tracking> getConsumerFactory() {
		Map<String, Object> configProps = new HashMap<>();
		configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConsumerConstants.HOST);
		configProps.put(ConsumerConfig.GROUP_ID_CONFIG, KafkaConsumerConstants.GROUP_ID);
		configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		return new DefaultKafkaConsumerFactory<>(configProps, new StringDeserializer(),
				new JsonDeserializer<>(Tracking.class));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, Tracking> trackingContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, Tracking> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(getConsumerFactory());
		return factory;
	}
}
