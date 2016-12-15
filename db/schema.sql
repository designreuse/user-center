DROP TABLE IF EXISTS `auth_apps`;

CREATE TABLE `auth_apps` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) DEFAULT NULL COMMENT '应用名称',
  `key` varchar(128) DEFAULT NULL COMMENT '应用编号',
  `secret` varchar(128) DEFAULT NULL COMMENT '应用秘钥',
  `domain` varchar(128) DEFAULT NULL COMMENT '域名',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='应用表';


DROP TABLE IF EXISTS `auth_authorities`;

CREATE TABLE `auth_authorities` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) DEFAULT NULL COMMENT '权限名称',
  `auth` varchar(128) DEFAULT NULL COMMENT '权限类型',
  `perm_key` varchar(128) DEFAULT NULL COMMENT '权限key',
  `role_key` varchar(128) DEFAULT NULL COMMENT '角色key',
  `url` varchar(128) DEFAULT NULL COMMENT 'url',
  `app_id` varchar(128) DEFAULT NULL COMMENT '应用ID',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限表';


DROP TABLE IF EXISTS `auth_role_authorities`;

CREATE TABLE `auth_role_authorities` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `authority_id` bigint(20) DEFAULT NULL COMMENT '权限ID',
  `authority_key` varchar(128) DEFAULT NULL COMMENT '权限key',
  `app_id` bigint(20) DEFAULT NULL COMMENT '应用ID',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限表';


DROP TABLE IF EXISTS `auth_roles`;

CREATE TABLE `auth_roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) DEFAULT NULL COMMENT '角色名称',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';


DROP TABLE IF EXISTS `club_users`;

CREATE TABLE `club_users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) DEFAULT NULL COMMENT '姓名',
  `username` varchar(128) DEFAULT NULL COMMENT '登录名',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `salt` varchar(64) DEFAULT NULL COMMENT '密钥',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色',
  `outer_id` bigint(20) DEFAULT NULL COMMENT '外部id',
  `email` varchar(64) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(64) DEFAULT NULL COMMENT '手机号码',
  `stu_no` varchar(32) DEFAULT NULL COMMENT '学号',
  `qq` varchar(16) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL COMMENT '状态',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

