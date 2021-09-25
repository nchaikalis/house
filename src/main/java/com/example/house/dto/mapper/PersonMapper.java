package com.example.house.dto.mapper;

import com.example.house.dto.person.LogonPersonDto;
import com.example.house.dto.person.PersonAttemptToLoginDto;
import com.example.house.dto.person.PersonRegisterDto;
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
