-- 系统初始化时候,启动应用后自动初始化脚本状态表,该状态表用来记录最初需要手动初始化的1.0_prod.sql的状态
-- ----------------------------
-- Table structure for 1.0_init_table
-- ----------------------------
CREATE TABLE If Not Exists  `init_table` (
  `id` int(11) NOT NULL COMMENT '主键',
  `init_name` varchar(255) DEFAULT NULL COMMENT 'base基版本名',
  `status` int(1) unsigned zerofill NOT NULL DEFAULT '0' COMMENT '执行状态:0未执行或者失败;1成功执行',
  `init_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '初始化时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of 1.0_init_table
-- ----------------------------
INSERT INTO `init_table` VALUES ('1', '1.0_prod.sql', '0', '2022-12-12 11:00:00');
