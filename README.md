# Kafka: Producer and Consumer

## Owner
SRAVANI KIRLA  
Data Science Graduate Student  
Univeristy of Minnesota, Twin Cities  
CSCI-5751 : BONUS PROJECT

## Purpose
Produce and Consume messages simultaneously

Producer: Will produce 10 messages using an incremental value from 1 to 10  
Consumer: Will consume 10 messages in parallel

## Main Files

* [KafkaClient](src/main/java/edu/umn/kafka/KafkaClient.java)
* [KafkaMessageProcessor](src/main/java/edu/umn/kafka/KafkaMessageProcessor.java)
* [Demo](src/main/java/edu/umn/kafka/Demo.java)

### *Class Definitions*
**KafkaClient** : This is the Client class for producer and the consumer  
**KafkaMessageProcessor** : This is the Message Processor to Poll the messages from the producer  
**Demo** :  Main executable file consist of the Producer and Consumer configuration and their initiation calls  

## Laguage

Code is written in **Java** and any Java IDE can be used to read the code

## Prerequisite
* If running locally, create a Kafka-topic "bigdata-demo" or change the global variable name in the Demo.java file
* This project assumes you have a running Kafka instace which is accesible via "localhost:9092"
* If you are running on a different host or port, please edit the Demo.java accordingly for the "bootstrap.servers" parameter of both Producer and Consumer

## Instructions To Run
* Run the kafka-client/src/main/java/Demo.java 
* Verify the Console Output for the Producer and the Consumer messages


