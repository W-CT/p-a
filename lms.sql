/*
 Navicat Premium Data Transfer

 Source Server         : 123
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : localhost:3308
 Source Schema         : lms

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 20/12/2022 13:54:53
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `author` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `press` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `isbn` bigint NOT NULL,
  `dir_id` bigint NOT NULL,
  `numbers` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES (1, '斗罗大陆', '唐家三少', '某某出版社', 9888888888, 5, 6);
INSERT INTO `book` VALUES (2, '斗破苍穹', '天蚕土豆', '某某出版社', 9888888889, 1, 12);
INSERT INTO `book` VALUES (3, '七剑下天山', '梁羽生', '某某出版社', 9888888800, 5, 1);
INSERT INTO `book` VALUES (5, '唐砖', '孑与2', '某某出版社', 9888888899, 4, 7);
INSERT INTO `book` VALUES (6, '圣墟', '辰东', '某某出版社', 9888888809, 1, 50);
INSERT INTO `book` VALUES (7, '鹿鼎记', '金庸', '某某出版社', 9888888800, 5, 10);

-- ----------------------------
-- Table structure for directory
-- ----------------------------
DROP TABLE IF EXISTS `directory`;
CREATE TABLE `directory`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `describes` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of directory
-- ----------------------------
INSERT INTO `directory` VALUES (1, '玄幻小说', '玄幻小说是一种文学类型，思想内容往往幽深玄妙、奇伟瑰丽。');
INSERT INTO `directory` VALUES (2, '都市小说', '都市小说大多是指在都市发生的小说。');
INSERT INTO `directory` VALUES (3, '言情小说', '言情小说是中国旧体小说的一种，又称才子佳人小说。');
INSERT INTO `directory` VALUES (4, '穿越小说', '穿越小说，是穿越时空小说的简称，网络小说最热门题材的一种。');
INSERT INTO `directory` VALUES (5, '武侠小说', '武侠小说');

-- ----------------------------
-- Table structure for timeline
-- ----------------------------
DROP TABLE IF EXISTS `timeline`;
CREATE TABLE `timeline`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `timestamp` datetime NOT NULL,
  `color` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 32 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of timeline
-- ----------------------------
INSERT INTO `timeline` VALUES (14, '2021-06-27 14:03:07', '#00BFFF', NULL, '更新了:adsadsad');
INSERT INTO `timeline` VALUES (13, '2021-06-27 14:02:57', '#54FF9F', NULL, '添加了:321');
INSERT INTO `timeline` VALUES (15, '2021-06-27 14:03:13', '#FF3030', NULL, '删除了:adsadsad');
INSERT INTO `timeline` VALUES (16, '2021-06-27 14:03:39', '#54FF9F', NULL, '添加了:wqeq');
INSERT INTO `timeline` VALUES (17, '2021-06-27 14:03:49', '#00BFFF', NULL, '更新了:3213213');
INSERT INTO `timeline` VALUES (18, '2021-06-27 14:03:54', '#FF3030', NULL, '删除了:3213213');
INSERT INTO `timeline` VALUES (19, '2022-12-02 09:03:39', '#00BFFF', NULL, '更新了:斗罗大陆');
INSERT INTO `timeline` VALUES (20, '2022-12-09 17:27:42', '#00BFFF', NULL, '更新了:斗罗大陆');
INSERT INTO `timeline` VALUES (21, '2022-12-12 15:57:14', '#54FF9F', NULL, '添加了:2');
INSERT INTO `timeline` VALUES (22, '2022-12-12 15:57:25', '#00BFFF', NULL, '更新了:2');
INSERT INTO `timeline` VALUES (23, '2022-12-12 15:57:43', '#00BFFF', NULL, '更新了:2');
INSERT INTO `timeline` VALUES (24, '2022-12-12 15:57:52', '#00BFFF', NULL, '更新了:3');
INSERT INTO `timeline` VALUES (25, '2022-12-12 16:01:37', '#FF3030', NULL, '删除了:3');
INSERT INTO `timeline` VALUES (26, '2022-12-12 16:05:10', '#54FF9F', NULL, '添加了:2');
INSERT INTO `timeline` VALUES (27, '2022-12-12 16:05:22', '#00BFFF', NULL, '更新了:3333333');
INSERT INTO `timeline` VALUES (28, '2022-12-12 16:05:28', '#FF3030', NULL, '删除了:3333333');
INSERT INTO `timeline` VALUES (29, '2022-12-12 16:06:36', '#54FF9F', NULL, '添加了:2');
INSERT INTO `timeline` VALUES (30, '2022-12-12 16:06:44', '#00BFFF', NULL, '更新了:2');
INSERT INTO `timeline` VALUES (31, '2022-12-12 16:06:49', '#FF3030', NULL, '删除了:2');
INSERT INTO `timeline` VALUES (32, '2022-12-20 11:18:07', '#54FF9F', NULL, '添加了:22121');
INSERT INTO `timeline` VALUES (33, '2022-12-20 11:18:19', '#00BFFF', NULL, '更新了:22121');
INSERT INTO `timeline` VALUES (34, '2022-12-20 11:18:26', '#FF3030', NULL, '删除了:22121');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role` tinyint(1) NOT NULL COMMENT '“1”等于管理员，“0”等于用户',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '123456789', 1);
INSERT INTO `user` VALUES (2, 'user12', '123456', 0);
INSERT INTO `user` VALUES (4, 'www', '111111', 0);

SET FOREIGN_KEY_CHECKS = 1;
