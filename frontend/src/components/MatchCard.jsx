import React, { useEffect, useState } from 'react';

const MatchCard = ({ match }) => {
  const [prevScore1, setPrevScore1] = useState(match.team1Score);
  const [prevScore2, setPrevScore2] = useState(match.team2Score);
  const [highlight1, setHighlight1] = useState(false);
  const [highlight2, setHighlight2] = useState(false);

  useEffect(() => {
    if (match.team1Score !== prevScore1) {
      setHighlight1(true);
      setPrevScore1(match.team1Score);
      setTimeout(() => setHighlight1(false), 1000);
    }
    if (match.team2Score !== prevScore2) {
      setHighlight2(true);
      setPrevScore2(match.team2Score);
      setTimeout(() => setHighlight2(false), 1000);
    }
  }, [match.team1Score, match.team2Score, prevScore1, prevScore2]);

  const getStatusClass = (status) => {
    switch (status) {
      case 'LIVE': return 'status-live';
      case 'COMPLETED': return 'status-completed';
      default: return 'status-upcoming';
    }
  };

  return (
    <div className="match-card">
      <div className="match-header">
        <span className="format-badge">{match.format || 'T20'}</span>
        <span className={`status-badge ${getStatusClass(match.status)}`}>
          {match.status}
        </span>
      </div>
      
      <div className="teams-container">
        <div className="team-row">
          <div className="team-name">{match.team1}</div>
          <div className={`team-score ${highlight1 ? 'score-update' : ''}`}>
            <div className="runs-wickets">
              {match.team1Score}/{match.team1Wickets}
            </div>
            <div className="overs">
              ({parseFloat(match.team1Overs).toFixed(1)} ov)
            </div>
          </div>
        </div>
        
        <div className="team-row">
          <div className="team-name">{match.team2}</div>
          <div className={`team-score ${highlight2 ? 'score-update' : ''}`}>
            <div className="runs-wickets">
              {match.team2Score}/{match.team2Wickets}
            </div>
            <div className="overs">
              ({parseFloat(match.team2Overs).toFixed(1)} ov)
            </div>
          </div>
        </div>
      </div>
      
      {match.summary && (
        <div className="match-summary">
          {match.summary}
        </div>
      )}
    </div>
  );
};

export default MatchCard;
