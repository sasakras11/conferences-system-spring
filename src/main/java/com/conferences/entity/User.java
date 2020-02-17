package com.conferences.entity;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", unique = true, nullable = false)
    private Integer userId;

    @Column(name = "username")
    @Length(min = 3, message = "Username must be at least 3 characters long")
    @NotEmpty(message = "Please provide your username")
    private String username;

    @Length(min = 10, message = "password is not hashed")
    @NotEmpty(message = "Please provide your username")
    @Column(name = "password")
    private String password;

    @Column(name = "role")
    @NotNull(message = "role is not provided")
    @Enumerated(EnumType.STRING)
    private Role role;

    @ToString.Exclude
    @ManyToMany(mappedBy = "visitors", fetch = FetchType.LAZY,cascade=CascadeType.ALL)
    private List<Speech> speeches;

    @ToString.Exclude
    @ManyToMany(mappedBy = "members", fetch = FetchType.LAZY,cascade=CascadeType.ALL)
    private List<Conference> conferences;

}
