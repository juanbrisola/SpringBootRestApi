DROP TABLE IF EXISTS `ingrediente`;
CREATE TABLE `ingrediente` (
                               `id` int NOT NULL AUTO_INCREMENT,
                               `descricao` varchar(45) NOT NULL,
                               `valor` decimal(6,2) NOT NULL DEFAULT '0.00',
                               PRIMARY KEY (`id`)
);

INSERT INTO `ingrediente` VALUES (1,'Alface',0.40),(2,'Bacon',2.00),(3,'Hamburguer',3.00),(4,'Ovo',0.80),(5,'Queijo',1.50);

DROP TABLE IF EXISTS `lanche`;
CREATE TABLE `lanche` (
                          `id` int NOT NULL AUTO_INCREMENT,
                          `nome` varchar(45) NOT NULL,
                          PRIMARY KEY (`id`)
);

INSERT INTO `lanche` VALUES (1,'X-Bacon'),(2,'X-Burger'),(3,'X-Egg'),(4,'X-Egg Bacon');

DROP TABLE IF EXISTS `lanche_ingrediente`;
CREATE TABLE `lanche_ingrediente` (
                                      `lanche_id` int NOT NULL,
                                      `ingrediente_id` int NOT NULL,
                                      `id` bigint NOT NULL,
                                      PRIMARY KEY (`lanche_id`,`ingrediente_id`),
                                      KEY `fk_lanche_has_ingrediente_ingrediente1_idx` (`ingrediente_id`),
                                      KEY `fk_lanche_has_ingrediente_lanche_idx` (`lanche_id`),
                                      CONSTRAINT `fk_lanche_has_ingrediente_ingrediente1` FOREIGN KEY (`ingrediente_id`) REFERENCES `ingrediente` (`id`),
                                      CONSTRAINT `fk_lanche_has_ingrediente_lanche` FOREIGN KEY (`lanche_id`) REFERENCES `lanche` (`id`)
);

INSERT INTO `lanche_ingrediente` VALUES (1,2,1),(1,3,2),(1,5,3),(2,3,4),(2,5,5),(3,3,7),(3,4,6),(3,5,8),(4,2,10),(4,3,11),(4,4,9),(4,5,12);

DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
                              `id` bigint NOT NULL AUTO_INCREMENT,
                              `description` varchar(255) DEFAULT NULL,
                              PRIMARY KEY (`id`)
);

INSERT INTO `permission` VALUES (1,'ADMIN'),(2,'USER');

DROP TABLE IF EXISTS `user_permission`;
CREATE TABLE `user_permission` (
                                   `id_user` bigint NOT NULL,
                                   `id_permission` bigint NOT NULL,
                                   PRIMARY KEY (`id_user`,`id_permission`),
                                   KEY `fk_user_permission_permission` (`id_permission`),
                                   CONSTRAINT `fk_user_permission` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`),
                                   CONSTRAINT `fk_user_permission_permission` FOREIGN KEY (`id_permission`) REFERENCES `permission` (`id`)
);

INSERT INTO `user_permission` VALUES (1,1),(1,2);

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
                         `id` bigint NOT NULL AUTO_INCREMENT,
                         `user_name` varchar(255) DEFAULT NULL,
                         `password` varchar(255) DEFAULT NULL,
                         `account_non_expired` bit(1) DEFAULT NULL,
                         `account_non_locked` bit(1) DEFAULT NULL,
                         `credentials_non_expired` bit(1) DEFAULT NULL,
                         `enabled` bit(1) DEFAULT NULL,
                         PRIMARY KEY (`id`),
                         UNIQUE KEY `uk_user_name` (`user_name`)
);

INSERT INTO `users` VALUES (1,'juan','$2a$16$9qr2tv0HmXbHBsx.TZFjfux742wCZM32a8Wi6iBqwIqaizlHPuxHS',_binary '',_binary '',_binary '',_binary '');