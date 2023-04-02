package microservice.orderservice.publisher;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import microservice.orderservice.config.RabbitMQProperties;
import microservice.orderservice.payload.Order;
import microservice.orderservice.payload.OrderEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class OrderPublisher {
    private final RabbitTemplate rabbitTemplate;
    @Value("${rabbitmq.order.exchange}")
    private String exchange;
    @Value("${rabbitmq.order.routingKey}")
    private String routingKey;


    public void publish(OrderEvent message) {
        log.info("Publishing message to exchange: {} with routing key: {}", exchange, routingKey);
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }
}
