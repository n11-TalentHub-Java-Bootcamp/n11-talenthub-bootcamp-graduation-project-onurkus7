package com.onurkus.graduationproject.message.service;

import com.onurkus.graduationproject.credit.dto.CreditDto;
import com.onurkus.graduationproject.customer.service.CustomerService;
import com.onurkus.graduationproject.message.converter.MessageMapper;
import com.onurkus.graduationproject.message.repository.MessageRepository;
import com.onurkus.graduationproject.message.dto.MessageDto;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final CustomerService customerService;

    private static final String ACCOUNT_SID = "ACb22980023a710da4fc6582f758b15d3a";
    private static final String AUTH_TOKEN = "2cf665672d395d74a545a3e687758404";
    private static final String BASE_PHONE_NUMBER = "+17755225036";

    public void sendMessageByIdentityId(CreditDto creditDto) {

        String phoneNumberByIdentityId = customerService.findPhoneNumberByIdentityId(creditDto.getIdentityId());

        String messageContent = twilioSmsProcess(creditDto, phoneNumberByIdentityId);
        saveMessage(creditDto, phoneNumberByIdentityId, messageContent);

    }

    private String twilioSmsProcess(CreditDto creditDto, String phoneNumberByIdentityId) {

        String messageContent = "K4RK1N BANK: Your loan application has been " + creditDto.getCreditStatus() +
                ". Your Credit Limit: " + creditDto.getCreditLimit();

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message.creator(
                new com.twilio.type.PhoneNumber(phoneNumberByIdentityId),
                new com.twilio.type.PhoneNumber(BASE_PHONE_NUMBER),
                messageContent)
                .create();

        return messageContent;

    }

    private void saveMessage(CreditDto creditDto, String phoneNumberByIdentityId, String messageContent) {
        MessageDto messageDto = new MessageDto();
        messageDto.setCustomerId(creditDto.getCustomerId());
        messageDto.setIdentityId(creditDto.getIdentityId());
        messageDto.setPhoneNumber(phoneNumberByIdentityId);
        messageDto.setMessageContent(messageContent);

        messageRepository.save(MessageMapper.INSTANCE.convertToMessage(messageDto));

    }

}
