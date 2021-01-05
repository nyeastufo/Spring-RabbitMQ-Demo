package consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import dto.OrderStatus;
import config.MessagingConfig;

@Component
public class OrderConsumer {
	
	@RabbitListener(queues = MessagingConfig.QUEUE)
	public void consumeMessageFromQueue(OrderStatus orderStatus) {
		System.out.println("Message received from queue " + orderStatus.getMessage());
	}
}
