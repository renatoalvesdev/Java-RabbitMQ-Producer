package ao.co.tistech.amqp;

public interface AmqpProducer<T> {
    void producer(T t);
}
