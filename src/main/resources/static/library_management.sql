/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : library_management

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2021-10-29 18:59:30
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `address`
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
  `address_id` bigint(20) NOT NULL,
  `district` varchar(255) DEFAULT NULL,
  `near_by` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `zipcode` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`address_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO address VALUES ('3', null, null, 'madhya pradesh', '455445');
INSERT INTO address VALUES ('24', null, null, 'vihar ', '455449');
INSERT INTO address VALUES ('26', null, null, 'UP ', '455449');

-- ----------------------------
-- Table structure for `book`
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `book_id` bigint(20) NOT NULL,
  `book_name` varchar(255) DEFAULT NULL,
  `book_published_date` date DEFAULT NULL,
  `is_active` int(11) DEFAULT NULL,
  `is_available` int(11) DEFAULT NULL,
  `number_of_pages` bigint(20) DEFAULT NULL,
  `book_publisher_publisher_id` bigint(20) DEFAULT NULL,
  `subject_subject_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`book_id`),
  KEY `FKn1xmlibnuatt59s3tpcxhuayt` (`book_publisher_publisher_id`),
  KEY `FKrg3u8sa4s2any4q4df9s2tpa5` (`subject_subject_id`),
  CONSTRAINT `FKn1xmlibnuatt59s3tpcxhuayt` FOREIGN KEY (`book_publisher_publisher_id`) REFERENCES `book_publisher` (`publisher_id`),
  CONSTRAINT `FKrg3u8sa4s2any4q4df9s2tpa5` FOREIGN KEY (`subject_subject_id`) REFERENCES `subject` (`subject_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO book VALUES ('4', 'The Things of Physics', '2019-02-12', '1', '0', '100', '5', '1');
INSERT INTO book VALUES ('10', 'The Things of Physics part - 100', '2019-09-16', '1', '0', '119', '11', '1');
INSERT INTO book VALUES ('14', 'Master of physics for begginer', '2019-09-08', '1', '0', '200', '15', '1');
INSERT INTO book VALUES ('19', 'Master of mathametics', '2019-09-08', '1', '0', '200', '20', '18');
INSERT INTO book VALUES ('29', 'Master of mathametics-1', '2020-09-08', '1', '1', '200', '30', '18');
INSERT INTO book VALUES ('33', 'Master of super maths', '2020-09-08', '1', '0', '200', '34', '18');
INSERT INTO book VALUES ('37', 'Master of super physics', '2020-09-08', '1', '1', '200', '38', '1');
INSERT INTO book VALUES ('41', 'Master of super physics-1', '2020-09-08', '1', '1', '200', '42', '1');

-- ----------------------------
-- Table structure for `book_author`
-- ----------------------------
DROP TABLE IF EXISTS `book_author`;
CREATE TABLE `book_author` (
  `book_author_id` bigint(20) NOT NULL,
  `author_name` varchar(255) DEFAULT NULL,
  `book_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`book_author_id`),
  KEY `FKhwgu59n9o80xv75plf9ggj7xn` (`book_id`),
  CONSTRAINT `FKhwgu59n9o80xv75plf9ggj7xn` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of book_author
-- ----------------------------
INSERT INTO book_author VALUES ('6', 'adnan', '4');
INSERT INTO book_author VALUES ('7', 'dheeraj', '4');
INSERT INTO book_author VALUES ('12', 'adnan abbas khan', '10');
INSERT INTO book_author VALUES ('13', 'dheeraj patil', '10');
INSERT INTO book_author VALUES ('16', 'monik patil', '14');
INSERT INTO book_author VALUES ('17', 'dheeraj patil   ', '14');
INSERT INTO book_author VALUES ('21', 'monik patil', '19');
INSERT INTO book_author VALUES ('22', 'dheeraj patil   ', '19');
INSERT INTO book_author VALUES ('31', 'monik patil', '29');
INSERT INTO book_author VALUES ('32', 'dheeraj patil   ', '29');
INSERT INTO book_author VALUES ('35', 'monik patil', '33');
INSERT INTO book_author VALUES ('36', 'dheeraj patil   ', '33');
INSERT INTO book_author VALUES ('39', 'monik patil', '37');
INSERT INTO book_author VALUES ('40', 'dheeraj patil   ', '37');
INSERT INTO book_author VALUES ('43', 'monik patil', '41');
INSERT INTO book_author VALUES ('44', 'dheeraj patil   ', '41');

