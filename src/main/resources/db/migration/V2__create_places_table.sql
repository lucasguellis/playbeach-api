CREATE TABLE places (
    id SERIAL PRIMARY KEY,                  -- Unique identifier for the place
    name VARCHAR(150) NOT NULL,             -- Name of the place
    email VARCHAR(255) UNIQUE NOT NULL,     -- Email address
    latitude DOUBLE PRECISION NOT NULL,     -- Latitude coordinate
    longitude DOUBLE PRECISION NOT NULL,    -- Longitude coordinate
    address VARCHAR(255),                   -- Optional address of the place
    isActive BOOLEAN DEFAULT TRUE,          -- Status of the place
    createdAt TIMESTAMP DEFAULT NOW(),      -- Timestamp of record creation
    updatedAt TIMESTAMP DEFAULT NOW()       -- Timestamp of last update
);
