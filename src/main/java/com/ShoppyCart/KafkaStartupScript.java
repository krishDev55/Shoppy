package com.ShoppyCart;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
//
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ShoppyCart.Kafka.vo.UserSend;

//@Configuration
public class KafkaStartupScript {
	
	  
	
	 @Value( "${spring.kafka.consumer.bootstrap-servers}")
	    String bootstrapAddress;
	    // Import all the other settings from application.yaml using @Value-notation

//	    @Bean
//	    public KafkaConsumer<String, UserSend> consumerFactory() {
//	        // Create props for Factory
//	        Map<String, Object> props = new HashMap<String, Object>();
//	        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
//	        props.put(ConsumerConfig.GROUP_ID_CONFIG, "mygroup");
//	        props.put("auto.offset.reset", "earliest");
//	        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,  "org.apache.kafka.common.serialization.StringDeserializer");
//	        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringDeserializer");      
//	        
//	        // ...
//
//	        return new KafkaConsumer<String, UserSend>(props);
//}
//	
//	
//
//	  public ApplicationRunner startKafkaOnBoot() {
//	        return args -> {
//String query="C:\\software\\kafka\\bin\\windows\\kafka-server-start.bat"	;        	
//	    String query1 ="C:\\software\\kafka\\config\\server.properties";      
//		ProcessBuilder processBuilder = new ProcessBuilder(query,query1);
//	            processBuilder.inheritIO();
//	            try {
//	                Process process = processBuilder.start();
//	                process.waitFor();
//	            } catch ( InterruptedException e) {
//	                e.printStackTrace();
//	            }
//	        };
//	    }
	
}
