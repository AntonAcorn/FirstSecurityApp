package ru.acorn.FirstSecurityApp.modells;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Setter
    @Getter
    private int id;

    @NotEmpty(message = "Name should be not empty")
    @Size(min = 2, max = 100, message = "Name should be between 2 and 100")
    @Column(name = "username")
    @Setter
    @Getter
    private String username;

    @Min(value = 1900, message = "Year of birth should be more than 1900")
    @Column(name = "year_of_birth")
    @Setter
    @Getter
    private int yearOfBirth;

    @Column(name = "password")
    @Setter
    @Getter
    private String password;

    public Person() {
    }

    public Person(String username, int yearOfBirth) {
        this.username = username;
        this.yearOfBirth = yearOfBirth;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", yearOfBirth=" + yearOfBirth +
                ", password='" + password + '\'' +
                '}';
    }
}
