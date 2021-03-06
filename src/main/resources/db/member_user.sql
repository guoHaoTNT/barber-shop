/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50728
Source Host           : localhost:3306
Source Database       : barber_shop

Target Server Type    : MYSQL
Target Server Version : 50728
File Encoding         : 65001

Date: 2022-04-05 22:30:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for member_user
-- ----------------------------
DROP TABLE IF EXISTS `member_user`;
CREATE TABLE `member_user` (
  `id` bigint(64) unsigned NOT NULL COMMENT '主键',
  `member_user_name` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '会员姓名',
  `birthday` datetime DEFAULT NULL COMMENT '生日',
  `address` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '家庭住址',
  `remark` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '备注',
  `status` tinyint(2) NOT NULL DEFAULT '1' COMMENT '产品状态：1、编辑中；2、审核中；3、待上线；4、已上线；5、已下线',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_user` bigint(64) DEFAULT NULL COMMENT '创建人',
  `update_user` bigint(64) DEFAULT NULL COMMENT '修改人',
  `is_deleted` int(2) DEFAULT '0' COMMENT '是否已删除',
  `phone_num` varchar(11) DEFAULT NULL COMMENT '手机号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
