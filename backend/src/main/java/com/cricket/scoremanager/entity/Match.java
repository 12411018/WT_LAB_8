package com.cricket.scoremanager.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "cricket_match")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String team1;
    private String team2;
    private String status; // LIVE, COMPLETED, UPCOMING
    private String format; // T20, ODI, TEST
    private String tossResult;

    private int team1Score;
    private int team1Wickets;
    private double team1Overs;

    private int team2Score;
    private int team2Wickets;
    private double team2Overs;

    @Column(length = 500)
    private String summary;

    private String currentStriker;
    private String currentNonStriker;
    private String currentBowler;

    private LocalDateTime matchDate;
}
