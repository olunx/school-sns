/*
Navicat MySQL Data Transfer

Source Server         : loacalhost
Source Server Version : 50145
Source Host           : localhost:3306
Source Database       : schoolsns

Target Server Type    : MYSQL
Target Server Version : 50145
File Encoding         : 65001

Date: 2010-04-24 15:09:10
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `t_achievement`
-- ----------------------------
DROP TABLE IF EXISTS `t_achievement`;
CREATE TABLE `t_achievement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `peopleId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5675DFE425998C05` (`peopleId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_achievement
-- ----------------------------

-- ----------------------------
-- Table structure for `t_admin`
-- ----------------------------
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin` (
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9FF53FC41079C136` (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_admin
-- ----------------------------

-- ----------------------------
-- Table structure for `t_attendance`
-- ----------------------------
DROP TABLE IF EXISTS `t_attendance`;
CREATE TABLE `t_attendance` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `clerk` int(11) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `week` varchar(255) DEFAULT NULL,
  `day` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2E752794BCFCEA84` (`clerk`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_attendance
-- ----------------------------

-- ----------------------------
-- Table structure for `t_attendance_course`
-- ----------------------------
DROP TABLE IF EXISTS `t_attendance_course`;
CREATE TABLE `t_attendance_course` (
  `attendanceId` int(11) NOT NULL,
  `courseId` int(11) NOT NULL,
  PRIMARY KEY (`attendanceId`,`courseId`),
  KEY `FK40A1C726E4E5B41D` (`courseId`),
  KEY `FK40A1C726C5669839` (`attendanceId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_attendance_course
-- ----------------------------

-- ----------------------------
-- Table structure for `t_attendance_student`
-- ----------------------------
DROP TABLE IF EXISTS `t_attendance_student`;
CREATE TABLE `t_attendance_student` (
  `attendanceId` int(11) NOT NULL,
  `stuId` int(11) NOT NULL,
  PRIMARY KEY (`attendanceId`,`stuId`),
  KEY `FK2A7C7C90BDE2407E` (`stuId`),
  KEY `FK2A7C7C90C5669839` (`attendanceId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_attendance_student
-- ----------------------------

-- ----------------------------
-- Table structure for `t_classes`
-- ----------------------------
DROP TABLE IF EXISTS `t_classes`;
CREATE TABLE `t_classes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `intro` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `entryYear` int(11) DEFAULT NULL,
  `assistantId` int(11) DEFAULT NULL,
  `instituteId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKEE732B5BBE32BC4F` (`assistantId`),
  KEY `FKEE732B5BEB45D2F9` (`instituteId`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_classes
-- ----------------------------

-- ----------------------------
-- Table structure for `t_classfee`
-- ----------------------------
DROP TABLE IF EXISTS `t_classfee`;
CREATE TABLE `t_classfee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stuId` int(11) DEFAULT NULL,
  `fee` double DEFAULT NULL,
  `event` varchar(255) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `classId` int(11) DEFAULT NULL,
  `indexId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKDFF24279BDE2407E` (`stuId`),
  KEY `FKDFF242798D1B20AD` (`classId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_classfee
-- ----------------------------

-- ----------------------------
-- Table structure for `t_course`
-- ----------------------------
DROP TABLE IF EXISTS `t_course`;
CREATE TABLE `t_course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `startLesson` int(11) DEFAULT NULL,
  `endLesson` int(11) DEFAULT NULL,
  `whatDay` int(11) DEFAULT NULL,
  `year` int(11) DEFAULT NULL,
  `term` int(11) DEFAULT NULL,
  `classId` int(11) DEFAULT NULL,
  `indexId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK62BB32468D1B20AD` (`classId`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_course
-- ----------------------------

-- ----------------------------
-- Table structure for `t_duty`
-- ----------------------------
DROP TABLE IF EXISTS `t_duty`;
CREATE TABLE `t_duty` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `job` varchar(255) DEFAULT NULL,
  `peopleId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKCB5C1BC125998C05` (`peopleId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_duty
-- ----------------------------

-- ----------------------------
-- Table structure for `t_excel`
-- ----------------------------
DROP TABLE IF EXISTS `t_excel`;
CREATE TABLE `t_excel` (
  `id` int(11) NOT NULL,
  `peopleId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKA0368F2CF7D9A7E1` (`id`),
  KEY `FKA0368F2C25998C05` (`peopleId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_excel
-- ----------------------------

-- ----------------------------
-- Table structure for `t_feed`
-- ----------------------------
DROP TABLE IF EXISTS `t_feed`;
CREATE TABLE `t_feed` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `author` int(11) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `msgId` int(11) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKCB5CC689BCA6D5A6` (`author`)
) ENGINE=MyISAM AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_feed
-- ----------------------------

-- ----------------------------
-- Table structure for `t_goods`
-- ----------------------------
DROP TABLE IF EXISTS `t_goods`;
CREATE TABLE `t_goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `imageId` int(11) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `value` double DEFAULT NULL,
  `record` varchar(255) DEFAULT NULL,
  `gtId` int(11) DEFAULT NULL,
  `exchange` varchar(255) DEFAULT NULL,
  `hot` int(11) DEFAULT NULL,
  `airTime` datetime DEFAULT NULL,
  `peopleId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `imageId` (`imageId`),
  KEY `FKA04ED3CB69DC9205` (`imageId`),
  KEY `FKA04ED3CB25998C05` (`peopleId`),
  KEY `FKA04ED3CBDEA4E641` (`userId`),
  KEY `FKA04ED3CB73D156C` (`gtId`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_goods
-- ----------------------------

-- ----------------------------
-- Table structure for `t_goodstype`
-- ----------------------------
DROP TABLE IF EXISTS `t_goodstype`;
CREATE TABLE `t_goodstype` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_goodstype
-- ----------------------------

-- ----------------------------
-- Table structure for `t_group`
-- ----------------------------
DROP TABLE IF EXISTS `t_group`;
CREATE TABLE `t_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `intro` varchar(255) DEFAULT NULL,
  `picId` int(11) DEFAULT NULL,
  `works` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `admin` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `picId` (`picId`),
  KEY `FKA05032F4FE424A74` (`picId`),
  KEY `FKA05032F415FFB76A` (`admin`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_group
-- ----------------------------

-- ----------------------------
-- Table structure for `t_group_people`
-- ----------------------------
DROP TABLE IF EXISTS `t_group_people`;
CREATE TABLE `t_group_people` (
  `peopleId` int(11) NOT NULL,
  `groupId` int(11) NOT NULL,
  PRIMARY KEY (`groupId`,`peopleId`),
  KEY `FKC964823A94A734D` (`groupId`),
  KEY `FKC964823A25998C05` (`peopleId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_group_people
-- ----------------------------

-- ----------------------------
-- Table structure for `t_hobby`
-- ----------------------------
DROP TABLE IF EXISTS `t_hobby`;
CREATE TABLE `t_hobby` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `peopleId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKA05CBA4725998C05` (`peopleId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_hobby
-- ----------------------------

-- ----------------------------
-- Table structure for `t_image`
-- ----------------------------
DROP TABLE IF EXISTS `t_image`;
CREATE TABLE `t_image` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `oriFileName` varchar(255) DEFAULT NULL,
  `bigFileName` varchar(255) DEFAULT NULL,
  `bigFileUrl` varchar(255) DEFAULT NULL,
  `minFileName` varchar(255) DEFAULT NULL,
  `minFileUrl` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_image
-- ----------------------------

-- ----------------------------
-- Table structure for `t_institute`
-- ----------------------------
DROP TABLE IF EXISTS `t_institute`;
CREATE TABLE `t_institute` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `schoolId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2111B40AEC71388F` (`schoolId`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_institute
-- ----------------------------

-- ----------------------------
-- Table structure for `t_issue`
-- ----------------------------
DROP TABLE IF EXISTS `t_issue`;
CREATE TABLE `t_issue` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `value` double DEFAULT NULL,
  `issueId` int(11) DEFAULT NULL,
  `itId` int(11) DEFAULT NULL,
  `hot` int(11) DEFAULT NULL,
  `airTime` datetime DEFAULT NULL,
  `deadline` datetime DEFAULT NULL,
  `peopleId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `issueId` (`issueId`),
  KEY `FKA06CE54E3C26FE5B` (`issueId`),
  KEY `FKA06CE54E75B9CDD7` (`issueId`),
  KEY `FKA06CE54EBF21962D` (`itId`),
  KEY `FKA06CE54E25998C05` (`peopleId`),
  KEY `FKA06CE54EDEA4E641` (`userId`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_issue
-- ----------------------------

-- ----------------------------
-- Table structure for `t_issuetype`
-- ----------------------------
DROP TABLE IF EXISTS `t_issuetype`;
CREATE TABLE `t_issuetype` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `isleaf` bit(1) DEFAULT NULL,
  `itId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  KEY `FK29E591A8BF21962D` (`itId`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_issuetype
-- ----------------------------

-- ----------------------------
-- Table structure for `t_mail`
-- ----------------------------
DROP TABLE IF EXISTS `t_mail`;
CREATE TABLE `t_mail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mytype` varchar(255) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `sender` int(11) DEFAULT NULL,
  `receiver` int(11) DEFAULT NULL,
  `isreaded` bit(1) DEFAULT NULL,
  `istopic` bit(1) DEFAULT NULL,
  `hasreply` bit(1) DEFAULT NULL,
  `mailId` int(11) DEFAULT NULL,
  `indexId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKCB5FE6A21A1DFB55` (`mailId`),
  KEY `FKCB5FE6A2DA79CE50` (`sender`),
  KEY `FKCB5FE6A2E0459E0A` (`receiver`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_mail
-- ----------------------------

-- ----------------------------
-- Table structure for `t_people`
-- ----------------------------
DROP TABLE IF EXISTS `t_people`;
CREATE TABLE `t_people` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `permission` int(11) DEFAULT NULL,
  `imageId` int(11) DEFAULT NULL,
  `phoneNo` varchar(255) DEFAULT NULL,
  `qq` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `birthday` datetime DEFAULT NULL,
  `regTime` datetime DEFAULT NULL,
  `schoolId` int(11) DEFAULT NULL,
  `instituteId` int(11) DEFAULT NULL,
  `classesId` int(11) DEFAULT NULL,
  `dorm` varchar(255) DEFAULT NULL,
  `entryYear` int(11) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `activity` int(11) DEFAULT NULL,
  `lastlogin` datetime DEFAULT NULL,
  `point` bigint(20) DEFAULT NULL,
  `schooladminId` int(11) DEFAULT NULL,
  `indexId` int(11) DEFAULT NULL,
  `classId` int(11) DEFAULT NULL,
  `dutyId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `imageId` (`imageId`),
  KEY `FK785A84BABDD99D3` (`dutyId`),
  KEY `FK785A84BAEC71388F` (`schoolId`),
  KEY `FK785A84BAEB45D2F9` (`instituteId`),
  KEY `FK785A84BA69DC9205` (`imageId`),
  KEY `FK785A84BA8D1B20AD` (`classId`),
  KEY `FK785A84BA7585EFB6` (`schooladminId`),
  KEY `FK785A84BA59ADBBDB` (`classesId`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_people
-- ----------------------------

-- ----------------------------
-- Table structure for `t_province`
-- ----------------------------
DROP TABLE IF EXISTS `t_province`;
CREATE TABLE `t_province` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_province
-- ----------------------------

-- ----------------------------
-- Table structure for `t_school`
-- ----------------------------
DROP TABLE IF EXISTS `t_school`;
CREATE TABLE `t_school` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `imageId` int(11) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `provinceId` int(11) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `imageId` (`imageId`),
  KEY `FK7D59AD9F95A7A607` (`provinceId`),
  KEY `FK7D59AD9F69DC9205` (`imageId`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_school
-- ----------------------------

-- ----------------------------
-- Table structure for `t_score`
-- ----------------------------
DROP TABLE IF EXISTS `t_score`;
CREATE TABLE `t_score` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `studentId` int(11) DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `marks` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKA0F27B0741BE1EC5` (`studentId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_score
-- ----------------------------

-- ----------------------------
-- Table structure for `t_student`
-- ----------------------------
DROP TABLE IF EXISTS `t_student`;
CREATE TABLE `t_student` (
  `id` int(11) NOT NULL,
  `sno` varchar(255) DEFAULT NULL,
  `peopleId` int(11) DEFAULT NULL,
  `classesId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4B9075701079C136` (`id`),
  KEY `FK4B90757025998C05` (`peopleId`),
  KEY `FK4B90757059ADBBDB` (`classesId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_student
-- ----------------------------

-- ----------------------------
-- Table structure for `t_teacher`
-- ----------------------------
DROP TABLE IF EXISTS `t_teacher`;
CREATE TABLE `t_teacher` (
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK65C3BF171079C136` (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_teacher
-- ----------------------------

-- ----------------------------
-- Table structure for `t_topic`
-- ----------------------------
DROP TABLE IF EXISTS `t_topic`;
CREATE TABLE `t_topic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `author` int(11) DEFAULT NULL,
  `istopic` bit(1) DEFAULT NULL,
  `hasreply` bit(1) DEFAULT NULL,
  `classId` int(11) DEFAULT NULL,
  `indexId` int(11) DEFAULT NULL,
  `groupId` int(11) DEFAULT NULL,
  `topicId` int(11) DEFAULT NULL,
  `goodsId` int(11) DEFAULT NULL,
  `issueId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKA10609A4B49B34AD` (`topicId`),
  KEY `FKA10609A47520A981` (`issueId`),
  KEY `FKA10609A4BCA6D5A6` (`author`),
  KEY `FKA10609A4422DB3B` (`goodsId`),
  KEY `FKA10609A494A734D` (`groupId`),
  KEY `FKA10609A48D1B20AD` (`classId`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_topic
-- ----------------------------

-- ----------------------------
-- Table structure for `t_twitter`
-- ----------------------------
DROP TABLE IF EXISTS `t_twitter`;
CREATE TABLE `t_twitter` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `time` datetime DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `author` int(11) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `link` varchar(255) DEFAULT NULL,
  `imageId` int(11) DEFAULT NULL,
  `istopic` bit(1) DEFAULT NULL,
  `hasreply` bit(1) DEFAULT NULL,
  `twitterId` int(11) DEFAULT NULL,
  `indexId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `imageId` (`imageId`),
  KEY `FK84F39DA8BCA6D5A6` (`author`),
  KEY `FK84F39DA8E85B4135` (`twitterId`),
  KEY `FK84F39DA869DC9205` (`imageId`)
) ENGINE=MyISAM AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_twitter
-- ----------------------------

-- ----------------------------
-- Table structure for `t_visitor`
-- ----------------------------
DROP TABLE IF EXISTS `t_visitor`;
CREATE TABLE `t_visitor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `peopleId` int(11) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `schoolId` int(11) DEFAULT NULL,
  `indexId` int(11) DEFAULT NULL,
  `classId` int(11) DEFAULT NULL,
  `visitorId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKD76427A3EC71388F` (`schoolId`),
  KEY `FKD76427A38D1B20AD` (`classId`),
  KEY `FKD76427A325998C05` (`peopleId`),
  KEY `FKD76427A380909224` (`visitorId`)
) ENGINE=MyISAM AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_visitor
-- ----------------------------

-- ----------------------------
-- Table structure for `t_vote`
-- ----------------------------
DROP TABLE IF EXISTS `t_vote`;
CREATE TABLE `t_vote` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `author` int(11) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `summary` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `scope` int(11) DEFAULT NULL,
  `airTime` datetime DEFAULT NULL,
  `deadline` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKCB6433D5BCA6D5A6` (`author`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_vote
-- ----------------------------

-- ----------------------------
-- Table structure for `t_vote_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_vote_user`;
CREATE TABLE `t_vote_user` (
  `peopleId` int(11) NOT NULL,
  `voteId` int(11) NOT NULL,
  PRIMARY KEY (`voteId`,`peopleId`),
  KEY `FK2B2D8DF52A4814FB` (`voteId`),
  KEY `FK2B2D8DF525998C05` (`peopleId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_vote_user
-- ----------------------------

-- ----------------------------
-- Table structure for `t_voteitem`
-- ----------------------------
DROP TABLE IF EXISTS `t_voteitem`;
CREATE TABLE `t_voteitem` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `num` int(11) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `voteId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK53FDBC682A4814FB` (`voteId`)
) ENGINE=MyISAM AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_voteitem
-- ----------------------------
