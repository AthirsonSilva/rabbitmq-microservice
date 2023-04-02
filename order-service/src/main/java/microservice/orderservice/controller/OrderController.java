package microservice.orderservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import microservice.orderservice.payload.Order;
import microservice.orderservice.payload.OrderEvent;
import microservice.orderservice.payload.OrderStatus;
import microservice.orderservice.publisher.OrderPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
@Log4j2
public class OrderController {
    private final OrderPublisher orderPublisher;

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        log.info("Received order: {}", order);

        OrderEvent orderEvent = OrderEvent.builder()
                .status(OrderStatus.PENDING.name())
                .message("Order received successfully")
                .order(order)
                .build();

        orderPublisher.publish(orderEvent);

        return new ResponseEntity<>(order, null, 201);
    }
}
