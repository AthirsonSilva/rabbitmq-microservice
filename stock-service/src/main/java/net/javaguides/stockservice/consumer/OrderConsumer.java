package net.javaguides.stockservice.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.javaguides.stockservice.dto.Order;
import net.javaguides.stockservice.dto.OrderEvent;
import net.javaguides.stockservice.repository.OrderRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class OrderConsumer {
    private final OrderRepository repository;

    @RabbitListener(queues = "${rabbitmq.queue.order.name}")
    public void consume(OrderEvent event) {
        log.info("Order event received: {}", event);

        Order order = event.getOrder();

        // save order event data in database
        repository.save(order);
    }
}
