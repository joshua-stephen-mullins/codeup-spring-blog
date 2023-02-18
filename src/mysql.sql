CREATE DATABASE IF NOT EXISTS blog_db;

CREATE TABLE posts
(
    id                INT UNSIGNED NOT NULL AUTO_INCREMENT,
    title VARCHAR(50),
    body  TEXT,
    content           TEXT         NOT NULL,
    PRIMARY KEY (id)
);

