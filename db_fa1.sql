

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_fa1`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_applicants`
--
-- Error reading structure for table db_fa1.tbl_applicants: #1932 - Table 'db_fa1.tbl_applicants' doesn't exist in engine
-- Error reading data for table db_fa1.tbl_applicants: #1064 - You have an error in your SQL syntax; check the manual that corresponds to your MariaDB server version for the right syntax to use near 'FROM `db_fa1`.`tbl_applicants`' at line 1

-- --------------------------------------------------------
CREATE DATABASE `db_fa1` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
--
-- Table structure for table `tbl_applicants1`
--

CREATE TABLE `tbl_applicants1` (
  `id` int(11) NOT NULL,
  `applicant_name` varchar(50) NOT NULL,
  `service` varchar(50) NOT NULL,
  `applicant_address` varchar(50) NOT NULL,
  `applicant_contact` varchar(50) NOT NULL,
  `applicant_created_on` date NOT NULL DEFAULT current_timestamp(),
  `Status` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tbl_applicants1`
--

INSERT INTO `tbl_applicants1` (`id`, `applicant_name`, `service`, `applicant_address`, `applicant_contact`, `applicant_created_on`, `Status`) VALUES
(6, 'SANDY', 'Broken furniture', '6 Well street', '0712345678', '2021-03-26', 'Pending');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_login`
--
-- Error reading structure for table db_fa1.tbl_login: #1932 - Table 'db_fa1.tbl_login' doesn't exist in engine
-- Error reading data for table db_fa1.tbl_login: #1064 - You have an error in your SQL syntax; check the manual that corresponds to your MariaDB server version for the right syntax to use near 'FROM `db_fa1`.`tbl_login`' at line 1

-- --------------------------------------------------------

--
-- Table structure for table `tbl_login1`
--

CREATE TABLE `tbl_login1` (
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `usertype` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tbl_login1`
--

INSERT INTO `tbl_login1` (`username`, `password`, `usertype`) VALUES
('ANDY', 'ANDY', 'Staff'),
('JOHN', 'JOHN', 'Staff'),
('Kgotso', 'Password', ' ');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_projects`
--
-- Error reading structure for table db_fa1.tbl_projects: #1932 - Table 'db_fa1.tbl_projects' doesn't exist in engine
-- Error reading data for table db_fa1.tbl_projects: #1064 - You have an error in your SQL syntax; check the manual that corresponds to your MariaDB server version for the right syntax to use near 'FROM `db_fa1`.`tbl_projects`' at line 1

-- --------------------------------------------------------

--
-- Table structure for table `tbl_projects1`
--

CREATE TABLE `tbl_projects1` (
  `id` int(11) NOT NULL,
  `project_type` varchar(50) NOT NULL,
  `materials` varchar(50) NOT NULL,
  `applicant_name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tbl_projects1`
--

INSERT INTO `tbl_projects1` (`id`, `project_type`, `materials`, `applicant_name`) VALUES
(1, 'Broken furniture', 'Furniture toolkit', 'Sara'),
(2, 'Gardening', 'Gardening Kit', 'Sandra'),
(3, 'Electrical faults', 'ELECTRICAL KIT', 'sandra');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_staff`
--
-- Error reading structure for table db_fa1.tbl_staff: #1932 - Table 'db_fa1.tbl_staff' doesn't exist in engine
-- Error reading data for table db_fa1.tbl_staff: #1064 - You have an error in your SQL syntax; check the manual that corresponds to your MariaDB server version for the right syntax to use near 'FROM `db_fa1`.`tbl_staff`' at line 1

-- --------------------------------------------------------

--
-- Table structure for table `tbl_staff1`
--

CREATE TABLE `tbl_staff1` (
  `id` int(11) NOT NULL,
  `v_name` varchar(50) NOT NULL,
  `v_service` varchar(50) NOT NULL,
  `v_duration` varchar(50) NOT NULL,
  `v_created_on` date NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tbl_staff1`
--

INSERT INTO `tbl_staff1` (`id`, `v_name`, `v_service`, `v_duration`, `v_created_on`) VALUES
(1, 'Henry', 'Broken furniture', '2 weeks', '2021-03-25'),
(2, 'Will Smith', 'Gardening', '6 Months', '2021-03-25'),
(3, 'Henry salva', 'Electrical faults', '6 months', '2021-03-26');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_applicants1`
--
ALTER TABLE `tbl_applicants1`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_login1`
--
ALTER TABLE `tbl_login1`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `tbl_projects1`
--
ALTER TABLE `tbl_projects1`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_staff1`
--
ALTER TABLE `tbl_staff1`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_applicants1`
--
ALTER TABLE `tbl_applicants1`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `tbl_projects1`
--
ALTER TABLE `tbl_projects1`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `tbl_staff1`
--
ALTER TABLE `tbl_staff1`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
