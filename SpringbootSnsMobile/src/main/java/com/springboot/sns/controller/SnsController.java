package com.springboot.sns.controller;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.springboot.sns.model.Notification;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// Causes Lombok to generate a logger field.
@Slf4j
@RestController
public class SnsController {

    // Topic arn. Developers are free to choose their own topic arn.
    private static final String TOPIC_ARN = "arn:aws:sns:ap-south-1:278105647443:aws-sns-mumbai";

    @Autowired
    private AmazonSNSClient amazonSNSClient;

    
    @PostMapping(value = "/addSubscription/{phoneNumber}")
    public ResponseEntity<String> addSubscription(@PathVariable final String phoneNumber) {
        //log.info("Adding new phone number subscription = {} to the topic.", phoneNumber);
        final SubscribeRequest subscribeRequest = new SubscribeRequest(TOPIC_ARN, "sms", phoneNumber);
        amazonSNSClient.subscribe(subscribeRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    
    @PostMapping(value = "/sendNotification")
    public ResponseEntity<String> publishMessageToTopic(@RequestBody final Notification notification) {
        //log.info("Publishing the notification = {} to the topic.", notification.toString());
    	Map<String, MessageAttributeValue> smsAttributes =
                new HashMap<String, MessageAttributeValue>();
    	smsAttributes.put("AWS.SNS.SMS.SMSType", new MessageAttributeValue()
                .withStringValue("Transactional") //Sets the type to promotional.
                .withDataType("String"));
        final PublishRequest publishRequest = new PublishRequest(TOPIC_ARN, notification.getMessage());
        publishRequest.setMessageAttributes(smsAttributes);
        amazonSNSClient.publish(publishRequest);
        return new ResponseEntity<>("Notification sent successfully!!", HttpStatus.OK);
    }
    @PostMapping(value = "/sendmessage")
    public ResponseEntity<String> sendMessageToPhone(@RequestBody final Notification notification) {
        //log.info("Publishing the notification = {} to the topic.", notification.toString());
    	String phoneNumber = "+919178100319";
    	//String[] phoneNumbers = new String[]{"+917760041698", "917760041698", "7760041698" };

    	String message=notification.getMessage();
    	Map<String, MessageAttributeValue> smsAttributes =
                new HashMap<String, MessageAttributeValue>();
    	smsAttributes.put("AWS.SNS.SMS.SMSType", new MessageAttributeValue()
                .withStringValue("Transactional") //Sets the type to promotional.
                .withDataType("String"));
    	smsAttributes.put("AWS.SNS.SMS.SenderID", new MessageAttributeValue()
    	        .withStringValue("mywebsite") //The sender ID shown on the device.
    	        .withDataType("String"));
        final PublishRequest publishRequest = new PublishRequest().withMessage(message).withPhoneNumber(phoneNumber).withMessageAttributes(smsAttributes);
        //publishRequest.setMessageAttributes(smsAttributes);
        amazonSNSClient.publish(publishRequest);
        return new ResponseEntity<>("Notification sent successfully!!", HttpStatus.OK);
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
