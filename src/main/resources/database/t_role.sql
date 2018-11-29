/*
 Navicat Premium Data Transfer

 Source Server         : MySQL57
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : sblearning

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 29/11/2018 09:36:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `description` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
