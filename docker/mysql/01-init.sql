CREATE TABLE IF NOT EXISTS `users` (
    `id` VARCHAR(50) NOT NULL PRIMARY KEY,
    `username` VARCHAR(50) NOT NULL UNIQUE,
    `password` VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS `roles` (
    `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `role` VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS `users_roles` (
    `user_id` VARCHAR(50) NOT NULL,
    `role_id` BIGINT NOT NULL,
    PRIMARY KEY (`user_id`, `role_id`),
    FOREIGN KEY (`user_id`) REFERENCES `users`(`id`) ON DELETE CASCADE,
    FOREIGN KEY (`role_id`) REFERENCES `roles`(`id`) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS `restricted_endpoints` (
    `role_id` BIGINT NOT NULL,
    `restricted_endpoint` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`role_id`, `restricted_endpoint`),
    FOREIGN KEY (`role_id`) REFERENCES `roles`(`id`) ON DELETE CASCADE
);