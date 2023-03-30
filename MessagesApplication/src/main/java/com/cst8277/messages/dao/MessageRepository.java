package com.cst8277.messages.dao;

import com.cst8277.messages.dto.Message;
import com.cst8277.messages.dto.Subscription;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MessageRepository {

    List <Message> findAllMessages();

    List <Message> getAllMessagesFromProducer(UUID id);

    List <Message> getMessagesForSubscriber(UUID id);

    Message createMessage (Message message);

    String deleteMessageById (UUID messageId);

    Subscription createSubscription(Subscription subscription);

    String deleteSubscribstion(UUID producers_id, UUID subscribers_id);

}
