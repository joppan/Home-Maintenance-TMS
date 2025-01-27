package com.valarkot.tms.controller;

import com.valarkot.tms.dto.CreateTicketRequest;
import com.valarkot.tms.dto.UpdateTicketRequest;
import com.valarkot.tms.dto.TicketResponse;
import com.valarkot.tms.dto.TicketResponseMessage;
import com.valarkot.tms.dto.MessageResponse;
import com.valarkot.tms.dto.TicketListResponse;
import com.valarkot.tms.service.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public ResponseEntity<Object> createTicket(
            @Valid @RequestBody CreateTicketRequest request) {
        TicketResponse createdTicket = ticketService.createTicket(request);
        return ResponseEntity.ok(
            new TicketResponseMessage("Ticket created successfully!", createdTicket)
        );
    }

    @PutMapping("/{ticketId}")
    public ResponseEntity<Object> updateTicket(
            @PathVariable String ticketId,
            @RequestBody UpdateTicketRequest request) {
        TicketResponse updatedTicket = ticketService.updateTicket(ticketId, request);
        return ResponseEntity.ok(
            new TicketResponseMessage("Ticket updated successfully!", updatedTicket)
        );
    }

    @DeleteMapping("/{ticketId}")
    public ResponseEntity<Object> deleteTicket(@PathVariable String ticketId) {
        try {
            ticketService.deleteTicket(ticketId);
            return ResponseEntity.ok(new MessageResponse("Ticket deleted successfully."));
        } catch (Exception e) {
            return ResponseEntity.status(404).body(new MessageResponse("Ticket not found for deletion."));
        }
    }

    @GetMapping("/{ticketId}")
    public ResponseEntity<Object> getTicket(@PathVariable String ticketId) {
        TicketResponse ticketResponse = ticketService.getTicket(ticketId);
        return ResponseEntity.ok(
            new TicketResponseMessage("Ticket found", ticketResponse)
        );
    }

    @GetMapping("/assignee/{assigneeId}")
    public ResponseEntity<Object> getTicketsByAssignee(
            @PathVariable String assigneeId) {
        List<TicketResponse> tickets = ticketService.getTicketsByAssignee(assigneeId);
        return ResponseEntity.ok(
            new TicketListResponse(tickets.size(), tickets)
        );
    }

    @GetMapping
    public ResponseEntity<Object> getAllTickets() {
        List<TicketResponse> tickets = ticketService.getAllTickets();
        return ResponseEntity.ok(
            new TicketListResponse(tickets.size(), tickets)
        );
    }
}
