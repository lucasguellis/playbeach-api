-- Create user_team Table
CREATE TABLE IF NOT EXISTS tmp_user_category (
     id SERIAL PRIMARY KEY,               -- Auto-incrementing primary key
     category_id INT NOT NULL,            -- Foreign key to match_teams table
     user_id INT NOT NULL,                -- Foreign key to users table
     created_at TIMESTAMP DEFAULT NOW(),  -- Timestamp for when the record is created
     updated_at TIMESTAMP DEFAULT NOW(),  -- Timestamp for when the record is updated
     CONSTRAINT fk_team FOREIGN KEY (category_id) REFERENCES categories (id) ON DELETE CASCADE, -- Foreign key constraint
     CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE -- Foreign key constraint
);

-- Optional: Add indexes for faster querying
CREATE INDEX idx_tmp_user_category_category_id ON tmp_user_category (category_id);
CREATE INDEX idx_tmp_user_category_user_id ON tmp_user_category (user_id);
