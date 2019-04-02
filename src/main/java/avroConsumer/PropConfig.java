package avroConsumer;

import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;
import io.confluent.kafka.serializers.KafkaAvroSerializer;

import java.util.Properties;

/***
 * Properties configuration class
 */
public class PropConfig {
    /***
     * setting properties for consumer
     * @param props
     */
    public void setProperties(Properties props){
        // Set the brokers (bootstrap servers)
        props.setProperty("bootstrap.servers", "10.75.17.84:9092");
        // Set how to serialize key/value pairs
        // Set how to deserialize key/value pairs
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        //Avro Deserialization
        props.put("value.deserializer", KafkaAvroDeserializer.class.getName());
        //the url for the schema registy
        props.put("schema.registry.url","http://10.75.17.84:8081");
        props.put("group.id","group-artik");
        //Use Specific Record or else you get Avro GenericRecord.
        props.put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, "true");



    }
}
