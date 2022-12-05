/*
Navicat MySQL Data Transfer

Source Server         : Navicat for MySQL
Source Server Version : 80023
Source Host           : 127.0.0.1:3306
Source Database       : network_disk

Target Server Type    : MYSQL
Target Server Version : 80023
File Encoding         : 65001

Date: 2022-02-28 09:36:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_account`
-- ----------------------------
DROP TABLE IF EXISTS `t_account`;
CREATE TABLE `t_account` (
  `name` varchar(32) NOT NULL,
  `money` double DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_account
-- ----------------------------
INSERT INTO `t_account` VALUES ('lisi', '1000');
INSERT INTO `t_account` VALUES ('zhangsan', '1000');
INSERT INTO `t_account` VALUES ('zhuyuhan', '1000');
INSERT INTO `t_account` VALUES ('zhuyuhang', '1000');

-- ----------------------------
-- Table structure for `t_file`
-- ----------------------------
DROP TABLE IF EXISTS `t_file`;
CREATE TABLE `t_file` (
  `id` int NOT NULL,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `path` varchar(100) DEFAULT NULL,
  `length` varchar(100) DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `uploadRecord` int DEFAULT NULL,
  `downloadRecord` int DEFAULT NULL,
  `create_time` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_userid` (`user_id`),
  CONSTRAINT `FK_userid` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_file
-- ----------------------------
INSERT INTO `t_file` VALUES ('0', '周杰伦 - 爷爷泡的茶.mflac', 'D:\\Server\\upload\\zhuyuhang\\周杰伦 - 爷爷泡的茶.mflac', '29692684', '2006632120', '0', '0', '2021-06-05 18:54:05');
INSERT INTO `t_file` VALUES ('6', 'test.txt', 'D:\\Server\\upload\\zhuyuhang\\test.txt', '9', '12345678', '0', '0', '2021-06-07 17:24:59');
INSERT INTO `t_file` VALUES ('152', '大学个人成长报告.docx', 'D:\\Server\\upload\\zhuyuhang\\大学个人成长报告.docx', '17492', '2006632120', '1', '1', '2022-01-10 21:42:02');
INSERT INTO `t_file` VALUES ('214', '网易云音乐.exe', 'D:\\Server\\upload\\yandeqing\\网易云音乐.exe', '44599440', '987654321', '0', '0', '2021-06-18 16:38:31');
INSERT INTO `t_file` VALUES ('255', '2006632120_朱宇航.png', 'D:\\Server\\upload\\zhuyuhang\\2006632120_朱宇航.png', '371465', '2006632120', '0', '0', '2022-01-10 21:33:33');
INSERT INTO `t_file` VALUES ('452', '百度网盘项目.pptx', 'D:\\Server\\upload\\zhuyuhang\\百度网盘项目.pptx', '1871108', '2006632120', '0', '0', '2021-07-11 13:15:04');
INSERT INTO `t_file` VALUES ('457', '综合性实训报告（陈志雷）.doc', 'D:\\Server\\upload\\zhuyuhang\\综合性实训报告（陈志雷）.doc', '93184', '2006632120', '0', '0', '2021-07-11 13:16:47');
INSERT INTO `t_file` VALUES ('477', '综合性实训报告（朱宇航）.doc', 'D:\\Server\\upload\\zhuyuhang\\综合性实训报告（朱宇航）.doc', '92160', '2006632120', '0', '0', '2021-07-11 13:15:18');
INSERT INTO `t_file` VALUES ('589', '软件工程14组 朱宇航 徐航 潘鹏.zip', 'D:\\Server\\upload\\zhuyuhang\\软件工程14组 朱宇航 徐航 潘鹏.zip', '35296061', '2006632120', '0', '0', '2022-01-10 21:35:00');
INSERT INTO `t_file` VALUES ('878', 'navicat.exe.lnk', 'D:\\Server\\upload\\zyh\\navicat.exe.lnk', '1028', '24703', '0', '0', '2021-06-20 20:23:42');
INSERT INTO `t_file` VALUES ('1028', 'navicat.exe.lnk', 'D:\\Server\\upload\\zhuyuhang\\navicat.exe.lnk', '1028', '2006632120', '0', '0', '2021-06-17 15:01:00');
INSERT INTO `t_file` VALUES ('1251', '枫.txt', 'D:\\Server\\upload\\zhuyuhang\\枫.txt', '1251', '2006632120', '0', '0', '2021-06-05 19:21:21');
INSERT INTO `t_file` VALUES ('3146', '皮影戏.txt', 'D:\\Server\\upload\\zhuyuhang\\皮影戏.txt', '3146', '2006632120', '0', '0', '2021-06-05 19:21:04');
INSERT INTO `t_file` VALUES ('13993', 'notepad.java', 'D:\\Server\\upload\\zhuyuhang\\notepad.java', '13993', '2006632120', '0', '0', '2021-06-05 19:38:50');
INSERT INTO `t_file` VALUES ('62442', 'navicat.exe.lnk', 'D:\\Server\\upload\\yandeqing\\navicat.exe.lnk', '1028', '987654321', '0', '1', '2021-06-18 16:36:02');
INSERT INTO `t_file` VALUES ('644561', 'image1.jpg', 'D:\\Server\\upload\\zhuyuhang\\image1.jpg', '644561', '2006632120', '0', '0', '2021-06-05 19:39:45');
INSERT INTO `t_file` VALUES ('748094', '屏幕截图 2021-04-10 111327.png', 'D:\\Server\\upload\\zhuyuhang\\屏幕截图 2021-04-10 111327.png', '748094', '12345678', '0', '0', '2021-06-07 17:16:42');
INSERT INTO `t_file` VALUES ('1407758', '2021-03-12.png', 'D:\\Server\\upload\\zhuyuhang\\2021-03-12.png', '1407758', '2006632120', '0', '0', '2021-06-15 20:33:08');
INSERT INTO `t_file` VALUES ('1466695', '2.1——PPT高手之道.pdf', 'D:\\Server\\upload\\zhuyuhang\\2.1——PPT高手之道.pdf', '1466695', '2006632120', '0', '0', '2021-06-17 20:09:12');
INSERT INTO `t_file` VALUES ('2428176', '2.1.3——PPT演绎互动.pdf', 'D:\\Server\\upload\\zhuyuhang\\2.1.3——PPT演绎互动.pdf', '2428176', '2006632120', '0', '0', '2021-06-17 15:21:06');
INSERT INTO `t_file` VALUES ('3462192', 'java答辩ppt.pptx', 'D:\\Server\\upload\\zhuyuhang\\java答辩ppt.pptx', '3462192', '2006632120', '0', '0', '2021-06-05 19:39:30');
INSERT INTO `t_file` VALUES ('4518676', '2.1.1—PPT谋篇布局.pdf', 'D:\\Server\\upload\\yandeqing\\2.1.1—PPT谋篇布局.pdf', '4518676', '987654321', '0', '0', '2021-06-18 16:26:03');
INSERT INTO `t_file` VALUES ('9594167', 'linux基础gitbook.zip', 'D:\\Server\\upload\\zhuyuhang\\linux基础gitbook.zip', '9594167', '2006632120', '0', '0', '2021-06-15 10:13:15');
INSERT INTO `t_file` VALUES ('11234780', '20210618_130437.mp4', 'D:\\Server\\upload\\zhuyuhang\\20210618_130437.mp4', '11234780', '2006632120', '0', '0', '2021-06-18 13:07:29');
INSERT INTO `t_file` VALUES ('12832011', '20210525_114249.mp4', 'D:\\Server\\upload\\zhuyuhang\\20210525_114249.mp4', '12832011', '2006632120', '0', '0', '2021-06-05 19:41:12');
INSERT INTO `t_file` VALUES ('30675932', '周杰伦 - 枫.flac', 'D:\\Server\\upload\\zhuyuhang\\周杰伦 - 枫.flac', '30675932', '2006632120', '0', '1', '2021-06-05 19:14:40');
INSERT INTO `t_file` VALUES ('44599440', '网易云音乐.exe', 'D:\\Server\\upload\\zhuyuhang\\网易云音乐.exe', '44599440', '2006632120', '0', '0', '2021-06-15 20:33:43');
INSERT INTO `t_file` VALUES ('54315251', 'Python-3.9.0.rar', 'D:\\Server\\upload\\zhuyuhang\\Python-3.9.0.rar', '196608', '2006632120', '0', '0', '2021-06-06 08:58:25');

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int NOT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `telephone` varchar(20) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `type` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('2981', 'qwer', '123456', '1598879657@qq.com', '1598879657@qq.com', '1');
INSERT INTO `t_user` VALUES ('4621', 'baidu', '123456', '3380094115@qq.com', '3380094115@qq.com', '1');
INSERT INTO `t_user` VALUES ('24703', 'zyh', '123456', '3380094155@qq.com', '3380094155', '1');
INSERT INTO `t_user` VALUES ('12345678', 'zhuyu', '123456', '12345678', '12345678', '1');
INSERT INTO `t_user` VALUES ('987654321', 'yandeqing', '1234567', '987654321', '987654321', '1');
INSERT INTO `t_user` VALUES ('2006632120', 'zhuyuhang', '12345', '18398873221', '3380094155@qq.com', '1');
