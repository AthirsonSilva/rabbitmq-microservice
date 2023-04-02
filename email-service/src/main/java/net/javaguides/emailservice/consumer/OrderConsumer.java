package net.javaguides.emailservice.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.javaguides.emailservice.dto.OrderEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class OrderConsumer {
    private final JavaMailSender mailSender;

    @RabbitListener(queues = "${rabbitmq.queue.email.name}")
    public void consume(OrderEvent event) {
        log.info("Order event received: {}", event);

        // send an email
        sendEmail(event);
    }

    public void sendEmail(OrderEvent event) {
        log.info("Sending email --> {}", event);

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo("athirsonarceus@gmail.com");
        message.setSubject("Order Event");
        message.setText(event.toString());

        mailSender.send(message);

        log.info("Email sent successfully!");
    }
}
