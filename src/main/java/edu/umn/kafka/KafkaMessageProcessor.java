package edu.umn.kafka;

import org.apache.kafka.clients.consumer.*;

import java.util.*;
import java.util.concurrent.atomic.*;

public class KafkaMessageProcessor implements Runnable {

    private final KafkaConsumer<String, String> consumer;
    private final List<String> topics = new ArrayList<>();
    private final AtomicBoolean shutdown;

    public KafkaMessageProcessor(KafkaConsumer<String, String> kafkaConsumer, String topic) {
        this.consumer = kafkaConsumer;
        topics.add(topic);
        this.shutdown = new AtomicBoolean(false);
    }

    public void run() {
        try {

            consumer.subscribe(topics);
            System.out.println("********************** CONSUMER STARTED **********************");
            while (!shutdown.get()) {
                ConsumerRecords<String, String> records = consumer.poll(500);
                records.forEach(record -> {
                    System.out.println("CONSUMED: " + record.toString());
                });
            }
        } finally {
            consumer.close();
        }
    }

    public void shutdown() throws InterruptedException {
        shutdown.set(true);
        System.out.println("********************** CONSUMER SHUTDOWN **********************");
    }
}