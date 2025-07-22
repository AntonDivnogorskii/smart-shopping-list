CREATE DATABASE IF NOT EXISTS smart_shopping_list;

USE smart_shopping_list;

CREATE TABLE IF NOT EXISTS note (
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
	title VARCHAR(255) NOT NULL,
	content VARCHAR(255),
	quantity INT,
	indicator BOOLEAN 
);