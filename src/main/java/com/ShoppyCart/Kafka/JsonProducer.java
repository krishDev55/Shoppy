package com.ShoppyCart.Kafka;

//import org.apache.kafka.clients.producer.Producer;
//import org.apache.kafka.clients.producer.ProducerRecord;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.support.KafkaHeaders;
//import org.springframework.messaging.Message;
//import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.ShoppyCart.Kafka.vo.UserSend;





//@Service
public class JsonProducer {
//	@Autowired
//	private KafkaTemplate<String,UserSend > kafkaTemplate;
//
//	public void sendMessage(UserSend data) {
//		
////		Producer<String, UserSend> pr=kafkaTemplate.getProducerFactory().createNonTransactionalProducer();
//	
//		
//		Message<UserSend>  message =MessageBuilder
//								.withPayload(data)
//								.setHeader(KafkaHeaders.TOPIC, "javaToken")
//								
//								.build();
//		kafkaTemplate.send(message);
//	}
}
