CREATE DATABASE IF NOT EXISTS url_shortener;
USE url_shortener;

CREATE TABLE urls (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    long_url VARCHAR(2048) NOT NULL,
    short_code VARCHAR(32) UNIQUE NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    expires_at DATETIME
);

select * from urls;
