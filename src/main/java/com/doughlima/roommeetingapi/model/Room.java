package com.doughlima.roommeetingapi.model;

import lombok.*;

import javax.persistence.*;
import java.util.stream.Stream;

@Entity
@Table(name = "meetingroom")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "date", nullable = false)
    private String date;
    @Column(name = "startHour", nullable = false)
    private String startHour;
    @Column(name = "endHour",nullable = false)
    private String endHour;
}
