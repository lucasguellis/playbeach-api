CREATE TABLE tournaments (
   id SERIAL PRIMARY KEY,
   name VARCHAR(255) NOT NULL,
   status VARCHAR(50) NOT NULL,
   created_at TIMESTAMP NOT NULL DEFAULT NOW(),
   updated_at TIMESTAMP NOT NULL DEFAULT NOW(),
   place_id BIGINT NOT NULL,
   CONSTRAINT fk_place FOREIGN KEY (place_id) REFERENCES places (id) ON DELETE CASCADE
);