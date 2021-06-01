import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import java.util.Arrays;
import java.util.Properties;

public class Consumer {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "192.168.24.128:9092");
        //指定消费者组
        properties.setProperty("group.id", "test2");
        //自动提交offset
        properties.setProperty("enable.auto.commit", "true");
        properties.put(ConsumerConfig.REQUEST_TIMEOUT_MS_CONFIG, "60000");
        properties.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.setProperty(ConsumerConfig.CLIENT_ID_CONFIG,"test2");
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(properties);
        kafkaConsumer.subscribe(Arrays.asList("test2"));
        while (true) {
            ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(10000);
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
