package com.cricket.scoremanager.service;

import com.cricket.scoremanager.entity.Match;
import com.cricket.scoremanager.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class CricketService {

    @Autowired
    private MatchRepository matchRepository;

    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }

    public List<Match> getLiveMatches() {
        return matchRepository.findByStatus("LIVE");
    }

    public Match createMatch(Match match) {
        match.setStatus("UPCOMING");
        return matchRepository.save(match);
    }

    public Match updateMatchStatus(Long id, String status) {
        Optional<Match> matchOpt = matchRepository.findById(id);
        if (matchOpt.isPresent()) {
            Match match = matchOpt.get();
            match.setStatus(status);
            return matchRepository.save(match);
        }
        return null;
    }

    public Match getMatchById(Long id) {
        return matchRepository.findById(id).orElse(null);
    }

    // Simulate Live Scoring
    @Scheduled(fixedRate = 5000)
    public void simulateLiveScore() {
        List<Match> liveMatches = matchRepository.findByStatus("LIVE");
        Random random = new Random();

        for (Match match : liveMatches) {
            // Very basic simulation for team1 batting
            if (match.getTeam1Overs() < 20.0 && match.getTeam1Wickets() < 10) {
                int runs = random.nextInt(7); // 0 to 6 runs
                if (runs == 5) runs = 0; // 5 is rare, replace with dot ball
                
                boolean isWicket = random.nextInt(20) == 0; // 5% chance of wicket
                
                if (isWicket) {
                    match.setTeam1Wickets(match.getTeam1Wickets() + 1);
                } else {
                    match.setTeam1Score(match.getTeam1Score() + runs);
                }

                // Update overs logic
                int balls = (int) Math.round((match.getTeam1Overs() - Math.floor(match.getTeam1Overs())) * 10);
                balls++;
                if (balls == 6) {
                    match.setTeam1Overs(Math.floor(match.getTeam1Overs()) + 1);
                } else {
                    match.setTeam1Overs(Math.floor(match.getTeam1Overs()) + (balls / 10.0));
                }
                
                match.setSummary(match.getTeam1() + " is batting. Score: " + match.getTeam1Score() + "/" + match.getTeam1Wickets() + " in " + String.format("%.1f", match.getTeam1Overs()) + " overs.");
                
            } else if (match.getTeam2Overs() < 20.0 && match.getTeam2Wickets() < 10) {
                 // Team 2 batting
                 int runs = random.nextInt(7);
                 if (runs == 5) runs = 0;
                 boolean isWicket = random.nextInt(20) == 0;

                 if (isWicket) {
                     match.setTeam2Wickets(match.getTeam2Wickets() + 1);
                 } else {
                     match.setTeam2Score(match.getTeam2Score() + runs);
                 }

                 int balls = (int) Math.round((match.getTeam2Overs() - Math.floor(match.getTeam2Overs())) * 10);
                 balls++;
                 if (balls == 6) {
                     match.setTeam2Overs(Math.floor(match.getTeam2Overs()) + 1);
                 } else {
                     match.setTeam2Overs(Math.floor(match.getTeam2Overs()) + (balls / 10.0));
                 }
                 
                 match.setSummary(match.getTeam2() + " is batting. Score: " + match.getTeam2Score() + "/" + match.getTeam2Wickets() + " in " + String.format("%.1f", match.getTeam2Overs()) + " overs. Target: " + (match.getTeam1Score() + 1));
                 
                 // Check if match is over
                 if (match.getTeam2Score() > match.getTeam1Score()) {
                     match.setStatus("COMPLETED");
                     match.setSummary(match.getTeam2() + " won by " + (10 - match.getTeam2Wickets()) + " wickets!");
                 } else if (match.getTeam2Overs() >= 20.0 || match.getTeam2Wickets() == 10) {
                     match.setStatus("COMPLETED");
                     if (match.getTeam2Score() == match.getTeam1Score()) {
                         match.setSummary("Match Tied!");
                     } else {
                         match.setSummary(match.getTeam1() + " won by " + (match.getTeam1Score() - match.getTeam2Score()) + " runs!");
                     }
                 }
            } else {
                match.setStatus("COMPLETED");
            }
            matchRepository.save(match);
        }
    }
}
