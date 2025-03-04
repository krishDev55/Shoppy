package com.ShoppyCart.Kafka;

import java.time.Duration;
import java.util.Collections;

//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.apache.kafka.clients.consumer.ConsumerRecords;
//import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ShoppyCart.Kafka.vo.UserSend;

//@Service
public class Consumers {
	
//	 @Autowired
//	 KafkaConsumer<String, UserSend> kafka;
//	
////	 KafkaConsumer<String, String> kafka1;
//		
//		public   void getMessege(String str) {
//			
//			 kafka.subscribe(Collections.singleton(str));
//		        kafka.seekToBeginning(kafka.assignment());
//		             kafka.beginningOffsets(kafka.assignment());
//
//		            ConsumerRecords<String, UserSend> consumerRecords = kafka.poll(Duration.ofMillis(1000));
//		     
//		            for (ConsumerRecord<String, UserSend> cr : consumerRecords) {
//		
//		               System.out.println("test.... "+cr.timestampType().toString()+" - " +cr.offset()+" - "+cr.partition()+" -- "+cr.key()+" -- "+cr.value());
//		            }
//			
//		}
}
