package com.cricket.scoremanager.controller;

import com.cricket.scoremanager.entity.Match;
import com.cricket.scoremanager.service.CricketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matches")
@CrossOrigin(origins = "*") // Simplify CORS for development
public class CricketController {

    @Autowired
    private CricketService cricketService;

    @GetMapping
    public List<Match> getAllMatches() {
        return cricketService.getAllMatches();
    }

    @GetMapping("/live")
    public List<Match> getLiveMatches() {
        return cricketService.getLiveMatches();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Match> getMatchById(@PathVariable Long id) {
        Match match = cricketService.getMatchById(id);
        if (match != null) {
            return ResponseEntity.ok(match);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public Match createMatch(@RequestBody Match match) {
        return cricketService.createMatch(match);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Match> updateMatchStatus(@PathVariable Long id, @RequestParam String status) {
        Match updated = cricketService.updateMatchStatus(id, status);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }
}
