package project.config;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class KafkaProducerConfig {


    public KafkaProducerConfig(){
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:8081");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringDeserializer");

        ProducerRecord record = new ProducerRecord("channel", "name", "selftuts");

        KafkaProducer kafkaProducer = new KafkaProducer(properties);
        kafkaProducer.send(record);
        kafkaProducer.close();
    }

}
