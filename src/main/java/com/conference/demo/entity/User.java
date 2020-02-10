package com.conference.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id",unique = true,nullable = false)
    private int userId;

    @Column(name = "username")
    @Length(min = 5, message = "Username must be at least 5 characters long")
    @NotEmpty(message = "Please provide your username")
    private String username;

    @Length(min = 128, message = "password is not hashed")
    @NotEmpty(message = "Please provide your username")
    @Column(name = "password")
    private String password;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;


    @ManyToMany(mappedBy = "visitors")
    List<Speech> speeches;


    @ManyToMany(mappedBy = "members")
    List<Conference> conferences;

}
