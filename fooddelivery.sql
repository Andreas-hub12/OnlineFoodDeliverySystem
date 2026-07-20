-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 20, 2026 at 02:43 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `fooddelivery`
--

-- --------------------------------------------------------

--
-- Table structure for table `food_items`
--

CREATE TABLE `food_items` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `category` varchar(50) NOT NULL,
  `restaurant_name` varchar(255) DEFAULT 'Jollibee'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `food_items`
--

INSERT INTO `food_items` (`id`, `name`, `price`, `category`, `restaurant_name`) VALUES
(1, 'ChickenJoy', 99.00, 'Chicken', 'Jollibee'),
(2, 'Burger', 120.00, 'Fast Food', 'Jollibee'),
(4, 'Fried Chicken', 199.00, 'Meals', 'Jollibee'),
(5, 'Milk Tea', 95.00, 'Drinks', 'Jollibee'),
(8, 'Shawarma Rice', 175.00, 'Rice', 'Jollibee'),
(9, '1-pc Chickenjoy w/ Rice', 95.00, 'Chicken', 'Jollibee'),
(10, 'Yumburger', 40.00, 'Burgers', 'Jollibee'),
(11, 'Jolly Spaghetti', 60.00, 'Pasta', 'Jollibee'),
(12, 'Peach Mango Pie', 45.00, 'Dessert', 'Jollibee'),
(13, 'Big Mac', 175.00, 'Burgers', 'McDonald\'s'),
(14, '1-pc Chicken McDo w/ Rice', 99.00, 'Chicken', 'McDonald\'s'),
(15, 'McFries Medium', 70.00, 'Sides', 'McDonald\'s'),
(16, 'McFlurry Oreo', 65.00, 'Dessert', 'McDonald\'s'),
(17, '1-pc Original Recipe Chicken', 105.00, 'Chicken', 'KFC'),
(18, 'Zinger Burger', 160.00, 'Burgers', 'KFC'),
(19, 'Famous Bowl', 85.00, 'Meals', 'KFC'),
(20, 'Pork Chao Fan w/ Siomai', 110.00, 'Rice Meals', 'Chowking'),
(21, 'Sweet & Sour Pork Lauriat', 220.00, 'Lauriat', 'Chowking'),
(22, 'Super Sangkap Halo-Halo', 90.00, 'Dessert', 'Chowking'),
(23, 'PM1 Chicken Inasal Paa w/ Rice', 145.00, 'Chicken Inasal', 'Mang Inasal'),
(24, '2-pc Pork BBQ w/ Rice', 130.00, 'Barbecue', 'Mang Inasal'),
(25, 'Extra Creamy Halo-Halo', 85.00, 'Dessert', 'Mang Inasal'),
(26, 'Leylam Wrap', 85.00, 'Shawarma', 'Leylam'),
(27, 'Leylam Rice', 90.00, 'Rice Meals', 'Leylam'),
(28, 'Leylam Noodles', 85.00, 'Noodles', 'Leylam');

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `food_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `total` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`id`, `user_id`, `food_id`, `quantity`, `total`) VALUES
(1, 2, 1, 2, 190.00),
(2, 2, 4, 1, 65.00);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `role` enum('ADMIN','CUSTOMER') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `role`) VALUES
(1, 'admin', 'admin123', 'ADMIN'),
(2, 'customer', 'customer123', 'CUSTOMER'),
(3, 'test', 'test', 'CUSTOMER'),
(4, 'andi', '1234', 'CUSTOMER'),
(5, 'test2', 'test1', 'CUSTOMER');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `food_items`
--
ALTER TABLE `food_items`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_user` (`user_id`),
  ADD KEY `fk_food` (`food_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `food_items`
--
ALTER TABLE `food_items`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
