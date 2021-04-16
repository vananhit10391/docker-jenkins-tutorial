-- Create employee_manage table
DROP TABLE IF EXISTS `employee_manage`.`employee`;
CREATE TABLE `employee_manage`.`employee`(
    `id` bigint NOT NULL,
    `name` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
-- Insert data for employee_manage table
INSERT INTO `employee` VALUES (1,'Mrs.Thuy'),(2,'Mr.Tam');