CREATE TABLE RATING (
	id BIGINT(20) NOT NULL UNIQUE PRIMARY KEY AUTO_INCREMENT,
	user_name VARCHAR(200) NOT NULL,
	author_id BIGINT(20) NOT NULL,
	rating	SMALLINT NOT NULL
);

