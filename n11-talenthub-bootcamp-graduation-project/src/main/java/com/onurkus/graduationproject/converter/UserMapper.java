package com.onurkus.graduationproject.converter;

import com.onurkus.graduationproject.dto.UserDto;
import com.onurkus.graduationproject.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserMapper INSTANCE= Mappers.getMapper(UserMapper.class);

    UserDto convertToUserDto(User user);

    //User convertToUserSave(UserSaveDto userSaveDto);

}
