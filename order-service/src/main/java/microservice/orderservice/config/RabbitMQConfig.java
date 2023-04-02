package microservice.orderservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
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
        return new Queue(queueName, true); // Create queue with name
    }

    @Bean
    public TopicExchange orderExchange() {
        return new TopicExchange(exchange); // Create exchange with name
    }

    @Bean
    public Binding orderBinding() {
        return BindingBuilder.bind(orderQueue()).to(orderExchange()).with(routingKey); // Bind queue to exchange with routing key
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter(); // Convert message to json format
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(); // Create rabbit template to send message to queue
        rabbitTemplate.setMessageConverter(jsonMessageConverter()); // Set message converter to rabbit template to convert message to json

        return rabbitTemplate;
    }
}
