CREATE DATABASE IF NOT EXISTS blog_db;

use blog_db;

DROP TABLE posts;

CREATE TABLE posts
(
    id        BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    title     VARCHAR(100),
    body      TEXT,
    poster_id BIGINT          NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (poster_id) REFERENCES users(id)
);

DROP TABLE users;

CREATE TABLE users
(
    id        BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    username  VARCHAR(100) UNIQUE,
    email     VARCHAR(100),
    password     VARCHAR(256),
    PRIMARY KEY (id)
);

INSERT INTO users (username, email, password)
VALUES ('joshua', 'joshua@email', 'usa');

INSERT INTO posts (title, body, poster_id)
VALUES ('Greece', 'Lovely country, good beaches', 1),
       ('Italy', 'Oh boy... the pasta was tight', 1),
       ('England', 'Fish&Chips mate.', 1);


CREATE USER 'blog_user'@'localhost' IDENTIFIED BY 'usa';
GRANT ALL ON post TO 'blog_user'@'localhost' WITH GRANT OPTION;
GRANT ALL ON users TO 'blog_user'@'localhost' WITH GRANT OPTION;
SHOW GRANTS for 'blog_user'@'localhost';
