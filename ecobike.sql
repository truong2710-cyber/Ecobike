-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 24, 2023 at 06:21 PM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 8.0.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ecobike`
--
CREATE DATABASE IF NOT EXISTS `ecobike` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `ecobike`;

-- --------------------------------------------------------

--
-- Table structure for table `event`
--

DROP TABLE IF EXISTS `event`;
CREATE TABLE `event` (
  `id` int(11) NOT NULL,
  `rental_id` int(11) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `paymenttransaction`
--

DROP TABLE IF EXISTS `paymenttransaction`;
CREATE TABLE `paymenttransaction` (
  `id` int(11) NOT NULL,
  `rental_id` int(11) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `rental`
--

DROP TABLE IF EXISTS `rental`;
CREATE TABLE `rental` (
  `id` int(11) NOT NULL,
  `rentee_id` int(11) DEFAULT NULL,
  `bike_id` int(11) DEFAULT NULL,
  `cardcode` varchar(12) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `bike`
--

DROP TABLE IF EXISTS `bike`;
CREATE TABLE `bike` (
  `id` int(11) NOT NULL,
  `type` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `country` varchar(45) DEFAULT NULL,
  `date_of_manufacture` date DEFAULT NULL,
  `color` varchar(45) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `battery` int(11) DEFAULT NULL,
  `maximum_usage_time` int(11) DEFAULT NULL,
  `license_plate_number` varchar(45) DEFAULT NULL,
  `slot_id` int(11) DEFAULT NULL,
  `park_id` int(11) DEFAULT NULL,
  `is_rented` tinyint(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `bike`
--

INSERT INTO `bike` (`id`, `type`, `name`, `country`, `date_of_manufacture`, `color`, `price`, `battery`, `maximum_usage_time`, `license_plate_number`, `slot_id`, `park_id`, `is_rented`) VALUES
(1, 'electric single', 'Tornado', 'Germany', '2020-05-01', 'silver', 4500, 89, 144, '30F4-48446', 39, 3, 0),
(2, 'electric double', 'Arrow', 'UK', '2020-01-03', 'blank', 1500, 79, 96, '33S2-16028', 18, 4, 0),
(3, 'double', 'Cheetah', 'Korea', '2021-04-23', 'blank', 7000, 64, 96, '31Y5-89131', 29, 3, 0),
(4, 'single', 'Express', 'Japan', '2022-12-11', 'green', 5500, 73, 144, '33P6-1168', 13, 2, 0),
(5, 'double', 'Bullet', 'China', '2020-07-25', 'silver', 3100, 48, 72, '33F2-34358', 20, 3, 0),
(6, 'double', 'Flash', 'Germany', '2022-07-19', 'red', 3000, 46, 120, '31A9-78098', 5, 1, 0),
(7, 'single', 'Flash', 'UK', '2021-09-22', 'green', 3200, 56, 120, '32C6-14467', 9, 3, 0),
(8, 'double', 'Flash', 'Vietnam', '2021-05-27', 'white', 7700, 48, 72, '30A0-32074', 8, 3, 0),
(9, 'double', 'Express', 'Germany', '2021-07-17', 'blue', 9300, 42, 96, '33A4-54890', 27, 3, 0),
(10, 'double', 'Arrow', 'China', '2021-10-04', 'gold', 3200, 43, 72, '29H1-32416', 19, 3, 0),
(11, 'electric double', 'Bullet', 'UK', '2021-05-05', 'blue', 1800, 72, 144, '31C7-40008', 4, 1, 0),
(12, 'electric double', 'Cheetah', 'Korea', '2021-02-21', 'red', 5700, 84, 144, '29K2-58176', 30, 1, 0),
(13, 'electric double', 'Cheetah', 'Japan', '2021-06-14', 'green', 1200, 75, 72, '31E6-98314', 27, 2, 0),
(14, 'electric double', 'Tornado', 'France', '2022-12-21', 'silver', 9500, 82, 120, '29M2-70274', 9, 2, 0),
(15, 'electric double', 'Cheetah', 'France', '2020-10-12', 'green', 7500, 96, 144, '29B8-53400', 22, 4, 0),
(16, 'single', 'Cheetah', 'US', '2021-03-03', 'white', 5600, 71, 144, '29D7-48582', 43, 3, 0),
(17, 'single', 'Tornado', 'Korea', '2022-07-14', 'silver', 1700, 71, 144, '33X1-16902', 13, 3, 0),
(18, 'single', 'Phoenix', 'China', '2022-11-20', 'blank', 4600, 43, 144, '31C7-11427', 27, 1, 0),
(19, 'electric single', 'Express', 'UK', '2020-09-28', 'red', 3500, 96, 120, '30X5-14489', 7, 1, 0),
(20, 'single', 'Express', 'Korea', '2020-01-20', 'blue', 1000, 51, 72, '29L9-60726', 35, 1, 0),
(21, 'electric single', 'Phoenix', 'US', '2022-03-25', 'silver', 4800, 95, 96, '30P5-1595', 32, 3, 0),
(22, 'electric double', 'Flash', 'China', '2021-10-09', 'white', 2700, 86, 120, '31U3-47005', 42, 3, 0),
(23, 'double', 'Bullet', 'Vietnam', '2022-05-08', 'blue', 8900, 95, 144, '29C3-39585', 29, 4, 0),
(24, 'electric single', 'Alpha', 'Korea', '2022-03-21', 'silver', 2400, 50, 72, '30F1-43786', 6, 1, 0),
(25, 'electric double', 'Flash', 'Korea', '2021-10-21', 'blue', 9000, 80, 120, '30D6-76634', 17, 1, 0),
(26, 'single', 'Cheetah', 'France', '2021-05-13', 'green', 7800, 88, 144, '30B5-92695', 37, 1, 0),
(27, 'electric single', 'Phoenix', 'Korea', '2020-12-17', 'gold', 1300, 86, 120, '31E0-26881', 2, 1, 0),
(28, 'electric single', 'Arrow', 'France', '2021-09-18', 'blue', 4700, 65, 120, '29X9-89192', 2, 4, 0),
(29, 'single', 'Flash', 'France', '2022-03-25', 'silver', 2700, 92, 144, '31F1-26586', 18, 2, 0),
(30, 'electric single', 'Express', 'France', '2021-01-13', 'silver', 6300, 53, 72, '30U9-52732', 34, 3, 0),
(31, 'electric double', 'Phoenix', 'UK', '2020-02-20', 'gold', 4400, 62, 72, '32M7-49738', 36, 1, 0),
(32, 'single', 'Phoenix', 'France', '2021-12-03', 'silver', 6600, 86, 120, '32E6-25735', 24, 4, 0),
(33, 'electric double', 'Bullet', 'Vietnam', '2020-03-11', 'red', 4600, 96, 96, '33F3-64800', 5, 3, 0),
(34, 'electric double', 'Express', 'UK', '2021-03-13', 'silver', 7000, 88, 96, '29F3-62367', 47, 3, 0),
(35, 'electric double', 'Arrow', 'China', '2021-04-10', 'blank', 4200, 95, 96, '29G9-41215', 8, 1, 0),
(36, 'double', 'Alpha', 'Germany', '2022-10-12', 'gold', 9900, 85, 96, '32K2-60895', 16, 3, 0),
(37, 'electric single', 'Phoenix', 'Germany', '2020-01-22', 'blank', 8200, 41, 72, '31P0-11749', 21, 4, 0),
(38, 'single', 'Tornado', 'Korea', '2020-02-06', 'blank', 1300, 95, 72, '30Z0-86369', 17, 2, 0),
(39, 'electric double', 'Alpha', 'Japan', '2022-06-01', 'white', 5400, 51, 72, '29G0-59102', 3, 2, 0),
(40, 'electric single', 'Cheetah', 'Japan', '2021-09-25', 'blank', 1700, 78, 144, '31D4-58927', 8, 2, 0),
(41, 'electric single', 'Phoenix', 'Vietnam', '2022-12-05', 'blue', 6000, 100, 144, '31E0-31366', 8, 4, 0),
(42, 'single', 'Bullet', 'France', '2022-09-03', 'gold', 8800, 72, 72, '33H0-11473', 38, 1, 0),
(43, 'electric single', 'Arrow', 'Germany', '2021-02-18', 'red', 7600, 40, 120, '30Y6-9440', 10, 2, 0),
(44, 'electric single', 'Arrow', 'Japan', '2020-06-08', 'gold', 7500, 73, 72, '30G1-97285', 7, 4, 0),
(45, 'single', 'Flash', 'China', '2021-09-23', 'green', 2900, 96, 120, '33G0-64100', 40, 1, 0),
(46, 'double', 'Tornado', 'Korea', '2020-09-20', 'blank', 2300, 97, 144, '29N3-97700', 26, 4, 0),
(47, 'single', 'Alpha', 'Germany', '2020-02-18', 'blue', 8100, 52, 144, '33E6-4671', 29, 2, 0),
(48, 'electric double', 'Bullet', 'China', '2020-05-07', 'green', 2200, 71, 72, '31N3-41940', 11, 3, 0),
(49, 'double', 'Alpha', 'US', '2021-03-09', 'blank', 4700, 78, 72, '30M5-65848', 19, 1, 0),
(50, 'electric single', 'Bullet', 'Korea', '2022-04-07', 'gold', 5700, 85, 72, '33P9-71280', 1, 1, 0),
(51, 'single', 'Arrow', 'France', '2021-07-12', 'white', 3400, 63, 72, '31H1-38939', 10, 4, 0),
(52, 'electric single', 'Phoenix', 'France', '2021-05-03', 'blue', 6400, 57, 72, '29H6-82479', 10, 1, 0),
(53, 'double', 'Alpha', 'US', '2022-06-02', 'blank', 9800, 80, 144, '33S7-41567', 12, 3, 0),
(54, 'electric single', 'Bullet', 'Vietnam', '2021-04-19', 'red', 5400, 42, 96, '29G1-8749', 18, 3, 0),
(55, 'single', 'Flash', 'Vietnam', '2021-05-28', 'silver', 3700, 84, 144, '29H6-89076', 32, 1, 0),
(56, 'single', 'Bullet', 'Germany', '2022-03-01', 'white', 7800, 41, 120, '31H1-64223', 25, 3, 0),
(57, 'electric single', 'Express', 'Germany', '2022-06-03', 'red', 6800, 70, 96, '29P2-93737', 44, 3, 0),
(58, 'single', 'Tornado', 'Germany', '2022-08-27', 'blank', 5000, 83, 72, '32A8-31973', 24, 2, 0),
(59, 'single', 'Phoenix', 'France', '2022-02-03', 'silver', 2700, 100, 120, '33A6-29216', 28, 1, 0),
(60, 'electric double', 'Phoenix', 'US', '2021-10-20', 'blue', 7100, 80, 120, '31X1-4021', 23, 1, 0),
(61, 'single', 'Flash', 'China', '2022-04-19', 'red', 9000, 91, 72, '29C4-68384', 29, 1, 0),
(62, 'electric single', 'Arrow', 'France', '2020-09-09', 'green', 6400, 78, 72, '31G3-38164', 3, 1, 0),
(63, 'electric single', 'Phoenix', 'Japan', '2021-10-04', 'white', 4200, 86, 144, '31A7-59628', 9, 4, 0),
(64, 'electric double', 'Tornado', 'US', '2021-05-24', 'gold', 9700, 67, 72, '30N0-65628', 15, 1, 0),
(65, 'electric single', 'Flash', 'Germany', '2022-11-18', 'silver', 6700, 42, 120, '30K7-69766', 28, 3, 0),
(66, 'single', 'Alpha', 'France', '2020-02-21', 'blank', 9000, 67, 72, '32F8-20322', 3, 4, 0),
(67, 'electric double', 'Express', 'China', '2021-10-18', 'red', 3600, 94, 96, '31S4-2863', 9, 1, 0),
(68, 'electric double', 'Alpha', 'Japan', '2020-10-25', 'green', 8700, 86, 96, '31C9-26434', 25, 4, 0),
(69, 'double', 'Tornado', 'China', '2020-11-07', 'gold', 3100, 81, 144, '31B9-58403', 20, 1, 0),
(70, 'electric single', 'Alpha', 'Korea', '2022-12-01', 'blank', 9000, 88, 96, '31D9-35046', 17, 4, 0),
(71, 'double', 'Arrow', 'US', '2021-10-22', 'white', 7800, 89, 72, '29K5-75787', 22, 3, 0),
(72, 'electric double', 'Arrow', 'UK', '2021-11-26', 'white', 1200, 80, 144, '32B8-84024', 30, 4, 0),
(73, 'electric single', 'Arrow', 'UK', '2020-02-22', 'blank', 1900, 42, 120, '31G7-6989', 41, 3, 0),
(74, 'double', 'Express', 'France', '2020-07-26', 'red', 8600, 43, 120, '31T5-86879', 22, 2, 0),
(75, 'electric double', 'Phoenix', 'France', '2022-03-09', 'silver', 4100, 88, 96, '32Y0-53624', 16, 2, 0),
(76, 'double', 'Cheetah', 'Japan', '2020-03-24', 'blank', 7300, 80, 120, '33E1-93881', 14, 1, 0),
(77, 'electric single', 'Bullet', 'Vietnam', '2020-10-04', 'green', 4400, 69, 144, '29Z5-96253', 3, 3, 0),
(78, 'electric single', 'Flash', 'Korea', '2021-01-09', 'gold', 5900, 70, 120, '32L1-9528', 21, 1, 0),
(79, 'electric single', 'Express', 'France', '2022-04-22', 'gold', 6900, 40, 144, '30L0-41875', 27, 4, 0),
(80, 'double', 'Tornado', 'UK', '2022-12-21', 'blue', 8100, 90, 72, '32M4-92578', 7, 2, 0),
(81, 'double', 'Cheetah', 'Japan', '2021-11-28', 'blank', 6300, 96, 144, '33H8-77004', 19, 4, 0),
(82, 'electric single', 'Cheetah', 'Germany', '2021-11-18', 'blank', 9000, 88, 72, '31N5-89945', 24, 1, 0),
(83, 'electric single', 'Express', 'Japan', '2020-06-22', 'white', 4300, 72, 72, '30P7-88676', 45, 3, 0),
(84, 'electric double', 'Alpha', 'China', '2021-07-23', 'green', 3500, 93, 96, '33A3-4468', 20, 4, 0),
(85, 'single', 'Bullet', 'China', '2020-08-26', 'white', 4900, 58, 96, '31T4-52583', 16, 4, 0),
(86, 'single', 'Express', 'Korea', '2020-01-02', 'blue', 4000, 80, 144, '31U2-88790', 15, 2, 0),
(87, 'single', 'Express', 'Japan', '2021-10-02', 'blue', 6100, 50, 120, '31V1-87185', 1, 4, 0),
(88, 'single', 'Phoenix', 'China', '2022-07-13', 'gold', 3400, 94, 120, '31G4-42609', 14, 3, 0),
(89, 'double', 'Bullet', 'China', '2021-09-21', 'blue', 9900, 61, 120, '32M0-45655', 15, 3, 0),
(90, 'single', 'Flash', 'Vietnam', '2022-12-09', 'green', 7300, 77, 96, '29E4-81272', 46, 3, 0),
(91, 'electric single', 'Tornado', 'Korea', '2021-11-09', 'green', 1700, 75, 72, '30K6-84448', 13, 1, 0),
(92, 'electric double', 'Arrow', 'France', '2021-05-28', 'blank', 8500, 85, 96, '29P8-20340', 26, 3, 0),
(93, 'double', 'Tornado', 'Korea', '2021-10-21', 'silver', 2000, 62, 144, '31B0-3330', 5, 2, 0),
(94, 'single', 'Tornado', 'China', '2021-07-19', 'gold', 9900, 48, 72, '29V5-44089', 2, 2, 0),
(95, 'electric single', 'Arrow', 'China', '2021-04-13', 'red', 3400, 85, 120, '33C1-89470', 20, 2, 0),
(96, 'double', 'Phoenix', 'Japan', '2020-11-18', 'silver', 6700, 48, 144, '29L3-70171', 13, 4, 0),
(97, 'double', 'Bullet', 'Vietnam', '2021-10-05', 'green', 9700, 58, 144, '33N5-43833', 6, 2, 0),
(98, 'electric double', 'Express', 'France', '2020-04-14', 'blue', 3000, 49, 72, '32B5-79053', 30, 2, 0),
(99, 'double', 'Bullet', 'France', '2022-04-19', 'green', 4800, 83, 72, '29C7-50772', 11, 2, 0),
(100, 'double', 'Phoenix', 'UK', '2021-08-05', 'white', 3200, 66, 96, '32N1-44960', 2, 3, 0);

-- --------------------------------------------------------

--
-- Table structure for table `parkinglot`
--

DROP TABLE IF EXISTS `parkinglot`;
CREATE TABLE `parkinglot` (
  `id` int(11) NOT NULL,
  `location` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `total_slots` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `parkinglot`
--

INSERT INTO `parkinglot` (`id`, `location`, `name`, `total_slots`) VALUES
(1, 'Số 100 Giải Phóng', 'Giải Phóng', 40),
(2, 'Số 23 Đê La Thành', 'Ba Đình', 30),
(3, 'Số 1 Đại Cồ Viêt', 'Tự Do', 50),
(4, 'Số 52 Cầu Giấy', 'Cầu Giấy', 30);

-- --------------------------------------------------------

--
-- Table structure for table `card`
--

DROP TABLE IF EXISTS `card`;
CREATE TABLE `card` (
  `cardcode` varchar(12) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `owner` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `cvv` varchar(6) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `expireddate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `card`
