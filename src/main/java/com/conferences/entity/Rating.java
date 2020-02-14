package com.conferences.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ratings")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rating_id")
    private Integer id;

    @JoinColumn(name = "user_id", unique = true)
    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    @Column(name = "rating")
    private Integer rating;

    public long getBonuses() {
        return Math.round(rating * Math.sqrt(rating) * 3);
    }
}
