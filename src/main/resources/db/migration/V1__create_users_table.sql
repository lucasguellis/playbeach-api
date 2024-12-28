CREATE TABLE users (
    id SERIAL PRIMARY KEY,                      -- Auto-incrementing primary key
    name VARCHAR(100) NOT NULL,                 -- User's first name
    surname VARCHAR(100) NOT NULL,              -- User's surname
    username VARCHAR(50) UNIQUE NOT NULL,       -- Unique username
    email VARCHAR(255) UNIQUE NOT NULL,         -- Unique email address
    birthday DATE,                              -- Date of birth
    gender VARCHAR(10),                         -- Gender (e.g., Male/Female/Other)
    is_active BOOLEAN DEFAULT TRUE,             -- Indicates if the user is active
    created_at TIMESTAMP DEFAULT NOW(),         -- Timestamp of record creation
    updated_at TIMESTAMP DEFAULT NOW(),         -- Timestamp of last update
    last_login TIMESTAMP                        -- Timestamp of last login
);
