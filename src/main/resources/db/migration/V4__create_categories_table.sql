-- Create Categories Table
CREATE TABLE IF NOT EXISTS categories (
    id SERIAL PRIMARY KEY,       -- Auto-incrementing primary key
    name VARCHAR(255) NOT NULL,  -- Name of the category
    tournament_id INT NOT NULL,
    min_age INT,                 -- Minimum age for the category
    max_age INT,                 -- Maximum age for the category
    gender VARCHAR(10),          -- Gender for the category (e.g., Male, Female, Other)
    created_at TIMESTAMP DEFAULT NOW(), -- Timestamp for when the category is created
    updated_at TIMESTAMP DEFAULT NOW(), -- Timestamp for when the category is updated
    CONSTRAINT fk_category_tournament FOREIGN KEY (tournament_id) REFERENCES tournaments (id) ON DELETE CASCADE -- Foreign key constraint
);

-- Optional: Add indexes for faster querying
CREATE INDEX idx_categories_name ON categories (name);
CREATE INDEX idx_categories_gender ON categories (gender);
CREATE INDEX idx_categories_tournament ON categories (tournament_id);
