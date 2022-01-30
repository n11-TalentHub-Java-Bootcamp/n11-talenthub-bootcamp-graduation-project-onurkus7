package com.onurkus.graduationproject.message.converter;

import com.onurkus.graduationproject.message.dto.MessageDto;
import com.onurkus.graduationproject.message.entity.Message;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MessageMapper {

    MessageMapper INSTANCE= Mappers.getMapper(MessageMapper.class);

    @Mapping(source ="customerId", target="customerId.id")
    Message convertToMessage(MessageDto messageDto);


}
