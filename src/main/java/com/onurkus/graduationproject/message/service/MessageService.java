package com.onurkus.graduationproject.message.service;

import com.onurkus.graduationproject.credit.dto.CreditDto;
import com.onurkus.graduationproject.customer.service.CustomerService;
import com.onurkus.graduationproject.message.converter.MessageMapper;
import com.onurkus.graduationproject.message.dto.MessageDto;
import com.onurkus.graduationproject.message.repository.MessageRepository;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final CustomerService customerService;

    private static final Logger LOGGER = LogManager.getLogger(MessageService.class);


    private static final String ACCOUNT_SID = "ACb22980023a710da4fc6582f758b15d3a";
    private static final String AUTH_TOKEN = "2cf665672d395d74a545a3e687758404";
    private static final String BASE_PHONE_NUMBER = "+17755225036";

    public void sendMessageByIdentityId(CreditDto creditDto) {

        String phoneNumber = customerService.findPhoneNumberByIdentityId(creditDto.getIdentityId());

        String messageContent = twilioSmsProcess(creditDto, phoneNumber);
        saveMessage(creditDto, phoneNumber, messageContent);

        LOGGER.info("Necessary configurations have been made to send sms to the customer with "
                + creditDto.getIdentityId());
    }

    private String twilioSmsProcess(CreditDto creditDto, String phoneNumber) {

        String messageContent = "K4RK1N BANK: Your loan application has been " + creditDto.getCreditStatus() +
                ". Your Credit Limit: " + creditDto.getCreditLimit();

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message.creator(
                new com.twilio.type.PhoneNumber(phoneNumber),
                new com.twilio.type.PhoneNumber(BASE_PHONE_NUMBER),
                messageContent)
                .create();

        LOGGER.info("Credit information was sent to the customer with the phone number " + phoneNumber + " as an sms.");

        return messageContent;
    }

    private void saveMessage(CreditDto creditDto, String phoneNumber, String messageContent) {

        MessageDto messageDto = new MessageDto();
        messageDto.setCustomerId(creditDto.getCustomerId());
        messageDto.setIdentityId(creditDto.getIdentityId());
        messageDto.setPhoneNumber(phoneNumber);
        messageDto.setMessageContent(messageContent);

        messageRepository.save(MessageMapper.INSTANCE.convertToMessage(messageDto));
        LOGGER.info("The message information sent to the customer with the phone number " +
                phoneNumber + " has been recorded in the database.");
    }

}
