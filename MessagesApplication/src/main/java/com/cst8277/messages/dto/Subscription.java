package com.cst8277.messages.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subscription {
    private UUID producers_id;
    private UUID subscribers_id;

    public UUID getProducers_id() {
        return producers_id;
    }

    public void setProducers_id(UUID producers_id) {
        this.producers_id = producers_id;
    }

    public UUID getSubscribers_id() {
        return subscribers_id;
    }

    public void setSubscribers_id(UUID subscribers_id) {
        this.subscribers_id = subscribers_id;
    }
}
