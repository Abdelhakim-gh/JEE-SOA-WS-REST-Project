-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 01, 2024 at 04:41 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `jeegestionprojet`
--

-- --------------------------------------------------------

--
-- Table structure for table `classes`
--

CREATE TABLE `classes` (
  `id_classe` int(11) NOT NULL,
  `label` varchar(255) DEFAULT NULL,
  `section_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `classes`
--

INSERT INTO `classes` (`id_classe`, `label`, `section_id`) VALUES
(1, 'n/d', 1),
(6, 'S2', 8),
(11, 'P1', 11),
(12, 'S4', 8),
(13, 'S3', 9),
(14, 'S1', 9),
(15, 'S10', 9),
(17, 'S2', 12);

-- --------------------------------------------------------

--
-- Table structure for table `enseigner_matiere`
--

CREATE TABLE `enseigner_matiere` (
  `id` int(11) NOT NULL,
  `matiere_id` int(11) DEFAULT NULL,
  `prof_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `enseigner_matiere`
--

INSERT INTO `enseigner_matiere` (`id`, `matiere_id`, `prof_id`) VALUES
(18, 2, 4),
(20, 3, 4),
(27, 2, 9),
(33, 2, 10),
(35, 2, 11),
(36, 3, 11),
(40, 15, 12);

-- --------------------------------------------------------

--
-- Table structure for table `etudiants`
--

CREATE TABLE `etudiants` (
  `id_etud` int(11) NOT NULL,
  `cne` varchar(255) NOT NULL,
  `prix` decimal(10,2) DEFAULT NULL,
  `payement` tinyint(1) DEFAULT 0,
  `classe_id` int(11) DEFAULT NULL,
  `section_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `etudiants`
--

INSERT INTO `etudiants` (`id_etud`, `cne`, `prix`, `payement`, `classe_id`, `section_id`, `user_id`) VALUES
(27, 'Id reprehenderit co', 275.00, 1, 14, 9, 60),
(28, 'Ducimus dolor maior', 100.00, 0, 17, 12, 61),
(37, 'Magni qui fugit pra', 100.00, 0, 11, 11, 70);

-- --------------------------------------------------------

--
-- Table structure for table `matieres`
--

CREATE TABLE `matieres` (
  `id_matiere` int(11) NOT NULL,
  `label` varchar(255) NOT NULL,
  `section_id` int(11) DEFAULT NULL,
  `prix` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `matieres`
--

INSERT INTO `matieres` (`id_matiere`, `label`, `section_id`, `prix`) VALUES
(1, 'n/d', 1, 0.00),
(2, 'Math', 8, 150.00),
(3, 'Physique', 8, 125.00),
(12, 'Math', 9, 150.00),
(13, 'Physique', 9, 125.00),
(15, 'SVT', 11, 100.00);

-- --------------------------------------------------------

--
-- Table structure for table `matiere_inscrit`
--

CREATE TABLE `matiere_inscrit` (
  `id` int(11) NOT NULL,
  `matiere_id` int(11) DEFAULT NULL,
  `etud_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `matiere_inscrit`
--

INSERT INTO `matiere_inscrit` (`id`, `matiere_id`, `etud_id`) VALUES
(45, 15, 37),
(51, 12, 27),
(52, 13, 27);

-- --------------------------------------------------------

--
-- Table structure for table `profs`
--

CREATE TABLE `profs` (
  `id_prof` int(11) NOT NULL,
  `salaire` decimal(10,2) DEFAULT NULL,
  `payement` tinyint(1) DEFAULT 0,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `profs`
--

INSERT INTO `profs` (`id_prof`, `salaire`, `payement`, `user_id`) VALUES
(4, 2000.00, 1, 24),
(9, 150.00, 0, 41),
(10, 1000.00, 0, 45),
(11, 1500.00, 0, 47),
(12, 500.00, 1, 48);

-- --------------------------------------------------------

--
-- Table structure for table `sections`
--

CREATE TABLE `sections` (
  `id_section` int(11) NOT NULL,
  `niveau` varchar(255) NOT NULL,
  `filiere` varchar(255) NOT NULL,
  `label` varchar(255) GENERATED ALWAYS AS (concat(`niveau`,'/',`filiere`)) STORED
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `sections`
--

INSERT INTO `sections` (`id_section`, `niveau`, `filiere`) VALUES
(1, 'n/d', 'n/d'),
(8, '1-BAC', 'SM'),
(9, '2-BAC', 'SM'),
(11, '2-BAC', 'PC'),
(12, '2-BAC', 'SVT'),
(18, '1-BAC', 'EX');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id_user` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `tel` varchar(20) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `role` enum('admin','prof','etudiant') NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id_user`, `nom`, `prenom`, `email`, `tel`, `password`, `role`, `created_at`, `updated_at`) VALUES
(1, 'EL Ghayoubi', 'Abdelhakim', 'abdo@gmail.com', '060908088', 'abdo', 'admin', '2023-12-01 21:02:52', '2023-12-17 17:18:57'),
(24, 'Numquam et aperiam d', 'Nesciunt expedita e', 'pariwo@mailinator.com', '+1 (771) 415-2044', 'Pa$$w0rd!', 'prof', '2023-12-04 18:13:50', '2023-12-17 17:12:11'),
(41, 'Voluptate dignissimo', 'Et incididunt quis o', 'gikar@mailinator.com', '+1 (291) 569-2856', 'Pa$$w0rd!', 'prof', '2023-12-05 20:01:40', '2023-12-05 20:01:40'),
(45, 'Quidem vitae amet a', 'Deserunt aperiam por', 'dyvykip@mailinator.com', '+1 (694) 697-5387', 'Pa$$w0rd!', 'prof', '2023-12-06 07:45:44', '2023-12-06 07:45:44'),
(47, 'Ut officia dolorem a', 'Ullam odio iusto sin', 'qezulige@mailinator.com', '+1 (363) 612-6844', 'Pa$$w0rd!', 'prof', '2023-12-07 17:36:08', '2023-12-07 17:36:08'),
(48, 'Laboriosam repellen', 'Quidem cillum veniam', 'posebice@mailinator.com', '+1 (972) 173-5376', 'Pa$$w0rd!', 'prof', '2023-12-08 10:06:09', '2023-12-08 10:06:09'),
(60, 'Quis et et exercitat', 'Sequi aspernatur vel', 'tiriximi@mailinator.com', '+1 (851) 175-4509', 'Pa$$w0rd!', 'etudiant', '2023-12-17 19:06:28', '2023-12-17 19:06:28'),
(61, 'Et saepe ducimus am', 'Sunt optio eos vol', 'cebac@mailinator.com', '+1 (626) 903-4477', 'Pa$$w0rd!', 'etudiant', '2023-12-17 19:07:01', '2023-12-17 19:07:01'),
(70, 'Molestiae facere nos', 'Aspernatur assumenda', 'soxana@mailinator.com', '+1 (505) 511-6605', 'Pa$$w0rd!', 'etudiant', '2024-02-28 14:59:31', '2024-02-28 14:59:31'),
(72, 'Aut quia ipsa porro', 'Maxime omnis harum q', 'cycuzo@mailinator.com', '+1 (731) 924-8102', 'Pa$$w0rd!', 'etudiant', '2024-04-28 12:21:36', '2024-04-28 12:21:36'),
(73, 'Libero totam velit r', 'Ut distinctio Elit', 'dybe@mailinator.com', '+1 (281) 878-1923', 'Pa$$w0rd!', 'etudiant', '2024-04-28 12:22:35', '2024-04-28 12:22:35'),
(78, 'John', 'Doe', 'john.doe.modify.email@example.com', '123456789', 'password123', 'admin', '2024-04-29 10:57:01', '2024-04-29 11:19:33');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `classes`
--
ALTER TABLE `classes`
  ADD PRIMARY KEY (`id_classe`),
  ADD KEY `section_id` (`section_id`);

--
-- Indexes for table `enseigner_matiere`
--
ALTER TABLE `enseigner_matiere`
  ADD PRIMARY KEY (`id`),
  ADD KEY `matiere_id` (`matiere_id`),
  ADD KEY `prof_id` (`prof_id`);

--
-- Indexes for table `etudiants`
--
ALTER TABLE `etudiants`
  ADD PRIMARY KEY (`id_etud`),
  ADD KEY `classe_id` (`classe_id`),
  ADD KEY `section_id` (`section_id`),
  ADD KEY `fk_etudiant_user_id` (`user_id`);

--
-- Indexes for table `matieres`
--
ALTER TABLE `matieres`
  ADD PRIMARY KEY (`id_matiere`),
  ADD KEY `section_id` (`section_id`);

--
-- Indexes for table `matiere_inscrit`
--
ALTER TABLE `matiere_inscrit`
  ADD PRIMARY KEY (`id`),
  ADD KEY `matiere_id` (`matiere_id`),
  ADD KEY `etud_id` (`etud_id`);

--
-- Indexes for table `profs`
--
ALTER TABLE `profs`
  ADD PRIMARY KEY (`id_prof`),
  ADD KEY `fk_user_id` (`user_id`);

--
-- Indexes for table `sections`
--
ALTER TABLE `sections`
  ADD PRIMARY KEY (`id_section`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id_user`),
  ADD UNIQUE KEY `unique_email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `classes`
--
ALTER TABLE `classes`
  MODIFY `id_classe` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT for table `enseigner_matiere`
--
ALTER TABLE `enseigner_matiere`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=46;

--
-- AUTO_INCREMENT for table `etudiants`
--
ALTER TABLE `etudiants`
  MODIFY `id_etud` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=46;

--
-- AUTO_INCREMENT for table `matieres`
--
ALTER TABLE `matieres`
  MODIFY `id_matiere` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `matiere_inscrit`
--
ALTER TABLE `matiere_inscrit`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=60;

--
-- AUTO_INCREMENT for table `profs`
--
ALTER TABLE `profs`
  MODIFY `id_prof` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `sections`
--
ALTER TABLE `sections`
  MODIFY `id_section` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=85;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `classes`
--
ALTER TABLE `classes`
  ADD CONSTRAINT `classes_ibfk_1` FOREIGN KEY (`section_id`) REFERENCES `sections` (`id_section`);

--
-- Constraints for table `enseigner_matiere`
--
ALTER TABLE `enseigner_matiere`
  ADD CONSTRAINT `enseigner_matiere_ibfk_1` FOREIGN KEY (`matiere_id`) REFERENCES `matieres` (`id_matiere`),
  ADD CONSTRAINT `enseigner_matiere_ibfk_2` FOREIGN KEY (`prof_id`) REFERENCES `profs` (`id_prof`);

--
-- Constraints for table `etudiants`
--
ALTER TABLE `etudiants`
  ADD CONSTRAINT `etudiants_ibfk_1` FOREIGN KEY (`classe_id`) REFERENCES `classes` (`id_classe`),
  ADD CONSTRAINT `etudiants_ibfk_2` FOREIGN KEY (`section_id`) REFERENCES `sections` (`id_section`),
  ADD CONSTRAINT `fk_etudiant_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id_user`);

--
-- Constraints for table `matieres`
--
ALTER TABLE `matieres`
  ADD CONSTRAINT `matieres_ibfk_1` FOREIGN KEY (`section_id`) REFERENCES `sections` (`id_section`);

--
-- Constraints for table `matiere_inscrit`
--
ALTER TABLE `matiere_inscrit`
  ADD CONSTRAINT `matiere_inscrit_ibfk_1` FOREIGN KEY (`matiere_id`) REFERENCES `matieres` (`id_matiere`),
  ADD CONSTRAINT `matiere_inscrit_ibfk_2` FOREIGN KEY (`etud_id`) REFERENCES `etudiants` (`id_etud`);

--
-- Constraints for table `profs`
--
ALTER TABLE `profs`
  ADD CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id_user`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
