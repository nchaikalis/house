package com.example.house.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "person", schema = "public", catalog = "postgres")
@ToString
@Getter
@Setter
public class Person {
    @Id
    @Column(name = "person_id")
    private int personId;

    @Basic
    @Column(name = "first_name")
    private String firstName;

    @Basic
    @Column(name = "last_name")
    private String lastName;

    @Basic
    @Column(name = "username")
    private String username;

    @Basic
    @Column(name = "user_password")
    private String userPassword;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return personId == person.personId && Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName) && Objects.equals(username, person.username) && Objects.equals(userPassword, person.userPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId, firstName, lastName, username, userPassword);
    }
}
