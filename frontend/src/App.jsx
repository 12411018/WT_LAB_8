import React, { useState, useEffect } from 'react';
import MatchCard from './components/MatchCard';
import CreateMatchModal from './components/CreateMatchModal';
import './index.css';
import './modal.css';

const initialMatches = [
  {
    id: 1,
    team1: "India",
    team2: "Australia",
    format: "T20",
    status: "LIVE",
    team1Score: 154,
    team1Wickets: 4,
    team1Overs: 16.2,
    team2Score: 0,
    team2Wickets: 0,
    team2Overs: 0.0,
    summary: "India is batting. Score: 154/4 in 16.2 overs."
  },
  {
    id: 2,
    team1: "England",
    team2: "South Africa",
    format: "ODI",
    status: "UPCOMING",
    team1Score: 0,
    team1Wickets: 0,
    team1Overs: 0.0,
    team2Score: 0,
    team2Wickets: 0,
    team2Overs: 0.0,
    summary: "Match will start soon"
  }
];

function App() {
  const [matches, setMatches] = useState(initialMatches);
  const [loading, setLoading] = useState(false);
  const [isModalOpen, setIsModalOpen] = useState(false);

  // Simulate Live Scoring
  useEffect(() => {
    const interval = setInterval(() => {
      setMatches(prevMatches => 
        prevMatches.map(match => {
          if (match.status === 'LIVE') {
            const newMatch = { ...match };
            // Simple simulation for team 1 batting
            if (newMatch.team1Overs < 20.0 && newMatch.team1Wickets < 10) {
              const runs = Math.floor(Math.random() * 7); // 0 to 6
              const isWicket = Math.random() < 0.05; // 5% chance

              if (isWicket) {
                newMatch.team1Wickets += 1;
              } else if (runs !== 5) {
                newMatch.team1Score += runs;
              }

              let balls = Math.round((newMatch.team1Overs - Math.floor(newMatch.team1Overs)) * 10);
              balls++;
              if (balls === 6) {
                newMatch.team1Overs = Math.floor(newMatch.team1Overs) + 1;
              } else {
                newMatch.team1Overs = Math.floor(newMatch.team1Overs) + (balls / 10.0);
              }
              newMatch.summary = `${newMatch.team1} is batting. Score: ${newMatch.team1Score}/${newMatch.team1Wickets} in ${newMatch.team1Overs.toFixed(1)} overs.`;
            }
            return newMatch;
          }
          return match;
        })
      );
    }, 4000); // Simulate every 4 seconds
    return () => clearInterval(interval);
  }, []);

  const handleMatchCreated = (newMatchData) => {
    const newMatch = {
      ...newMatchData,
      id: Date.now(), // Generate a fake ID
      status: 'LIVE',
      team1Score: 0,
      team1Wickets: 0,
      team1Overs: 0.0,
      team2Score: 0,
      team2Wickets: 0,
      team2Overs: 0.0,
      summary: 'Match just started!'
    };
    setMatches([newMatch, ...matches]);
  };

  return (
    <div className="dashboard-container">
      <header className="header">
        <h1>CricLive Dashboard</h1>
        <p>Real-time cricket score management system (Local JSON Mode)</p>
      </header>

      {loading ? (
        <div className="loader"></div>
      ) : (
        <main>
          <div className="section-title" style={{display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginBottom: '1.5rem'}}>
            <span style={{fontSize: '1.5rem', fontWeight: '600'}}>Live & Recent Matches</span>
            <button className="btn btn-primary" onClick={() => setIsModalOpen(true)}>+ Create Match</button>
          </div>
          <div className="matches-grid">
            {matches.map(match => (
              <MatchCard key={match.id} match={match} />
            ))}
            {matches.length === 0 && (
              <p style={{color: 'var(--text-secondary)'}}>No matches found. Create a match to start tracking!</p>
            )}
          </div>
        </main>
      )}

      {isModalOpen && (
        <CreateMatchModal 
          onClose={() => setIsModalOpen(false)} 
          onMatchCreated={handleMatchCreated} 
        />
      )}
    </div>
  );
}

export default App;
