package com.cst8277.messages.controllers;

import com.cst8277.messages.dao.MessageImplemetationRepository;
import com.cst8277.messages.dto.Message;
import com.cst8277.messages.dto.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class SubscriptionController {

    @Autowired
    MessageImplemetationRepository messageImpRepository;

    @RequestMapping(method = RequestMethod.POST, path="/messages/subscription")
    public Subscription createSubcription(@RequestBody Subscription subscription){
        return messageImpRepository.createSubscription(subscription);
    }

    @RequestMapping(method= RequestMethod.DELETE, path="/messages/subscription/{producers-id}/{subscribers-id}")
    public String deleteSubscription (@PathVariable("producers-id") UUID producers_id, @PathVariable("subscribers-id") UUID subscribers_id){
        return messageImpRepository.deleteSubscribstion(producers_id, subscribers_id);
    }

}
