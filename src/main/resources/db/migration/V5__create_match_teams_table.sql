-- Create match_teams Table
CREATE TABLE IF NOT EXISTS match_teams (
    id SERIAL PRIMARY KEY,               -- Auto-incrementing primary key
    team_name VARCHAR(255) NOT NULL,     -- Name of the team
    created_at TIMESTAMP DEFAULT NOW(),  -- Timestamp for when the record is created
    updated_at TIMESTAMP DEFAULT NOW()  -- Timestamp for when the record is updated
);
