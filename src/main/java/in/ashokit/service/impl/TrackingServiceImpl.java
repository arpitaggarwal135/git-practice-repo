package in.ashokit.service.impl;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import in.ashokit.constants.KafkaConsumerConstants;
import in.ashokit.model.Tracking;
import in.ashokit.service.TrackingService;

@Service
public class TrackingServiceImpl implements TrackingService {
	@Override
	@KafkaListener(
			topics = KafkaConsumerConstants.TOPIC,
			groupId = KafkaConsumerConstants.GROUP_ID,
			containerFactory = "trackingContainerFactory"
	)
	public void subscribeTrackingMsg(Tracking tracking) {
		System.out.println("Message Received from Kafka Topic : "+tracking);
	}
}
