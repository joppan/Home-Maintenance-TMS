package com.valarkot.tms.dto;

import lombok.Data;

@Data
public class UpdateTicketRequest {
    private String description;
    private String category;
    private String priority;
    private String status;
    private String dueDate;
    private String assigneeId;
    private String imageUrl;
}