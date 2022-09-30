package vttp2022.assessment.csf.orderbackend.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp2022.assessment.csf.orderbackend.models.Order;
import vttp2022.assessment.csf.orderbackend.models.OrderSummary;
import vttp2022.assessment.csf.orderbackend.repositories.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository ordRepo;

	// @Autowired
	// private PricingService priceSvc;

	// POST /api/order
	// Create a new order by inserting into orders table in pizzafactory database
	// IMPORTANT: Do not change the method's signature
	public boolean createOrder(Order order) {

		Integer uuid = Integer.parseInt(UUID.randomUUID().toString().substring(0, 8));
		order.setOrderId(uuid);
		
		if (ordRepo.insertOrder(order)) {
            System.out.println(":::::PASS: Inserted Order: " + order);
            return true;
        }
        System.out.println(":::::FAIL: Did Not Insert Order: " + order);
        return false;
	}

	// GET /api/order/<email>/all
	// Get a list of orders for email from orders table in pizzafactory database
	// IMPORTANT: Do not change the method's signature
	public List<OrderSummary> getOrdersByEmail(String email) {
		// Use priceSvc to calculate the total cost of an order
		List<OrderSummary> orderSummaries = ordRepo.getOrderByEmail(email);
	
		return orderSummaries;
	}
}
