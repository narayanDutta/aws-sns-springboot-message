package com.springboot.sns.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

// Marker annotation that tells spring to generate bean definitions at runtime for the methods annotated with @Bean annotation.
@Configuration
public class AwsConfig {

    // Value is populated with the aws access key.
    @Value("${cloud.aws.credentials.access-key}")
    private String awsAccessKey;

    // Value is populated with the aws secret key
    @Value("${cloud.aws.credentials.secret-key}")
    private String awsSecretKey;

    // Value is populated with the aws region code
    @Value("${cloud.aws.region.static}")
    private String region;

    // @Primary annotation gives a higher preference to a bean (when there are multiple beans of same type).
    @Primary
    // @Bean annotation tells that a method produces a bean which is to be managed by the spring container.
    @Bean
    public AmazonSNSClient amazonSNSClient() {
        return (AmazonSNSClient) AmazonSNSClientBuilder
                .standard()
                .withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(
                        new BasicAWSCredentials(awsAccessKey, awsSecretKey)))
                .build();
    }
}
