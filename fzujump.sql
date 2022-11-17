/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 8.0.30 : Database - fzujump
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`fzujump` /*!40100 DEFAULT CHARACTER SET utf8mb3 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `fzujump`;

/*Table structure for table `log` */

DROP TABLE IF EXISTS `log`;

CREATE TABLE `log` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `message` varchar(32) NOT NULL COMMENT '消息',
  `createTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;

/*Data for the table `log` */

insert  into `log`(`id`,`message`,`createTime`) values (1,'11月14号晚上8点在紫荆一楼开会','2022-11-13 14:20:17'),(2,'11月16号下午4点到6点的组会在宿舍楼27#3钟声文学社开组会','2022-11-15 10:21:44'),(3,'11月16号下午的组会改为20#2潮韵文学社进行','2022-11-15 16:44:47');

/*Table structure for table `permission` */

DROP TABLE IF EXISTS `permission`;

CREATE TABLE `permission` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `code` int NOT NULL COMMENT '编号',
  `permissionName` varchar(32) DEFAULT NULL COMMENT '权限名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;

/*Data for the table `permission` */

insert  into `permission`(`id`,`code`,`permissionName`) values (1,1,'用户管理'),(2,2,'角色管理'),(3,3,'成绩管理'),(4,4,'日志管理');

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `rolename` varchar(32) NOT NULL COMMENT '角色名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3;

/*Data for the table `role` */

insert  into `role`(`id`,`rolename`) values (1,'组长'),(2,'全栈工程师'),(3,'后端工程师'),(4,'前端工程师'),(5,'测试人员'),(6,'摄影师'),(7,'用户');

/*Table structure for table `role_permission` */

DROP TABLE IF EXISTS `role_permission`;

CREATE TABLE `role_permission` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `roleId` int NOT NULL COMMENT '角色id',
  `permissionId` int NOT NULL COMMENT '权限id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3;

/*Data for the table `role_permission` */

insert  into `role_permission`(`id`,`roleId`,`permissionId`) values (1,1,1),(2,1,2),(3,1,3),(4,1,4),(5,2,1),(6,3,1),(7,4,4),(8,5,1),(9,6,3),(10,7,4);

/*Table structure for table `score` */

DROP TABLE IF EXISTS `score`;

CREATE TABLE `score` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `userName` varchar(15) DEFAULT NULL COMMENT '用户名称',
  `userRoleId` bigint DEFAULT NULL COMMENT '用户角色（取自角色表-角色id）',
  `JumpFrequency` bigint DEFAULT NULL COMMENT '跳跃次数',
  `ItemNumber` bigint DEFAULT NULL COMMENT '道具数',
  `modifyDate` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=123460 DEFAULT CHARSET=utf8mb3;

/*Data for the table `score` */

insert  into `score`(`id`,`userName`,`userRoleId`,`JumpFrequency`,`ItemNumber`,`modifyDate`) values (1,'赵郑豪',1,262,27,NULL),(2,'张晓彤',5,35,31,NULL),(3,'李志取',1,29,30,NULL),(4,'苏小林',2,34,28,NULL),(5,'陈雨昕',3,27,30,NULL),(6,'余绍弘',3,36,39,NULL),(7,'陈俊宏',2,27,21,NULL),(8,'李雨晴',4,29,34,NULL),(9,'黄宁',4,38,31,NULL),(10,'林嗣',6,24,20,NULL),(11,'连道鑫',7,40,34,NULL),(12,'林卢希',7,27,30,NULL);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `usercode` varchar(32) DEFAULT NULL COMMENT '用户账号',
  `username` varchar(32) NOT NULL COMMENT '用户姓名',
  `password` varchar(64) NOT NULL DEFAULT '123456' COMMENT '用户密码',
  `roleId` int NOT NULL COMMENT '角色id',
  `headPic` varchar(255) DEFAULT NULL COMMENT '头像描述',
  `state` int DEFAULT '0' COMMENT '是否离职0：未离职1：已离职',
  `tel` varchar(11) DEFAULT NULL COMMENT '电话',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `address` varchar(200) DEFAULT NULL COMMENT '地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb3;

/*Data for the table `user` */

insert  into `user`(`id`,`usercode`,`username`,`password`,`roleId`,`headPic`,`state`,`tel`,`email`,`address`) values (1,'032002414','李志取','$2a$10$ldDVP5nvNViT09iafdz3p.AA8W/RKIusoeO6noI/y6Gajj0bCS5lC',3,'1',0,'18359597989','2207750450@qq.com',NULL),(2,'032002439','张晓彤','$2a$10$ldDVP5nvNViT09iafdz3p.AA8W/RKIusoeO6noI/y6Gajj0bCS5lC',5,NULL,0,NULL,NULL,NULL),(3,'032002440','赵郑豪','$2a$10$ldDVP5nvNViT09iafdz3p.AA8W/RKIusoeO6noI/y6Gajj0bCS5lC',1,NULL,0,NULL,NULL,NULL),(4,'032002431','苏小林','$2a$10$ldDVP5nvNViT09iafdz3p.AA8W/RKIusoeO6noI/y6Gajj0bCS5lC',2,NULL,0,NULL,NULL,NULL),(5,'032002402','陈雨昕','$2a$10$ldDVP5nvNViT09iafdz3p.AA8W/RKIusoeO6noI/y6Gajj0bCS5lC',3,NULL,0,NULL,NULL,NULL),(6,'032002413','李雨晴','$2a$10$ldDVP5nvNViT09iafdz3p.AA8W/RKIusoeO6noI/y6Gajj0bCS5lC',4,NULL,0,NULL,NULL,NULL),(7,'032002418','林嗣','$2a$10$ldDVP5nvNViT09iafdz3p.AA8W/RKIusoeO6noI/y6Gajj0bCS5lC',5,NULL,0,NULL,NULL,NULL),(8,'032002106','陈俊宏','$2a$10$ldDVP5nvNViT09iafdz3p.AA8W/RKIusoeO6noI/y6Gajj0bCS5lC',2,NULL,0,NULL,NULL,NULL),(9,'032002118','黄宁','$2a$10$ldDVP5nvNViT09iafdz3p.AA8W/RKIusoeO6noI/y6Gajj0bCS5lC',4,NULL,0,NULL,NULL,NULL),(10,'032002336','余绍弘','$2a$10$ldDVP5nvNViT09iafdz3p.AA8W/RKIusoeO6noI/y6Gajj0bCS5lC',3,NULL,0,NULL,NULL,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
