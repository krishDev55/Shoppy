package com.ShoppyCart;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication()
@EnableCaching
public class ShoppyCartApplication //implements CommandLineRunner
									{

	public static void main(String[] args) throws IOException {
		SpringApplication.run(ShoppyCartApplication.class, args);
		
	}

//	@Override
//	public void run(String... args) throws Exception {
//		
//		System.out.println("\n\nstarting the zookeeper.......................!!!!!!!\n");
//		
//		startZookiperOnBoot();
//		
//		System.out.println("\n\nstarting the KafkaServers.......................!!!!!!!\n");
//		
//		startKafkaOnBoot();
//	}
	
	
	public void startZookiperOnBoot() throws IOException {
	       
		String query="c:\\software\\kafka\\bin\\windows\\zookeeper-server-start.bat"	;        	
			    String query1 ="c:\\software\\kafka\\config\\zookeeper.properties";      
				ProcessBuilder processBuilder = new ProcessBuilder(query,query1);
			            processBuilder.inheritIO();
			            try {
			                Process process = processBuilder.start();
			                process.waitFor(30,TimeUnit.SECONDS);
			              
			            } catch ( InterruptedException e) {
			                e.printStackTrace();
			            }
			   
			    }
	
	
	 public void startKafkaOnBoot() throws IOException {
	     
String query="c:\\software\\kafka\\bin\\windows\\kafka-server-start.bat"	;        	
	    String query1 ="c:\\software\\kafka\\config\\server.properties";      
		ProcessBuilder processBuilder = new ProcessBuilder(query,query1);
	            processBuilder.inheritIO();
	            try {
	                Process process = processBuilder.start();
	                process.waitFor(30,TimeUnit.SECONDS);
	            } catch ( InterruptedException e) {
	                e.printStackTrace();
	            }
	      
	    }
			
	 
	 public void stopKafkaOnBoot() throws IOException {
	     
		 String query="D:\\software\\kafka\\bin\\windows\\kafka-server-stop.bat"	;        	
		 	    String query1 ="D:\\software\\kafka\\config\\server.properties";      
		 		ProcessBuilder processBuilder = new ProcessBuilder(query,query1);
		 	            processBuilder.inheritIO();
		 	            try {
		 	                Process process = processBuilder.start();
		 	                process.waitFor();
		 	            } catch ( InterruptedException e) {
		 	                e.printStackTrace();
		 	            }
		 	      
		 	    }
	 
	 public void stopZookiperOnBoot() throws IOException {
	       
			String query="D:\\software\\kafka\\bin\\windows\\zookeeper-server-stop.bat"	;        	
				    String query1 ="D:\\software\\kafka\\config\\zookeeper.properties";      
					ProcessBuilder processBuilder = new ProcessBuilder(query,query1);
				            processBuilder.inheritIO();
				            try {
				                Process process = processBuilder.start();
				                process.waitFor(30,TimeUnit.SECONDS);
				              
				            } catch ( InterruptedException e) {
				                e.printStackTrace();
				            }
				   
				    }

}
