-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.4.18-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             11.2.0.6213
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for jmusic
CREATE DATABASE IF NOT EXISTS `jmusic` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `jmusic`;

-- Dumping structure for table jmusic.album
CREATE TABLE IF NOT EXISTS `album` (
  `id` varchar(100) NOT NULL,
  `clientID` int(11) NOT NULL,
  `artiste` varchar(50) NOT NULL,
  `duree` int(11) NOT NULL DEFAULT 0,
  `dateDeSortie` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK__client` (`clientID`),
  CONSTRAINT `FK__client` FOREIGN KEY (`clientID`) REFERENCES `client` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.

-- Dumping structure for table jmusic.chanson
CREATE TABLE IF NOT EXISTS `chanson` (
  `id` varchar(50) NOT NULL,
  `titre` varchar(50) NOT NULL,
  `duree` varchar(50) NOT NULL,
  `artiste` varchar(50) NOT NULL,
  `genre` varchar(50) DEFAULT NULL,
  `type` varchar(50) NOT NULL,
  `albumID` varchar(100) DEFAULT NULL,
  `playlistID` varchar(100) DEFAULT NULL,
  `clientID` int(11) NOT NULL,
  `chemin` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_chanson_client` (`clientID`),
  KEY `FK_chanson_playlist` (`playlistID`),
  KEY `FK_chanson_album` (`albumID`),
  CONSTRAINT `FK_chanson_album` FOREIGN KEY (`albumID`) REFERENCES `album` (`id`),
  CONSTRAINT `FK_chanson_client` FOREIGN KEY (`clientID`) REFERENCES `client` (`id`),
  CONSTRAINT `FK_chanson_playlist` FOREIGN KEY (`playlistID`) REFERENCES `playlist` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.

-- Dumping structure for table jmusic.client
CREATE TABLE IF NOT EXISTS `client` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(50) DEFAULT NULL,
  `lastname` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `dateCreation` date NOT NULL,
  `password` varchar(50) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `gender` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

-- Data exporting was unselected.

-- Dumping structure for table jmusic.livreaudio
CREATE TABLE IF NOT EXISTS `livreaudio` (
  `id` varchar(50) NOT NULL,
  `titre` varchar(50) NOT NULL,
  `duree` varchar(50) NOT NULL,
  `auteur` varchar(50) NOT NULL,
  `langue` varchar(50) NOT NULL,
  `categorie` varchar(50) NOT NULL,
  `type` varchar(50) NOT NULL,
  `playlistID` varchar(100) DEFAULT NULL,
  `clientID` int(11) NOT NULL,
  `chemin` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_livreaudio_client` (`clientID`),
  KEY `FK_livreaudio_playlist` (`playlistID`),
  CONSTRAINT `FK_livreaudio_client` FOREIGN KEY (`clientID`) REFERENCES `client` (`id`),
  CONSTRAINT `FK_livreaudio_playlist` FOREIGN KEY (`playlistID`) REFERENCES `playlist` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Data exporting was unselected.

-- Dumping structure for table jmusic.playlist
CREATE TABLE IF NOT EXISTS `playlist` (
  `id` varchar(100) NOT NULL DEFAULT 'AUTO_INCREMENT',
  `nom` varchar(50) NOT NULL,
  `clientID` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_playlist_client` (`clientID`),
  CONSTRAINT `FK_playlist_client` FOREIGN KEY (`clientID`) REFERENCES `client` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
