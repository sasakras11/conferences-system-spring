package com.conferences.entity;


import lombok.*; // wildcard
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "conferences")
public class Conference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "conference_id")
    private Integer conferenceId;

    @Column(name = "name")
    @Length(max = 200, min = 4)
    @NotEmpty(message = "name of conference not provided")
    private String name;

    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY,cascade=CascadeType.ALL)
    @JoinColumn(name = "location_id")
    private Location location;

    @ToString.Exclude
    @ManyToMany(fetch = FetchType.LAZY,cascade=CascadeType.ALL)
    @JoinColumn(name = "conference_id")
    private List<User> members;

    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY,cascade=CascadeType.ALL)
    private List<Speech> speeches;
}
