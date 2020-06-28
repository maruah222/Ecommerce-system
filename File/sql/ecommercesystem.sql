/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 8.0.20 : Database - ecommercesystem
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ecommercesystem` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `ecommercesystem`;

/*Table structure for table `chart` */

DROP TABLE IF EXISTS `chart`;

CREATE TABLE `chart` (
  `chartID` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '自增Id',
  `userID` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户ID',
  `GoodID` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品ID（要商品图片，价格，名字，属性）',
  `number` int NOT NULL DEFAULT '0' COMMENT '数量',
  `price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '价钱',
  `CheckState` int NOT NULL COMMENT '结算状态，0为false，1为true',
  `Attribute` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品的规格',
  `Goodname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品的名字',
  `frontpicture` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品的图片',
  `ispackage` int DEFAULT NULL COMMENT '该商品是否包邮',
  `skuid` int DEFAULT NULL COMMENT 'sku的Id',
  PRIMARY KEY (`chartID`) USING BTREE,
  KEY `chart_userr` (`userID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=REDUNDANT;

/*Data for the table `chart` */

insert  into `chart`(`chartID`,`userID`,`GoodID`,`number`,`price`,`CheckState`,`Attribute`,`Goodname`,`frontpicture`,`ispackage`,`skuid`) values ('473855','111','1592550475',1,'3600.00',0,'[{\"attrKey\":\"颜色\",\"attrValue\":\"白色\"},{\"attrKey\":\"内存\",\"attrValue\":\"256G\"}]','华为手机','//img20.360buyimg.com/vc/jfs/t1/112707/38/3362/195227/5ea7970dE5e120f21/d138f55f3565d8ef.jpg',1,13);

/*Table structure for table `good_sku` */

DROP TABLE IF EXISTS `good_sku`;

