package ao.co.tistech.amqp.impl;

import ao.co.tistech.amqp.AmqpProducer;
import ao.co.tistech.dto.Message;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ProducerRabbitMQ implements AmqpProducer<Message> {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private DirectExchange direct;

    @Value("${spring.rabbitmq.template.routing-key}")
    private String queue;

    @Value("${spring.rabbitmq.template.exchange}")
    private String exchange;

    @Override
    public void producer(Message message) {
        try {
            String key = message.getText();

            rabbitTemplate.convertAndSend(direct.getName(), key, message);
        }catch (Exception ex){
            throw new AmqpRejectAndDontRequeueException(ex);
        }
    }
}
