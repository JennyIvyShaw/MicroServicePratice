package com.cst8277.messages.controllers;

import com.cst8277.messages.dao.MessageImplemetationRepository;
import com.cst8277.messages.dto.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class MessageController {

    @Autowired
    MessageImplemetationRepository messageImpRepository;

    @RequestMapping(method= RequestMethod.GET, path="/messages")
    public List<Message> findAllMessages(){
        return messageImpRepository.findAllMessages();
    }

    @RequestMapping(method= RequestMethod.GET, path="/messages/message/producer/{id}")
    public List <Message> findAllMessagesFromProducer(@PathVariable("id") UUID id){
        return messageImpRepository.getAllMessagesFromProducer(id);
    }

    @RequestMapping(method= RequestMethod.GET, path="/messages/message/subscriber/{id}")
    public List<Message> findAllMessagesForSubscriber(@PathVariable("id")UUID id){
        return messageImpRepository.getMessagesForSubscriber(id);
       }


    @RequestMapping(method= RequestMethod.POST, path="/messages/message")
    public Message createMessage(@RequestBody Message message){
        return messageImpRepository.createMessage(message);
    }

    @RequestMapping(method= RequestMethod.DELETE, path="/messages/message/{message-id}")
    public String deleteMessageById (@PathVariable("message-id") UUID messageId){
        return messageImpRepository.deleteMessageById(messageId);
    }
}
