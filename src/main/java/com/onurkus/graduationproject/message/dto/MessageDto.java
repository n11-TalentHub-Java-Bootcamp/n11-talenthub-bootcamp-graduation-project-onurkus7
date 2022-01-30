package com.onurkus.graduationproject.message.dto;

import lombok.Data;

@Data
public class MessageDto {

    private Long customerId;
    private Long identityId;
    private String phoneNumber;
    private String messageContent;

}