CREATE TABLE `good_sku` (
  `SKUID` int NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `goodID` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品Id',
  `number` int NOT NULL COMMENT '数量',
  `price` decimal(10,2) NOT NULL COMMENT '价格',
  `Vipprice` decimal(10,2) NOT NULL COMMENT 'Vip价格',
  `Left_number` int DEFAULT NULL COMMENT '库存量',
  `picture` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图片',
  `Attribute` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '属性',
  PRIMARY KEY (`SKUID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `good_sku` */

insert  into `good_sku`(`SKUID`,`goodID`,`number`,`price`,`Vipprice`,`Left_number`,`picture`,`Attribute`) values (7,'1592550242',11,'2000.00','1700.00',0,'//img14.360buyimg.com/cms/jfs/t1/120813/1/1474/254048/5ebcaee4E7b4ecc48/0b3492048eb289e1.jpg','[{\"attrKey\":\"颜色\",\"attrValue\":\"白色\"},{\"attrKey\":\"内存\",\"attrValue\":\"128G\"}]'),(8,'1592550242',21,'2000.00','1900.00',13,'//img14.360buyimg.com/cms/jfs/t1/120813/1/1474/254048/5ebcaee4E7b4ecc48/0b3492048eb289e1.jpg','[{\"attrKey\":\"颜色\",\"attrValue\":\"黄色\"},{\"attrKey\":\"内存\",\"attrValue\":\"128G\"}]'),(9,'1592550242',34,'1000.00','800.00',21,'//img14.360buyimg.com/cms/jfs/t1/120813/1/1474/254048/5ebcaee4E7b4ecc48/0b3492048eb289e1.jpg','[{\"attrKey\":\"颜色\",\"attrValue\":\"绿色\"},{\"attrKey\":\"内存\",\"attrValue\":\"64G\"}]'),(10,'1592550242',22,'5000.00','4800.00',7,'//img14.360buyimg.com/cms/jfs/t1/120813/1/1474/254048/5ebcaee4E7b4ecc48/0b3492048eb289e1.jpg','[{\"attrKey\":\"颜色\",\"attrValue\":\"黑色\"},{\"attrKey\":\"内存\",\"attrValue\":\"256G\"}]'),(11,'1592550475',20,'800.00','700.00',20,'//img20.360buyimg.com/vc/jfs/t1/109597/16/14515/187027/5ea7970dE74c63673/b450475763982e24.jpg','[{\"attrKey\":\"颜色\",\"attrValue\":\"金色\"},{\"attrKey\":\"内存\",\"attrValue\":\"64G\"}]'),(12,'1592550475',21,'1800.00','1500.00',19,'//img20.360buyimg.com/vc/jfs/t1/109597/16/14515/187027/5ea7970dE74c63673/b450475763982e24.jpg','[{\"attrKey\":\"颜色\",\"attrValue\":\"银色\"},{\"attrKey\":\"内存\",\"attrValue\":\"128G\"}]'),(13,'1592550475',40,'3600.00','3000.00',36,'//img20.360buyimg.com/vc/jfs/t1/109597/16/14515/187027/5ea7970dE74c63673/b450475763982e24.jpg','[{\"attrKey\":\"颜色\",\"attrValue\":\"白色\"},{\"attrKey\":\"内存\",\"attrValue\":\"256G\"}]'),(14,'1592550475',10,'1800.00','1500.00',9,'//img20.360buyimg.com/vc/jfs/t1/109597/16/14515/187027/5ea7970dE74c63673/b450475763982e24.jpg','[{\"attrKey\":\"颜色\",\"attrValue\":\"白色\"},{\"attrKey\":\"内存\",\"attrValue\":\"128G\"}]'),(18,'1592921345',7000,'500.00','500.00',6990,'//img30.360buyimg.com/popWareDetail/jfs/t1/90545/35/15900/204731/5e746ec9E89ccda38/8f8cbff11d87f64d.jpg','[{\"attrKey\":\"颜色\",\"attrValue\":\"天绿色\"},{\"attrKey\":\"尺寸\",\"attrValue\":\"XL\"}]'),(19,'1592921345',6100,'300.00','290.00',6098,'//img30.360buyimg.com/popWareDetail/jfs/t1/90545/35/15900/204731/5e746ec9E89ccda38/8f8cbff11d87f64d.jpg','[{\"attrKey\":\"颜色\",\"attrValue\":\"天紫色\"},{\"attrKey\":\"尺寸\",\"attrValue\":\"XLL\"}]'),(20,'1592922495',2000,'800.00','700.00',2000,'//img10.360buyimg.com/imgzone/jfs/t1/111786/12/8275/107027/5ecf19abE220e1ab3/cad169002179f3a5.jpg','[{\"attrKey\":\"颜色\",\"attrValue\":\"绿色\"},{\"attrKey\":\"尺寸\",\"attrValue\":\"XL\"}]'),(21,'1592922495',2000,'800.00','700.00',1998,'//img10.360buyimg.com/imgzone/jfs/t1/111786/12/8275/107027/5ecf19abE220e1ab3/cad169002179f3a5.jpg','[{\"attrKey\":\"颜色\",\"attrValue\":\"绿色\"},{\"attrKey\":\"尺寸\",\"attrValue\":\"XXL\"}]'),(22,'1592922495',2000,'800.00','700.00',2000,'//img10.360buyimg.com/imgzone/jfs/t1/111786/12/8275/107027/5ecf19abE220e1ab3/cad169002179f3a5.jpg','[{\"attrKey\":\"颜色\",\"attrValue\":\"绿色\"},{\"attrKey\":\"尺寸\",\"attrValue\":\"L\"}]'),(23,'1592922495',2000,'700.00','600.00',1991,'//img10.360buyimg.com/imgzone/jfs/t1/111786/12/8275/107027/5ecf19abE220e1ab3/cad169002179f3a5.jpg','[{\"attrKey\":\"颜色\",\"attrValue\":\"蓝色\"},{\"attrKey\":\"尺寸\",\"attrValue\":\"XL\"}]'),(26,'1592967234',1111,'11.00','10.00',1111,'dasd','[{\"attrKey\":\"颜色\",\"attrValue\":\"红\"},{\"attrKey\":\"内存\",\"attrValue\":\"123\"}]'),(27,'1593002352',1000,'19.90','15.00',999,'http://www.shuaike.net.cn/uploads/allimg/170213/1-1F2131H1560-L.jpg','[{\"attrKey\":\"包装\",\"attrValue\":\"小\"},{\"attrKey\":\"口味\",\"attrValue\":\"草莓味\"}]'),(28,'1593002352',1000,'20.90','15.50',999,'http://www.shuaike.net.cn/uploads/allimg/170213/1-1F2131H1560-L.jpg','[{\"attrKey\":\"包装\",\"attrValue\":\"小\"},{\"attrKey\":\"口味\",\"attrValue\":\"橘子味\"}]'),(29,'1593002352',500,'30.90','25.70',488,'http://www.shuaike.net.cn/uploads/allimg/170213/1-1F2131H1560-L.jpg','[{\"attrKey\":\"包装\",\"attrValue\":\"大\"},{\"attrKey\":\"口味\",\"attrValue\":\"草莓味\"}]'),(32,'string',0,'0.00','0.00',0,'string','string'),(34,'1592921345',5000,'700.00','600.00',4995,'//img30.360buyimg.com/popWareDetail/jfs/t1/90545/35/15900/204731/5e746ec9E89ccda38/8f8cbff11d87f64d.jpg','[{\"attrKey\":\"颜色\",\"attrValue\":\"地绿色\"},{\"attrKey\":\"尺寸\",\"attrValue\":\"M\"}]'),(42,'1593231084',1000,'30.90','20.90',1000,'https://cbu01.alicdn.com/img/ibank/2016/579/625/2884526975_14403962.310x310.jpg','[{\"attrKey\":\"颜色\",\"attrValue\":\"红\"},{\"attrKey\":\"大小\",\"attrValue\":\"大\"}]'),(43,'1593231084',1000,'32.90','22.90',1000,'https://cbu01.alicdn.com/img/ibank/2016/119/230/3220032911_340828440.400x400.jpg','[{\"attrKey\":\"颜色\",\"attrValue\":\"黑色\"},{\"attrKey\":\"大小\",\"attrValue\":\"大\"}]'),(44,'1593231084',1000,'22.80','20.80',1000,'https://cbu01.alicdn.com/img/ibank/2016/579/625/2884526975_14403962.310x310.jpg','[{\"attrKey\":\"颜色\",\"attrValue\":\"红色\"},{\"attrKey\":\"大小\",\"attrValue\":\"小\"}]'),(45,'1593232076',2000,'70.00','50.00',1998,'//img14.360buyimg.com/n9/s40x40_jfs/t1/97529/11/8342/449530/5e041707E0c883256/8db64a8fa2b4315c.jpg','[{\"attrKey\":\"颜色\",\"attrValue\":\"灰色\"},{\"attrKey\":\"尺码\",\"attrValue\":\"41\"}]'),(46,'1593232076',1000,'70.00','50.00',997,'//img14.360buyimg.com/n9/s40x40_jfs/t1/97529/11/8342/449530/5e041707E0c883256/8db64a8fa2b4315c.jpg','[{\"attrKey\":\"颜色\",\"attrValue\":\"灰色\"},{\"attrKey\":\"尺码\",\"attrValue\":\"42\"}]'),(47,'1593232076',1000,'70.00','50.00',999,'//img10.360buyimg.com/n9/s40x40_jfs/t1/86123/6/8402/480671/5e041714E18e9d1da/46e1cbb50001ac17.jpg','[{\"attrKey\":\"颜色\",\"attrValue\":\"粉色\"},{\"attrKey\":\"尺码\",\"attrValue\":\"42\"}]'),(48,'1593232076',1000,'70.00','50.00',998,'//img12.360buyimg.com/n9/s40x40_jfs/t1/92988/25/8519/558042/5e0416dfE8ac69d3e/a5b8ca572b216782.jpg','[{\"attrKey\":\"颜色\",\"attrValue\":\"蓝色\"},{\"attrKey\":\"尺码\",\"attrValue\":\"43\"}]'),(54,'1593309905',2000,'50.00','40.00',2000,'//img11.360buyimg.com/n1/jfs/t1/86567/9/13941/402261/5e5e032bE0e7db7cb/adba8e0f4f90bafa.jpg','[{\"attrKey\":\"口味\",\"attrValue\":\"巧克力\"},{\"attrKey\":\"规格\",\"attrValue\":\"20条\"},{\"attrKey\":\"赠礼\",\"attrValue\":\"小玩具\"}]'),(55,'1593309905',2000,'50.00','40.00',2000,'//img11.360buyimg.com/n1/jfs/t1/86567/9/13941/402261/5e5e032bE0e7db7cb/adba8e0f4f90bafa.jpg','[{\"attrKey\":\"口味\",\"attrValue\":\"白巧克力\"},{\"attrKey\":\"规格\",\"attrValue\":\"20条\"},{\"attrKey\":\"赠礼\",\"attrValue\":\"无小玩具\"}]'),(56,'1593309905',2000,'50.00','40.00',2000,'//img11.360buyimg.com/n1/jfs/t1/86567/9/13941/402261/5e5e032bE0e7db7cb/adba8e0f4f90bafa.jpg','[{\"attrKey\":\"口味\",\"attrValue\":\"混合口味\"},{\"attrKey\":\"规格\",\"attrValue\":\"40条\"},{\"attrKey\":\"赠礼\",\"attrValue\":\"小玩具\"}]'),(57,'1593309905',2000,'50.00','40.00',2000,'//img11.360buyimg.com/n1/jfs/t1/86567/9/13941/402261/5e5e032bE0e7db7cb/adba8e0f4f90bafa.jpg','[{\"attrKey\":\"口味\",\"attrValue\":\"抹茶口味\"},{\"attrKey\":\"规格\",\"attrValue\":\"30条\"},{\"attrKey\":\"赠礼\",\"attrValue\":\"无小玩具\"}]');

/*Table structure for table `goods` */

DROP TABLE IF EXISTS `goods`;

CREATE TABLE `goods` (
  `goodID` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品ID',
  `ShopID` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商家ID',
  `Goodname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品名字',
  `Goodpicture` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品图片',
  `introduction` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品介绍',
  `CheckState` int NOT NULL COMMENT '审核状态，0为待审核上架，1为已审核成功上架',
  `ispackage` int NOT NULL COMMENT '包邮状态，0为不包邮，1为包邮',
  `Frontpicture` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '首页图片',
  `ShangTime` datetime DEFAULT NULL COMMENT '上架时间',
  `categoryId` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '分类Id',
  `AllsellNumber` int DEFAULT NULL COMMENT '总销售量',
  `UpdownState` int NOT NULL COMMENT '上下架状态，0是待上架，1是已上架，2是已下架，3是拒绝上架',
  PRIMARY KEY (`goodID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `goods` */

insert  into `goods`(`goodID`,`ShopID`,`Goodname`,`Goodpicture`,`introduction`,`CheckState`,`ispackage`,`Frontpicture`,`ShangTime`,`categoryId`,`AllsellNumber`,`UpdownState`) values ('1592550242','492807975@qq.com','苹果手机','//img13.360buyimg.com/n1/s450x450_jfs/t1/85742/14/5474/48229/5dedee7bE17d4f7cc/6a9b4794f4da7e93.jpg','土豪苹果手机',1,1,'//img30.360buyimg.com/popWaterMark/jfs/t1/84978/13/5519/90066/5dedeee8E60109f72/1b2c88c58052066f.jpg','2020-06-19 15:06:32','手机',47,1),('1592550475','492807975@qq.com','华为手机','//img14.360buyimg.com/n1/s450x450_jfs/t1/117400/10/2356/65449/5ea182c4E2d5c0060/e57574f6564bea42.jpg','垃圾华为手机',1,1,'//img20.360buyimg.com/vc/jfs/t1/112707/38/3362/195227/5ea7970dE5e120f21/d138f55f3565d8ef.jpg','2020-06-19 15:10:49','手机',7,1),('1592921345','965241334@qq.com','七匹狼','//img30.360buyimg.com/popWaterMark/jfs/t1/90961/35/8198/37503/5e03337cE36ca9456/f78f660b425086d0.jpg','七匹狼还是七条狗',1,1,'//img30.360buyimg.com/popWaterMark/jfs/t1/87735/25/8299/104155/5e033381E4f310c3a/015a9d89fdc5ab48.jpg','2020-06-23 22:11:13','服装',17,1),('1592922495','965241334@qq.com','JEEP','//img14.360buyimg.com/n9/s60x76_jfs/t1/126653/9/3178/48525/5ecf17acE53d13108/5db6543a2adda395.jpg!cc_60x76.jpg','外国进口货',1,0,'//img10.360buyimg.com/cms/jfs/t3607/113/2460413636/209691/a7518a7a/58539921Nbcefbd51.jpg','2020-06-23 22:30:02','服装',12,1),('1592967234','1005131042@qq.com','ces','null','ccc',1,1,'dad','2020-06-24 10:54:49','1',0,3),('1593002352','915477812@qq.com','狗粮','http://www.shuaike.net.cn/uploads/allimg/170213/1-1F2131H1560-L.jpg','高级狗粮',1,0,'http://www.shuaike.net.cn/uploads/allimg/170213/1-1F2131H1560-L.jpg','2020-06-24 20:44:10','宠物',14,1),('1593231084','915477812@qq.com','狗项圈','https://cbu01.alicdn.com/img/ibank/2016/579/625/2884526975_14403962.310x310.jpg','狗戴的',1,1,'https://cbu01.alicdn.com/img/ibank/2016/579/625/2884526975_14403962.310x310.jpg','2020-06-27 12:16:16','宠物',0,1),('1593232076','965241334@qq.com','南极人拖鞋','//img14.360buyimg.com/n1/jfs/t1/97529/11/8342/449530/5e041707E0c883256/8db64a8fa2b4315c.jpg','居家拖鞋',1,1,'//img14.360buyimg.com/n1/jfs/t1/92058/23/8221/494739/5e02bf96Ef10f0931/a9f0cc3e57056413.jpg','2020-06-27 12:30:07','服装',8,1),('1593309905','965241334@qq.com','脆脆鲨','//img30.360buyimg.com/sku/jfs/t1/131854/7/3017/120957/5ef35447E82702ead/c131262889b0dc5a.jpg','好吃的脆脆鲨',1,0,'//img11.360buyimg.com/n1/jfs/t1/86567/9/13941/402261/5e5e032bE0e7db7cb/adba8e0f4f90bafa.jpg','2020-06-28 10:08:03','零食',0,1);

/*Table structure for table `goods_category` */

DROP TABLE IF EXISTS `goods_category`;

CREATE TABLE `goods_category` (
  `Id` int NOT NULL COMMENT '自增ID',
  `CategoryId` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品分类Id',
  `SellerId` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商家Id',
  `Goodsname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `pic` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图片',
  `CategoryDegree` int NOT NULL COMMENT '分类等级',
  `Number` int NOT NULL COMMENT '数量',
  `Danwei` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '单位',
  `ShowState` int NOT NULL COMMENT '是否展示在主页',
  `Descipt` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '描述',
  PRIMARY KEY (`Id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `goods_category` */

insert  into `goods_category`(`Id`,`CategoryId`,`SellerId`,`Goodsname`,`pic`,`CategoryDegree`,`Number`,`Danwei`,`ShowState`,`Descipt`) values (777,'11111','2wqreqw','第二家','314',2,1343,'条',1,'一般般吧'),(12314,'231','12323','傻逼','21312',1,23123,'件',1,'这个商品很垃圾');

/*Table structure for table `goods_comment` */

DROP TABLE IF EXISTS `goods_comment`;

CREATE TABLE `goods_comment` (
  `CommentId` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评价ID，自增',
  `Userid` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户账号',
  `TypeId` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品属性',
  `Level` int NOT NULL COMMENT '评价星级',
  `CommentTIme` datetime DEFAULT NULL COMMENT '评论时间',
  `State` int DEFAULT NULL COMMENT '0是未评价，1是已评价',
  `Comment` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '评论详情',
  PRIMARY KEY (`CommentId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `goods_comment` */

/*Table structure for table `goodsuprecord` */

DROP TABLE IF EXISTS `goodsuprecord`;

CREATE TABLE `goodsuprecord` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增Id',
  `ShopId` varchar(50) NOT NULL COMMENT '商家Id',
  `GoodId` varchar(50) NOT NULL COMMENT '商品Id',
  `State` int NOT NULL COMMENT '状态，0是审核不通过，1是审核通过',
  `Goodname` varchar(50) NOT NULL COMMENT '商品的名字',
  `Verifytime` datetime NOT NULL COMMENT '审核的时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `goodsuprecord` */

insert  into `goodsuprecord`(`id`,`ShopId`,`GoodId`,`State`,`Goodname`,`Verifytime`) values (1,'915477812@qq.com','1593231084',1,'狗项圈','2020-06-27 12:16:36'),(2,'965241334@qq.com','1593230558',1,'南极人拖鞋','2020-06-27 12:17:44'),(3,'965241334@qq.com','1593232076',1,'南极人拖鞋','2020-06-27 12:30:53'),(4,'965241334@qq.com','1593309056',1,'1','2020-06-28 09:53:13'),(5,'965241334@qq.com','1593309905',1,'脆脆鲨','2020-06-28 10:08:17');

/*Table structure for table `loginrecord` */

DROP TABLE IF EXISTS `loginrecord`;

CREATE TABLE `loginrecord` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增Id',
  `userid` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录的账号',
  `logintime` datetime NOT NULL COMMENT '登录的时间',
  `role` int NOT NULL COMMENT '角色，0是用户，1是管理员，2是商家',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `loginrecord` */

insert  into `loginrecord`(`id`,`userid`,`logintime`,`role`) values (1,'965241334@qq.com','2020-06-26 12:24:45',2),(2,'965241334@qq.com','2020-06-26 12:27:59',2),(3,'915477812@qq.com','2020-06-26 12:42:31',2),(4,'915477812@qq.com','2020-06-26 12:47:54',2),(5,'915477812@qq.com','2020-06-26 12:48:46',2),(6,'965241334@qq.com','2020-06-26 16:10:44',2),(7,'root','2020-06-26 16:11:48',1),(8,'965241334@qq.com','2020-06-26 16:12:10',2),(9,'965241334@qq.com','2020-06-26 16:13:26',2),(10,'965241334@qq.com','2020-06-26 16:14:39',2),(11,'123456','2020-06-26 16:15:15',2),(12,'1846682140@qq.com','2020-06-26 16:24:05',0),(13,'965241334@qq.com','2020-06-26 16:35:17',2),(14,'965241334@qq.com','2020-06-26 16:36:26',2),(15,'965241334@qq.com','2020-06-26 16:37:07',2),(16,'965241334@qq.com','2020-06-26 16:38:16',2),(17,'965241334@qq.com','2020-06-26 16:42:48',2),(18,'965241334@qq.com','2020-06-26 16:46:14',2),(19,'965241334@qq.com','2020-06-26 16:50:33',2),(20,'123456','2020-06-26 16:51:04',2),(21,'1846682140@qq.com','2020-06-26 19:57:06',0),(22,'999','2020-06-26 20:04:53',0),(23,'999','2020-06-26 20:04:54',0),(24,'965241334@qq.com','2020-06-26 20:35:55',2),(25,'915477812@qq.com','2020-06-26 20:36:59',2),(26,'1846682140@qq.com','2020-06-26 20:52:08',0),(27,'965241334@qq.com','2020-06-26 20:54:54',2),(28,'965241334@qq.com','2020-06-26 20:55:18',2),(29,'965241334@qq.com','2020-06-26 20:55:53',2),(30,'965241334@qq.com','2020-06-26 20:56:40',2),(31,'root','2020-06-26 20:59:17',1),(32,'965241334@qq.com','2020-06-26 20:59:50',2),(33,'965241334@qq.com','2020-06-26 20:59:56',2),(34,'root','2020-06-26 21:00:56',1),(35,'1846682140@qq.com','2020-06-26 22:51:43',0),(36,'965241334@qq.com','2020-06-26 23:06:22',2),(37,'965241334@qq.com','2020-06-26 23:32:14',2),(38,'965241334@qq.com','2020-06-26 23:43:33',2),(39,'965241334@qq.com','2020-06-26 23:45:20',2),(40,'965241334@qq.com','2020-06-26 23:49:45',2),(41,'965241334@qq.com','2020-06-26 23:50:57',2),(42,'1846682140@qq.com','2020-06-27 09:53:27',0),(43,'965241334@qq.com','2020-06-27 10:00:03',2),(44,'root','2020-06-27 10:03:05',1),(45,'root','2020-06-27 10:05:06',1),(46,'1846682140@qq.com','2020-06-27 10:18:32',0),(47,'965241334@qq.com','2020-06-27 11:48:28',2),(48,'965241334@qq.com','2020-06-27 12:02:37',2),(49,'1846682140@qq.com','2020-06-27 12:06:00',0),(50,'965241334@qq.com','2020-06-27 12:07:54',2),(51,'root','2020-06-27 12:09:48',1),(52,'915477812@qq.com','2020-06-27 12:11:13',2),(53,'915477812@qq.com','2020-06-27 12:11:14',2),(54,'965241334@qq.com','2020-06-27 12:12:16',2),(55,'root','2020-06-27 12:14:49',1),(56,'root','2020-06-27 12:16:29',1),(57,'root','2020-06-27 12:17:11',1),(58,'root','2020-06-27 12:17:16',1),(59,'1846682140@qq.com','2020-06-27 12:18:02',0),(60,'965241334@qq.com','2020-06-27 12:23:40',2),(61,'965241334@qq.com','2020-06-27 12:23:41',2),(62,'999','2020-06-27 12:24:51',0),(63,'999','2020-06-27 12:24:53',0),(64,'915477812@qq.com','2020-06-27 12:24:54',2),(65,'965241334@qq.com','2020-06-27 12:25:09',2),(66,'965241334@qq.com','2020-06-27 12:27:54',2),(67,'root','2020-06-27 12:30:47',1),(68,'965241334@qq.com','2020-06-27 12:31:04',2),(69,'root','2020-06-27 12:34:53',1),(70,'965241334@qq.com','2020-06-27 12:35:08',2),(71,'1846682140@qq.com','2020-06-27 12:40:12',0),(72,'965241334@qq.com','2020-06-27 12:42:21',2),(73,'999','2020-06-27 15:37:04',0),(74,'999','2020-06-27 15:53:10',0),(75,'915477812@qq.com','2020-06-27 16:06:47',2),(76,'1005131042@qq.com','2020-06-27 16:13:44',0),(77,'root','2020-06-27 16:31:06',1),(78,'1005131042@qq.com','2020-06-27 16:32:33',2),(79,'root','2020-06-28 09:53:08',1),(80,'root','2020-06-28 09:54:04',1),(81,'965241334@qq.com','2020-06-28 10:01:12',2),(82,'root','2020-06-28 10:08:13',1);

/*Table structure for table `manager` */

DROP TABLE IF EXISTS `manager`;

CREATE TABLE `manager` (
  `adminID` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员的ID（主码）',
  `adminWord` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员的密码',
  PRIMARY KEY (`adminID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `manager` */

insert  into `manager`(`adminID`,`adminWord`) values ('admin1','$2a$10$4/VDVUvQFdqNAGnbX44JGuDb0REsLAvdbCt7wiiRMmhN3qJRqK5sq'),('root','$2a$10$xBweZ2c6GUi2Ad3FcWkYIu/I7OaffnyVFxCxc/n1X8FTHhnwHrwtG');

/*Table structure for table `order` */

DROP TABLE IF EXISTS `order`;

CREATE TABLE `order` (
  `OrderID` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单号',
  `UserID` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `State` int NOT NULL COMMENT '0未支付，1已支付，2已收货，3申请退，4超时，5取消',
  `PayTime` datetime DEFAULT NULL COMMENT '支付时间',
  `GetTIme` datetime DEFAULT NULL COMMENT '收货时间',
  `Comment` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评论',
  `CommentTime` datetime DEFAULT NULL COMMENT '评论时间',
  `number` int NOT NULL COMMENT '数量',
  `price` decimal(10,2) NOT NULL COMMENT '价钱',
  `money` decimal(10,2) NOT NULL COMMENT '订单总价',
  `GoodId` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商品的Id',
  `attribute` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '属性',
  `address` varchar(255) DEFAULT NULL COMMENT '用户的地址',
  PRIMARY KEY (`OrderID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `order` */

insert  into `order`(`OrderID`,`UserID`,`State`,`PayTime`,`GetTIme`,`Comment`,`CommentTime`,`number`,`price`,`money`,`GoodId`,`attribute`,`address`) values ('014194','1732427704@qq.com',5,'2020-06-26 23:49:58',NULL,'未评论',NULL,1,'30.90','30.90','1593002352','狗粮 包装:大 口味:草莓味 X1','广东省'),('032045','111',3,'2020-06-24 14:52:52','2020-06-24 20:50:38','未评论',NULL,1,'1800.00','1800.00','1592550475','颜色:银色 内存:128G','hhh'),('034611','1732427704@qq.com',3,'2020-06-24 23:33:33','2020-06-25 10:28:42','未评论',NULL,1,'1000.00','1000.00','1592922495','颜色:灰色 尺寸:M',NULL),('049402','1846682140@qq.com',5,'2020-06-27 12:41:39','2020-06-27 12:41:53','未评论',NULL,3,'70.00','210.00','1593232076','南极人拖鞋 颜色:灰色 尺码:42 X3','华南理工大学'),('064266','1846682140@qq.com',2,'2020-06-27 12:41:39','2020-06-27 12:42:44','未评论',NULL,1,'70.00','70.00','1593232076','南极人拖鞋 颜色:粉色 尺码:42 X1','华南理工大学'),('107257','1732427704@qq.com',3,'2020-06-24 21:21:49','2020-06-24 22:57:02','未评论',NULL,1,'300.00','300.00','1592921345','颜色:天绿色 尺寸:XL',NULL),('178638','111',3,'2020-06-23 22:45:37','2020-06-24 09:23:22','未评论',NULL,2,'1000.00','2000.00','1592550242','颜色:绿色 内存:64G',NULL),('180780','1846682140@qq.com',2,'2020-06-27 12:41:39','2020-06-27 12:42:45','未评论',NULL,2,'70.00','140.00','1593232076','南极人拖鞋 颜色:蓝色 尺码:43 X2','华南理工大学'),('319900','1732427704@qq.com',3,'2020-06-25 10:27:08','2020-06-25 10:35:06','未评论',NULL,1,'300.00','300.00','1592921345','颜色:天绿色 尺寸:XL',NULL),('336733','1005131042@qq.com',5,'2020-06-27 16:16:36',NULL,'未评论',NULL,1,'70.00','70.00','1593232076','南极人拖鞋 颜色:灰色 尺码:41 X1','广东省'),('405535','1732427704@qq.com',3,'2020-06-24 23:33:55','2020-06-25 11:02:21','未评论',NULL,1,'300.00','300.00','1592921345','颜色:天绿色 尺寸:XL',NULL),('418573','111',3,'2020-06-23 22:04:36',NULL,'未评论',NULL,2,'1000.00','2000.00','1592550242','颜色:绿色 内存:64G',NULL),('490360','1732427704@qq.com',1,'2020-06-27 16:05:29',NULL,'未评论',NULL,1,'70.00','70.00','1593232076','南极人拖鞋 颜色:灰色 尺码:41 X1','广东省'),('525235','1732427704@qq.com',3,'2020-06-24 23:32:30','2020-06-24 23:45:09','未评论',NULL,1,'700.00','700.00','1592922495','颜色:蓝色 尺寸:XL',NULL),('555571','1732427704@qq.com',3,'2020-06-24 21:04:44','2020-06-24 23:02:07','未评论',NULL,1,'300.00','300.00','1592921345','颜色:天绿色 尺寸:XL',NULL),('660188','1732427704@qq.com',5,'2020-06-27 15:22:35','2020-06-27 16:06:18','未评论',NULL,1,'30.90','30.90','1593002352','狗粮 包装:大 口味:草莓味 X1','广东省'),('697691','1732427704@qq.com',3,'2020-06-25 10:23:08','2020-06-25 10:27:50','未评论',NULL,1,'20.90','20.90','1593002352','包装:小 口味:橘子味',NULL),('978316','1732427704@qq.com',2,'2020-06-24 21:05:40','2020-06-24 23:08:37','未评论',NULL,1,'300.00','300.00','1592921345','颜色:天绿色 尺寸:XL',NULL);

/*Table structure for table `order_goods` */

DROP TABLE IF EXISTS `order_goods`;

CREATE TABLE `order_goods` (
  `OrderId` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单Id',
  `Goodname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品名字',
  `Shopname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商家名字',
  `State` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '状态',
  `Total_number` int NOT NULL COMMENT '总数量',
  `Total_price` decimal(10,2) NOT NULL COMMENT '总价钱',
  `Goodpictrue` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '图片',
  `Attribute` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '属性',
  PRIMARY KEY (`OrderId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `order_goods` */

/*Table structure for table `order_return` */

DROP TABLE IF EXISTS `order_return`;

CREATE TABLE `order_return` (
  `OrderId` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单ID',
  `ReturnReason` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '退换原因',
  `Money` decimal(10,2) NOT NULL COMMENT '总金额',
  `UserId` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户ID',
  `OrderStatus` int NOT NULL COMMENT '订单状态，0是待审核，1是审核通过，2是拒绝退款',
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增Id',
  `GoodId` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ShopId` varchar(50) NOT NULL COMMENT '商家Id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `order_return` */

insert  into `order_return`(`OrderId`,`ReturnReason`,`Money`,`UserId`,`OrderStatus`,`id`,`GoodId`,`ShopId`) values ('123457','太便宜','1565.00','123',0,1,'12327','123456'),('123456','太贵了','1231.12','123',2,2,'12323','123456'),('034611','不好用啊啊','1000.00','999',1,3,'1592922495','965241334@qq.com'),('405535','太难穿了','300.00','1732427704@qq.com',1,4,'1592921345','965241334@qq.com'),('451249','买错了','300.00','1846682140@qq.com',1,5,'1592921345','965241334@qq.com'),('536839','不想要了','2000.00','1846682140@qq.com',1,6,'1592550242','123456'),('822556','不好用','700.00','1846682140@qq.com',1,7,'1592921345','965241334@qq.com'),('531240','垃圾','1400.00','1846682140@qq.com',1,8,'1592921345','965241334@qq.com'),('049402','买错了','210.00','1846682140@qq.com',1,9,'1593232076','965241334@qq.com'),('660188','不好吃','30.90','1732427704@qq.com',1,10,'1593002352','915477812@qq.com');

/*Table structure for table `shop` */

DROP TABLE IF EXISTS `shop`;

CREATE TABLE `shop` (
  `ShopId` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商家Id',
  `Shopname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商家名',
  `TotalSales` int NOT NULL COMMENT '总销量',
  `ShopAddress` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商家地址',
  `RegisterState` int NOT NULL COMMENT '注册的审核状态，0为待审核，1为审核通过',
  `Sellerpassword` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商家的登录密码',
  `Sellername` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '店主的名字',
  `SellerTelephone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '店主的电话',
  PRIMARY KEY (`ShopId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `shop` */

insert  into `shop`(`ShopId`,`Shopname`,`TotalSales`,`ShopAddress`,`RegisterState`,`Sellerpassword`,`Sellername`,`SellerTelephone`) values ('1005131042@qq.com','华工小店',0,'广州华工',1,'$2a$10$CG69qGYaKVGMont.FWQtGOCa8CiHmXiWxj3iTnUEbDffaHBkfkeUu','哈哈哈','10011'),('492807975@qq.com','这家店',0,'华南理工大学',1,'$2a$10$jE4MjVZjJEATdHDxYVbiO.HyJ0J/KSCLffdqGHHhUYIOIX7drTXU6','阿猫','19927523092'),('915477812@qq.com','阿狗家',0,'广东省',1,'$2a$10$0ZGEOb58F8ZxgRfc9oBtfu457zo/OIPgrqwiPWxFJBG8idotwlR42','阿狗','171717171'),('965241334@qq.com','咸鱼的店',0,'隔壁双鸭山',1,'$2a$10$6Uw8nGCiNYA4WGjAC6voFOIMTX5Np5Ld26Wb4ReOOu6cOtMDsnZiu','阿鱼','19927523092');

/*Table structure for table `userpermission` */

DROP TABLE IF EXISTS `userpermission`;

CREATE TABLE `userpermission` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增Id',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户名 ',
  `value` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '权限值',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `role` int DEFAULT NULL COMMENT '角色，0是用户，1是管理员，2是商家',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `userpermission` */

insert  into `userpermission`(`id`,`name`,`value`,`createTime`,`role`) values (1,'667','普通用户','2020-06-05 19:27:10',NULL),(2,'888','普通用户','2020-06-05 19:58:12',0),(3,'999','普通用户','2020-06-05 20:00:48',0),(4,'711','普通用户','2020-06-05 20:27:01',0),(5,'root','管理员','2020-06-06 09:56:44',1),(6,'669','普通用户','2020-06-08 17:34:36',0),(7,'213123','普通用户','2020-06-09 14:59:07',0),(8,'123456','商家','2020-06-09 20:21:30',2),(9,'889','商家','2020-06-12 11:49:06',2),(11,'333','商家','2020-06-12 11:50:57',2),(13,'sbbb','商家','2020-06-12 11:55:45',2),(14,'233','商家','2020-06-19 10:39:56',2),(15,'111','普通用户','2020-06-19 10:47:00',0),(17,'1846682140@qq.com','普通用户','2020-06-20 09:20:08',0),(18,'965241334@qq.com','商家','2020-06-24 15:14:11',2),(19,'915477812@qq.com','商家','2020-06-24 20:38:25',2),(20,'1732427704@qq.com','普通用户','2020-06-24 20:51:57',0),(21,'1005131042@qq.com','普通用户','2020-06-27 16:13:30',0),(22,'1005131042@qq.com','商家','2020-06-27 16:31:43',2);

/*Table structure for table `userr` */

DROP TABLE IF EXISTS `userr`;

CREATE TABLE `userr` (
  `UserID` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户的账号',
  `Userpassword` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户的密码',
  `UserAddress` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户的电话号码',
  `UserTelephone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户的地址',
  `Userpower` int DEFAULT NULL COMMENT '用户的权限，0是普通用户，1是VIP',
  PRIMARY KEY (`UserID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `userr` */

insert  into `userr`(`UserID`,`Userpassword`,`UserAddress`,`UserTelephone`,`Userpower`) values ('009','$2a$10$yYd3eOQjVLE45QjUA1L2Qeodbj62/ZPPsQAvKZP0hKQyyUxCsh5bi','未评论','123456',NULL),('1005131042@qq.com','$2a$10$9LmbPRgpxjLfBWx2Hh.dQujRJ0cv0QxnbyVFjjf1V2Z3ojoVEqgEC','未填写','10086',NULL),('111','$2a$10$XuVBbg27fj4DVaA1SStZ7eMCEVeEtHf3ptFplSzRFa8CIyUUb2wzK','未评论','111',NULL),('123','234','空','132',NULL),('123333','1122222','未填写','12131313',NULL),('1732427704@qq.com','$2a$10$cws5dxlp4rWFpRlU1oYrpevySlpRnelHgpjlozGi.rpcHi7feB4CC','未填写','1111111',NULL),('1846682140@qq.com','$2a$10$PGQsnTCkUGFSvFiVd17cG.E25JLv5syYa3UMfqlFtB7oBNVnctTxK','未评论','19927523092',NULL),('213123','$2a$10$Kpc/EAP0yocIdbetjePaDuLwyEh5vSfs6Rcg/KLdIziQykdjhlYLy','未评论','10086',NULL),('667','$2a$10$l7DcLXytbD8obYL7oiqiSu6jRKWxXEOeEQ86jPDyXSQqAfvoCW1Y.','未评论','1234567',NULL),('669','$2a$10$gUaj1/AHzNyn03AeSL8xaO3mR6u5ODn1/ms7mohO4mE0DyAxBG2tG','未评论','123456',NULL),('711','$2a$10$/kGS5RKYEiPI1qbxonEGmOOLtLigek9/k033ZQGZrOuluubFKtFHS','未评论','3433',NULL),('888','$2a$10$DPcgNA5lEXuhPnECV.FK/e/k9c8VgIFuKIMO7O58c4f54V7tgodgO','未评论','88888',NULL),('999','$2a$10$tCDWBd3K0VfIpsX2/9Tj.eqxZp/wUqKGmtg7WPpEjvUZMmLH1TZXq','未评论','999999',NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
