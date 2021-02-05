package com.springboot.sns;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;

// Lombok annotation
// Causes Lombok to generate a logger field.
@Slf4j
// Spring framework annotation
// Main implementation class which serves two purpose in a spring boot application: Configuration and bootstrapping.
@SpringBootApplication
public class SpringbootSnsMobile {

    // Main program to start up the spring boot application.
    public static void main(String[] args) {
		/*
		 * //AmazonSNSClient snsClient = new AmazonSNSClient(); String message =
		 * "My SMS message"; String phoneNumber = "+919178100319"; Map<String,
		 * MessageAttributeValue> smsAttributes = new HashMap<String,
		 * MessageAttributeValue>(); smsAttributes.put("AWS.SNS.SMS.SenderID", new
		 * MessageAttributeValue() .withStringValue("mywebsite") //The sender ID shown
		 * on the device. .withDataType("String"));
		 * 
		 * smsAttributes.put("AWS.SNS.SMS.SMSType", new MessageAttributeValue()
		 * .withStringValue("Transactional") //Sets the type to promotional.
		 * .withDataType("String")); //<set SMS attributes>
		 * sendSMSMessage(amazonSNSClient, message, phoneNumber, smsAttributes);
		 */
        SpringApplication.run(SpringbootSnsMobile.class, args);
        
    }
    
	/*
	 * public static void sendSMSMessage(AmazonSNSClient snsClient, String message,
	 * String phoneNumber, Map<String, MessageAttributeValue> smsAttributes) {
	 * PublishResult result = snsClient.publish(new PublishRequest()
	 * .withMessage(message) .withPhoneNumber(phoneNumber)
	 * .withMessageAttributes(smsAttributes)); System.out.println(result); // Prints
	 * the message ID. }
	 */
}
