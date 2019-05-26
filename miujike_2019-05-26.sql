# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.22)
# Database: miujike
# Generation Time: 2019-05-26 15:27:03 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table miu_comment
# ------------------------------------------------------------

CREATE TABLE `miu_comment` (
  `comment_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `from_user_id` bigint(11) DEFAULT NULL,
  `target_type` int(1) DEFAULT NULL COMMENT '1--music  2--video. 3--comment',
  `content` varchar(100) DEFAULT NULL COMMENT '评论内容',
  `create_time` datetime DEFAULT NULL,
  `thumbs` int(8) NOT NULL DEFAULT '0' COMMENT '评论点赞数',
  `target_id` bigint(20) DEFAULT NULL COMMENT '被评论的music或者videoid或者commentid',
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table miu_fan
# ------------------------------------------------------------

CREATE TABLE `miu_fan` (
  `fan_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `from_user_id` bigint(11) DEFAULT NULL COMMENT '关注者',
  `to_user_id` bigint(11) DEFAULT NULL COMMENT '被关注者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`fan_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table miu_music
# ------------------------------------------------------------

CREATE TABLE `miu_music` (
  `music_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(11) DEFAULT NULL,
  `singer` varchar(20) DEFAULT NULL COMMENT '演唱者',
  `title` varchar(20) DEFAULT NULL COMMENT '标题',
  `detail` varchar(100) DEFAULT NULL COMMENT '描述',
  `music_url` varchar(256) DEFAULT NULL,
  `cover` varchar(256) DEFAULT NULL,
  `duration` int(8) DEFAULT NULL COMMENT '时长',
  `status` int(1) DEFAULT NULL COMMENT '0-审核中  -1--审核未通过 1--审核通过，已发布',
  `create_time` datetime DEFAULT NULL COMMENT '提交时间',
  `release_time` datetime DEFAULT NULL COMMENT '审核通过发布时间',
  `eggs` int(8) NOT NULL DEFAULT '0' COMMENT '获得鸡蛋数',
  `thumbs` int(8) NOT NULL DEFAULT '0' COMMENT '获得点赞数',
  `comments` int(8) NOT NULL DEFAULT '0' COMMENT '获得鸡蛋数',
  `original_singer` varchar(20) DEFAULT NULL COMMENT '原唱',
  `play_times` int(11) DEFAULT NULL COMMENT '播放次数',
  PRIMARY KEY (`music_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table miu_sign_log
# ------------------------------------------------------------

CREATE TABLE `miu_sign_log` (
  `sign_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '签到时间',
  `eggs_get` int(2) DEFAULT NULL COMMENT '本次签到获取的鸡蛋数',
  PRIMARY KEY (`sign_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table miu_thumb
# ------------------------------------------------------------

CREATE TABLE `miu_thumb` (
  `thumb_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `from_user_id` bigint(11) DEFAULT NULL COMMENT '点赞人的id',
  `target_type` int(1) DEFAULT NULL COMMENT '1--music  2--video. 3--comment',
  `target_id` bigint(20) DEFAULT NULL COMMENT '被点赞的music或者videoid或者commentid',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`thumb_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table miu_user
# ------------------------------------------------------------

CREATE TABLE `miu_user` (
  `user_id` bigint(11) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(30) DEFAULT NULL,
  `avatar_url` varchar(256) DEFAULT NULL,
  `country` varchar(30) DEFAULT NULL,
  `province` varchar(30) DEFAULT NULL,
  `city` varchar(30) DEFAULT NULL,
  `open_id` varchar(100) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `gender` int(1) DEFAULT NULL,
  `last_login_time` datetime DEFAULT NULL COMMENT '最近一次登陆时间',
  `eggs` int(8) NOT NULL DEFAULT '0' COMMENT '鸡蛋数',
  `thumbs` int(8) NOT NULL DEFAULT '0' COMMENT '获赞数',
  `last_sign_time` datetime DEFAULT NULL COMMENT '上次签到时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table miu_video
# ------------------------------------------------------------

CREATE TABLE `miu_video` (
  `video_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(11) DEFAULT NULL,
  `singer` varchar(20) DEFAULT NULL COMMENT '演唱者',
  `title` varchar(20) DEFAULT NULL COMMENT '标题',
  `detail` varchar(100) DEFAULT NULL COMMENT '描述',
  `video_url` varchar(256) DEFAULT NULL,
  `duration` int(6) DEFAULT NULL COMMENT '时长',
  `status` int(1) DEFAULT NULL COMMENT '0-审核中  -1--审核未通过 1--审核通过，已发布',
  `create_time` datetime DEFAULT NULL COMMENT '提交时间',
  `release_time` datetime DEFAULT NULL COMMENT '审核通过发布时间',
  `eggs` int(8) NOT NULL DEFAULT '0' COMMENT '获得鸡蛋数',
  `thumbs` int(8) NOT NULL DEFAULT '0' COMMENT '获得点赞数',
  `comments` int(8) NOT NULL DEFAULT '0' COMMENT '获得评论数',
  `original_singer` varchar(20) DEFAULT NULL COMMENT '原唱',
  `play_times` int(8) DEFAULT '0' COMMENT '播放次数',
  `cover` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`video_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;




/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
