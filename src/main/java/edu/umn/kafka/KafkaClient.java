package edu.umn.kafka;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.producer.*;

import java.util.*;

public class KafkaClient {

    public static KafkaConsumer<String, String> createKafkaConsumer(Properties config) {
        return new KafkaConsumer<>(config);
    }


    public static KafkaProducer<String, String> createKafkaProducer(Properties config) {
        return new KafkaProducer<>(config);
    }
}
