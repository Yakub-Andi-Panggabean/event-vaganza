-- phpMyAdmin SQL Dump
-- version 4.0.9
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Feb 14, 2017 at 02:07 AM
-- Server version: 5.6.14
-- PHP Version: 5.5.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `kado`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `P_GET_SEQ`(IN `PSEQID` VARCHAR(50), OUT `PRETURN` BIGINT)
BEGIN
	
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET PRETURN := 0;
	DECLARE CONTINUE HANDLER FOR SQLEXCEPTION SET PRETURN := 0;	
	
		SELECT seq_nextvalue	   
		  INTO PRETURN
		  FROM global_sequences
	 	 WHERE seq_id = PSEQID
		   FOR UPDATE; 
		
		UPDATE global_sequences
		   SET seq_currvalue=seq_nextvalue,
		       seq_nextvalue=seq_currvalue+seq_interval
		 WHERE seq_id = PSEQID;
END$$

--
-- Functions
--
CREATE DEFINER=`root`@`localhost` FUNCTION `F_GET_SEQ`(`PSEQID` VARCHAR(50)) RETURNS bigint(20)
BEGIN
	DECLARE VCURRVAL BIGINT;
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET VCURRVAL := 0;
	DECLARE CONTINUE HANDLER FOR SQLEXCEPTION SET VCURRVAL := -1;
		
	CALL P_GET_SEQ(PSEQID,@VRETURN);
	
	SELECT @VRETURN
	  INTO VCURRVAL;
	RETURN VCURRVAL;
    END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `admin_roles`
--

