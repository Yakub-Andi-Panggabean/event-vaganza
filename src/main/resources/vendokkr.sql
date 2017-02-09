/*
SQLyog Ultimate v10.42 
MySQL - 5.7.16-log : Database - kado
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`kado` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `kado`;

/*Table structure for table `admin_roles` */

DROP TABLE IF EXISTS `admin_roles`;

CREATE TABLE `admin_roles` (
  `auth_authority` varchar(30) DEFAULT NULL,
  `user_name` varchar(50) NOT NULL,
  PRIMARY KEY (`user_name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `admin_roles` */

insert  into `admin_roles`(`auth_authority`,`user_name`) values ('ROLE_ADMIN','agus');

/*Table structure for table `booking_transaction` */

DROP TABLE IF EXISTS `booking_transaction`;

CREATE TABLE `booking_transaction` (
  `transaction_id` varchar(20) NOT NULL,
  `transaction_date` varchar(8) DEFAULT NULL,
  `transaction_time` varchar(6) DEFAULT NULL,
  `user_id` varchar(10) DEFAULT NULL,
  `vendor_id` varchar(10) DEFAULT NULL,
  `event_id` varchar(10) DEFAULT NULL,
  `date_booking` varchar(8) DEFAULT NULL,
  `time_booking` varchar(6) DEFAULT NULL,
  `status_transaction` char(1) DEFAULT NULL,
  `status_payment` char(1) DEFAULT NULL,
  `price_all` int(11) DEFAULT NULL,
  `methode_payment` char(1) DEFAULT NULL,
  `vendor_type` char(3) DEFAULT NULL,
  `price_payment` int(11) DEFAULT NULL,
  PRIMARY KEY (`transaction_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `booking_transaction` */

/*Table structure for table `global_sequences` */

DROP TABLE IF EXISTS `global_sequences`;

CREATE TABLE `global_sequences` (
  `SEQ_ID` varchar(50) NOT NULL COMMENT 'Sequence ID',
  `SEQ_CURRVALUE` bigint(11) NOT NULL COMMENT 'Current Value',
  `SEQ_INTERVAL` int(11) NOT NULL COMMENT 'Interval; Usually 1',
  `SEQ_NEXTVALUE` bigint(11) NOT NULL COMMENT 'Next value',
  PRIMARY KEY (`SEQ_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `global_sequences` */

insert  into `global_sequences`(`SEQ_ID`,`SEQ_CURRVALUE`,`SEQ_INTERVAL`,`SEQ_NEXTVALUE`) values ('INVOICE_ID',1000000000,1,1000000001),('PACKAGE_ID',1000000009,1,1000000010),('TRANSACTION_ID',100000000000000,1,100000000000001),('USER_ID',1000000044,1,1000000045),('VENDOR_ID',5000000942,1,5000000943),('VENUE_ID',1000000006,1,1000000007);

/*Table structure for table `invoice_user` */

DROP TABLE IF EXISTS `invoice_user`;

CREATE TABLE `invoice_user` (
  `no_invoice` varchar(10) NOT NULL,
  `date_booking` varchar(8) DEFAULT NULL,
  `time_booking` varchar(6) DEFAULT NULL,
  `name_booking` varchar(50) DEFAULT NULL,
  `event_type` char(2) DEFAULT NULL,
  `event_place` varchar(50) DEFAULT NULL,
  `address_booking` varchar(100) DEFAULT NULL,
  `style_event` varchar(10) DEFAULT NULL,
  `type_decoration` varchar(20) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `total_price` int(11) DEFAULT NULL,
  `transaction_id` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`no_invoice`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `invoice_user` */

/*Table structure for table `need_confirmation` */

DROP TABLE IF EXISTS `need_confirmation`;

CREATE TABLE `need_confirmation` (
  `transaction_id` varchar(20) NOT NULL,
  `status` char(1) DEFAULT NULL,
  PRIMARY KEY (`transaction_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `need_confirmation` */

/*Table structure for table `package_city` */

DROP TABLE IF EXISTS `package_city`;

CREATE TABLE `package_city` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(60) NOT NULL,
  `description` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `package_city` */

insert  into `package_city`(`id`,`name`,`description`) values (1,'Jakarta','the capital city of indonesia'),(2,'London','the capital city of great britain'),(3,'New York','bussiness city'),(4,'Hongkong','bussiness city');

/*Table structure for table `package_vendor` */

DROP TABLE IF EXISTS `package_vendor`;

CREATE TABLE `package_vendor` (
  `vendor_id` varchar(10) DEFAULT NULL,
  `package_id` varchar(10) DEFAULT NULL,
  `package_name` varchar(50) DEFAULT NULL,
  `package_capacity` int(11) DEFAULT NULL,
  `package_desc` varchar(300) DEFAULT NULL,
  `package_category` varchar(20) DEFAULT NULL,
  `package_style` varchar(20) DEFAULT NULL,
  `package_price` int(11) DEFAULT NULL,
  `package_img` varchar(500) DEFAULT NULL,
  `package_promo` varchar(100) DEFAULT NULL,
  `discount_rate` int(11) DEFAULT NULL,
  `vendor_type` char(3) DEFAULT NULL,
  `minimum_payment` int(11) DEFAULT NULL,
  `time_package` varchar(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `package_vendor` */

insert  into `package_vendor`(`vendor_id`,`package_id`,`package_name`,`package_capacity`,`package_desc`,`package_category`,`package_style`,`package_price`,`package_img`,`package_promo`,`discount_rate`,`vendor_type`,`minimum_payment`,`time_package`) values ('5000000160','70000001','Wedding Vintage Style',200,'is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s','020','vintage',60000000,'http://southernweddings.com/wp-content/uploads/2014/02/southern-wedding-foot-washing.jpg,http://bridalguide.com/sites/default/files/blog-images/real-brides-speak-out/angelica-posts/creative-wedding-programs/1-chalkboard-sign-Wedding-Program.jpg,http://junebugweddings.com/wedding-blog/wp-content/uploads/2016/01/Gorgeous-Irish-Countryside-Wedding-at-Millbroo-Lodge-31-of-40-600x400.jpg',NULL,NULL,'020',10000000,'6'),('5000000160','70000002','Wedding Indonesian Style',3000,'is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s','020','Indonesian',80000000,'http://images.weddingku.com/images/upload/products/images/52033_b3Hmh3.jpg,http://images.weddingku.com/images/upload/products/images/477_BldLi5.jpg,http://images.weddingku.com/images/upload/products/images/49337_OI3m47.jpg',NULL,NULL,'020',11000000,'8'),('5000000160','70000003','Wedding European Style',2000,'is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s','020','European',50000000,'https://s-media-cache-ak0.pinimg.com/736x/11/89/6c/11896cff7fe12eceaa7c7d72f1b0c2ff.jpg,https://s-media-cache-ak0.pinimg.com/736x/0b/75/94/0b7594b4dd7ae8af13728998641a0792.jpg,https://s-media-cache-ak0.pinimg.com/736x/af/6a/b1/af6ab12915e18aa356413e095414884e.jpg',NULL,NULL,'020',10000000,'4'),('5000000160','70000004','Wedding Bohemian Style',4000,'is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s','020','Bohemian',40000000,'http://www.grainsofearth.org/wp-content/uploads/2013/03/83.jpg,https://s-media-cache-ak0.pinimg.com/736x/12/2d/9c/122d9ca9ba7915276e9c1427572c3131.jpg,https://s-media-cache-ak0.pinimg.com/736x/54/c5/cc/54c5ccef33cb9d642a3b8f6578070743.jpg',NULL,NULL,'020',20000000,'5'),('5000000160','70000005','Wedding Chinese Style',2000,'is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s','020','Chinese',20000000,'https://s-media-cache-ak0.pinimg.com/736x/bd/4a/be/bd4abebb8c1ae7154df5ec35e002716a.jpg,https://s-media-cache-ak0.pinimg.com/originals/2d/43/d5/2d43d512a4da421666fd74ff440a692a.jpg,https://s-media-cache-ak0.pinimg.com/736x/c3/cc/ca/c3cccabd82855c4ed6c7ad943477f7f9.jpg',NULL,NULL,'020',5000000,'2'),('5000000160','70000006','Wedding Indian Style',300,'is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s','020','Indian',20000000,'https://s-media-cache-ak0.pinimg.com/736x/6c/a5/2f/6ca52fae700f3e790bc7edeb5c106aff.jpg,http://aacreation.com/wp-content/uploads/2011/05/Indian-Wedding-Phere.jpg,https://d397bfy4gvgcdm.cloudfront.net/74574-Katie_Darshan_Wedding-1007.jpeg',NULL,NULL,'020',2000000,'3'),('5000000160','70000007','Wedding Russian Style',200,'is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s','020','Russian',40000000,'https://s-media-cache-ak0.pinimg.com/736x/2a/cf/38/2acf38341cf3b52aa33ef03df3174593.jpg,https://s-media-cache-ak0.pinimg.com/736x/f9/18/ea/f918ea683ab5e15c9dc970aabe3efdeb.jpg,https://s-media-cache-ak0.pinimg.com/originals/85/4f/4d/854f4d44502762a30bc6729161f28f53.jpg',NULL,NULL,'020',20000000,'7'),('5000000160','70000008','Wedding Garden Decoration',4000,'is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s','031','Garden',20000000,'https://s-media-cache-ak0.pinimg.com/736x/c4/c9/5c/c4c95c2bff3ce4768aeceba1a1db26f8.jpg,https://s-media-cache-ak0.pinimg.com/736x/fe/44/cc/fe44cc2342a981d72178a032acd62498.jpg,https://s-media-cache-ak0.pinimg.com/736x/1c/c6/85/1cc6856b4814827e214054f986e4a6c9.jpg',NULL,NULL,'031',20000000,'5'),('5000000160','70000009','Catering Wedding',3000,'is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s','011','Indonesian',15000000,'https://wwcdn.weddingwire.com/vendor/320001_325000/322229/thumbnails/600x600_1461348803695-happy-catering-0021.jpg,https://s-media-cache-ak0.pinimg.com/736x/6f/de/39/6fde39c64a0b558a15dec8890e40fd79.jpg,https://wwcdn.weddingwire.com/vendor/65001_70000/66593/thumbnails/600x600_1438903415917-summiteventcateringholiday2011-4.jpg',NULL,NULL,'011',5000000,'3'),('5000000160','10000010','Japanese Catering',200,'is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s','011','Japanese',54000000,'https://s-media-cache-ak0.pinimg.com/736x/05/f6/b5/05f6b52b6cdfac45db2b3a20dbe4f824.jpg,https://www.hkcec.com/sites/default/files/imce/7%20F1%20Beef_0.jpg,https://s-media-cache-ak0.pinimg.com/736x/5c/db/cd/5cdbcdde79b5c6dd34da8966e49d7432.jpg\\',NULL,NULL,'011',5000000,'2'),('5000000160','10000011','Japanese Catering 2',200,'is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s','011','Japanese',54000000,'https://s-media-cache-ak0.pinimg.com/736x/05/f6/b5/05f6b52b6cdfac45db2b3a20dbe4f824.jpg,https://www.hkcec.com/sites/default/files/imce/7%20F1%20Beef_0.jpg,https://s-media-cache-ak0.pinimg.com/736x/5c/db/cd/5cdbcdde79b5c6dd34da8966e49d7432.jpg\\',NULL,NULL,'011',5000000,'2'),('5000000160','10000012','Japanese Catering 3',200,'is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s','011','Japanese',54000000,'https://s-media-cache-ak0.pinimg.com/736x/05/f6/b5/05f6b52b6cdfac45db2b3a20dbe4f824.jpg,https://www.hkcec.com/sites/default/files/imce/7%20F1%20Beef_0.jpg,https://s-media-cache-ak0.pinimg.com/736x/5c/db/cd/5cdbcdde79b5c6dd34da8966e49d7432.jpg\\',NULL,NULL,'011',5000000,'2'),('5000000160','10000013','Japanese Catering 4',200,'is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s','011','Japanese',54000000,'https://s-media-cache-ak0.pinimg.com/736x/05/f6/b5/05f6b52b6cdfac45db2b3a20dbe4f824.jpg,https://www.hkcec.com/sites/default/files/imce/7%20F1%20Beef_0.jpg,https://s-media-cache-ak0.pinimg.com/736x/5c/db/cd/5cdbcdde79b5c6dd34da8966e49d7432.jpg\\',NULL,NULL,'011',5000000,'2'),('5000000160','10000014','Japanese Catering 5',200,'is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s','011','Japanese',54000000,'https://s-media-cache-ak0.pinimg.com/736x/05/f6/b5/05f6b52b6cdfac45db2b3a20dbe4f824.jpg,https://www.hkcec.com/sites/default/files/imce/7%20F1%20Beef_0.jpg,https://s-media-cache-ak0.pinimg.com/736x/5c/db/cd/5cdbcdde79b5c6dd34da8966e49d7432.jpg\\',NULL,NULL,'011',5000000,'2'),('5000000160','10000015','Japanese Catering 6',200,'is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s','011','Japanese',54000000,'https://s-media-cache-ak0.pinimg.com/736x/05/f6/b5/05f6b52b6cdfac45db2b3a20dbe4f824.jpg,https://www.hkcec.com/sites/default/files/imce/7%20F1%20Beef_0.jpg,https://s-media-cache-ak0.pinimg.com/736x/5c/db/cd/5cdbcdde79b5c6dd34da8966e49d7432.jpg\\',NULL,NULL,'011',5000000,'2'),('5000000160','10000016','Japanese Catering 7',200,'is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s','011','Japanese',54000000,'https://s-media-cache-ak0.pinimg.com/736x/05/f6/b5/05f6b52b6cdfac45db2b3a20dbe4f824.jpg,https://www.hkcec.com/sites/default/files/imce/7%20F1%20Beef_0.jpg,https://s-media-cache-ak0.pinimg.com/736x/5c/db/cd/5cdbcdde79b5c6dd34da8966e49d7432.jpg\\',NULL,NULL,'011',5000000,'2'),('5000000160','10000018','Japanese Catering 8',200,'is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s','011','Japanese',54000000,'https://s-media-cache-ak0.pinimg.com/736x/05/f6/b5/05f6b52b6cdfac45db2b3a20dbe4f824.jpg,https://www.hkcec.com/sites/default/files/imce/7%20F1%20Beef_0.jpg,https://s-media-cache-ak0.pinimg.com/736x/5c/db/cd/5cdbcdde79b5c6dd34da8966e49d7432.jpg\\',NULL,NULL,'011',5000000,'2'),('5000000160','10000019','Japanese Catering 9',200,'is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s','011','Japanese',54000000,'https://s-media-cache-ak0.pinimg.com/736x/05/f6/b5/05f6b52b6cdfac45db2b3a20dbe4f824.jpg,https://www.hkcec.com/sites/default/files/imce/7%20F1%20Beef_0.jpg,https://s-media-cache-ak0.pinimg.com/736x/5c/db/cd/5cdbcdde79b5c6dd34da8966e49d7432.jpg\\',NULL,NULL,'011',5000000,'2'),('5000000160','10000020','Japanese Catering 10',200,'is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s','011','Japanese',54000000,'https://s-media-cache-ak0.pinimg.com/736x/05/f6/b5/05f6b52b6cdfac45db2b3a20dbe4f824.jpg,https://www.hkcec.com/sites/default/files/imce/7%20F1%20Beef_0.jpg,https://s-media-cache-ak0.pinimg.com/736x/5c/db/cd/5cdbcdde79b5c6dd34da8966e49d7432.jpg\\',NULL,NULL,'011',5000000,'2'),('5000000160','10000021','Japanese Catering 11',200,'is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s','011','Japanese',54000000,'https://s-media-cache-ak0.pinimg.com/736x/05/f6/b5/05f6b52b6cdfac45db2b3a20dbe4f824.jpg,https://www.hkcec.com/sites/default/files/imce/7%20F1%20Beef_0.jpg,https://s-media-cache-ak0.pinimg.com/736x/5c/db/cd/5cdbcdde79b5c6dd34da8966e49d7432.jpg\\',NULL,NULL,'011',5000000,'2'),('5000000160','10000022','Japanese Catering 12',200,'is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s','011','Japanese',54000000,'https://s-media-cache-ak0.pinimg.com/736x/05/f6/b5/05f6b52b6cdfac45db2b3a20dbe4f824.jpg,https://www.hkcec.com/sites/default/files/imce/7%20F1%20Beef_0.jpg,https://s-media-cache-ak0.pinimg.com/736x/5c/db/cd/5cdbcdde79b5c6dd34da8966e49d7432.jpg\\',NULL,NULL,'011',5000000,'2'),('5000000160','10000023','Japanese Catering 13',200,'is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s','011','Japanese',54000000,'https://s-media-cache-ak0.pinimg.com/736x/05/f6/b5/05f6b52b6cdfac45db2b3a20dbe4f824.jpg,https://www.hkcec.com/sites/default/files/imce/7%20F1%20Beef_0.jpg,https://s-media-cache-ak0.pinimg.com/736x/5c/db/cd/5cdbcdde79b5c6dd34da8966e49d7432.jpg\\',NULL,NULL,'011',5000000,'2'),('5000000160','10000024','Japanese Catering 14',200,'is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s','011','Japanese',54000000,'https://s-media-cache-ak0.pinimg.com/736x/05/f6/b5/05f6b52b6cdfac45db2b3a20dbe4f824.jpg,https://www.hkcec.com/sites/default/files/imce/7%20F1%20Beef_0.jpg,https://s-media-cache-ak0.pinimg.com/736x/5c/db/cd/5cdbcdde79b5c6dd34da8966e49d7432.jpg\\',NULL,NULL,'011',5000000,'2'),('5000000160','10000025','Japanese Catering 15',200,'is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s','011','Japanese',54000000,'https://s-media-cache-ak0.pinimg.com/736x/05/f6/b5/05f6b52b6cdfac45db2b3a20dbe4f824.jpg,https://www.hkcec.com/sites/default/files/imce/7%20F1%20Beef_0.jpg,https://s-media-cache-ak0.pinimg.com/736x/5c/db/cd/5cdbcdde79b5c6dd34da8966e49d7432.jpg\\',NULL,NULL,'011',5000000,'2'),('5000000160','10000026','Japanese Catering 16',200,'is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s','011','Japanese',54000000,'https://s-media-cache-ak0.pinimg.com/736x/05/f6/b5/05f6b52b6cdfac45db2b3a20dbe4f824.jpg,https://www.hkcec.com/sites/default/files/imce/7%20F1%20Beef_0.jpg,https://s-media-cache-ak0.pinimg.com/736x/5c/db/cd/5cdbcdde79b5c6dd34da8966e49d7432.jpg\\',NULL,NULL,'011',5000000,'2'),('5000000160','10000027','Japanese Catering 17',200,'is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s','011','Japanese',54000000,'https://s-media-cache-ak0.pinimg.com/736x/05/f6/b5/05f6b52b6cdfac45db2b3a20dbe4f824.jpg,https://www.hkcec.com/sites/default/files/imce/7%20F1%20Beef_0.jpg,https://s-media-cache-ak0.pinimg.com/736x/5c/db/cd/5cdbcdde79b5c6dd34da8966e49d7432.jpg\\',NULL,NULL,'011',5000000,'2'),('5000000160','10000028','Japanese Catering 18',200,'is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s','011','Japanese',54000000,'https://s-media-cache-ak0.pinimg.com/736x/05/f6/b5/05f6b52b6cdfac45db2b3a20dbe4f824.jpg,https://www.hkcec.com/sites/default/files/imce/7%20F1%20Beef_0.jpg,https://s-media-cache-ak0.pinimg.com/736x/5c/db/cd/5cdbcdde79b5c6dd34da8966e49d7432.jpg\\',NULL,NULL,'011',5000000,'2'),('5000000160','10000029','Japanese Catering 19',200,'is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s','011','Japanese',54000000,'https://s-media-cache-ak0.pinimg.com/736x/05/f6/b5/05f6b52b6cdfac45db2b3a20dbe4f824.jpg,https://www.hkcec.com/sites/default/files/imce/7%20F1%20Beef_0.jpg,https://s-media-cache-ak0.pinimg.com/736x/5c/db/cd/5cdbcdde79b5c6dd34da8966e49d7432.jpg\\',NULL,NULL,'011',5000000,'2');

/*Table structure for table `package_venue` */

DROP TABLE IF EXISTS `package_venue`;

CREATE TABLE `package_venue` (
  `vendor_id` varchar(10) DEFAULT NULL,
  `venue_id` varchar(10) NOT NULL,
  `venue_room` varchar(50) DEFAULT NULL,
  `room_capacity` varchar(5) DEFAULT NULL,
  `venue_package` varchar(1000) DEFAULT NULL,
  `venue_portofolio` varchar(1000) DEFAULT NULL,
  `venue_promo` varchar(100) DEFAULT NULL,
  `discount_rate` int(11) DEFAULT NULL,
  `rental_price` int(11) DEFAULT NULL,
  `time_rent` varchar(10) DEFAULT NULL,
  `city` varchar(20) DEFAULT NULL,
  `pax_price` int(11) DEFAULT NULL,
  `minimum_payment` int(11) DEFAULT NULL,
  `vendor_type` char(3) DEFAULT NULL,
  `venue_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`venue_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `package_venue` */

insert  into `package_venue`(`vendor_id`,`venue_id`,`venue_room`,`room_capacity`,`venue_package`,`venue_portofolio`,`venue_promo`,`discount_rate`,`rental_price`,`time_rent`,`city`,`pax_price`,`minimum_payment`,`vendor_type`,`venue_name`) values ('5000000160','80000001','Ball Room X','2500','There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don\'t look even slightly believable.','https://s-media-cache-ak0.pinimg.com/736x/2a/30/f7/2a30f76c4e5960ef8aa33a7362e51130.jpg,https://s-media-cache-ak0.pinimg.com/736x/74/a6/39/74a639ce6a983bd5f45e2a4bd919591f.jpg,http://cdn4.hotelopia.com/giata/bigger/40/407949/407949a_hb_a_001.jpg',NULL,0,45000000,'4','new york',300000,20000000,'000','Hilton Hotel'),('5000000160','80000002','Ball Room Z','3000','There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don\'t look even slightly believable.','https://i1.wp.com/six-and-seven.com/wp-content/uploads/2015/10/Colony-at-The-Ritz-Carlton-Millenia-Singapore-Dining-at-Colony.jpg?resize=400%2C600,https://i2.wp.com/thepointsguy.com/wp-content/uploads/2016/09/IMG-Ritz-Carlton-beach-night.jpg,http://www.trbimg.com/img-4fea2b32/turbine/ct-ae-0624-ritzcarlton-20120626-001/600',NULL,0,50000000,'2','london',230000,15000000,'000','Ritz Carlton Hotel'),('5000000160','80000003','Ball Room Y','4000','There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don\'t look even slightly believable.','http://letsgobucharest.ro/content/poze_locatii/20120606035011jw-marriott-bucharest-grand-hotel.jpg,http://3.bp.blogspot.com/-LCh31pHPtE8/Vqhp-sCu4rI/AAAAAAAADC4/8KvL25fy0NY/s1600/chef-preparing-ros-omelette-at-goan-food-fest-spice-kitchen-jw-marriott-senapati-bapat-road-pune.JPG,http://www.jagatreview.com/wp-content/uploads/2011/10/sushi-section-400x600.jpg',NULL,0,47500000,'7','hongkong',200000,25000000,'000','JW Marriot');

/*Table structure for table `session_app` */

DROP TABLE IF EXISTS `session_app`;

CREATE TABLE `session_app` (
  `user_id` varchar(10) NOT NULL,
  `access_token` varchar(50) DEFAULT NULL,
  `last_send_pass` varchar(8) DEFAULT NULL,
  `forgot_count` char(1) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `session_app` */

/*Table structure for table `user_cms` */

DROP TABLE IF EXISTS `user_cms`;

CREATE TABLE `user_cms` (
  `user_id` varchar(10) DEFAULT NULL,
  `user_password` varchar(64) DEFAULT NULL,
  `user_name` varchar(30) DEFAULT NULL,
  `full_name` varchar(30) DEFAULT NULL,
  `phone_number` varchar(15) DEFAULT NULL,
  `user_email` varchar(30) DEFAULT NULL,
  `user_status` char(1) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `user_cms` */

insert  into `user_cms`(`user_id`,`user_password`,`user_name`,`full_name`,`phone_number`,`user_email`,`user_status`) values ('1000000001','fdf169558242ee051cca1479770ebac3','agus','agus','085215196070','agus.stn06@gmail.com','1');

/*Table structure for table `user_web` */

DROP TABLE IF EXISTS `user_web`;

CREATE TABLE `user_web` (
  `user_id` varchar(10) NOT NULL,
  `user_password` varchar(64) DEFAULT NULL,
  `vendor_id` varchar(10) DEFAULT NULL,
  `user_name` varchar(50) DEFAULT NULL,
  `user_address` varchar(100) DEFAULT NULL,
  `user_zip` varchar(20) DEFAULT NULL,
  `user_hp` varchar(15) DEFAULT NULL,
  `user_telp` varchar(15) DEFAULT NULL,
  `user_email` varchar(50) DEFAULT NULL,
  `user_status` char(1) DEFAULT '0',
  `full_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `user_web` */

insert  into `user_web`(`user_id`,`user_password`,`vendor_id`,`user_name`,`user_address`,`user_zip`,`user_hp`,`user_telp`,`user_email`,`user_status`,`full_name`) values ('1000000001','0433a62564b3e81fa9e9d111ee89b71b87e238bc42c7952597a76a5c5f088205',NULL,'yakub andi panggabean','jln mampang 16 no 1','30118','081213741988','021345678','yakub.jobs@gmail.com','0',NULL),('1000000003','0433a62564b3e81fa9e9d111ee89b71b87e238bc42c7952597a76a5c5f088205',NULL,'Freddie Mercury','asfdfafdsafdsafdfdsafdf','30998','0812384773487','12873918273','mercury@m.com','0',NULL),('1000000002','86c58c9a4c0ab7403d6531b72b1c8bea015b27cd02e0a25c648bb4c3e9e0bb40',NULL,'John Lennon','asdfsafdsfdsafsf','30118','081213741988','021345678','yakub.jobs1@gmail.com','0',NULL);

/*Table structure for table `vendor` */

DROP TABLE IF EXISTS `vendor`;

CREATE TABLE `vendor` (
  `vendor_type` char(3) NOT NULL,
  `vendor_id` varchar(10) NOT NULL,
  `user_id` varchar(10) DEFAULT NULL,
  `vendor_name` varchar(50) DEFAULT NULL,
  `vendor_desc` longtext,
  `vendor_address` varchar(100) DEFAULT NULL,
  `vendor_hp` varchar(15) DEFAULT NULL,
  `vendor_telp` varchar(15) DEFAULT NULL,
  `vendor_pic` varchar(30) DEFAULT NULL,
  `vendor_email` varchar(50) DEFAULT NULL,
  `vendor_status` char(1) CHARACTER SET latin1 DEFAULT '0',
  PRIMARY KEY (`vendor_type`,`vendor_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `vendor` */

insert  into `vendor`(`vendor_type`,`vendor_id`,`user_id`,`vendor_name`,`vendor_desc`,`vendor_address`,`vendor_hp`,`vendor_telp`,`vendor_pic`,`vendor_email`,`vendor_status`) values ('020','5000000160','1000000001','flat studio','safdsfdsfdsf','jakarta','081213567866','765437','afsfsafsafdsfdsfa','asfdsafd@dfaf.com','0'),('031','5000000160','1000000001','flat studio','safdsfdsfdsf','jakarta','081213567866','765437','afsfsafsafdsfdsfa','asfdsafd@dfaf.com','0'),('000','5000000160','1000000001','flat studio','safdsfdsfdsf','jakarta','081213567866','765437','afsfsafsafdsfdsfa','asfdsafd@dfaf.com','0'),('011','5000000160','1000000001','flat studio','safdsfdsfdsf','jakarta','081213567866','765437','afsfsafsafdsfdsfa','asfdsafd@dfaf.com','0');

/*Table structure for table `vendor_description` */

DROP TABLE IF EXISTS `vendor_description`;

CREATE TABLE `vendor_description` (
  `vendor_type` char(3) NOT NULL,
  `vendor_description` varchar(100) DEFAULT NULL,
  `vendor_type_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`vendor_type`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `vendor_description` */

insert  into `vendor_description`(`vendor_type`,`vendor_description`,`vendor_type_name`) values ('000','Parent for Venue','Venue'),('010','Parent for Catering','Catering'),('030','Parent for Decoration','Decoration'),('020','Package','Package'),('001','venue for meeting','Meeting'),('011','Catering Weeding','Catering Weeding'),('012','Catering Meeting','Catering Meeting'),('031','Decoration weeding','Weeding Decoration'),('032','Decoration for Birthday','Birthday Decoration'),('040','Entertaiment parents','Entertaiment '),('050','Transport Parents','Transport '),('060','Photography parents','Photography '),('070','Make Up Parents','Make Up'),('080','Event Organizer Parent','Event Organizer'),('090','Buy Equipment Parent','Buy Equipment '),('100','Rent Equipment Parents','Rent Equipment '),('110','Others parents','Others '),('041','MUSICIAN','MUSICIAN'),('042','MASTER OF CEREMONY','MASTER OF CEREMONY'),('043','DANCER','DANCER'),('051','BMW','BMW'),('052','MERCEDES','MERCEDES'),('053','BIG LUXURY CAR','BIG LUXURY CAR'),('061','Photography ','Photography '),('071','Make Up','Make Up'),('081','Event Organizer','Event Organizer'),('091','Buy Equipment','Buy Equipment'),('101','Rent Equipment ','Rent Equipment '),('111','Others ','Others ');

/* Function  structure for function  `F_GET_SEQ` */

/*!50003 DROP FUNCTION IF EXISTS `F_GET_SEQ` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` FUNCTION `F_GET_SEQ`(`PSEQID` VARCHAR(50)) RETURNS bigint(20)
    READS SQL DATA
BEGIN
	DECLARE VCURRVAL BIGINT;
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET VCURRVAL := 0;
	DECLARE CONTINUE HANDLER FOR SQLEXCEPTION SET VCURRVAL := -1;
		
	CALL P_GET_SEQ(PSEQID,@VRETURN);
	
	SELECT @VRETURN
	  INTO VCURRVAL;
	RETURN VCURRVAL;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `P_GET_SEQ` */

/*!50003 DROP PROCEDURE IF EXISTS  `P_GET_SEQ` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` PROCEDURE `P_GET_SEQ`(IN `PSEQID` VARCHAR(50), OUT `PRETURN` BIGINT)
BEGIN		DECLARE CONTINUE HANDLER FOR NOT FOUND SET PRETURN := 0;	DECLARE CONTINUE HANDLER FOR SQLEXCEPTION SET PRETURN := 0;				SELECT seq_nextvalue	   		  INTO PRETURN		  FROM global_sequences	 	 WHERE seq_id = PSEQID		   FOR UPDATE; 				UPDATE global_sequences		   SET seq_currvalue=seq_nextvalue,		       seq_nextvalue=seq_currvalue+seq_interval		 WHERE seq_id = PSEQID;END */$$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
