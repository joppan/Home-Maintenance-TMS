package com.valarkot.tms.repository;

import com.valarkot.tms.model.Ticket;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.PageIterable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TicketRepository {

    private final DynamoDbTable<Ticket> ticketTable;

    public TicketRepository(DynamoDbEnhancedClient enhancedClient) {
        this.ticketTable = enhancedClient.table("tickets", 
            TableSchema.fromBean(Ticket.class));
    }

    public Ticket save(Ticket ticket) {
        if (ticket.getCreatedAt() == null) {
            ticket.setCreatedAt(LocalDateTime.now());
        }
        ticket.setUpdatedAt(LocalDateTime.now());
        ticketTable.putItem(ticket);
        return ticket;
    }

    public Optional<Ticket> findById(String ticketId) {
        Key key = Key.builder().partitionValue(ticketId).build();
        return Optional.ofNullable(ticketTable.getItem(key));
    }

    public List<Ticket> findAll() {
        PageIterable<Ticket> results = ticketTable.scan();
        List<Ticket> tickets = new ArrayList<>();
        results.items().forEach(tickets::add);
        return tickets;
    }

    public void deleteById(String ticketId) {
        Key key = Key.builder().partitionValue(ticketId).build();
        ticketTable.deleteItem(key);
    }

    public boolean existsById(String ticketId) {
        return findById(ticketId).isPresent();
    }
}
