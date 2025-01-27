package com.valarkot.tms.dto;

import java.util.List;

public class TicketListResponse {
    private int count;
    private List<TicketResponse> tickets;

    public TicketListResponse(int count, List<TicketResponse> tickets) {
        this.count = count;
        this.tickets = tickets;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<TicketResponse> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketResponse> tickets) {
        this.tickets = tickets;
    }
}
