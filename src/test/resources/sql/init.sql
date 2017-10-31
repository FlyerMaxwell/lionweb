CREATE DATABASE IF NOT EXISTS db_forum CHARACTER SET UTF8;
USE db_forum;

#用户信息表
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '用户id',
`user_name` VARCHAR(30) NOT NULL UNIQUE COMMENT '用户名',
`password` VARCHAR(30) NOT NULL DEFAULT '' COMMENT '密码',
`user_email` VARCHAR(30) NOT NULL UNIQUE COMMENT '用户邮箱',
`user_sex` VARCHAR(30) NOT NULL DEFAULT '' COMMENT '用户性别',
`user_phone` INT(11) NOT NULL DEFAULT '' COMMENT '电话号码',
`create_time` date NOT NULL DEFAULT now() COMMENT '用户创建时间',
`user_type` INT(2) NOT NULL DEFAULT '1' COMMENT '用户类型 0:管理员 1:普通用户',
`user_state` INT(2) NOT NULL DEFAULT '0' COMMENT '用户状态 0:正常 1:冻结',
`last_login_time` DATETIME DEFAULT NULL COMMENT '用户最后登入时间',
`last_ip` VARCHAR(20) DEFAULT NULL COMMENT '用户最后登入ip',
PRIMARY KEY (`id`)
)ENGINE=InnoDB  DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

#用户登录日志表
DROP TABLE IF EXISTS `t_login_log`;
CREATE TABLE `t_login_log`(
login_log_id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '日志ID',
user_name VARCHAR(30) NOT NULL DEFAULT '' COMMENT '用户名',
login_ip VARCHAR(30) NOT NULL DEFAULT '' COMMENT '登录IP',
login_datetime DATETIME NOT NULL DEFAULT now() COMMENT '登录时间',
PRIMARY KEY (`login_log_id`)
)ENGINE=InnoDB  DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

#Publication表
DROP TABLE IF EXISTS `t_publication`;
CREATE TABLE `t_publication`(
`user_name` VARCHAR(30) NOT NULL DEFAULT '' COMMENT '创建者用户名',
`author_name` VARCHAR(30) NOT NULL DEFAULT '' COMMENT '作者用户名',
`status` INT(2) NOT NULL DEFAULT '0' COMMENT '状态位 0-正常 1-锁定',
`create_time` DATETIME NOT NULL DEFAULT now() COMMENT '创建时间',
`update_time` DATETIME NOT NULL DEFAULT now()
)
