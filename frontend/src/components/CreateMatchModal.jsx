import React, { useState } from 'react';
import axios from 'axios';
import '../index.css';

const API_BASE_URL = 'http://localhost:8080/api/matches';

const CreateMatchModal = ({ onClose, onMatchCreated }) => {
  const [formData, setFormData] = useState({
    team1: '',
    team2: '',
    format: 'T20',
  });
  const [loading, setLoading] = useState(false);

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    try {
      // Call parent handler directly with local JSON data instead of calling backend
      onMatchCreated(formData);
      onClose();
    } catch (error) {
      console.error("Error creating match:", error);
      alert("Failed to create match locally.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="modal-overlay">
      <div className="modal-content">
        <h2>Start New Match</h2>
        <form onSubmit={handleSubmit} className="match-form">
          <div className="form-group">
            <label>Team 1</label>
            <input 
              type="text" 
              name="team1" 
              value={formData.team1} 
              onChange={handleChange} 
              placeholder="e.g., India" 
              required 
            />
          </div>
          <div className="form-group">
            <label>Team 2</label>
            <input 
              type="text" 
              name="team2" 
              value={formData.team2} 
              onChange={handleChange} 
              placeholder="e.g., Australia" 
              required 
            />
          </div>
          <div className="form-group">
            <label>Format</label>
            <select name="format" value={formData.format} onChange={handleChange}>
              <option value="T20">T20</option>
              <option value="ODI">ODI</option>
              <option value="TEST">TEST</option>
            </select>
          </div>
          <div className="modal-actions">
            <button type="button" className="btn btn-secondary" onClick={onClose}>Cancel</button>
            <button type="submit" className="btn btn-primary" disabled={loading}>
              {loading ? 'Creating...' : 'Create Match'}
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default CreateMatchModal;
