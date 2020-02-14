package com.conferences.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "locations")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id")
    private Integer id;


    @NotEmpty
    @Column(name = "address")
    @NotNull
    private String address;

    @NotNull
    @Column(name = "max_people", columnDefinition="int default 10")
    private Integer maxPeople;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "location")
    private List<Conference> conferences;
}
