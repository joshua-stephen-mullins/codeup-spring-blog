CREATE DATABASE IF NOT EXISTS blog_db;

use blog_db;

DROP TABLE posts;

CREATE TABLE posts
(
    id    INT UNSIGNED NOT NULL AUTO_INCREMENT,
    title VARCHAR(50),
    body  TEXT,
    PRIMARY KEY (id)
);

INSERT INTO posts (title, body)
VALUES ('Greece', 'Lovely country, good beaches'),
       ('Italy', 'Oh boy... the pasta was tight'),
       ('England', 'Fish&Chips mate.');


CREATE USER 'blog_user'@'localhost' IDENTIFIED BY 'usa';
GRANT ALL ON posts TO 'blog_user'@'localhost' WITH GRANT OPTION;
SHOW GRANTS for 'blog_user'@'localhost';
