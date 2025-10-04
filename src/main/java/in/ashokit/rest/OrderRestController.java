package in.ashokit.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.model.Order;
import in.ashokit.service.OrderService;

@RestController
public class OrderRestController {
	private OrderService service;

	public OrderRestController(OrderService service) {
		this.service = service;
	}

	@PostMapping("/order")
	public String saveOrder(@RequestBody Order order) {
		return service.publishOrderMsg(order);
	}
}
