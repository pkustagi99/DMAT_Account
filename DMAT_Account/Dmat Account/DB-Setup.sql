CREATE DATABASE `dmat_manager` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
CREATE TABLE `share` (
  `share_id` int NOT NULL AUTO_INCREMENT,
  `company_name` varchar(256) DEFAULT NULL,
  `price` int DEFAULT NULL,
  `lastUpdatedOn` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`share_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `transaction` (
  `transaction_id` int NOT NULL AUTO_INCREMENT,
  `shareCount` int DEFAULT NULL,
  `pricePerShare` float DEFAULT NULL,
  `transactionCharges` float DEFAULT NULL,
  `sttCharges` float DEFAULT NULL,
  `type` int DEFAULT NULL,
  `share_id` int DEFAULT NULL,
  `transactedOn` datetime DEFAULT CURRENT_TIMESTAMP,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`transaction_id`),
  KEY `user_id` (`user_id`),
  KEY `share_id` (`share_id`),
  CONSTRAINT `transaction_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `transaction_ibfk_2` FOREIGN KEY (`share_id`) REFERENCES `share` (`share_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `user_name` varchar(256) DEFAULT NULL,
  `account_number` int NOT NULL,
  `password` varchar(256) DEFAULT NULL,
  `account_balance` int DEFAULT NULL,
  `lastUpdatedOn` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `account_number` (`account_number`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `usershares` (
  `userShare_id` int NOT NULL AUTO_INCREMENT,
  `shareCount` int DEFAULT NULL,
  `share_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `company_name` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`userShare_id`),
  KEY `share_id` (`share_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `usershares_ibfk_1` FOREIGN KEY (`share_id`) REFERENCES `share` (`share_id`),
  CONSTRAINT `usershares_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
