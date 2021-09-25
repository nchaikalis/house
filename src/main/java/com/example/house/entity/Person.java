package com.example.house.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "person", schema = "public", catalog = "postgres")
@ToString
@Getter
@Setter
public class Person implements Serializable {
    @Id
    @NotNull
    @Basic(optional = false)
    @Column(name = "person_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int personId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "username")
    private String username;

    @Column(name = "user_password")
    private String userPassword;

    @Column(name = "user_role")
    private String role;

    @OneToMany(mappedBy = "personId")
    private List<Asset> passwordsById;

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
