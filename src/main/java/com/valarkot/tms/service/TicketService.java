package com.valarkot.tms.service;

import com.valarkot.tms.dto.CreateTicketRequest;
import com.valarkot.tms.dto.UpdateTicketRequest;
import com.valarkot.tms.dto.TicketResponse;
import com.valarkot.tms.exception.TicketNotFoundException;
import com.valarkot.tms.model.Ticket;
import com.valarkot.tms.repository.TicketRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public TicketResponse createTicket(CreateTicketRequest request) {
        Ticket ticket = new Ticket();
        
        // Set ticket ID, status, and timestamps
        ticket.setTicketId(UUID.randomUUID().toString());
        ticket.setStatus("OPEN");
        ticket.setCreatedAt(LocalDateTime.now());
        ticket.setUpdatedAt(LocalDateTime.now());
    
        // Set fields only if they're provided (optional fields)
        if (request.getDescription() != null) {
            ticket.setDescription(request.getDescription());
        }
        if (request.getCategory() != null) {
            ticket.setCategory(request.getCategory());
        }
        if (request.getPriority() != null) {
            ticket.setPriority(request.getPriority());
        }
        if (request.getCreatedByUserId() != null) {
            ticket.setCreatedByUserId(request.getCreatedByUserId());
        }
        if (request.getAssigneeId() != null) {
            ticket.setAssigneeId(request.getAssigneeId());
        }
        if (request.getImageUrl() != null) {
            ticket.setImageUrl(request.getImageUrl());
        }
        
        // Handle dueDate if provided (optional)
        if (request.getDueDate() != null) {
            // Convert String to LocalDateTime and then back to String (ISO format)
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
            String formattedDueDate = LocalDateTime.parse(request.getDueDate(), formatter).format(formatter);
            ticket.setDueDate(formattedDueDate);  // Set as String
        }
    
        // Save the ticket
        Ticket savedTicket = ticketRepository.save(ticket);
        
        return convertToResponse(savedTicket);
    }

    public TicketResponse updateTicket(String ticketId, UpdateTicketRequest request) {
        Ticket ticket = ticketRepository.findById(ticketId)
            .orElseThrow(() -> new TicketNotFoundException(ticketId));

        if (request.getDescription() != null) ticket.setDescription(request.getDescription());
        if (request.getCategory() != null) ticket.setCategory(request.getCategory());
        if (request.getPriority() != null) ticket.setPriority(request.getPriority());
        if (request.getStatus() != null) ticket.setStatus(request.getStatus());
        if (request.getDueDate() != null) ticket.setDueDate(request.getDueDate());
        if (request.getAssigneeId() != null) ticket.setAssigneeId(request.getAssigneeId());
        if (request.getImageUrl() != null) ticket.setImageUrl(request.getImageUrl());

        ticket.setUpdatedAt(LocalDateTime.now());
        
        Ticket updatedTicket = ticketRepository.save(ticket);
        return convertToResponse(updatedTicket);
    }

    public void deleteTicket(String ticketId) {
        if (!ticketRepository.existsById(ticketId)) {
            throw new TicketNotFoundException(ticketId);
        }
        ticketRepository.deleteById(ticketId);
    }

    public TicketResponse getTicket(String ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId)
            .orElseThrow(() -> new TicketNotFoundException(ticketId));
        return convertToResponse(ticket);
    }

    public List<TicketResponse> getTicketsByAssignee(String assigneeId) {
        return ticketRepository.findAll().stream()
            .filter(ticket -> assigneeId.equals(ticket.getAssigneeId()))
            .map(this::convertToResponse)
            .collect(Collectors.toList());
    }

    public List<TicketResponse> getAllTickets() {
        return ticketRepository.findAll().stream()
            .map(this::convertToResponse)
            .collect(Collectors.toList());
    }

    private TicketResponse convertToResponse(Ticket ticket) {
        TicketResponse response = new TicketResponse();
        BeanUtils.copyProperties(ticket, response);
        return response;
    }
}
