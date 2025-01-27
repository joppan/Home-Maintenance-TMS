package com.valarkot.tms.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;

@Data
public class CreateTicketRequest {
    @NotBlank
    private String description;
    
    @NotBlank
    private String category;
    
    @NotBlank
    private String priority;

    private String createdByUserId;
    private String dueDate;
    private String assigneeId;
    private String imageUrl;
}