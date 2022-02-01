CREATE DATABASE `adidas_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

CREATE TABLE `adidas_db`.`subscription` (
    `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
    `email` varchar(45) DEFAULT NULL,
    `first_name` varchar(45) DEFAULT NULL,
    `gender` varchar(45) DEFAULT NULL,
    `birthdate` timestamp NULL DEFAULT NULL,
    `consent` bit(1) DEFAULT NULL,
    `newsletter_id` int(11) DEFAULT NULL,
    `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated` timestamp NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `adidas_db`.`subscription` VALUES
    (null, 'test@test.com', 'test1', 'MALE', '2022-01-30 19:32:46', 1, '1', '2022-01-30 19:36:13', NULL),
    (null, 'test@test.com', 'test2', 'MALE', '2022-01-30 19:32:46', 0, '1', '2022-01-30 19:36:13', NULL);