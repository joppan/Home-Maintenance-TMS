package com.valarkot.tms.model;

import lombok.Data;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import java.time.LocalDateTime;

@Data
@DynamoDbBean
public class Ticket {
    private String ticketId;
    private String description;
    private String category;
    private String priority;
    private String status;
    private String dueDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String assigneeId;
    private String createdByUserId;
    private String imageUrl;

    @DynamoDbPartitionKey
    public String getTicketId() {
        return ticketId;
    }
}