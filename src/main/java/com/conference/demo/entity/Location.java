package com.conference.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
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
    private  int id;


    @NotEmpty
    @Column(name = "address")
    private  String address;

    @NotEmpty
    @Column(name = "max_people")
    private int maxPeople;



    @OneToMany(fetch = FetchType.LAZY, mappedBy = "location")
    private List<Conference> conferences;
}
