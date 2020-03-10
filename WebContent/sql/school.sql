-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 10, 2020 at 12:47 PM
-- Server version: 10.1.38-MariaDB
-- PHP Version: 7.3.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `school`
--

-- --------------------------------------------------------

--
-- Table structure for table `examination`
--

CREATE TABLE `examination` (
  `EXAMINATION_ID` int(11) NOT NULL,
  `EXAMINATION_NAME` varchar(255) NOT NULL,
  `EXAMINATION_DATE` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `examination`
--

INSERT INTO `examination` (`EXAMINATION_ID`, `EXAMINATION_NAME`, `EXAMINATION_DATE`) VALUES
(1, 'Peperiksaan OTI 1', '2020-03-10');

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `STUDENT_ID` int(100) NOT NULL,
  `STUDENT_IC` varchar(50) NOT NULL,
  `STUDENT_NAME` varchar(255) NOT NULL,
  `STUDENT_AGE` int(11) NOT NULL,
  `STUDENT_ADDRESS` varchar(255) NOT NULL,
  `CLASS_NAME` varchar(255) NOT NULL,
  `GUARDIAN_NAME` varchar(255) NOT NULL,
  `GUARDIAN_JOB` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`STUDENT_ID`, `STUDENT_IC`, `STUDENT_NAME`, `STUDENT_AGE`, `STUDENT_ADDRESS`, `CLASS_NAME`, `GUARDIAN_NAME`, `GUARDIAN_JOB`) VALUES
(1, '3', 'Ali Baba Bin Rifa\'i', 14, 'Taman Mawar', '2 CENDIKIAWAN', 'Abu', 'Polis'),
(100, '3', 'a', 3, 'd', '4 DAMAI', 's', 'd'),
(101, '3', 'a', 3, 'd', '1 ARIF', 's', 'd'),
(102, '980519065633', 'Imran', 321, 'd', '2 BESTARI', 'Sasudin', 'A');

-- --------------------------------------------------------

--
-- Table structure for table `teacher`
--

CREATE TABLE `teacher` (
  `TEACHER_ID` int(11) NOT NULL,
  `PASSWORD` varchar(100) NOT NULL,
  `TEACHER_NAME` varchar(250) NOT NULL,
  `CLASS_HANDLE` varchar(100) NOT NULL,
  `DEPARTMENT` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `teacher`
--

INSERT INTO `teacher` (`TEACHER_ID`, `PASSWORD`, `TEACHER_NAME`, `CLASS_HANDLE`, `DEPARTMENT`) VALUES
(1000, '123', 'Siti Rahmah', '1 ARIF', 'Matematik'),
(1001, '123', 'Ali Abu Hassan', '4 EFISIEN', 'Bahasa Melayu 2'),
(1003, '123', 'Imran Sasudin', '2 CENDIKIAWAN', 'Matematik'),
(1004, '123', 'Teacher Test 1', '3 EFISIEN', 'English');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `examination`
--
ALTER TABLE `examination`
  ADD PRIMARY KEY (`EXAMINATION_ID`);

--
-- Indexes for table `teacher`
--
ALTER TABLE `teacher`
  ADD PRIMARY KEY (`TEACHER_ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `examination`
--
ALTER TABLE `examination`
  MODIFY `EXAMINATION_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `teacher`
--
ALTER TABLE `teacher`
  MODIFY `TEACHER_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1005;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
