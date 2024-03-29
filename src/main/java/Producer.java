import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.server.quota.ClientQuotaEntity;



import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Producer {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "192.168.24.130:9092");
        properties.put("acks", "all");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(properties);

        for (int i = 0; i < 9999; i++) {
            ProducerRecord<String, String> producerRecord = new ProducerRecord<>("test2", null, i + "");
            Future<RecordMetadata> future = kafkaProducer.send(producerRecord);
            //调用Future的get方法等待响应
            future.get();
            System.out.println("Produce " + i + " Message!");
        }
        kafkaProducer.close();
    }
}
