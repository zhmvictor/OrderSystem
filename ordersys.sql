/*
Navicat MySQL Data Transfer

Source Server         : A308
Source Server Version : 50508
Source Host           : localhost:3306
Source Database       : ordersys

Target Server Type    : MYSQL
Target Server Version : 50508
File Encoding         : 65001

Date: 2017-07-04 13:07:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `dishesinfo`
-- ----------------------------
DROP TABLE IF EXISTS `dishesinfo`;
CREATE TABLE `dishesinfo` (
  `dishesId` int(11) NOT NULL AUTO_INCREMENT,
  `dishesName` varchar(50) DEFAULT NULL,
  `dishesDiscript` varchar(100) DEFAULT NULL,
  `dishesImg` varchar(100) DEFAULT NULL,
  `dishesTxt` varchar(400) DEFAULT NULL,
  `recommend` int(11) DEFAULT '0',
  `dishesPrice` float DEFAULT '1',
  PRIMARY KEY (`dishesId`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dishesinfo
-- ----------------------------
INSERT INTO `dishesinfo` VALUES ('1', '川香回锅肉', '菜色鲜香，肥而不腻', '1.jpg', '采用上等五花肉制作，配以酸姜、酸辣椒及秘制豆豉酱，味道鲜美，营养丰富', '1', '26.5');
INSERT INTO `dishesinfo` VALUES ('3', '鱼香肉丝', '很好吃，但是没有鱼肉', '20170703193922_15.jpg', '很好吃，但是没有鱼肉.很好吃，但是没有鱼肉.很好吃，但是没有鱼肉.重要的事情说三遍！~', '0', '30');
INSERT INTO `dishesinfo` VALUES ('4', '肉椒似漆', '肉椒似漆', '20170703191148_5.jpg', '肉椒似漆', '0', '10.5');
INSERT INTO `dishesinfo` VALUES ('5', '剁椒鱼头', '精心腌制七七四十九天的鱼头', '20170703185619_350.jpg', '精心腌制七七四十九天的鱼头，配上来自神秘阿富汗文明的秘制酱料，采用每日清晨露水精心烹制九九八十一天。确保每一片鱼肉都渗透汤汁，入口即化，香浓口感，让您的每一口都充满幸福感。', '1', '50');
INSERT INTO `dishesinfo` VALUES ('6', '凉拌藕片', '原料采用进口莲藕', '20170703190758_529.jpg', '来自神秘西西里的莲藕经过精心腌制。', '1', '16');
INSERT INTO `dishesinfo` VALUES ('7', '甘蓝爆螺片', '爆炒甘蓝还不知道是啥么？', '20170704095348_327.jpg', '爆炒甘蓝还不知道是啥么？不要让我多说了', '0', '10');
INSERT INTO `dishesinfo` VALUES ('8', '醋溜白肉', '醋溜白肉', '20170703191706_967.jpg', '醋溜白肉', '1', '20');
INSERT INTO `dishesinfo` VALUES ('9', '花仁豆干', '花仁豆干', '20170704101227_217.jpg', '花仁豆干', '0', '10');
INSERT INTO `dishesinfo` VALUES ('10', '可乐鸡翅', '可乐鸡翅', '20170704102229_801.jpeg', '可乐鸡翅', '0', '30');
INSERT INTO `dishesinfo` VALUES ('11', '孜然小羊排', '孜然小羊排', '20170704102637_471.jpg', '孜然小羊排', '1', '50');
INSERT INTO `dishesinfo` VALUES ('13', '糖醋里脊', '糖醋里脊', '20170704103325_948.jpg', '糖醋里脊', '0', '30');
INSERT INTO `dishesinfo` VALUES ('14', '菠萝古老肉', '菠萝古老肉', '20170704103921_504.jpg', '菠萝古老肉', '0', '25.5');
INSERT INTO `dishesinfo` VALUES ('15', '夫妻肺片', '夫妻肺片', '20170704110740_819.jpg', '夫妻肺片', '0', '20.5');
INSERT INTO `dishesinfo` VALUES ('16', '扬州炒饭', '扬州炒饭', '20170704125912_233.jpg', '扬州炒饭', '0', '12');
INSERT INTO `dishesinfo` VALUES ('17', '五丝菜卷', '五丝菜卷', '20170704125948_656.jpg', '五丝菜卷', '0', '10');
INSERT INTO `dishesinfo` VALUES ('18', '四喜饺', '四喜饺', '20170704130151_81.jpg', '四喜饺', '0', '23');
INSERT INTO `dishesinfo` VALUES ('19', '四川麻婆豆腐', '四川麻婆豆腐', '20170704130250_504.jpeg', '四川麻婆豆腐', '0', '23');
INSERT INTO `dishesinfo` VALUES ('20', '麻辣毛血旺', '麻辣毛血旺', '20170704130357_734.png', '麻辣毛血旺', '0', '25.5');
INSERT INTO `dishesinfo` VALUES ('21', '香辣花甲', '香辣花甲', '20170704130441_828.jpg', '香辣花甲', '1', '25.5');
INSERT INTO `dishesinfo` VALUES ('22', '潮州小炒皇', '潮州小炒皇', '20170704130530_731.jpg', '潮州小炒皇', '0', '25.5');

-- ----------------------------
-- Table structure for `orderdishes`
-- ----------------------------
DROP TABLE IF EXISTS `orderdishes`;
CREATE TABLE `orderdishes` (
  `odId` int(11) NOT NULL AUTO_INCREMENT,
  `orderReference` int(11) DEFAULT NULL,
  `dishes` int(11) DEFAULT NULL,
  `num` int(11) DEFAULT '1',
  `dishstatic` int(1) DEFAULT '0',
  PRIMARY KEY (`odId`),
  KEY `orderReference` (`orderReference`),
  KEY `dishes` (`dishes`),
  CONSTRAINT `orderdishes_ibfk_1` FOREIGN KEY (`orderReference`) REFERENCES `orderinfo` (`orderId`),
  CONSTRAINT `orderdishes_ibfk_2` FOREIGN KEY (`dishes`) REFERENCES `dishesinfo` (`dishesId`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orderdishes
-- ----------------------------
INSERT INTO `orderdishes` VALUES ('1', '1', '1', '4', '0');
INSERT INTO `orderdishes` VALUES ('2', '2', '5', '1', '2');
INSERT INTO `orderdishes` VALUES ('3', '2', '4', '2', '1');
INSERT INTO `orderdishes` VALUES ('4', '3', '1', '3', '0');
INSERT INTO `orderdishes` VALUES ('5', '4', '5', '1', '2');
INSERT INTO `orderdishes` VALUES ('6', '5', '4', '1', '0');
INSERT INTO `orderdishes` VALUES ('7', '6', '1', '2', '0');
INSERT INTO `orderdishes` VALUES ('8', '6', '6', '3', '1');
INSERT INTO `orderdishes` VALUES ('9', '6', '4', '1', '0');
INSERT INTO `orderdishes` VALUES ('10', '7', '1', '3', '0');
INSERT INTO `orderdishes` VALUES ('11', '7', '5', '1', '0');
INSERT INTO `orderdishes` VALUES ('12', '7', '6', '1', '1');
INSERT INTO `orderdishes` VALUES ('15', '9', '1', '3', '0');
INSERT INTO `orderdishes` VALUES ('16', '10', '5', '1', '0');
INSERT INTO `orderdishes` VALUES ('17', '10', '6', '1', '0');
INSERT INTO `orderdishes` VALUES ('18', '11', '1', '4', '0');
INSERT INTO `orderdishes` VALUES ('19', '11', '4', '4', '1');
INSERT INTO `orderdishes` VALUES ('20', '11', '6', '2', '0');
INSERT INTO `orderdishes` VALUES ('21', '12', '1', '1', '0');
INSERT INTO `orderdishes` VALUES ('22', '12', '5', '1', '0');
INSERT INTO `orderdishes` VALUES ('23', '13', '1', '3', '0');
INSERT INTO `orderdishes` VALUES ('24', '13', '5', '5', '0');
INSERT INTO `orderdishes` VALUES ('25', '13', '6', '1', '0');
INSERT INTO `orderdishes` VALUES ('26', '14', '1', '3', '0');
INSERT INTO `orderdishes` VALUES ('27', '14', '5', '1', '0');
INSERT INTO `orderdishes` VALUES ('28', '15', '3', '3', '0');
INSERT INTO `orderdishes` VALUES ('29', '15', '1', '1', '0');
INSERT INTO `orderdishes` VALUES ('30', '16', '1', '4', '0');
INSERT INTO `orderdishes` VALUES ('31', '16', '5', '2', '0');
INSERT INTO `orderdishes` VALUES ('32', '17', '1', '4', '0');
INSERT INTO `orderdishes` VALUES ('33', '17', '5', '2', '0');
INSERT INTO `orderdishes` VALUES ('34', '18', '5', '3', '1');
INSERT INTO `orderdishes` VALUES ('35', '18', '1', '3', '0');
INSERT INTO `orderdishes` VALUES ('36', '19', '1', '3', '0');
INSERT INTO `orderdishes` VALUES ('37', '19', '6', '1', '0');

-- ----------------------------
-- Table structure for `orderinfo`
-- ----------------------------
DROP TABLE IF EXISTS `orderinfo`;
CREATE TABLE `orderinfo` (
  `orderId` int(11) NOT NULL AUTO_INCREMENT,
  `orderBeginDate` datetime DEFAULT NULL,
  `orderEndDate` datetime DEFAULT NULL,
  `waiterId` int(11) DEFAULT NULL,
  `orderState` int(11) DEFAULT '0',
  `tableId` int(11) DEFAULT '1',
  PRIMARY KEY (`orderId`),
  KEY `waiterfk` (`waiterId`),
  CONSTRAINT `waiterfk` FOREIGN KEY (`waiterId`) REFERENCES `userinfo` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orderinfo
-- ----------------------------
INSERT INTO `orderinfo` VALUES ('1', '2015-09-09 16:49:29', '2015-09-10 11:49:57', '4', '2', '1');
INSERT INTO `orderinfo` VALUES ('2', '2015-09-09 16:51:35', '2015-10-12 11:42:01', '4', '1', '7');
INSERT INTO `orderinfo` VALUES ('3', '2015-09-09 16:53:59', '2015-10-13 10:47:09', '16', '0', '3');
INSERT INTO `orderinfo` VALUES ('4', '2015-09-09 16:56:16', '2015-09-10 14:13:16', '15', '2', '1');
INSERT INTO `orderinfo` VALUES ('5', '2015-10-12 11:15:41', '2015-10-12 11:42:36', '17', '2', '6');
INSERT INTO `orderinfo` VALUES ('6', '2015-10-12 11:46:57', '2015-10-12 11:48:25', '16', '1', '11');
INSERT INTO `orderinfo` VALUES ('7', '2015-10-12 11:51:15', '2015-10-12 11:52:05', '15', '1', '12');
INSERT INTO `orderinfo` VALUES ('9', '2015-10-13 10:46:53', '2015-10-13 10:47:02', '4', '2', '5');
INSERT INTO `orderinfo` VALUES ('10', '2015-10-13 10:47:31', '2015-10-13 10:47:35', '17', '2', '1');
INSERT INTO `orderinfo` VALUES ('11', '2015-10-13 10:50:39', '2015-10-13 10:51:00', '17', '1', '12');
INSERT INTO `orderinfo` VALUES ('12', '2015-10-13 10:52:57', '2015-10-13 10:53:54', '1', '1', '8');
INSERT INTO `orderinfo` VALUES ('13', '2015-10-13 13:31:11', '2015-10-29 14:19:23', '16', '1', '1');
INSERT INTO `orderinfo` VALUES ('14', '2015-10-13 13:34:47', '2015-10-26 16:01:19', '17', '0', '7');
INSERT INTO `orderinfo` VALUES ('15', '2015-10-13 14:50:51', '2015-10-13 14:51:32', '4', '1', '3');
INSERT INTO `orderinfo` VALUES ('16', '2015-10-26 15:32:39', '2015-10-30 13:06:48', '15', '1', '4');
INSERT INTO `orderinfo` VALUES ('17', '2015-10-26 15:33:43', '2017-07-04 09:02:53', '17', '0', '1');
INSERT INTO `orderinfo` VALUES ('18', '2015-10-29 15:49:04', '2015-10-29 15:49:39', '17', '1', '5');
INSERT INTO `orderinfo` VALUES ('19', '2015-11-04 14:05:57', '2015-11-04 14:08:28', '4', '1', '1');

-- ----------------------------
-- Table structure for `roleinfo`
-- ----------------------------
DROP TABLE IF EXISTS `roleinfo`;
CREATE TABLE `roleinfo` (
  `roleId` int(11) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of roleinfo
-- ----------------------------
INSERT INTO `roleinfo` VALUES ('1', '餐厅管理员');
INSERT INTO `roleinfo` VALUES ('2', '后厨人员');
INSERT INTO `roleinfo` VALUES ('3', '餐厅服务员');

-- ----------------------------
-- Table structure for `userinfo`
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `userAccount` varchar(30) DEFAULT NULL,
  `userPass` varchar(50) DEFAULT NULL,
  `role` int(11) DEFAULT NULL,
  `locked` int(11) DEFAULT '0',
  `faceimg` varchar(150) DEFAULT 'default.jpg',
  PRIMARY KEY (`userId`),
  KEY `fkrole` (`role`),
  CONSTRAINT `fkrole` FOREIGN KEY (`role`) REFERENCES `roleinfo` (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userinfo
-- ----------------------------
INSERT INTO `userinfo` VALUES ('1', 'admin1', '21232f297a57a5a743894a0e4a801fc3', '1', '0', '20170704101808_473.jpg');
INSERT INTO `userinfo` VALUES ('2', 'admin2', '21232f297a57a5a743894a0e4a801fc3', '1', '0', '20170703151904_437.jpg');
INSERT INTO `userinfo` VALUES ('3', 'User1', '2d86bdac01a3315b95794ffa7360edc3', '3', '0', '20170703151904_437.jpg');
INSERT INTO `userinfo` VALUES ('4', 'User2', '2d86bdac01a3315b95794ffa7360edc3', '3', '0', 'default.jpg');
INSERT INTO `userinfo` VALUES ('15', 'User3', '2d86bdac01a3315b95794ffa7360edc3', '3', '0', 'default.jpg');
INSERT INTO `userinfo` VALUES ('16', 'User4', '2d86bdac01a3315b95794ffa7360edc3', '3', '0', 'default.jpg');
INSERT INTO `userinfo` VALUES ('17', 'zyyyl', '2d86bdac01a3315b95794ffa7360edc3', '3', '0', 'default.jpg');
INSERT INTO `userinfo` VALUES ('18', 'chef1', '2d86bdac01a3315b95794ffa7360edc3', '2', '0', 'default.jpg');
INSERT INTO `userinfo` VALUES ('19', 'chef2', '2d86bdac01a3315b95794ffa7360edc3', '2', '0', 'default.jpg');
