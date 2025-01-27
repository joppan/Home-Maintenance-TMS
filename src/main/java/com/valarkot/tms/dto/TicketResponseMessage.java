package com.valarkot.tms.dto;

public class TicketResponseMessage {
    private String message;
    private TicketResponse ticket;

    public TicketResponseMessage(String message, TicketResponse ticket) {
        this.message = message;
        this.ticket = ticket;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public TicketResponse getTicket() {
        return ticket;
    }

    public void setTicket(TicketResponse ticket) {
        this.ticket = ticket;
    }
}
