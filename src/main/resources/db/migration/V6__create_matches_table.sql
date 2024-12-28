-- Create Matches Table
CREATE TABLE IF NOT EXISTS matches (
    id SERIAL PRIMARY KEY,               -- Auto-incrementing primary key
    category_id INT NOT NULL,
    status VARCHAR(50) NOT NULL,         -- Status of the match (e.g., Scheduled, Ongoing, Completed)
    scheduled_time TIMESTAMP NOT NULL,   -- Time when the match is scheduled
    match_type VARCHAR(50) NOT NULL,     -- Type of match (e.g., Singles, Doubles)
    team1_id INT,
    team2_id INT,
    score VARCHAR(255),                  -- Score of the match (e.g., "6-3, 4-6, 7-5")
    winner VARCHAR(255),                 -- Winner of the match
    tournament_phase VARCHAR(50),        -- Phase of the tournament (e.g., Quarterfinals, Semifinals, Finals)
    created_at TIMESTAMP DEFAULT NOW(),  -- Timestamp for when the record is created
    updated_at TIMESTAMP DEFAULT NOW(),   -- Timestamp for when the record is updated
    CONSTRAINT fk_matches_category FOREIGN KEY (category_id) REFERENCES categories (id) ON DELETE CASCADE, -- Foreign key constraint
    CONSTRAINT fk_matches_team1 FOREIGN KEY (team1_id) REFERENCES match_teams (id) ON DELETE CASCADE, -- Foreign key constraint
    CONSTRAINT fk_matches_team2 FOREIGN KEY (team2_id) REFERENCES match_teams (id) ON DELETE CASCADE -- Foreign key constraint
);

-- Optional: Add indexes for faster querying
CREATE INDEX idx_matches_status ON matches (status);
CREATE INDEX idx_matches_scheduled_time ON matches (scheduled_time);
