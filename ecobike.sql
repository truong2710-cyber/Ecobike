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
(1, 'single', 'Arrow', 'Vietnam', '2022-09-16', 'blue', 9700000, 64, 96, '31A2-85000', 3, 2, 0),
(2, 'double', 'Express', 'China', '2020-04-23', 'blank', 1400000, 66, 120, '30E4-90928', 11, 4, 0),
(3, 'electric single', 'Express', 'Germany', '2021-10-28', 'green', 5600000, 51, 120, '30T3-30745', 26, 4, 0),
(4, 'single', 'Alpha', 'China', '2022-01-05', 'blue', 1500000, 73, 144, '29C4-47911', 49, 3, 0),
(5, 'single', 'Phoenix', 'US', '2020-12-22', 'silver', 6900000, 69, 72, '31C3-47981', 21, 4, 0),
(6, 'double', 'Cheetah', 'Vietnam', '2021-06-12', 'blue', 6500000, 92, 144, '30S7-27662', 7, 1, 0),
(7, 'single', 'Alpha', 'Vietnam', '2022-09-08', 'red', 3200000, 51, 120, '31V7-18367', 15, 3, 0),
(8, 'electric double', 'Flash', 'France', '2022-08-11', 'white', 3500000, 88, 120, '33X4-49808', 28, 2, 0),
(9, 'double', 'Tornado', 'China', '2021-03-14', 'white', 5300000, 71, 120, '30L3-76938', 9, 2, 0),
(10, 'electric single', 'Arrow', 'China', '2021-07-13', 'blue', 6800000, 65, 72, '30K6-41039', 36, 3, 0),
(11, 'electric single', 'Cheetah', 'US', '2022-03-06', 'blank', 9700000, 48, 120, '31X8-2733', 14, 2, 0),
(12, 'electric single', 'Alpha', 'France', '2022-10-20', 'green', 9100000, 70, 96, '29N1-13608', 25, 4, 0),
(13, 'electric single', 'Flash', 'Germany', '2022-01-08', 'green', 5800000, 81, 120, '32X2-90453', 17, 1, 0),
(14, 'electric double', 'Cheetah', 'UK', '2022-09-25', 'red', 8800000, 68, 120, '31F9-48628', 30, 1, 0),
(15, 'electric double', 'Tornado', 'Vietnam', '2022-02-12', 'gold', 8200000, 46, 120, '29M3-55661', 6, 2, 0),
(16, 'single', 'Bullet', 'Japan', '2022-11-09', 'silver', 5100000, 100, 96, '31P6-93447', 1, 2, 0),
(17, 'double', 'Bullet', 'Japan', '2020-04-15', 'blank', 6400000, 66, 144, '32L0-29065', 19, 3, 0),
(18, 'electric double', 'Express', 'Japan', '2021-09-03', 'green', 7200000, 66, 120, '29Y0-62543', 19, 1, 0),
(19, 'single', 'Phoenix', 'UK', '2020-03-17', 'blue', 4000000, 84, 144, '33V4-71737', 47, 3, 0),
(20, 'electric double', 'Flash', 'UK', '2022-11-11', 'blue', 4300000, 59, 144, '33V1-34261', 8, 4, 0),
(21, 'electric single', 'Alpha', 'France', '2021-10-16', 'silver', 9300000, 56, 96, '29T8-96743', 10, 4, 0),
(22, 'single', 'Arrow', 'China', '2021-05-23', 'white', 9700000, 50, 96, '30B6-86905', 20, 3, 0),
(23, 'electric double', 'Phoenix', 'US', '2020-11-18', 'silver', 8100000, 100, 120, '33V0-79655', 3, 1, 0),
(24, 'single', 'Flash', 'UK', '2021-02-08', 'blank', 7300000, 76, 144, '31U0-52059', 38, 1, 0),
(25, 'electric single', 'Flash', 'Germany', '2020-03-14', 'red', 2900000, 71, 120, '31X2-35319', 26, 3, 0),
(26, 'double', 'Bullet', 'Japan', '2021-07-09', 'blank', 1200000, 45, 72, '30B0-89949', 8, 1, 0),
(27, 'single', 'Bullet', 'Korea', '2020-08-15', 'red', 3400000, 40, 96, '33P8-96949', 1, 4, 0),
(28, 'double', 'Arrow', 'Korea', '2022-06-07', 'white', 6300000, 86, 72, '30T2-18556', 27, 2, 0),
(29, 'double', 'Tornado', 'US', '2020-10-09', 'silver', 8900000, 41, 96, '32Z7-79014', 21, 2, 0),
(30, 'electric double', 'Alpha', 'Vietnam', '2021-04-01', 'blue', 9400000, 91, 96, '30B4-86434', 33, 1, 0),
(31, 'double', 'Bullet', 'Japan', '2021-05-12', 'white', 9700000, 71, 120, '30G7-74368', 18, 1, 0),
(32, 'electric single', 'Phoenix', 'Germany', '2021-09-24', 'silver', 1800000, 87, 120, '30D5-33218', 22, 1, 0),
(33, 'electric single', 'Flash', 'Japan', '2020-12-25', 'green', 7700000, 58, 120, '31Y3-47859', 2, 2, 0),
(34, 'electric double', 'Phoenix', 'Japan', '2022-12-13', 'blue', 4600000, 97, 96, '30Z2-33815', 9, 1, 0),
(35, 'single', 'Alpha', 'Germany', '2022-10-27', 'green', 9200000, 40, 144, '33S6-73654', 2, 3, 0),
(36, 'double', 'Express', 'Vietnam', '2021-07-01', 'white', 1500000, 55, 144, '33V5-60478', 2, 1, 0),
(37, 'double', 'Bullet', 'Vietnam', '2021-05-23', 'white', 9900000, 41, 72, '29U3-50132', 26, 2, 0),
(38, 'single', 'Arrow', 'UK', '2020-07-09', 'red', 7300000, 66, 72, '33L5-41751', 18, 2, 0),
(39, 'electric single', 'Express', 'UK', '2021-10-21', 'gold', 9100000, 98, 120, '30S6-68858', 50, 3, 0),
(40, 'double', 'Tornado', 'Korea', '2020-06-17', 'green', 4400000, 42, 144, '32C7-55325', 45, 3, 0),
(41, 'electric double', 'Tornado', 'China', '2020-07-03', 'red', 3700000, 95, 72, '32D8-81529', 4, 3, 0),
(42, 'electric double', 'Tornado', 'Vietnam', '2021-03-26', 'red', 9300000, 81, 96, '29X9-58904', 41, 3, 0),
(43, 'single', 'Cheetah', 'Germany', '2022-04-24', 'red', 7200000, 81, 120, '33Y0-1376', 1, 1, 0),
(44, 'electric single', 'Express', 'France', '2020-01-12', 'green', 1600000, 56, 144, '33A6-42611', 19, 2, 0),
(45, 'electric double', 'Flash', 'Japan', '2021-01-20', 'white', 6100000, 87, 72, '31M5-69756', 23, 3, 0),
(46, 'electric single', 'Arrow', 'Germany', '2021-09-06', 'red', 2600000, 89, 120, '30Y5-62563', 31, 3, 0),
(47, 'double', 'Bullet', 'China', '2020-05-13', 'silver', 2400000, 81, 96, '30T4-36993', 13, 3, 0),
(48, 'single', 'Alpha', 'Germany', '2022-07-09', 'green', 4900000, 67, 144, '33P0-42004', 5, 3, 0),
(49, 'electric double', 'Bullet', 'Germany', '2020-02-08', 'blank', 2900000, 91, 120, '33L9-56035', 22, 3, 0),
(50, 'electric single', 'Cheetah', 'Korea', '2022-04-26', 'blank', 2600000, 71, 72, '29A8-7775', 37, 3, 0),
(51, 'single', 'Cheetah', 'Germany', '2022-04-03', 'blank', 1100000, 70, 96, '30G1-35559', 29, 4, 0),
(52, 'electric double', 'Phoenix', 'Vietnam', '2020-01-06', 'blank', 2000000, 98, 144, '31D6-49867', 26, 1, 0),
(53, 'electric single', 'Express', 'France', '2021-05-26', 'silver', 9000000, 88, 96, '33V3-75561', 3, 3, 0),
(54, 'single', 'Alpha', 'China', '2021-08-18', 'silver', 2900000, 92, 96, '31H8-76602', 31, 1, 0),
(55, 'electric single', 'Express', 'Japan', '2020-07-25', 'red', 2400000, 75, 144, '32H3-44662', 16, 3, 0),
(56, 'electric double', 'Express', 'France', '2022-09-03', 'white', 4700000, 85, 96, '30X7-82908', 16, 2, 0),
(57, 'electric double', 'Arrow', 'US', '2020-08-03', 'silver', 1400000, 87, 96, '33U9-3118', 7, 4, 0),
(58, 'double', 'Express', 'US', '2021-08-13', 'red', 4200000, 96, 72, '33X4-93637', 9, 4, 0),
(59, 'single', 'Alpha', 'Japan', '2020-08-08', 'blank', 7200000, 61, 120, '31E5-30962', 20, 4, 0),
(60, 'single', 'Cheetah', 'Vietnam', '2021-05-04', 'blue', 5800000, 92, 96, '30U6-84491', 8, 3, 0),
(61, 'double', 'Arrow', 'US', '2022-09-26', 'silver', 6200000, 77, 96, '31S6-53704', 42, 3, 0),
(62, 'single', 'Tornado', 'China', '2022-07-15', 'red', 9000000, 45, 96, '32T6-43802', 24, 1, 0),
(63, 'double', 'Flash', 'Japan', '2021-10-25', 'silver', 4000000, 89, 72, '31F1-78581', 30, 4, 0),
(64, 'electric single', 'Bullet', 'France', '2020-02-20', 'red', 1200000, 48, 96, '30U2-67638', 13, 1, 0),
(65, 'electric double', 'Bullet', 'Korea', '2020-05-04', 'silver', 8800000, 86, 120, '31T0-1532', 21, 3, 0),
(66, 'single', 'Alpha', 'China', '2022-05-04', 'gold', 6600000, 68, 144, '30Z8-27519', 10, 2, 0),
(67, 'electric single', 'Flash', 'Korea', '2020-04-09', 'gold', 7300000, 97, 72, '31T6-85659', 6, 1, 0),
(68, 'single', 'Bullet', 'Japan', '2022-08-17', 'blue', 7600000, 82, 72, '29B9-13088', 25, 3, 0),
(69, 'electric double', 'Tornado', 'UK', '2020-09-20', 'silver', 4000000, 57, 120, '31D8-17280', 36, 1, 0),
(70, 'double', 'Flash', 'China', '2022-09-19', 'red', 2600000, 71, 144, '32N0-55763', 23, 1, 0),
(71, 'single', 'Bullet', 'China', '2020-11-27', 'blue', 4800000, 82, 120, '32P1-30796', 14, 1, 0),
(72, 'single', 'Bullet', 'UK', '2022-02-08', 'white', 2600000, 46, 96, '30C7-1166', 24, 3, 0),
(73, 'double', 'Alpha', 'China', '2022-04-22', 'silver', 3800000, 99, 72, '31T7-8178', 25, 1, 0),
(74, 'single', 'Arrow', 'France', '2022-02-03', 'gold', 6300000, 75, 120, '31D1-28485', 16, 1, 0),
(75, 'electric double', 'Cheetah', 'Germany', '2021-11-02', 'silver', 7200000, 46, 96, '33L7-73536', 15, 4, 0),
(76, 'electric single', 'Arrow', 'Korea', '2020-05-17', 'silver', 3900000, 75, 144, '31N5-48751', 24, 4, 0),
(77, 'double', 'Alpha', 'US', '2022-10-17', 'green', 5700000, 67, 144, '32T6-41785', 17, 3, 0),
(78, 'electric double', 'Tornado', 'Vietnam', '2021-06-03', 'red', 1200000, 76, 120, '30B5-68514', 30, 3, 0),
(79, 'electric single', 'Arrow', 'Germany', '2022-05-09', 'gold', 1700000, 95, 144, '30U8-62936', 2, 4, 0),
(80, 'single', 'Bullet', 'Germany', '2022-03-07', 'blank', 8400000, 56, 120, '33M7-91138', 5, 4, 0),
(81, 'single', 'Bullet', 'Germany', '2021-05-07', 'silver', 7600000, 85, 144, '30B1-87768', 28, 1, 0),
(82, 'single', 'Arrow', 'Vietnam', '2021-02-26', 'silver', 1900000, 91, 120, '33Z5-57824', 46, 3, 0),
(83, 'electric single', 'Alpha', 'UK', '2020-02-19', 'gold', 6100000, 100, 96, '29M8-42981', 34, 1, 0),
(84, 'single', 'Cheetah', 'Japan', '2022-02-22', 'blank', 6500000, 65, 72, '31G7-2208', 27, 3, 0),
(85, 'double', 'Cheetah', 'France', '2021-01-03', 'blue', 6100000, 90, 72, '33S0-92989', 40, 3, 0),
(86, 'electric single', 'Arrow', 'Korea', '2022-05-24', 'blue', 1500000, 64, 96, '32H5-35042', 32, 3, 0),
(87, 'electric single', 'Express', 'Korea', '2021-05-24', 'silver', 7200000, 93, 120, '33D1-13927', 12, 2, 0),
(88, 'single', 'Arrow', 'Korea', '2022-07-05', 'blue', 5900000, 65, 96, '33A0-91482', 11, 1, 0),
(89, 'double', 'Flash', 'Korea', '2022-06-26', 'white', 3900000, 59, 72, '33H7-13396', 20, 2, 0),
(90, 'electric double', 'Phoenix', 'US', '2020-12-03', 'blank', 7500000, 42, 144, '30U2-16494', 35, 1, 0),
(91, 'electric single', 'Alpha', 'Japan', '2020-12-08', 'white', 1300000, 62, 72, '32D0-85222', 10, 1, 0),
(92, 'electric single', 'Express', 'China', '2022-06-21', 'red', 7800000, 70, 120, '30F0-29217', 30, 2, 0),
(93, 'double', 'Cheetah', 'US', '2021-02-12', 'white', 9200000, 92, 72, '32B9-59424', 7, 3, 0),
(94, 'electric double', 'Express', 'UK', '2020-04-05', 'blue', 1900000, 93, 144, '29Y5-24714', 33, 3, 0),
(95, 'electric double', 'Alpha', 'Japan', '2022-11-22', 'gold', 1300000, 77, 144, '32A2-65431', 22, 2, 0),
(96, 'electric single', 'Flash', 'US', '2021-05-10', 'blue', 1700000, 47, 120, '29T5-78653', 15, 1, 0),
(97, 'electric double', 'Bullet', 'UK', '2021-12-26', 'red', 6300000, 91, 72, '30C6-62436', 39, 3, 0),
(98, 'single', 'Alpha', 'Korea', '2021-11-12', 'red', 3400000, 55, 120, '33T5-82092', 39, 1, 0),
(99, 'double', 'Tornado', 'Vietnam', '2022-05-14', 'red', 7900000, 60, 144, '33L2-44166', 34, 3, 0),
(100, 'electric double', 'Flash', 'Vietnam', '2022-07-12', 'red', 6000000, 64, 72, '30X5-48234', 13, 2, 0);

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
