package ao.co.tistech.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ProducerRabbitConfiguration {

    @Value("${spring.rabbitmq.template.routing-key}")
    private String queue;

    @Value("${spring.rabbitmq.template.exchange}")
    private String exchange;

    @Value("${spring.rabbitmq.request.deadletter.producer}")
    private String deadLetter;

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(exchange);
    }

    @Bean
    Queue deadLetter() {
        return new Queue(deadLetter);
    }

    @Bean
    Queue queue() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", exchange);
        args.put("x-dead-letter-routing-key", queue);

        return new Queue(queue, true, false, false, args);
    }

    @Bean
    public Binding bindingQueue() {
        return BindingBuilder.bind(queue())
                .to(exchange()).with(queue);
    }

    @Bean
    public Binding bindingDeadLetterQueue() {
        return BindingBuilder.bind(deadLetter())
                .to(exchange()).with(deadLetter);
    }

    @Bean
    public DirectExchange direct() {
        return new DirectExchange("direct");
    }
}
