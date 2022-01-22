package com.onurkus.graduationproject.converter;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MessageMapper {

    MessageMapper INSTANCE= Mappers.getMapper(MessageMapper.class);
}
