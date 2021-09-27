package com.example.house.dto.mapper;

import com.example.house.dto.person.LogonPersonDto;
import com.example.house.dto.person.PersonAttemptToLoginDto;
import com.example.house.dto.person.PersonRegisterDto;
import com.example.house.entity.Person;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-09-27T22:01:08+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.11 (Ubuntu)"
)
public class PersonMapperImpl implements PersonMapper {

    @Override
    public Person getEntityFromAttemptLoginDto(PersonAttemptToLoginDto dto) {
        if ( dto == null ) {
            return null;
        }

        Person person = new Person();

        person.setUsername( dto.getUsername() );
        person.setUserPassword( dto.getUserPassword() );

        return person;
    }

    @Override
    public Person getEntityFromPersonRegisterDto(PersonRegisterDto dto) {
        if ( dto == null ) {
            return null;
        }

        Person person = new Person();

        person.setFirstName( dto.getFirstName() );
        person.setLastName( dto.getLastName() );
        person.setUsername( dto.getUsername() );
        person.setUserPassword( dto.getUserPassword() );
        person.setRole( dto.getRole() );

        return person;
    }

    @Override
    public LogonPersonDto getLogonDto(Person entity) {
        if ( entity == null ) {
            return null;
        }

        LogonPersonDto logonPersonDto = new LogonPersonDto();

        logonPersonDto.setPersonId( entity.getPersonId() );
        logonPersonDto.setFirstName( entity.getFirstName() );
        logonPersonDto.setLastName( entity.getLastName() );
        logonPersonDto.setUsername( entity.getUsername() );
        logonPersonDto.setRole( entity.getRole() );

        return logonPersonDto;
    }
}
