-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        11.1.2-MariaDB - mariadb.org binary distribution
-- 서버 OS:                        Win64
-- HeidiSQL 버전:                  12.3.0.6589
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- monitor 데이터베이스 구조 내보내기
CREATE DATABASE IF NOT EXISTS `monitor` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `monitor`;

-- 테이블 monitor.carts 구조 내보내기
CREATE TABLE IF NOT EXISTS `carts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `member_id` int(11) NOT NULL,
  `item_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 monitor.carts:~1 rows (대략적) 내보내기
DELETE FROM `carts`;
INSERT INTO `carts` (`id`, `member_id`, `item_id`) VALUES
	(6, 1, 5);

-- 테이블 monitor.items 구조 내보내기
CREATE TABLE IF NOT EXISTS `items` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `img_path` varchar(100) DEFAULT NULL,
  `price` int(11) NOT NULL DEFAULT 0,
  `discount_per` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 monitor.items:~3 rows (대략적) 내보내기
DELETE FROM `items`;
INSERT INTO `items` (`id`, `name`, `img_path`, `price`, `discount_per`) VALUES
	(4, 'starry', '/img/starry-8450454_1280.jpg', 100000, 3),
	(5, 'watercolour', '/img/watercolour-2168729_1280.jpg', 250000, 5),
	(6, 'vincent van gogh', '/img/vincent-van-gogh-85799_1280.jpg', 420000, 30);

-- 테이블 monitor.members 구조 내보내기
CREATE TABLE IF NOT EXISTS `members` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 monitor.members:~1 rows (대략적) 내보내기
DELETE FROM `members`;
INSERT INTO `members` (`id`, `email`, `password`) VALUES
	(1, 'test@goo.com', 'test1234');

-- 테이블 monitor.orders 구조 내보내기
CREATE TABLE IF NOT EXISTS `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `member_id` int(11) NOT NULL DEFAULT 0,
  `name` varchar(50) NOT NULL DEFAULT '0',
  `address` varchar(500) NOT NULL DEFAULT '0',
  `payment` varchar(10) NOT NULL DEFAULT '0',
  `card_number` varchar(16) DEFAULT '0',
  `items` varchar(500) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 monitor.orders:~8 rows (대략적) 내보내기
DELETE FROM `orders`;
INSERT INTO `orders` (`id`, `member_id`, `name`, `address`, `payment`, `card_number`, `items`) VALUES
	(1, 1, '김철수', '서울시', 'card', '13552344', ''),
	(3, 1, '김영희', '부산시', 'bank', '44221144', ''),
	(4, 1, '영수', '창원시', 'card', '44326664', '[{"id":5,"name":"watercolour","imgPath":"/img/watercolour-2168729_1280.jpg","price":250000,"discountPer":5},{"id":6,"name":"vincent van gogh","imgPath":"/img/vincent-van-gogh-85799_1280.jpg","price":420000,"discountPer":30}]'),
	(5, 1, '김철수', '서울시', 'card', '13552344', '[{"id":5,"name":"watercolour","imgPath":"/img/watercolour-2168729_1280.jpg","price":250000,"discountPer":5},{"id":6,"name":"vincent van gogh","imgPath":"/img/vincent-van-gogh-85799_1280.jpg","price":420000,"discountPer":30}]'),
	(6, 1, '해피', '부산시', 'bank', '', '[{"id":5,"name":"watercolour","imgPath":"/img/watercolour-2168729_1280.jpg","price":250000,"discountPer":5},{"id":6,"name":"vincent van gogh","imgPath":"/img/vincent-van-gogh-85799_1280.jpg","price":420000,"discountPer":30}]'),
	(7, 1, '프린터', '목동', 'bank', '', '[{"id":5,"name":"watercolour","imgPath":"/img/watercolour-2168729_1280.jpg","price":250000,"discountPer":5}]'),
	(8, 1, '', '', '', '', '[{"id":5,"name":"watercolour","imgPath":"/img/watercolour-2168729_1280.jpg","price":250000,"discountPer":5}]'),
	(9, 1, '프린팅', '프린터', 'card', '13552344', '[{"id":5,"name":"watercolour","imgPath":"/img/watercolour-2168729_1280.jpg","price":250000,"discountPer":5}]');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
