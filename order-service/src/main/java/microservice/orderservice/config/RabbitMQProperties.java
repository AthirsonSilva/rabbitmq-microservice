package microservice.orderservice.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(RabbitMQProperties.class)
@ConfigurationProperties(prefix = "rabbitmq.order")
@NoArgsConstructor
@Getter
public class RabbitMQProperties {
    private String queue;
    private String exchange;
    private String routingKey;
}
