package com.onurkus.graduationproject.message.controller;

import com.onurkus.graduationproject.message.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("app/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping("/send-message/{identityId}")
    public void sendMessageByIdentityId(@PathVariable Long identityId) {
        messageService.sendMessageByIdentityId(identityId);
    }

}
