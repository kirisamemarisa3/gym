/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80029 (8.0.29)
 Source Host           : localhost:3306
 Source Schema         : db_gym

 Target Server Type    : MySQL
 Target Server Version : 80029 (8.0.29)
 File Encoding         : 65001

 Date: 29/07/2024 09:28:06
*/
DROP DATABASE IF EXISTS db_gym ;
CREATE DATABASE db_gym CHARACTER SET utf8;
use db_gym ;
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `adminNo` int NOT NULL AUTO_INCREMENT,
  `adminAccount` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `adminPassword` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`adminNo`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of admin
-- ----------------------------
BEGIN;
INSERT INTO `admin` (`adminNo`, `adminAccount`, `adminPassword`, `token`) VALUES (1, 'admin', 'admin', '');
COMMIT;

-- ----------------------------
-- Table structure for checkin
-- ----------------------------
DROP TABLE IF EXISTS `checkin`;
CREATE TABLE `checkin` (
  `checkNo` int NOT NULL AUTO_INCREMENT,
  `memberNo` int DEFAULT NULL,
  `checkDate` datetime DEFAULT NULL,
  PRIMARY KEY (`checkNo`) USING BTREE,
  KEY `memberNo` (`memberNo`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of checkin
-- ----------------------------
BEGIN;
INSERT INTO `checkin` (`checkNo`, `memberNo`, `checkDate`) VALUES (1, 1, '2023-06-20 00:00:00');
INSERT INTO `checkin` (`checkNo`, `memberNo`, `checkDate`) VALUES (2, NULL, '2023-06-29 00:00:00');
COMMIT;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `courseNo` int NOT NULL AUTO_INCREMENT,
  `courseName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `courseTime` varchar(255) DEFAULT NULL,
  `courseDuration` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `coursePrice` decimal(10,2) DEFAULT NULL,
  `courseDesc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `courseIntegral` int DEFAULT NULL,
  `employeeNo` int DEFAULT NULL COMMENT '教练员工编号',
  `managerNo` int DEFAULT NULL COMMENT '经理编号',
  PRIMARY KEY (`courseNo`) USING BTREE,
  KEY `employeeNo` (`employeeNo`) USING BTREE,
  KEY `managerNo` (`managerNo`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of course
-- ----------------------------
BEGIN;
INSERT INTO `course` (`courseNo`, `courseName`, `courseTime`, `courseDuration`, `coursePrice`, `courseDesc`, `courseIntegral`, `employeeNo`, `managerNo`) VALUES (3, '瑜伽', '2024-07-28 05:21:11', '10', 1000.00, '瑜伽课', 200, 1, 2);
INSERT INTO `course` (`courseNo`, `courseName`, `courseTime`, `courseDuration`, `coursePrice`, `courseDesc`, `courseIntegral`, `employeeNo`, `managerNo`) VALUES (4, '哑铃训练', '2024-07-29 16:00:00', '20', 5000.00, '哑铃', 500, 1, 2);
INSERT INTO `course` (`courseNo`, `courseName`, `courseTime`, `courseDuration`, `coursePrice`, `courseDesc`, `courseIntegral`, `employeeNo`, `managerNo`) VALUES (5, '游泳', '2024-07-23 16:00:00', '1000', 20000.00, '游泳', 200, 1, 2);
INSERT INTO `course` (`courseNo`, `courseName`, `courseTime`, `courseDuration`, `coursePrice`, `courseDesc`, `courseIntegral`, `employeeNo`, `managerNo`) VALUES (6, '跑步训练', '2024-07-30 16:00:00', '900', 2000.00, '跑步', 2, 1, 2);
COMMIT;

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `employeeNo` int NOT NULL AUTO_INCREMENT,
  `employeeAge` int DEFAULT NULL,
  `employeeName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `employeeGender` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `employeePhone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `employeeTime` datetime DEFAULT NULL,
  `employeeMessage` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin,
  `employeeJob` int DEFAULT NULL COMMENT '1 教练 2前台 3保洁 4经理',
  PRIMARY KEY (`employeeNo`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of employee
-- ----------------------------
BEGIN;
INSERT INTO `employee` (`employeeNo`, `employeeAge`, `employeeName`, `employeeGender`, `employeePhone`, `employeeTime`, `employeeMessage`, `employeeJob`) VALUES (1, 35, '橙灰', 'M', '15798645769', '2023-06-20 12:18:29', '冠军教练', 1);
INSERT INTO `employee` (`employeeNo`, `employeeAge`, `employeeName`, `employeeGender`, `employeePhone`, `employeeTime`, `employeeMessage`, `employeeJob`) VALUES (2, 40, '小黄', 'M', '13568974568', '2023-06-29 09:38:48', '项目经理', 4);
INSERT INTO `employee` (`employeeNo`, `employeeAge`, `employeeName`, `employeeGender`, `employeePhone`, `employeeTime`, `employeeMessage`, `employeeJob`) VALUES (3, 24, '小胡', 'F', '13598756985', '2023-06-29 10:09:15', NULL, 2);
COMMIT;

-- ----------------------------
-- Table structure for equipment
-- ----------------------------
DROP TABLE IF EXISTS `equipment`;
CREATE TABLE `equipment` (
  `equipmentNo` int NOT NULL AUTO_INCREMENT,
  `equipmentName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `equipmentLocation` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `equipmentState` int DEFAULT NULL,
  `equipmentMessage` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `employeeJob` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`equipmentNo`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of equipment
-- ----------------------------
BEGIN;
INSERT INTO `equipment` (`equipmentNo`, `equipmentName`, `equipmentLocation`, `equipmentState`, `equipmentMessage`, `employeeJob`) VALUES (1, '跑步机', '大厅', 1, '无', NULL);
INSERT INTO `equipment` (`equipmentNo`, `equipmentName`, `equipmentLocation`, `equipmentState`, `equipmentMessage`, `employeeJob`) VALUES (2, '瑜伽垫', '大厅', 1, '无', NULL);
COMMIT;

-- ----------------------------
-- Table structure for manager
-- ----------------------------
DROP TABLE IF EXISTS `manager`;
CREATE TABLE `manager` (
  `managerNo` int NOT NULL AUTO_INCREMENT,
  `employeeNo` int DEFAULT NULL,
  PRIMARY KEY (`managerNo`) USING BTREE,
  KEY `employeeNo` (`employeeNo`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of manager
-- ----------------------------
BEGIN;
INSERT INTO `manager` (`managerNo`, `employeeNo`) VALUES (1, 2);
COMMIT;

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
  `memberNo` int NOT NULL AUTO_INCREMENT,
  `memberUsername` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `memberPassword` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `memberName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `memberAge` int DEFAULT NULL,
  `memberGender` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `memberPhone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `cardTime` datetime DEFAULT NULL,
  `memberHeight` double DEFAULT NULL,
  `memberWeight` double DEFAULT NULL,
  `cardClass` int DEFAULT NULL,
  `cardNextClass` int DEFAULT NULL,
  `memberIntegral` int DEFAULT NULL,
  `memberChange` int DEFAULT NULL,
  `personalizedSignature` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `memberPower` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`memberNo`) USING BTREE,
  UNIQUE KEY `memberPhone` (`memberPhone`) USING BTREE COMMENT '手机号唯一'
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of member
-- ----------------------------
BEGIN;
INSERT INTO `member` (`memberNo`, `memberUsername`, `memberPassword`, `memberName`, `memberAge`, `memberGender`, `memberPhone`, `cardTime`, `memberHeight`, `memberWeight`, `cardClass`, `cardNextClass`, `memberIntegral`, `memberChange`, `personalizedSignature`, `memberPower`, `token`) VALUES (1, 'admin', 'admin', 'admin', 18, 'F', '18470558967', '2023-06-20 12:12:01', 175, 60, 200, 150, 47, 39, 'hhh', '1', NULL);
INSERT INTO `member` (`memberNo`, `memberUsername`, `memberPassword`, `memberName`, `memberAge`, `memberGender`, `memberPhone`, `cardTime`, `memberHeight`, `memberWeight`, `cardClass`, `cardNextClass`, `memberIntegral`, `memberChange`, `personalizedSignature`, `memberPower`, `token`) VALUES (2, '0031023684', '123456', '北京小熊', 20, 'M', '18470668957', '2023-06-20 12:16:16', 180, 70, 50, 50, 65, 1360, '1', '1', NULL);
COMMIT;

-- ----------------------------
-- Table structure for recharge
-- ----------------------------
DROP TABLE IF EXISTS `recharge`;
CREATE TABLE `recharge` (
  `rechargeNo` int NOT NULL AUTO_INCREMENT,
  `rechargeDate` datetime DEFAULT NULL,
  `rechargeMethod` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `rechargeStatus` int DEFAULT NULL,
  `rechargeMoney` double DEFAULT NULL,
  `memberNo` int DEFAULT NULL,
  PRIMARY KEY (`rechargeNo`) USING BTREE,
  KEY `memberNo` (`memberNo`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of recharge
-- ----------------------------
BEGIN;
INSERT INTO `recharge` (`rechargeNo`, `rechargeDate`, `rechargeMethod`, `rechargeStatus`, `rechargeMoney`, `memberNo`) VALUES (31, '2024-07-28 00:00:00', '在线充值', 1, 200, 2);
INSERT INTO `recharge` (`rechargeNo`, `rechargeDate`, `rechargeMethod`, `rechargeStatus`, `rechargeMoney`, `memberNo`) VALUES (32, '2024-07-28 00:00:00', '在线充值', 1, 200, 2);
INSERT INTO `recharge` (`rechargeNo`, `rechargeDate`, `rechargeMethod`, `rechargeStatus`, `rechargeMoney`, `memberNo`) VALUES (33, '2024-07-28 00:00:00', '在线充值', 1, 100, 2);
INSERT INTO `recharge` (`rechargeNo`, `rechargeDate`, `rechargeMethod`, `rechargeStatus`, `rechargeMoney`, `memberNo`) VALUES (34, '2024-07-28 00:00:00', '在线充值', 1, 200, 2);
INSERT INTO `recharge` (`rechargeNo`, `rechargeDate`, `rechargeMethod`, `rechargeStatus`, `rechargeMoney`, `memberNo`) VALUES (35, '2024-07-28 00:00:00', '在线充值', 1, 10, 2);
INSERT INTO `recharge` (`rechargeNo`, `rechargeDate`, `rechargeMethod`, `rechargeStatus`, `rechargeMoney`, `memberNo`) VALUES (36, '2024-07-28 00:00:00', '在线充值', 1, 200, 2);
INSERT INTO `recharge` (`rechargeNo`, `rechargeDate`, `rechargeMethod`, `rechargeStatus`, `rechargeMoney`, `memberNo`) VALUES (37, '2024-07-28 00:00:00', '在线充值', 1, 50, 2);
INSERT INTO `recharge` (`rechargeNo`, `rechargeDate`, `rechargeMethod`, `rechargeStatus`, `rechargeMoney`, `memberNo`) VALUES (38, '2024-07-28 00:00:00', '在线充值', 1, 200, 2);
INSERT INTO `recharge` (`rechargeNo`, `rechargeDate`, `rechargeMethod`, `rechargeStatus`, `rechargeMoney`, `memberNo`) VALUES (39, '2024-07-28 00:00:00', '在线充值', 1, 200, 2);
INSERT INTO `recharge` (`rechargeNo`, `rechargeDate`, `rechargeMethod`, `rechargeStatus`, `rechargeMoney`, `memberNo`) VALUES (40, '2024-07-28 00:00:00', '在线充值', 1, 200, 2);
INSERT INTO `recharge` (`rechargeNo`, `rechargeDate`, `rechargeMethod`, `rechargeStatus`, `rechargeMoney`, `memberNo`) VALUES (41, '2024-07-28 00:00:00', '在线充值', 1, 200, 2);
INSERT INTO `recharge` (`rechargeNo`, `rechargeDate`, `rechargeMethod`, `rechargeStatus`, `rechargeMoney`, `memberNo`) VALUES (42, '2024-07-28 00:00:00', '在线充值', 1, 200, 2);
INSERT INTO `recharge` (`rechargeNo`, `rechargeDate`, `rechargeMethod`, `rechargeStatus`, `rechargeMoney`, `memberNo`) VALUES (43, '2024-07-28 00:00:00', '在线充值', 1, 200, 2);
INSERT INTO `recharge` (`rechargeNo`, `rechargeDate`, `rechargeMethod`, `rechargeStatus`, `rechargeMoney`, `memberNo`) VALUES (44, '2024-07-28 00:00:00', '在线充值', 1, 200, 2);
COMMIT;

-- ----------------------------
-- Table structure for register
-- ----------------------------
DROP TABLE IF EXISTS `register`;
CREATE TABLE `register` (
  `registerNo` int NOT NULL AUTO_INCREMENT,
  `courseNo` int DEFAULT NULL,
  `memberNo` int DEFAULT NULL,
  PRIMARY KEY (`registerNo`) USING BTREE,
  KEY `courseNo` (`courseNo`) USING BTREE,
  KEY `memberNo` (`memberNo`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of register
-- ----------------------------
BEGIN;
INSERT INTO `register` (`registerNo`, `courseNo`, `memberNo`) VALUES (5, 3, 2);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
