-- Create user_team Table
CREATE TABLE IF NOT EXISTS user_team (
     id SERIAL PRIMARY KEY,               -- Auto-incrementing primary key
     team_id INT NOT NULL,                -- Foreign key to match_teams table
     user_id INT NOT NULL,                -- Foreign key to users table
     created_at TIMESTAMP DEFAULT NOW(),  -- Timestamp for when the record is created
     updated_at TIMESTAMP DEFAULT NOW(),  -- Timestamp for when the record is updated
     CONSTRAINT fk_team FOREIGN KEY (team_id) REFERENCES match_teams (id) ON DELETE CASCADE, -- Foreign key constraint
     CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE -- Foreign key constraint
);

-- Optional: Add indexes for faster querying
CREATE INDEX idx_user_team_team_id ON user_team (team_id);
CREATE INDEX idx_user_team_user_id ON user_team (user_id);
