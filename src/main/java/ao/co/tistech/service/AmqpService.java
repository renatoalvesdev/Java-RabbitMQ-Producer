package ao.co.tistech.service;

import ao.co.tistech.dto.Message;

public interface AmqpService {
    void sendToConsumer(Message message);
}
