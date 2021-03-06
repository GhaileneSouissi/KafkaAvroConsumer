package avroConsumer;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import schema.User;


import java.util.Arrays;
import java.util.Properties;

/***
 * A consumer for avro messages
 */
public class KafkaAvroConsumer {
    public static void main(String[] args) {
        //call the properties configuration
        PropConfig propConfig = new PropConfig();
        //property file
        Properties props = new Properties();
        propConfig.setProperties(props);
        //read the topic from the console
        if (args.length != 1) {
            System.err.println("Please specify 1 parameters ");
            System.exit(-1);
        }
        String topicName = args[0];
        //a consumer, that consumes avro messages (user is generated by an avro schema in avro/User.avsc)
       Consumer<String, User> consumer = new KafkaConsumer<String,User>(props);
       //subscription
       consumer.subscribe(Arrays.asList(topicName));
        //consuming the messages
        try{
            while (true){
                ConsumerRecords<String,User> records = consumer.poll(100);
                for (ConsumerRecord<String, User> record : records) {
                    System.out.printf("offset = %d, key = %s, value = %s \n", record.offset(), record.key(), record.value());
                }
            }
        }finally {
            consumer.close();
        }
    }
}
