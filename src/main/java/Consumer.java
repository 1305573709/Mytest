import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class Consumer {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "192.168.231.128:9092");
        //指定消费者组
        properties.setProperty("group.id", "test");
        //自动提交offset
        properties.setProperty("enable.auto.commit", "true");
        properties.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(properties);
        kafkaConsumer.subscribe(Arrays.asList("test"));
        while (true) {
            ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(Duration.ofSeconds(5));

            for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                String topic = consumerRecord.topic();
                long offset = consumerRecord.offset();
                String key = consumerRecord.key();
                String value = consumerRecord.value();
                System.out.println("topic:" + topic + " offset:" + offset + " key:" + key + " value:" + value);

            }

        }
    }
}
