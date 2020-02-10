package com.conference.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "speeches")
public class Speech {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "speech_id")
    private int id;

    @Column(name = "topic")
    @Length(max = 200,min = 10)
    private String topic;

    @Column(name = "suggested_topic")
    @Length(max = 200,min = 10)
    private String suggestedTopic;

    @Column(name = "start_hour")
    private int startHour;

    @Column(name = "end_hour")
    private int endHour;

    @JoinColumn(name = "user_id")
    @OneToOne(cascade = CascadeType.ALL)
    private User speaker;


    @ManyToMany
    @JoinColumn(name = "speech_id")
    private List<User> visitors;


    @ManyToOne
    @JoinColumn(name = "conference_id")
    private Conference conference;

}
