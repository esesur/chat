DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS messages CASCADE;

CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    username TEXT UNIQUE
);

CREATE TABLE IF NOT EXISTS messages (
    id SERIAL PRIMARY KEY,
    sender BIGINT,
    content TEXT NOT NULL,
    time_sent BIGINT NOT NULL,
    FOREIGN KEY (sender) REFERENCES users(id)
);

--CREATE TABLE IF NOT EXISTS users (
--    id SERIAL PRIMARY KEY,
--    username TEXT
--);
--
--CREATE TABLE IF NOT EXISTS messages (
--    id SERIAL PRIMARY KEY,
--    sender BIGINT,
--    content TEXT NOT NULL,
--    time_sent BIGINT NOT NULL,
--    FOREIGN KEY (sender) REFERENCES users(id)
--);