package microservice.orderservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RabbitMQConfig {
    @Value("${rabbitmq.order.queue}")
    private String queue;
    @Value("${rabbitmq.order.exchange}")
    private String exchange;
    @Value("${rabbitmq.order.routingKey}")
    private String routingKey;

    private RabbitMQProperties properties;

    @Bean
    public Queue orderQueue() {
        return new Queue(queue, true); // Create queue with name
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
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory); // Create rabbit template to send message to queue
        rabbitTemplate.setMessageConverter(jsonMessageConverter()); // Set message converter to rabbit template to convert message to json

        return rabbitTemplate;
    }
}
