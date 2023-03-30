package com.cst8277.messages.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    private UUID id;
    private String content;
    private int created;
    private UUID producer_id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCreated() {
        return created;
    }

    public void setCreated(int created) {
        this.created = created;
    }

    public UUID getProducer_id() {
        return producer_id;
    }

    public void setProducer_id(UUID producer_id) {
        this.producer_id = producer_id;
    }
}
