package publisher;

import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dto.Order;
import dto.OrderStatus;
import config.MessagingConfig;

@RestController
@RequestMapping("/order")
public class OrderPublisher {
	@Autowired
	private RabbitTemplate template;

	@PostMapping("/{restName}")
	public String bookOrder(@RequestBody Order order, @PathVariable String restName) {
		order.setOrderId(UUID.randomUUID().toString());
		// restService
		// paymentService
		OrderStatus orderStatus = new OrderStatus(order, "PROCESS", "order placed successfully " + restName);
		
		template.convertAndSend(MessagingConfig.EXCHANGE , MessagingConfig.ROUTING_KEY, orderStatus);
		return "Success";
	}
}
