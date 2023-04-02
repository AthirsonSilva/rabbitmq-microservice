package microservice.orderservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Value("${rabbitmq.queue.order.name}")
    private String queueName;
    @Value("${rabbitmq.exchange.order.name}")
    private String exchange;
    @Value("${rabbitmq.routing-key.order.name}")
    private String routingKey;

    @Bean
    public Queue orderQueue() {
        return new Queue(queueName, true);
    }
}
