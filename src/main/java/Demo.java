import edu.umn.kafka.*;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.producer.*;

import java.util.*;
import java.util.concurrent.*;

public class Demo {

    public static final String TOPIC_NAME = "bigdata-demo";

    public static void main(String[] args) throws ExecutionException, InterruptedException {


        KafkaConsumer<String, String> kafkaConsumer = configureKafkaConsumer();
        KafkaMessageProcessor kafkaMessageProcessor = new KafkaMessageProcessor(kafkaConsumer, TOPIC_NAME);
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.submit(kafkaMessageProcessor::run);


        KafkaProducer<String, String> kafkaProducer = configureKafkaProducer();

        for (int i = 1; i <= 10; i++) {
            ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC_NAME, String.valueOf(i), "My event number " + i);
            Future<RecordMetadata> send = kafkaProducer.send(record);
            RecordMetadata recordMetadata = send.get();
            System.out.println("PUBLISHED: " + recordMetadata.toString());
            Thread.sleep(5000);
        }

        kafkaMessageProcessor.shutdown();
    }

    private static KafkaProducer<String, String> configureKafkaProducer() {
        Properties producerConfig = new Properties();
        producerConfig.put("client.id", "local-producer");
        producerConfig.put("bootstrap.servers", "localhost:9092");
        producerConfig.put("batch.size", 1);
        producerConfig.put("acks", "all");
        producerConfig.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producerConfig.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        return KafkaClient.createKafkaProducer(producerConfig);
    }


    public static KafkaConsumer<String, String> configureKafkaConsumer() {
        Properties consumerConfig = new Properties();
        consumerConfig.put("group.id", "LOCAL-CONSUMER");
        consumerConfig.put("enable.auto.commit", "true");
        consumerConfig.put("bootstrap.servers", "localhost:9092");
        consumerConfig.put("auto.offset.reset", "earliest");
        consumerConfig.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumerConfig.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");


       return KafkaClient.createKafkaConsumer(consumerConfig);


    }


}
