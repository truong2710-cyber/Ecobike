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
-- Table structure for table `bike`
--

DROP TABLE IF EXISTS `bike`;
CREATE TABLE `bike` (
  `id` int(11) NOT NULL,
  `type` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `country` varchar(45) DEFAULT NULL,
  `date_of_manufacture` datetime DEFAULT NULL,
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
(1, 'single', 'Wave', 'Japan', '2022-01-01 00:00:00', 'red', 3000, 100, 72, '29X1-01234', 3, 1, 1),
(2, 'double', 'Flash', 'US', '2021-10-11 10:00:00', 'blue', 4500, 80, 60, '30X3-5294', 10, 4, 0),
(3, 'electric double', 'Flash', 'US', '2021-10-13 16:00:00', 'blue', 6500, 80, 60, '30T1-34942', 5, 2, 0),
(4, 'electric single', 'Future', 'Vietnam', '2022-03-23 18:30:00', 'green', 5000, 40, 48, '32Y2-67295', 18, 2, 0),
(5, 'electric single', 'Wave', 'Japan', '2021-04-18 09:40:00', 'white', 5800, 60, 60, '33Y3-9246', 20, 1, 0),
(6, 'double', 'Flash', 'US', '2022-09-18 11:20:00', 'silver', 6500, 55, 48, '31F6-1056', 9, 4, 0);

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
('123412341234', 'Nguyen Phuc Tan', '111111', '2029-01-12'),
('123412344321', 'Nguyen Huy Hoang', '333333', '2025-01-13'),
('123456781234', 'Vu Quang Truong', '222222', '2026-01-13');

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

--
-- Dumping data for table `event`
--

INSERT INTO `event` (`id`, `rental_id`, `time`, `type`) VALUES
(1, 2, '2023-01-01 15:11:18', 'start'),
(2, 2, '2023-01-02 15:12:32', 'end'),
(6, 10, '2023-01-24 16:10:30', 'start');

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

--
-- Dumping data for table `paymenttransaction`
--

INSERT INTO `paymenttransaction` (`id`, `rental_id`, `amount`, `time`, `type`) VALUES
(1, 10, 1200, '2023-01-24 16:10:30', 'deposit');

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

--
-- Dumping data for table `rental`
--

INSERT INTO `rental` (`id`, `rentee_id`, `bike_id`, `cardcode`) VALUES
(1, 1, 2, '123456781234'),
(2, 2, 3, '123456781234'),
(3, 1, 5, '123412344321'),
(4, 3, 3, '123456781234'),
(5, 4, 6, '123412344321'),
(6, 2, 6, '123456781234'),
(7, 4, 6, '123456781234'),
(8, 1, 2, '123412344321'),
(9, 3, 4, '123456781234'),
(10, 3, 1, '123412344321');

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
(3, 'tannp', '12344321'),
(4, 'baonn', '11111111');

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
