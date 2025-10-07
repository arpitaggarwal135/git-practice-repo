package in.ashokit.service.impl;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import in.ashokit.constants.KafkaPublisherConstants;
import in.ashokit.model.Order;
import in.ashokit.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	private KafkaTemplate<String, Order> kafkaTemplate;

	public OrderServiceImpl(KafkaTemplate<String, Order> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	@Override
	public String publishOrderMsg(Order order) {
		kafkaTemplate.send(KafkaPublisherConstants.TOPIC, order);
		System.out.println("****** Message Published to Kafka Topic ******");
		return "Order Added to Kafka Queue Successfully !!";
	}
}
