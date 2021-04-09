package ao.co.tistech.service.impl;

import ao.co.tistech.amqp.AmqpProducer;
import ao.co.tistech.dto.Message;
import ao.co.tistech.service.AmqpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQServiceImpl implements AmqpService {

    @Autowired
    private AmqpProducer<Message> amqp;

    @Override
    public void sendToConsumer(Message message) {
        amqp.producer(message);
    }
}
