package com.conferences.entity;

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
    private Integer id;

    @Column(name = "topic")
    @Length(max = 200, min = 10)
    private String topic;

    @Column(name = "suggested_topic")
    @Length(max = 200, min = 10)
    private String suggestedTopic;

    @Column(name = "start_hour")
    private Integer startHour;

    @Column(name = "end_hour")
    private Integer endHour;

    @JoinColumn(name = "user_id")
    @OneToOne(cascade = CascadeType.ALL)
    private User speaker;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "speech_id")
    private List<User> visitors;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conference_id")
    private Conference conference;

}
