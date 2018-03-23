/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost:3306
 Source Schema         : sys

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 23/03/2018 11:47:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config`  (
  `variable` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `value` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `set_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `set_by` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`variable`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES ('diagnostics.allow_i_s_tables', 'OFF', '2018-03-22 16:08:58', NULL);
INSERT INTO `sys_config` VALUES ('diagnostics.include_raw', 'OFF', '2018-03-22 16:08:58', NULL);
INSERT INTO `sys_config` VALUES ('ps_thread_trx_info.max_length', '65535', '2018-03-22 16:08:58', NULL);
INSERT INTO `sys_config` VALUES ('statement_performance_analyzer.limit', '100', '2018-03-22 16:08:58', NULL);
INSERT INTO `sys_config` VALUES ('statement_performance_analyzer.view', NULL, '2018-03-22 16:08:58', NULL);
INSERT INTO `sys_config` VALUES ('statement_truncate_len', '64', '2018-03-22 16:08:58', NULL);

-- ----------------------------
-- Triggers structure for table sys_config
-- ----------------------------
DROP TRIGGER IF EXISTS `sys_config_insert_set_user`;
delimiter ;;
CREATE TRIGGER `sys_config_insert_set_user` BEFORE INSERT ON `sys_config` FOR EACH ROW BEGIN IF @sys.ignore_sys_config_triggers != true AND NEW.set_by IS NULL THEN SET NEW.set_by = USER(); END IF; END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table sys_config
-- ----------------------------
DROP TRIGGER IF EXISTS `sys_config_update_set_user`;
delimiter ;;
CREATE TRIGGER `sys_config_update_set_user` BEFORE UPDATE ON `sys_config` FOR EACH ROW BEGIN IF @sys.ignore_sys_config_triggers != true AND NEW.set_by IS NULL THEN SET NEW.set_by = USER(); END IF; END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
