-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3307
-- Generation Time: May 06, 2020 at 06:31 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `testpaf`
--

-- --------------------------------------------------------

--
-- Table structure for table `appointment`
--

CREATE TABLE `appointment` (
  `appointmentid` int(4) NOT NULL,
  `doctorid` int(4) NOT NULL,
  `patientid` int(4) NOT NULL,
  `hospitalid` int(4) NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `appointment`
--

INSERT INTO `appointment` (`appointmentid`, `doctorid`, `patientid`, `hospitalid`, `date`) VALUES
(4, 1, 5, 2, '2020-04-15'),
(5, 1, 6, 2, '2020-04-16'),
(6, 1, 7, 2, '2020-04-17'),
(7, 1, 8, 2, '2020-04-17');

-- --------------------------------------------------------

--
-- Table structure for table `doctor`
--

CREATE TABLE `doctor` (
  `doctorid` int(4) NOT NULL,
  `firstname` varchar(12) NOT NULL,
  `lastname` varchar(12) NOT NULL,
  `email` varchar(40) NOT NULL,
  `password` varchar(15) NOT NULL,
  `phonenumber` int(10) NOT NULL,
  `specalization` varchar(20) NOT NULL,
  `charges` int(6) NOT NULL,
  `type` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `hospital`
--

CREATE TABLE `hospital` (
  `hospitalid` int(4) NOT NULL,
  `name` varchar(30) NOT NULL,
  `address` varchar(40) NOT NULL,
  `charge` double NOT NULL,
  `phonenumber` varchar(10) NOT NULL,
  `roomcount` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `hospital`
--

INSERT INTO `hospital` (`hospitalid`, `name`, `address`, `charge`, `phonenumber`, `roomcount`) VALUES
(2, 'Hemas Hospital', 'Homagama', 0, '0112750750', 60),
(3, 'General Hospital', 'Kandy', 0, '0112750698', 75),
(4, 'Cancer Hospital', 'Maharagama', 0, '0112985750', 200),
(5, 'Lanka Hospital', 'Colombo', 0, '0112719850', 210),
(20, 'Delmon', 'Kandy', 1250, '011236547', 250),
(21, 'Lanka', 'colombo', 1250, '011236547', 250);

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `username` varchar(20) NOT NULL,
  `password` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`username`, `password`) VALUES
('Anupama', 'anupama123'),
('isuru', 'isuru123'),
('kushan', 'kushan123'),
('muzir', 'munzil123'),
('shahan', 'shahan123');

-- --------------------------------------------------------

--
-- Table structure for table `patient`
--

CREATE TABLE `patient` (
  `patientid` int(4) NOT NULL,
  `name` varchar(15) NOT NULL,
  `password` varchar(15) NOT NULL,
  `email` varchar(30) NOT NULL,
  `phonenumber` int(10) NOT NULL,
  `address` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `patient`
--

INSERT INTO `patient` (`patientid`, `name`, `password`, `email`, `phonenumber`, `address`) VALUES
(5, 'shahan', 'shahan123', 'shahan@gmail.com', 778519292, 'mathara'),
(6, 'kushan', 'kushan123', 'kushan@gmail.com', 778518291, 'malabe'),
(7, 'isuru', 'isuru123', 'isuru@gmail.com', 778614251, 'kaduwela'),
(8, 'Anupama', 'anupama123', 'anupama@gmail.com', 778765453, 'matale');

--
-- Triggers `patient`
--
DELIMITER $$
CREATE TRIGGER `inserttologin` AFTER INSERT ON `patient` FOR EACH ROW INSERT INTO login VALUES(NEW.name,NEW.password)
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE `payment` (
  `paymentid` int(4) NOT NULL,
  `patientid` int(4) NOT NULL,
  `appointmentid` int(4) NOT NULL,
  `date` varchar(20) NOT NULL,
  `cardtype` varchar(10) NOT NULL,
  `cardnumber` varchar(20) NOT NULL,
  `expirydate` varchar(20) NOT NULL,
  `pinnumber` int(4) NOT NULL,
  `amount` int(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `payment`
--

INSERT INTO `payment` (`paymentid`, `patientid`, `appointmentid`, `date`, `cardtype`, `cardnumber`, `expirydate`, `pinnumber`, `amount`) VALUES
(16, 5, 4, '16-APR-2020', 'credit', '1677122213333', '16-JAN-2027', 555, 1000),
(17, 6, 5, '16-APR-2020', 'debit', '1677122213336', '16-JAN-2027', 666, 2000),
(18, 7, 6, '17-APR-2020', 'debit', '1677122213330', '17-JAN-2027', 777, 3000),
(19, 8, 7, '17-APR-2020', 'debit', '1677122213321', '17-JAN-2027', 888, 1200);

-- --------------------------------------------------------

--
-- Table structure for table `registereddoctor`
--

CREATE TABLE `registereddoctor` (
  `doctorid` int(4) NOT NULL,
  `firstname` varchar(15) NOT NULL,
  `lastname` varchar(15) NOT NULL,
  `email` varchar(40) NOT NULL,
  `password` varchar(40) NOT NULL,
  `phonenumber` int(10) NOT NULL,
  `specalization` varchar(20) NOT NULL,
  `charges` int(10) NOT NULL,
  `type` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `registereddoctor`
--

INSERT INTO `registereddoctor` (`doctorid`, `firstname`, `lastname`, `email`, `password`, `phonenumber`, `specalization`, `charges`, `type`) VALUES
(1, 'muzir', 'romeo', 'munzir@gmail.com', 'munzil123', 778519291, 'eyes', 1000, 'permenent');

--
-- Triggers `registereddoctor`
--
DELIMITER $$
CREATE TRIGGER `insertlogin` AFTER INSERT ON `registereddoctor` FOR EACH ROW INSERT INTO login VALUES(NEW.firstname,NEW.password)
$$
DELIMITER ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `appointment`
--
ALTER TABLE `appointment`
  ADD PRIMARY KEY (`appointmentid`),
  ADD KEY `hospitalid` (`hospitalid`),
  ADD KEY `patientid` (`patientid`),
  ADD KEY `appointment_ibfk_1` (`doctorid`);

--
-- Indexes for table `doctor`
--
ALTER TABLE `doctor`
  ADD PRIMARY KEY (`doctorid`);

--
-- Indexes for table `hospital`
--
ALTER TABLE `hospital`
  ADD PRIMARY KEY (`hospitalid`);

--
-- Indexes for table `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `patient`
--
ALTER TABLE `patient`
  ADD PRIMARY KEY (`patientid`);

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`paymentid`),
  ADD KEY `patientid` (`patientid`),
  ADD KEY `payment_ibfk_2` (`appointmentid`);

--
-- Indexes for table `registereddoctor`
--
ALTER TABLE `registereddoctor`
  ADD PRIMARY KEY (`doctorid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `appointment`
--
ALTER TABLE `appointment`
  MODIFY `appointmentid` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `doctor`
--
ALTER TABLE `doctor`
  MODIFY `doctorid` int(4) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `patient`
--
ALTER TABLE `patient`
  MODIFY `patientid` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `payment`
--
ALTER TABLE `payment`
  MODIFY `paymentid` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `appointment`
--
ALTER TABLE `appointment`
  ADD CONSTRAINT `appointment_ibfk_1` FOREIGN KEY (`doctorid`) REFERENCES `registereddoctor` (`doctorid`),
  ADD CONSTRAINT `appointment_ibfk_2` FOREIGN KEY (`hospitalid`) REFERENCES `hospital` (`hospitalid`),
  ADD CONSTRAINT `appointment_ibfk_3` FOREIGN KEY (`patientid`) REFERENCES `patient` (`patientid`);

--
-- Constraints for table `payment`
--
ALTER TABLE `payment`
  ADD CONSTRAINT `payment_ibfk_1` FOREIGN KEY (`patientid`) REFERENCES `patient` (`patientid`),
  ADD CONSTRAINT `payment_ibfk_2` FOREIGN KEY (`appointmentid`) REFERENCES `appointment` (`appointmentid`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