-- ----------------------------
-- Table structure for `book_publisher`
-- ----------------------------
DROP TABLE IF EXISTS `book_publisher`;
CREATE TABLE `book_publisher` (
  `publisher_id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `publisher_address` varchar(255) DEFAULT NULL,
  `publisher_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`publisher_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of book_publisher
-- ----------------------------
INSERT INTO book_publisher VALUES ('5', 'adnan01@gmail.com', null, 'adnan abbas khan');
INSERT INTO book_publisher VALUES ('11', 'adnan01@gmail.com', null, 'adnan abbas khan');
INSERT INTO book_publisher VALUES ('15', 'adnan01@gmail.com', null, 'adnan abbas khan');
INSERT INTO book_publisher VALUES ('20', 'adnan01@gmail.com', null, 'adnan abbas khan');
INSERT INTO book_publisher VALUES ('30', 'adnan01@gmail.com', null, 'adnan abbas khan');
INSERT INTO book_publisher VALUES ('34', 'adnan01@gmail.com', null, 'adnan abbas khan');
INSERT INTO book_publisher VALUES ('38', 'adnan01@gmail.com', null, 'adnan abbas khan');
INSERT INTO book_publisher VALUES ('42', 'adnan01@gmail.com', null, 'adnan abbas khan');

-- ----------------------------
-- Table structure for `hibernate_sequence`
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of hibernate_sequence
-- ----------------------------
INSERT INTO hibernate_sequence VALUES ('49');

-- ----------------------------
-- Table structure for `student`
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `student_id` bigint(20) NOT NULL,
  `date_of_birth` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `is_active` int(11) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `mobile_number` varchar(255) DEFAULT NULL,
  `number_of_book_issued` int(11) DEFAULT NULL,
  `registration_date` date DEFAULT NULL,
  `total_penalty` bigint(20) DEFAULT NULL,
  `address_address_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`student_id`),
  UNIQUE KEY `UK_fe0i52si7ybu0wjedj6motiim` (`email`),
  UNIQUE KEY `UK_lg90mt25pa5kic5gx5cu3qvt0` (`mobile_number`),
  KEY `FKnipsy8d8ys1hk9s4ehyk2t7wi` (`address_address_id`),
  CONSTRAINT `FKnipsy8d8ys1hk9s4ehyk2t7wi` FOREIGN KEY (`address_address_id`) REFERENCES `address` (`address_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO student VALUES ('2', '2001-04-15', 'aka034437@gmail.com', 'adnan', 'male', '1', 'khan', '8964882358', '4', '2021-10-29', '0', '3');
INSERT INTO student VALUES ('23', '2009-04-20', 'vishal@gmail.com', 'vishal', 'male', '1', 'singh', '9964872358', '0', '2021-10-29', '40', '24');
INSERT INTO student VALUES ('25', '2009-04-20', 'dherraj@gmail.com', 'dheeraj', 'male', '1', 'patil', '94564872358', '1', '2021-10-29', '0', '26');

-- ----------------------------
-- Table structure for `student_book_issued`
-- ----------------------------
DROP TABLE IF EXISTS `student_book_issued`;
CREATE TABLE `student_book_issued` (
  `id` bigint(20) NOT NULL,
  `book_id` bigint(20) DEFAULT NULL,
  `book_issued_date` date DEFAULT NULL,
  `book_name` varchar(255) DEFAULT NULL,
  `book_return_date` date DEFAULT NULL,
  `book_subject` varchar(255) DEFAULT NULL,
  `is_issued` int(11) DEFAULT NULL,
  `is_returned` int(11) DEFAULT NULL,
  `penalty` bigint(20) DEFAULT NULL,
  `student_date_of_birth` date DEFAULT NULL,
  `student_id` bigint(20) DEFAULT NULL,
  `student_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of student_book_issued
-- ----------------------------
INSERT INTO student_book_issued VALUES ('8', '4', '2021-10-29', 'The Things of Physics', '2021-10-29', 'physics', '0', '1', '0', '2001-04-15', '2', 'adnan khan');
INSERT INTO student_book_issued VALUES ('9', '4', '2021-10-29', 'The Things of Physics', null, 'physics', '1', '0', '0', '2001-04-15', '2', 'adnan khan');
INSERT INTO student_book_issued VALUES ('27', '14', '2021-10-18', 'Master of physics for begginer', '2021-10-29', 'physics', '0', '1', '40', '2009-04-20', '23', 'vishal singh');
INSERT INTO student_book_issued VALUES ('28', '19', '2021-10-29', 'Master of mathametics', null, 'maths', '1', '0', '0', '2009-04-20', '25', 'dheeraj patil');
INSERT INTO student_book_issued VALUES ('45', '10', '2021-10-29', 'The Things of Physics part - 100', null, 'physics', '1', '0', '0', '2001-04-15', '2', 'adnan khan');
INSERT INTO student_book_issued VALUES ('46', '14', '2021-10-29', 'Master of physics for begginer', null, 'physics', '1', '0', '0', '2001-04-15', '2', 'adnan khan');
INSERT INTO student_book_issued VALUES ('47', '33', '2021-10-29', 'Master of super maths', null, 'maths', '1', '0', '0', '2001-04-15', '2', 'adnan khan');
INSERT INTO student_book_issued VALUES ('48', '37', '2021-10-29', 'Master of super physics', '2021-10-29', 'physics', '0', '1', '0', '2001-04-15', '2', 'adnan khan');

-- ----------------------------
-- Table structure for `subject`
-- ----------------------------
DROP TABLE IF EXISTS `subject`;
CREATE TABLE `subject` (
  `subject_id` bigint(20) NOT NULL,
  `subject_code` bigint(20) DEFAULT NULL,
  `subject_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`subject_id`),
  UNIQUE KEY `UK_bqn0dl9ld0wcq9na8amhhramm` (`subject_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of subject
-- ----------------------------
INSERT INTO subject VALUES ('1', '101', 'physics');
INSERT INTO subject VALUES ('18', '150', 'maths');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` bigint(20) NOT NULL,
  `date_of_birth` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `is_active` int(11) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `mobile_number` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `registration_date` date DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `address_address_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`),
  UNIQUE KEY `UK_lt2b8ocin3hdnm9q6fm4o8xnr` (`mobile_number`),
  UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`),
  KEY `FKp890qjjx13q8wwq23o1cndl9i` (`address_address_id`),
  CONSTRAINT `FKp890qjjx13q8wwq23o1cndl9i` FOREIGN KEY (`address_address_id`) REFERENCES `address` (`address_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user
-- ----------------------------
