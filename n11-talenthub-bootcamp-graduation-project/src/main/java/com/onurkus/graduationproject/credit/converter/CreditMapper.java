package com.onurkus.graduationproject.credit.converter;

import com.onurkus.graduationproject.credit.dto.CreditDto;
import com.onurkus.graduationproject.credit.entity.Credit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CreditMapper {
    CreditMapper INSTANCE= Mappers.getMapper(CreditMapper.class);

    @Mapping(source = "customerId",target="customerId.id")
    Credit convertToCredit(CreditDto creditDto);

    @Mapping(source = "customerId.id",target="customerId")
    CreditDto convertToCreditDto(Credit credit);

}
