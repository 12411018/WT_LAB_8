# Live Cricket Score Management System

A highly scalable and real-time cricket score management application built using Spring Boot and React. The system simulates live matches, updates scores, tracks player statistics, and provides a beautiful responsive dashboard.

## Tech Stack
* **Backend:** Java, Spring Boot, Spring Data JPA, MySQL
* **Frontend:** React.js, Vite, Vanilla CSS with Glassmorphism Design
* **Database:** MySQL Workbench

## Prerequisites
1. **Java Development Kit (JDK 17+)**
2. **Node.js (v18+)**
3. **MySQL Server & MySQL Workbench**

## Database Setup (MySQL)
1. Open **MySQL Workbench**.
2. Make sure you have a local MySQL instance running on `localhost:3306`.
3. By default, the application connects to a database named `cricket_score` with:
   - Username: `root`
   - Password: `root`
4. *(Optional)* If your username/password is different, open `backend/src/main/resources/application.properties` and update `spring.datasource.username` and `spring.datasource.password`.
5. The `cricket_score` database will be created automatically when you run the backend (if it does not exist) and tables will be created.

## How to Run the Backend
1. Open the `backend` folder in your favorite Java IDE (e.g., IntelliJ IDEA, Eclipse, VS Code).
2. Run the `ScoreManagerApplication.java` main class.
3. The server will start on `http://localhost:8080`.
4. The system includes an automated simulator that will schedule score updates for "LIVE" matches every 5 seconds.

## How to Run the Frontend
1. Open a terminal and navigate to the `frontend` folder:
   ```bash
   cd frontend
   ```
2. Install dependencies (if you haven't already):
   ```bash
   npm install
   ```
3. Start the Vite development server:
   ```bash
   npm run dev
   ```
4. Open the displayed local URL in your browser (usually `http://localhost:5173`).
5. You will see a beautiful dashboard. Live scores will glow green and animate when they change, fetching updates from the backend in real-time.

## Features Included
- **Live Match Simulation**: Backend automatically schedules score updates for matches in 'LIVE' status.
- **REST APIs**: `MatchController` and `PlayerController` to interact with ongoing matches, score updates, and player statistics.
- **Rich Dashboard**: Modern glassmorphic responsive UI using React to display active matches and summaries.
