package com.application.kafkaconsumer.configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsSESConfiguration {

    @Value("${aws.access.ses}")
    private String access;

    @Value("${aws.secret.ses}")
    private String secret;

    @Value("${aws.region.ses}")

    private String region;

    @Bean
    public AWSCredentials getAwsCredentials(){
        return new BasicAWSCredentials(access, secret);
    }

    @Bean
    public com.amazonaws.services.simpleemail.AmazonSimpleEmailService getAwsClient(AWSCredentials awsCredentials){
        return AmazonSimpleEmailServiceClientBuilder
                .standard().withCredentials(new AWSStaticCredentialsProvider(awsCredentials)).withRegion(region).build();
    }
}