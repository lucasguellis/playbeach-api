ALTER TABLE categories
    ADD price INTEGER;

ALTER TABLE categories
    DROP COLUMN tournament_id;

ALTER TABLE categories
    ADD tournament_id BIGINT NOT NULL;

ALTER TABLE categories
    ADD CONSTRAINT FK_CATEGORIES_ON_TOURNAMENT FOREIGN KEY (tournament_id) REFERENCES tournaments (id);