DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`(
user_id INT(10) NOT NULL AUTO_INCREMENT COMMENT '用户id',
user_name VARCHAR(30) NOT NULL UNIQUE COMMENT '用户名',
password VARCHAR(30) NOT NULL DEFAULT '' COMMENT '密码',
user_email VARCHAR(30) NOT NULL UNIQUE COMMENT '用户邮箱',
user_sex VARCHAR(30) NOT NULL COMMENT '用户性别',
user_phone INT(11) DEFAULT NULL COMMENT '电话号码',
create_time DATETIME NOT NULL COMMENT '用户创建时间',
user_type INT(2) NOT NULL DEFAULT '1' COMMENT '用户类型 0:管理员 1:普通用户',
user_state INT(2) NOT NULL DEFAULT '0' COMMENT '用户状态 0:正常 1:冻结',
credit INT(10) NOT NULL DEFAULT '100' COMMENT '用户积分',
last_login_time DATETIME DEFAULT NULL COMMENT '用户最后登入时间',
last_ip VARCHAR(20) DEFAULT NULL COMMENT '用户最后登入ip',
PRIMARY KEY (`user_id`)
)ENGINE=InnoDB  DEFAULT CHARSET utf8 COLLATE utf8_general_ci;