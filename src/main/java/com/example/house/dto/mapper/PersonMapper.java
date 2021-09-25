package com.example.house.dto.mapper;

import com.example.house.dto.LogonPersonDto;
import com.example.house.dto.PersonAttemptToLoginDto;
import com.example.house.dto.PersonRegisterDto;
import com.example.house.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonMapper {
    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    Person getEntityFromAttemptLoginDto(PersonAttemptToLoginDto dto);
    Person getEntityFromPersonRegisterDto(PersonRegisterDto dto);

    LogonPersonDto getLogonDto(Person entity);
}
