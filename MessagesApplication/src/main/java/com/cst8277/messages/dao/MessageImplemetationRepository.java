package com.cst8277.messages.dao;

import com.cst8277.messages.dto.Message;
import com.cst8277.messages.dto.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MessageImplemetationRepository implements MessageRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    final static String GET_ALL_MESSAGES = "SELECT * FROM messages.messages;";
    final static String GET_MESSAGES_MADE_BY_PRODUCER= "SELECT * FROM messages.messages WHERE producer_id = (UUID_TO_BIN(?));";
    final static String GET_ALL_MESSAGES_FOR_A_SUBSCRIBER = "SELECT messages.id, messages.content, messages.created, messages.producer_id FROM messages.messages " +
            "LEFT JOIN messages.producers on messages.producer_id = producers.id " +
            "LEFT JOIN messages.subscriptions on subscriptions.producers_id = producers.id " +
            "LEFT JOIN messages.subscribers on subscriptions.subscribers_id = subscribers.id " +
            "WHERE messages.subscribers.id = (UUID_TO_BIN(?));";
    final static String CREATE_MESSAGE = "INSERT INTO messages.messages (id, content, created, producer_id) VALUES (UUID_TO_BIN(?),?,?,UUID_TO_BIN(?));";
    final static String DELETE_MESSAGE = "DELETE FROM messages.messages WHERE id = (UUID_TO_BIN(?));";

    final static String CREATE_SUBSCRIPTION ="INSERT INTO messages.subscriptions (producers_id, subscribers_id) VALUES (UUID_TO_BIN(?),UUID_TO_BIN(?));";

    final static String DELETE_SUBSCRIPTION="DELETE FROM messages.subscriptions WHERE producers_id = (UUID_TO_BIN(?)) AND subscribers_id =(UUID_TO_BIN(?));";



    @Override
    public List<Message> findAllMessages() {
        return jdbcTemplate.query(GET_ALL_MESSAGES, (rs, rowNum) -> {
            return new Message(BytesConverter.bytesToUuid(rs.getBytes("id")), rs.getString("content" ), rs.getInt("created"), BytesConverter.bytesToUuid(rs.getBytes("producer_id")));
        });
    }

    @Override
    public List <Message> getAllMessagesFromProducer(UUID id) {
        List<Map<String,Object>> message = jdbcTemplate.queryForList(GET_MESSAGES_MADE_BY_PRODUCER, id.toString());
        List <Message> messages = new ArrayList<>();
        message.forEach(rs ->{
            Message ms = new Message();
            ms.setId(BytesConverter.bytesToUuid((byte[]) rs.get("id")));
            ms.setContent((String)rs.get("content"));
            ms.setCreated((Integer)rs.get("created"));
            ms.setProducer_id(BytesConverter.bytesToUuid((byte[]) rs.get("producer_id")));
            messages.add(ms);
        });
        return messages;
    }

    @Override
    public List<Message> getMessagesForSubscriber(UUID id) {
        List<Map<String,Object>> message = jdbcTemplate.queryForList(GET_ALL_MESSAGES_FOR_A_SUBSCRIBER, id.toString());
        List <Message> messages = new ArrayList<>();
        message.forEach(rs ->{
            Message ms = new Message();
            ms.setId(BytesConverter.bytesToUuid((byte[]) rs.get("id")));
            ms.setContent((String)rs.get("content"));
            ms.setCreated((Integer)rs.get("created"));
            ms.setProducer_id(BytesConverter.bytesToUuid((byte[]) rs.get("producer_id")));
            messages.add(ms);
        });
        return messages;
    }

    @Override
    public Message createMessage(Message message) {
        jdbcTemplate.update(CREATE_MESSAGE, message.getId().toString(), message.getContent(), message.getCreated(), message.getProducer_id().toString());
        return message;
    }

    @Override
    public String deleteMessageById(UUID messageId) {
        jdbcTemplate.update(DELETE_MESSAGE, messageId.toString());
        return "Message" + messageId.toString() + " has been deleted.";

    }

    @Override
    public Subscription createSubscription(Subscription subscription) {
        jdbcTemplate.update(CREATE_SUBSCRIPTION, subscription.getProducers_id().toString(), subscription.getSubscribers_id().toString());
        return subscription;
    }

    @Override
    public String deleteSubscribstion(UUID producers_id, UUID subscribers_id) {
        jdbcTemplate.update(DELETE_SUBSCRIPTION, producers_id.toString(), subscribers_id.toString());
        return "Your subscrption to " + producers_id.toString() + " has been removed.";
    }
}
