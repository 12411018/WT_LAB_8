package com.cricket.scoremanager;

import com.cricket.scoremanager.entity.Match;
import com.cricket.scoremanager.repository.MatchRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDateTime;

@SpringBootApplication
@EnableScheduling
public class ScoreManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScoreManagerApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(MatchRepository matchRepository) {
        return args -> {
            if (matchRepository.count() == 0) {
                Match match1 = new Match();
                match1.setTeam1("India");
                match1.setTeam2("Australia");
                match1.setFormat("T20");
                match1.setStatus("LIVE");
                match1.setTeam1Score(154);
                match1.setTeam1Wickets(4);
                match1.setTeam1Overs(16.2);
                match1.setTeam2Score(0);
                match1.setTeam2Wickets(0);
                match1.setTeam2Overs(0.0);
                match1.setSummary("India is batting");
                match1.setMatchDate(LocalDateTime.now());
                matchRepository.save(match1);

                Match match2 = new Match();
                match2.setTeam1("England");
                match2.setTeam2("South Africa");
                match2.setFormat("ODI");
                match2.setStatus("UPCOMING");
                match2.setTeam1Score(0);
                match2.setTeam1Wickets(0);
                match2.setTeam1Overs(0.0);
                match2.setTeam2Score(0);
                match2.setTeam2Wickets(0);
                match2.setTeam2Overs(0.0);
                match2.setSummary("Match will start soon");
                match2.setMatchDate(LocalDateTime.now().plusDays(1));
                matchRepository.save(match2);
            }
        };
    }
}

