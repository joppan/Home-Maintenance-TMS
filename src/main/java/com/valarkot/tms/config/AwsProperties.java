// package com.valarkot.tms.config;

// import org.springframework.boot.context.properties.ConfigurationProperties;
// import org.springframework.context.annotation.Configuration;
// import lombok.Data;

// @Data
// @Configuration
// @ConfigurationProperties(prefix = "aws")
// public class AwsProperties {
    
//     private Credentials credentials = new Credentials();
//     private String region;
//     private DynamoDB dynamodb = new DynamoDB();

//     @Data
//     public static class Credentials {
//         private String accessKey;
//         private String secretKey;
//     }

//     @Data
//     public static class DynamoDB {
//         private String endpoint;
//     }
// }