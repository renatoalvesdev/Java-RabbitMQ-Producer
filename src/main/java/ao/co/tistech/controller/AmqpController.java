package ao.co.tistech.controller;

import ao.co.tistech.dto.Message;
import ao.co.tistech.service.AmqpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AmqpController {

    @Autowired
    private AmqpService service;

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/send")
    public void sendToConsumer(@RequestBody Message message) {
        service.sendToConsumer(message);
    }

}