--

INSERT INTO `card` (`cardcode`, `owner`, `cvv`, `expireddate`) VALUES
('123412341234', 'Vu Quang Truong', '111111', '2026-10-12'),
('123456781234', 'Nguyen Huy Hoang', '222222', '2026-05-25'),
('123412344321', 'Nguyen Phuc Tan', '333333', '2025-03-13');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `username`, `password`) VALUES
(1, 'truongvq', '12345678'),
(2, 'hoangnh', '98765432'),
(3, 'tannp', '12344321');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bike`
--
ALTER TABLE `bike`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_Bike` (`park_id`);

--
-- Indexes for table `card`
--
ALTER TABLE `card`
  ADD PRIMARY KEY (`cardcode`);

--
-- Indexes for table `event`
--
ALTER TABLE `event`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_Event_Rental` (`rental_id`);

--
-- Indexes for table `parkinglot`
--
ALTER TABLE `parkinglot`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `paymenttransaction`
--
ALTER TABLE `paymenttransaction`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_PaymentTransaction_Rental` (`rental_id`);

--
-- Indexes for table `rental`
--
ALTER TABLE `rental`
  ADD PRIMARY KEY (`id`),
  ADD KEY `cardcode` (`cardcode`),
  ADD KEY `fk_Rental_Bike` (`bike_id`),
  ADD KEY `fk_Rental_User` (`rentee_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bike`
--
ALTER TABLE `bike`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `event`
--
ALTER TABLE `event`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `parkinglot`
--
ALTER TABLE `parkinglot`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `paymenttransaction`
--
ALTER TABLE `paymenttransaction`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `rental`
--
ALTER TABLE `rental`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `bike`
--
ALTER TABLE `bike`
  ADD CONSTRAINT `fk_Bike` FOREIGN KEY (`park_id`) REFERENCES `parkinglot` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `event`
--
ALTER TABLE `event`
  ADD CONSTRAINT `fk_Event_Rental` FOREIGN KEY (`rental_id`) REFERENCES `rental` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `paymenttransaction`
--
ALTER TABLE `paymenttransaction`
  ADD CONSTRAINT `fk_PaymentTransaction_Rental` FOREIGN KEY (`rental_id`) REFERENCES `rental` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `rental`
--
ALTER TABLE `rental`
  ADD CONSTRAINT `fk_Card_Code` FOREIGN KEY (`cardcode`) REFERENCES `card` (`cardcode`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_Rental_Bike` FOREIGN KEY (`bike_id`) REFERENCES `bike` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_Rental_User` FOREIGN KEY (`rentee_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
