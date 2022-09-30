package vttp2022.assessment.csf.orderbackend.controllers;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import vttp2022.assessment.csf.orderbackend.models.Order;
import vttp2022.assessment.csf.orderbackend.models.OrderSummary;
import vttp2022.assessment.csf.orderbackend.services.OrderService;

@RestController
@RequestMapping(path="/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderRestController {

    @Autowired
    private OrderService orderSvc;

    private Logger logger = Logger.getLogger(OrderRestController.class.getName());

    @PostMapping(path="/order", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> postOrder(@RequestBody String payload) {
       
        logger.info(":::::PAYLOAD: " + payload);

        try {
            Gson gson = new Gson();
            Order order = gson.fromJson(payload, Order.class);
            System.out.println(":::::PASS: Order Received: " + order);

            boolean success = orderSvc.createOrder(order);
            System.out.println(":::::PASS: Order Created: " + success);

        } catch (Exception ex) {
            System.out.println(":::::FAIL: Order not created");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(":::::PASS: Order Posted!");
        
    }

    @GetMapping(path="/order/{email}/add")
    public ResponseEntity<List<OrderSummary>> getOrders(@PathVariable String email) {

        List<OrderSummary> orderSummaries = orderSvc.getOrdersByEmail(email);

        return ResponseEntity.status(HttpStatus.OK).body(orderSummaries);
    }

}
