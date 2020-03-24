-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 24, 2020 at 08:09 PM
-- Server version: 10.1.40-MariaDB
-- PHP Version: 7.3.5

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
-- Table structure for table `event`
--

CREATE TABLE `event` (
  `EVENT_ID` int(11) NOT NULL,
  `EVENT_NAME` varchar(250) NOT NULL,
  `EVENT_PLACE` varchar(250) NOT NULL,
  `EVENT_DATE` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `event`
--

INSERT INTO `event` (`EVENT_ID`, `EVENT_NAME`, `EVENT_PLACE`, `EVENT_DATE`) VALUES
(1, 'Kem Matematik', 'Hotel Sri Malaysia', '2020-03-16'),
(2, 'Kem Jawi', 'Surau Sekolah', '2020-04-09');

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
(1, 'OTI 1', '2020-03-10'),
(4, 'TOV', '2020-05-01'),
(5, 'OTI 2', '2020-06-01'),
(6, 'OTI 3', '2020-07-01');

-- --------------------------------------------------------

--
-- Table structure for table `grade`
--

CREATE TABLE `grade` (
  `GRADE_ID` int(11) NOT NULL,
  `GRADE_NAME` varchar(20) NOT NULL,
  `GRADE_MARK` decimal(11,2) NOT NULL,
  `CATEGORY` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `grade`
--

INSERT INTO `grade` (`GRADE_ID`, `GRADE_NAME`, `GRADE_MARK`, `CATEGORY`) VALUES
(1, 'A', '4.00', 1),
(2, 'B', '3.33', 1),
(3, 'C', '2.67', 1),
(4, 'D', '2.00', 1),
(5, 'E', '1.33', 1),
(6, 'F', '0.67', 1),
(7, 'A+', '4.00', 2),
(8, 'A', '3.75', 2),
(9, 'A-', '3.50', 2),
(10, 'B+', '3.25', 2),
(11, 'B', '3.00', 2),
(12, 'C+', '2.75', 2),
(13, 'C', '2.50', 2),
(14, 'D', '2.25', 2),
(15, 'E', '2.00', 2),
(16, 'G', '1.75', 2);

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
(1, '980519065612', 'Ali Baba', 14, 'Taman Mawar', '2 CENDIKIAWAN', 'Abu', 'Polis'),
(100, '980519065613', 'Siti', 3, 'd', '4 DAMAI', 's', 'd'),
(101, '980519065614', 'Sameon', 3, 'd', '1 ARIF', 's', 'd'),
(102, '980519065633', 'Imran', 16, 'Terengganu', '2 BESTARI', 'Sasudin', 'A'),
(103, '980519065611', 'Ahmad', 11, 'dsa', '5 BESTARI', 'Ahmad Abu', 'Polis');

-- --------------------------------------------------------

--
-- Table structure for table `studentevent`
--

CREATE TABLE `studentevent` (
  `STUDENT_ID` int(11) NOT NULL,
  `EVENT_ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `studentevent`
--

INSERT INTO `studentevent` (`STUDENT_ID`, `EVENT_ID`) VALUES
(101, 1),
(102, 1),
(102, 2);

-- --------------------------------------------------------

--
-- Table structure for table `studentgrade`
--

CREATE TABLE `studentgrade` (
  `STUDENT_ID` int(11) NOT NULL,
  `SUBJECT_ID` int(11) NOT NULL,
  `EXAMINATION_ID` int(11) NOT NULL,
  `GRADE_ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `studentgrade`
--

INSERT INTO `studentgrade` (`STUDENT_ID`, `SUBJECT_ID`, `EXAMINATION_ID`, `GRADE_ID`) VALUES
(1, 11, 1, 1),
(1, 16, 1, 1),
(102, 11, 1, 1),
(102, 11, 4, 1),
(102, 11, 5, 1),
(102, 12, 4, 1),
(102, 12, 5, 1),
(102, 13, 5, 1),
(102, 14, 5, 1),
(102, 15, 5, 1),
(102, 16, 5, 1),
(102, 17, 1, 1),
(102, 17, 5, 1),
(102, 18, 5, 1),
(102, 19, 5, 1),
(102, 20, 5, 1),
(103, 11, 1, 1),
(1, 12, 1, 2),
(1, 13, 1, 2),
(1, 14, 1, 2),
(1, 18, 1, 2),
(102, 12, 1, 2),
(102, 13, 4, 2),
(102, 15, 1, 2),
(102, 17, 4, 2),
(102, 18, 1, 2),
(102, 18, 4, 2),
(103, 12, 1, 2),
(103, 16, 1, 2),
(103, 19, 1, 2),
(1, 15, 1, 3),
(1, 17, 1, 3),
(1, 20, 1, 3),
(102, 14, 1, 3),
(102, 14, 4, 3),
(102, 15, 4, 3),
(102, 16, 4, 3),
(102, 20, 1, 3),
(103, 15, 1, 3),
(102, 13, 6, 4),
(102, 15, 6, 4),
(102, 16, 1, 4),
(102, 18, 6, 4),
(102, 20, 4, 4),
(103, 14, 1, 4),
(103, 17, 1, 4),
(1, 19, 1, 5),
(102, 12, 6, 5),
(102, 13, 1, 5),
(102, 14, 6, 5),
(102, 17, 6, 5),
(102, 19, 1, 5),
(102, 20, 6, 5),
(103, 13, 1, 5),
(103, 18, 1, 5),
(102, 11, 6, 6),
(102, 16, 6, 6),
(102, 19, 4, 6),
(102, 19, 6, 6),
(103, 20, 1, 6);

-- --------------------------------------------------------

--
-- Table structure for table `subject`
--

CREATE TABLE `subject` (
  `SUBJECT_ID` int(11) NOT NULL,
  `SUBJECT_NAME` varchar(100) NOT NULL,
  `CATEGORY` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `subject`
--

INSERT INTO `subject` (`SUBJECT_ID`, `SUBJECT_NAME`, `CATEGORY`) VALUES
(1, 'BM', 2),
(2, 'BI', 2),
(3, 'MATH', 2),
(4, 'SEJ', 2),
(5, 'PAI', 2),
(6, 'PJK', 2),
(7, 'FZ', 2),
(8, 'KM', 2),
(9, 'BIO', 2),
(10, 'ADD MATH', 2),
(11, 'BM (BERTULIS)', 1),
(12, 'BM (LISAN)', 1),
(13, 'BI (BERTULIS)', 1),
(14, 'BI (LISAN)', 1),
(15, 'SEJ', 1),
(16, 'GEO', 1),
(17, 'PAI', 1),
(18, 'MATH', 1),
(19, 'SC', 1),
(20, 'KH', 1);

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
-- Indexes for table `event`
--
ALTER TABLE `event`
  ADD PRIMARY KEY (`EVENT_ID`);

--
-- Indexes for table `examination`
--
ALTER TABLE `examination`
  ADD PRIMARY KEY (`EXAMINATION_ID`);

--
-- Indexes for table `grade`
--
ALTER TABLE `grade`
  ADD PRIMARY KEY (`GRADE_ID`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`STUDENT_ID`);

--
-- Indexes for table `studentevent`
--
ALTER TABLE `studentevent`
  ADD PRIMARY KEY (`STUDENT_ID`,`EVENT_ID`),
  ADD KEY `STUDENTEVENT_FK2` (`EVENT_ID`);

--
-- Indexes for table `studentgrade`
--
ALTER TABLE `studentgrade`
  ADD PRIMARY KEY (`STUDENT_ID`,`SUBJECT_ID`,`EXAMINATION_ID`),
  ADD KEY `STUDENTGRADE_FK2` (`SUBJECT_ID`),
  ADD KEY `STUDENTGRADE_FK3` (`EXAMINATION_ID`),
  ADD KEY `STUDENTGRADE_FK4` (`GRADE_ID`);

--
-- Indexes for table `subject`
--
ALTER TABLE `subject`
  ADD PRIMARY KEY (`SUBJECT_ID`);

--
-- Indexes for table `teacher`
--
ALTER TABLE `teacher`
  ADD PRIMARY KEY (`TEACHER_ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `event`
--
ALTER TABLE `event`
  MODIFY `EVENT_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `examination`
--
ALTER TABLE `examination`
  MODIFY `EXAMINATION_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `grade`
--
ALTER TABLE `grade`
  MODIFY `GRADE_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `student`
--
ALTER TABLE `student`
  MODIFY `STUDENT_ID` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=104;

--
-- AUTO_INCREMENT for table `subject`
--
ALTER TABLE `subject`
  MODIFY `SUBJECT_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `teacher`
--
ALTER TABLE `teacher`
  MODIFY `TEACHER_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1005;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `studentevent`
--
ALTER TABLE `studentevent`
  ADD CONSTRAINT `STUDENTEVENT_FK1` FOREIGN KEY (`STUDENT_ID`) REFERENCES `student` (`STUDENT_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `STUDENTEVENT_FK2` FOREIGN KEY (`EVENT_ID`) REFERENCES `event` (`EVENT_ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `studentgrade`
--
ALTER TABLE `studentgrade`
  ADD CONSTRAINT `STUDENTGRADE_FK1` FOREIGN KEY (`STUDENT_ID`) REFERENCES `student` (`STUDENT_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `STUDENTGRADE_FK2` FOREIGN KEY (`SUBJECT_ID`) REFERENCES `subject` (`SUBJECT_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `STUDENTGRADE_FK3` FOREIGN KEY (`EXAMINATION_ID`) REFERENCES `examination` (`EXAMINATION_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `STUDENTGRADE_FK4` FOREIGN KEY (`GRADE_ID`) REFERENCES `grade` (`GRADE_ID`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
