package com.cricket.scoremanager.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String team;
    private String role; // Batsman, Bowler, All-Rounder

    private int runsScored;
    private int ballsFaced;
    private int wicketsTaken;
    private int oversBowled;
    private int runsConceded;
}
