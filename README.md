Kafka相关代码
nohup kafka-server-start.sh config/server.properties >/opt/modules/kafka_2.11-2.2.1/Kafka.log  2>&1  &

kafka-topics.sh --create  --zookeeper localhost:2181 --replication-factor 1  --partitions 2  --topic test

kafka-topics.sh --list --zookeeper localhost:2181

kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic test

kafka-topics.sh --zookeeper localhost:2181 --describe --topic test

kafka-console-producer.sh --broker-list localhost:9092 --topic test

kafka-console-consumer.sh  --bootstrap-server localhost:9092 --clientid test --from-beginning --topic test

consumer.config

kafka-topics.sh --zookeeper localhost:2181  --delete --topic test
（注：不能真正删除topic只是把这个topic标记为删除（marked for deletion），要彻底把topic删除必须把kafka中与当前topic相关的数据目录和zookeeper中与当前topic相关的路径一并删除。）


kafka-configs.sh  --zookeeper localhost:2181 --alter --add-config 'producer_byte_rate=1024,consumer_byte_rate=1024' --entity-type clients --entity-name p1


kafka-producer-perf-test.sh --topic test --record-size 100 --num-records 991000000 --throughput -1 --producer-props bootstrap.servers=localhost:9092

kafka-consumer-perf-test.sh --broker-list localhost:9092 --topic test --fetch-size 10000 -messages 99000000 --threads 1  client.id=c1

kafka-producer-perf-test.sh --topic test --num-records 10000 --record-size 100 --throughput 150 --producer-props bootstrap.servers=localhost:9092 client.id=c1
