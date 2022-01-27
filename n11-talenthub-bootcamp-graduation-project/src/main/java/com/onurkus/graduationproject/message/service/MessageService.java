package com.onurkus.graduationproject.message.service;

import com.onurkus.graduationproject.message.converter.MessageMapper;
import com.onurkus.graduationproject.message.dao.MessageDao;
import com.onurkus.graduationproject.message.dto.MessageDto;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageDao messageDao;

    private static final String ACCOUNT_SID = "";
    private static final String AUTH_TOKEN = "";

    MessageDto messageDto=new MessageDto();

    public void sendMessageByIdentityId(Long identityId){
        //messageDao.findMessageByIdentityId(identityId);
        //messageDto = MessageMapper.INSTANCE.convertToMessageDto(messageDao.findMessageByIdentityId(identityId));
        twilioSmsProcess();
    }

    public void twilioSmsProcess(){

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(
                new com.twilio.type.PhoneNumber(""),
                new com.twilio.type.PhoneNumber("+17755225036"),
                "K4RK1N BANK: Your loan application has been APPROVED. Your Credit Limit: $61000")
                .create();

    }
     //messageDto.getPhoneNumber()  messageDto.getMessageContent()
}
