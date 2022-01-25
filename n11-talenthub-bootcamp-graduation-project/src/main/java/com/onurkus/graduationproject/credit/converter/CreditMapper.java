package com.onurkus.graduationproject.credit.converter;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CreditMapper {
    CreditMapper INSTANCE= Mappers.getMapper(CreditMapper.class);
}
