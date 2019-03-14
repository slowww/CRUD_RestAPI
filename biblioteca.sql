-- phpMyAdmin SQL Dump
-- version 4.8.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Mar 11, 2019 alle 22:15
-- Versione del server: 10.1.34-MariaDB
-- Versione PHP: 7.2.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `biblioteca`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `libri`
--

CREATE TABLE `libri` (
  `isbn` varchar(10) NOT NULL,
  `titolo` varchar(30) DEFAULT NULL,
  `autore` varchar(30) DEFAULT NULL,
  `casaed` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `libri`
--

INSERT INTO `libri` (`isbn`, `titolo`, `autore`, `casaed`) VALUES
('111111111', 'I promessi sposi', 'Alessandro Manzoni', 'Laterza'),
('121212121', 'aaa', 'bbb', 'ccc'),
('222222222', 'La divina commedia', 'Dante Alighieri', 'Laterza'),
('333333333', 'Pulp', 'Charles Bukovskij', 'Feltrinelli'),
('444444444', 'It', 'Stephen King', 'Giunti'),
('555555555', '1984', 'George Orwell', 'Feltrinelli'),
('666666666', 'Pinocchio', 'Giorgio Collodi', 'Einaudi'),
('666999666', 'aeiou', 'fgfgfg', 'kkkkkkkkkkk'),
('777777777', 'Il capitale', 'Karl Marx', 'Feltrinelli'),
('888888888', 'Rosso Malpelo', 'Giovanni Verga', 'Giunti'),
('999999999', 'Java 8', 'Michele Mastroianni', 'Einaudi');

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `libri`
--
ALTER TABLE `libri`
  ADD PRIMARY KEY (`isbn`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