CREATE TABLE IF NOT EXISTS `admin_roles` (
  `auth_authority` varchar(30) DEFAULT NULL,
  `user_name` varchar(50) NOT NULL,
  PRIMARY KEY (`user_name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `admin_roles`
--

INSERT INTO `admin_roles` (`auth_authority`, `user_name`) VALUES
('ROLE_ADMIN', 'agus');

-- --------------------------------------------------------

--
-- Table structure for table `booking_transaction`
--

CREATE TABLE IF NOT EXISTS `booking_transaction` (
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

-- --------------------------------------------------------

--
-- Table structure for table `global_sequences`
--

CREATE TABLE IF NOT EXISTS `global_sequences` (
  `SEQ_ID` varchar(50) NOT NULL COMMENT 'Sequence ID',
  `SEQ_CURRVALUE` bigint(11) NOT NULL COMMENT 'Current Value',
  `SEQ_INTERVAL` int(11) NOT NULL COMMENT 'Interval; Usually 1',
  `SEQ_NEXTVALUE` bigint(11) NOT NULL COMMENT 'Next value',
  PRIMARY KEY (`SEQ_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `global_sequences`
--

INSERT INTO `global_sequences` (`SEQ_ID`, `SEQ_CURRVALUE`, `SEQ_INTERVAL`, `SEQ_NEXTVALUE`) VALUES
('INVOICE_ID', 1000000000, 1, 1000000001),
('PACKAGE_ID', 3000000021, 1, 3000000022),
('TRANSACTION_ID', 100000000000000, 1, 100000000000001),
('USER_ID', 1000000047, 1, 1000000048),
('VENDOR_ID', 5000000561, 1, 5000000562),
('VENUE_ID', 1000000016, 1, 1000000017);

-- --------------------------------------------------------

--
-- Table structure for table `invoice_user`
--

CREATE TABLE IF NOT EXISTS `invoice_user` (
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

-- --------------------------------------------------------

--
-- Table structure for table `need_confirmation`
--

CREATE TABLE IF NOT EXISTS `need_confirmation` (
  `transaction_id` varchar(20) NOT NULL,
  `status` char(1) DEFAULT NULL,
  PRIMARY KEY (`transaction_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `package_city`
--

CREATE TABLE IF NOT EXISTS `package_city` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(60) NOT NULL,
  `description` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `package_city`
--

INSERT INTO `package_city` (`id`, `name`, `description`) VALUES
(1, 'Jakarta', 'jakarta '),
(2, 'London', 'london'),
(3, 'NewYork', 'new york'),
(4, 'Hongkong', 'hongkong');

-- --------------------------------------------------------

--
-- Table structure for table `package_vendor`
--

CREATE TABLE IF NOT EXISTS `package_vendor` (
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
  `discount_rate` int(11) DEFAULT '0',
  `vendor_type` char(3) DEFAULT NULL,
  `minimum_payment` int(11) DEFAULT NULL,
  `time_package` varchar(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `package_vendor`
--

INSERT INTO `package_vendor` (`vendor_id`, `package_id`, `package_name`, `package_capacity`, `package_desc`, `package_category`, `package_style`, `package_price`, `package_img`, `package_promo`, `discount_rate`, `vendor_type`, `minimum_payment`, `time_package`) VALUES
('5000000550', '1000000010', 'Weeding', 500, 'Menu is ...... ', 'Nasional', 'Nasional', 45000000, '1000000010_1.png:', 'Free drink', 2, '011', 4500000, '5'),
('5000000552', '1000000011', 'Padang Decoration', 0, 'Decoration  is ......', 'Nasional', 'Padang', 15000000, '1000000011_1.png:', 'Free ..', 0, '031', 1500000, '10'),
('5000000554', '1000000012', 'Musik', 0, 'Musik buat dinikahan', 'Nasional', 'Weeding', 2000000, '1000000012_1.png:', 'No Promo', 0, '041', 200000, '10'),
('5000000544', '1000000013', 'BMW seri apa', 0, 'Mobil BMW ', 'Car', 'Car', 4000000, '1000000013_1.png:', '', 0, '051', 400000, '10'),
('5000000555', '1000000015', 'Weeding Photography', 0, 'Description ...... ', 'Photo', 'Photo', 3000000, '1000000015_1.png:', 'No promo', 0, '061', 300000, '10'),
('5000000556', '1000000016', 'Makeup Weeding', 0, 'Description is .....', 'Nasional', 'Nasional', 5000000, '1000000016_1.png:', '', 0, '071', 500000, '10'),
('5000000557', '1000000017', 'EO Murah', 0, 'Murah karena .... ', 'EO', 'EO', 12000000, '1000000017_1.jpg:', 'Free.... ', 0, '081', 1200000, '10'),
('5000000558', '1000000018', 'Buy Cake', 0, 'Cake is..... ', 'Cake', 'Cake', 4500000, '1000000018_1.jpg:', '', 0, '091', 450000, '0'),
('5000000559', '1000000019', 'Rent Gaun', 0, 'Gaun', 'Gaun', 'gaun', 500000, '1000000019_1.jpg:', '', 0, '101', 50000, '5'),
('5000000560', '1000000020', 'Other bro', 0, 'Ini buat yang lain', 'Others', 'Others', 300000, '1000000020_1.jpg:', '', 0, '111', 30000, '0'),
('0', '1000000021', 'Weeding Murah', 1000, 'Description menu .....', 'Internasional', 'Jepang', 15000000, '1000000021_1.png:1000000021_2.png:1000000021_3.png:1000000021_4.png:', 'free bucket', 3, '021', 1500000, '5');

-- --------------------------------------------------------

--
-- Table structure for table `package_vendor_detail`
--

CREATE TABLE IF NOT EXISTS `package_vendor_detail` (
  `package_id` varchar(10) DEFAULT NULL,
  `vendor_id` varchar(10) DEFAULT NULL,
  `vendor_type` char(3) CHARACTER SET latin1 DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `package_vendor_detail`
--

INSERT INTO `package_vendor_detail` (`package_id`, `vendor_id`, `vendor_type`) VALUES
('1000000021', '5000000546', '000'),
('1000000021', '5000000550', '010'),
('1000000021', '5000000553', '030'),
('1000000021', '5000000556', '070'),
('1000000021', '5000000555', '060'),
('1000000021', '5000000557', '080');

-- --------------------------------------------------------

--
-- Table structure for table `package_venue`
--

CREATE TABLE IF NOT EXISTS `package_venue` (
  `vendor_id` varchar(10) DEFAULT NULL,
  `venue_id` varchar(10) NOT NULL,
  `venue_room` varchar(50) DEFAULT NULL,
  `room_capacity` varchar(5) DEFAULT NULL,
  `venue_package` varchar(1000) DEFAULT NULL,
  `venue_portofolio` varchar(1000) DEFAULT NULL,
  `venue_promo` varchar(100) DEFAULT NULL,
  `discount_rate` int(11) DEFAULT '0',
  `rental_price` int(11) DEFAULT NULL,
  `time_rent` varchar(10) DEFAULT NULL,
  `city` varchar(20) DEFAULT NULL,
  `pax_price` int(11) DEFAULT NULL,
  `minimum_payment` int(11) DEFAULT NULL,
  `vendor_type` char(3) DEFAULT NULL,
  `venue_name` varchar(50) DEFAULT NULL,
  `venue_address` varchar(100) NOT NULL,
  PRIMARY KEY (`venue_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `package_venue`
--

INSERT INTO `package_venue` (`vendor_id`, `venue_id`, `venue_room`, `room_capacity`, `venue_package`, `venue_portofolio`, `venue_promo`, `discount_rate`, `rental_price`, `time_rent`, `city`, `pax_price`, `minimum_payment`, `vendor_type`, `venue_name`, `venue_address`) VALUES
('5000000549', '1000000014', 'Hall', '500', 'Meeting', '1000000014_1.png:1000000014_2.png:', 'Free gift', 5, 25000000, '5', 'Jakarta', 2500000, 10000000, '001', 'Angke Restaurant', 'Jakarta Utara'),
('5000000561', '1000000016', 'bronze', '200', 'bronze', '1000000016_1.jpg:1000000016_2.jpg:1000000016_3.jpg:1000000016_4.jpg:', '0', 0, 150000, '3', 'Jakarta', 150000, 150000, '002', 'Take Bronze Wedding', 'jakarta'),
('5000000546', '1000000013', 'Ballrom', '1000', 'Weeding', '1000000013_1.png:1000000013_2.png:', 'Free Music', 5, 50000000, '5', 'Jakarta', 250000, 5000000, '002', 'Hotel Mulia ', 'Jakarta Selatan');

-- --------------------------------------------------------

--
-- Table structure for table `session_app`
--

CREATE TABLE IF NOT EXISTS `session_app` (
  `user_id` varchar(10) NOT NULL,
  `access_token` varchar(50) DEFAULT NULL,
  `last_send_pass` varchar(8) DEFAULT NULL,
  `forgot_count` char(1) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `session_app`
--

INSERT INTO `session_app` (`user_id`, `access_token`, `last_send_pass`, `forgot_count`) VALUES
('0000000000', '9a0c4257-c893-453b-ba21-157ec3dc4e1e', '20170201', '0');

-- --------------------------------------------------------

--
-- Table structure for table `user_cms`
--

CREATE TABLE IF NOT EXISTS `user_cms` (
  `user_id` varchar(10) DEFAULT NULL,
  `user_password` varchar(64) DEFAULT NULL,
  `user_name` varchar(30) DEFAULT NULL,
  `full_name` varchar(30) DEFAULT NULL,
  `phone_number` varchar(15) DEFAULT NULL,
  `user_email` varchar(30) DEFAULT NULL,
  `user_status` char(1) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user_cms`
--

INSERT INTO `user_cms` (`user_id`, `user_password`, `user_name`, `full_name`, `phone_number`, `user_email`, `user_status`) VALUES
('1000000001', 'fdf169558242ee051cca1479770ebac3', 'agus', 'agus', '085215196070', 'agus.stn06@gmail.com', '1');

-- --------------------------------------------------------

--
-- Table structure for table `user_web`
--

CREATE TABLE IF NOT EXISTS `user_web` (
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

--
-- Dumping data for table `user_web`
--

INSERT INTO `user_web` (`user_id`, `user_password`, `vendor_id`, `user_name`, `user_address`, `user_zip`, `user_hp`, `user_telp`, `user_email`, `user_status`, `full_name`) VALUES
('1000000001', '0433a62564b3e81fa9e9d111ee89b71b87e238bc42c7952597a76a5c5f088205', NULL, 'yakub andi panggabean', 'jln mampang 16 no 1', '30118', '081213741988', '021345678', 'yakub.jobs@gmail.com', '0', NULL),
('1000000003', '0433a62564b3e81fa9e9d111ee89b71b87e238bc42c7952597a76a5c5f088205', NULL, 'Freddie Mercury', 'asfdfafdsafdsafdfdsafdf', '30998', '0812384773487', '12873918273', 'mercury@m.com', '0', NULL),
('1000000002', '86c58c9a4c0ab7403d6531b72b1c8bea015b27cd02e0a25c648bb4c3e9e0bb40', NULL, 'John Lennon', 'asdfsafdsfdsafsf', '30118', '081213741988', '021345678', 'yakub.jobs1@gmail.com', '0', NULL),
('0000000000', '89e01536ac207279409d4de1e5253e01f4a1769e696db0d6062ca9b8f56767c8', '123', 'fery_dirgan', 'mampang, jakarta selatan', '', '087853321947', NULL, 'fery_dirgan@yahoo.com', '1', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `vendor`
--

CREATE TABLE IF NOT EXISTS `vendor` (
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
  `vendor_id_venue` varchar(10) NOT NULL,
  PRIMARY KEY (`vendor_type`,`vendor_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `vendor`
--

INSERT INTO `vendor` (`vendor_type`, `vendor_id`, `user_id`, `vendor_name`, `vendor_desc`, `vendor_address`, `vendor_hp`, `vendor_telp`, `vendor_pic`, `vendor_email`, `vendor_status`, `vendor_id_venue`) VALUES
('050', '5000000544', NULL, 'Toyota rent a car', 'penyewaan mobil khusus toyota', 'jakarta', '08900000000', '02178990000', 'agus', 'agus@gmail.com', '1', ''),
('010', '5000000550', NULL, 'Catering Bu Ida', 'Vendor Bu Ida catering', 'Jakarta ', '085212343555', '02167843990', 'Ida', 'ida@yahoo.com', '1', '5000000546'),
('050', '5000000545', NULL, 'Rent Car', 'Car BMW', 'jakarta', '082131321', '0021313', 'johan', 'johan@gmail.com', '1', ''),
('000', '5000000546', NULL, 'Hotel Mulia', 'hotel mulia description', 'jakarta', '09898989', '08900000', 'agus', 'agus@gmail.com', '1', ''),
('000', '5000000549', NULL, 'Angke Restaurant & Function Hall', 'Beberapa hal yang menjadi kelebihan Restoran Angke Kelapa Gading sehingga sangat patut untuk menjadi tempat pernikahan: -Bangunan baru berdiri dari 4 lantai, dengan total luas 6.000 m², dilengkapi dengan lift dan escalator, sehingga memudahkan pencapaian dari lantai bawah ke atas. -Plafon ruangan yang tinggi hingga mencapai 7 m, menyerupai plafon ballroom hotel berbintang, sehingga memberi kesan luas, megah dan elegan -Penutup lantai memakai karpet sehingga mampu menghadirkan suasa pesta seperti di Hotel Berbintang.  -Interior ruangan yang modern, unik ditunjang dengan Tata Cahaya dan Lampu- lampu gantung Kristal yang menambah romantis suasana pesta anda. -Dilayani oleh Staff yang berpengalaman dan nantinya mereka akan mampu berbahasa Mandarin dan Inggris. Banyak pilihan menu-menu baru khas Hakka yang unik. Harga yang terjangkau dan paket-paket menarik yang ditawarkan.', 'KELAPA GADING SQUARE Jl. Raya Boulevard Barat Jakarta Utara, 14240', '+6281511110933', '+622145866333', 'yunita', 'yunita@angke.com', '1', ''),
('010', '5000000551', NULL, 'Catering Bapak Ida', 'Vendor Catering Bapak Ida', 'Jakarta Barat', '0812345454544', '0214354533', 'Bapak Ida', 'bapak@yahoo.com', '1', '5000000546'),
('030', '5000000552', NULL, 'Decoration Weeding', 'Decoration Weeding', 'Jakarta Selatan', '08134321432', '02155345433', 'decor', 'decor@yahoo.com', '1', '5000000546'),
('030', '5000000553', NULL, 'Decor Mewah', 'Decor Mewah', 'Jakarta', '08890909000', '021343421', 'mewah', 'mewah@yahoo.com', '1', '5000000546'),
('040', '5000000554', NULL, 'Entertaint Vendor simple', 'Entertaint Vendor simple', 'Jakarta Barat', '08154623632', '0215453232', 'aji', 'aji@yahoo.com', '1', '5000000546'),
('060', '5000000555', NULL, 'Photography Vendor', 'Vendor Foto foto', 'Jakarta', '0852351233', '0214324214', 'foto', 'foto@yahoo.com', '1', '5000000546'),
('070', '5000000556', NULL, 'Makeup Vendor', 'MakeupVendor', 'Jakarta', '08123432535', '021151244', 'make', 'make@yahoo.com', '1', '5000000546'),
('080', '5000000557', NULL, 'EO Vendor', 'EO Vendor', 'Jakarta', '08123424241', '021545353', 'eo', 'eo@yahoo.com', '1', '5000000546'),
('090', '5000000558', NULL, 'Buy Vendor', 'Buy Vendor', 'Jakarta', '08123421533', '02143214', 'buy', 'buy@yahoo.com', '1', '5000000546'),
('100', '5000000559', NULL, 'Rent Vendor', 'Rent Vendor', 'Jakarta', '08125423643', '02145999778', 'rent', 'rent@yahoo.com', '1', '5000000546'),
('110', '5000000560', NULL, 'Others Vendor', 'Other Vendors', 'Jakarta', '0852154125', '0214153125', 'other', 'other@yahoo.com', '1', '5000000546'),
('0', '0', NULL, 'Internal', 'INTERNAL vendor', 'Jakarta', '02100', '081200', 'Internal', 'internal@yahoo.com', '1', ''),
('000', '5000000561', NULL, 'Takes Mansion & Hotel', 'Our Luxurious Function Rooms and Wedding Packages accommodate a function\n\nfor almost any size.', 'Jl. Taman Kebon Sirih I No. 3 - 4\nJakarta Pusat\n10250', '081218878361', '0213926111 ', 'Ria', 'adrian.ria1107@gmail.com', '1', '');

-- --------------------------------------------------------

--
-- Table structure for table `vendor_description`
--

CREATE TABLE IF NOT EXISTS `vendor_description` (
  `vendor_type` char(3) NOT NULL,
  `vendor_description` varchar(100) DEFAULT NULL,
  `vendor_type_name` varchar(50) DEFAULT NULL,
  `image` varchar(400) DEFAULT NULL,
  PRIMARY KEY (`vendor_type`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `vendor_description`
--

INSERT INTO `vendor_description` (`vendor_type`, `vendor_description`, `vendor_type_name`, `image`) VALUES
('000', 'Parent for Venue', 'All Venue', ''),
('010', 'Parent for Catering', 'All Catering', ''),
('030', 'Parent for Decoration', 'All Decoration', ''),
('020', 'Package', 'All Package', ''),
('001', 'venue for meeting', 'Meeting', ''),
('011', 'Catering Weeding', 'Catering Weeding', ''),
('012', 'Catering Meeting', 'Catering Meeting', ''),
('031', 'Decoration weeding', 'Weeding Decoration', ''),
('032', 'Decoration for Birthday', 'Birthday Decoration', ''),
('040', 'Entertaiment parents', 'All Entertaiment ', ''),
('050', 'Transport Parents', 'All Transport', ''),
('060', 'Photography parents', 'All Photography', ''),
('070', 'Make Up Parents', 'All Make Up', ''),
('080', 'Event Organizer Parent', 'All Event Organizer', ''),
('090', 'Buy Equipment Parent', 'All Buy Equipment', ''),
('100', 'Rent Equipment Parents', 'All Rent Equipment', ''),
('110', 'Others parents', 'All Others', ''),
('041', 'MUSICIAN', 'MUSICIAN', ''),
('042', 'MASTER OF CEREMONY', 'MASTER OF CEREMONY', ''),
('043', 'DANCER', 'DANCER', ''),
('051', 'BMW', 'BMW', ''),
('052', 'MERCEDES', 'MERCEDES', ''),
('053', 'BIG LUXURY CAR', 'BIG LUXURY CAR', ''),
('061', 'Photography ', 'Photography ', ''),
('071', 'Make Up', 'Make Up', ''),
('081', 'Event Organizer', 'Event Organizer', ''),
('091', 'Buy Equipment', 'Buy Equipment', ''),
('101', 'Rent Equipment ', 'Rent Equipment', ''),
('111', 'Others ', 'Others', ''),
('021', 'Weeding Internasional', 'Weeding Internasional', ''),
('022', 'Weeding Nasional', 'Weeding Nasional', ''),
('002', 'venue for wedding', 'Wedding', ''),
('003', 'venue for workshop', 'Workshop', ''),
('004', 'venue for birthday\r\n', 'Birthday', ''),
('0', 'Internal', 'Internal', '');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
