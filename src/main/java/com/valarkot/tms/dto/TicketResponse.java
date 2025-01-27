package com.valarkot.tms.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class TicketResponse {
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
}