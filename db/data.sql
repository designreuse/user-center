INSERT INTO `auth_apps` (`id`, `name`, `key`, `secret`, `domain`, `created_at`, `updated_at`)
VALUES
	(1,'权限系统','authSystem','123456','cms.yingchengpeng.com','2016-12-14 00:00:00','2016-12-14 00:00:00');


INSERT INTO `auth_authorities` (`id`, `name`, `auth`, `perm_key`, `role_key`, `url`, `app_id`, `created_at`, `updated_at`)
VALUES
	(1,'全部','anon',NULL,NULL,'/**','1','2016-12-14 00:00:00','2016-12-14 00:00:00'),
	(2,'全部','authc','','admin','/cms/**','1','2016-12-14 00:00:00','2016-12-14 00:00:00'),
	(3,'首页','anon',NULL,NULL,'/','1','2016-12-14 00:00:00','2016-12-14 00:00:00'),
	(4,'登录','anon',NULL,NULL,'/login','1','2016-12-14 00:00:00','2016-12-14 00:00:00'),
	(5,'用户列表','authc','authSystem:user:paging',NULL,'/user/paging','1','2016-12-14 00:00:00','2016-12-14 00:00:00'),
	(6,'用户详情','authc','authSystem:user:view',NULL,'/user/view','1','2016-12-14 00:00:00','2016-12-14 00:00:00'),
	(7,'用户更新','authc','authSystem:user:update',NULL,'/user/update','1','2016-12-14 00:00:00','2016-12-14 00:00:00');


INSERT INTO `auth_role_authorities` (`id`, `role_id`, `authority_id`, `authority_key`, `app_id`, `created_at`, `updated_at`)
VALUES
	(1,1,3,'authSystem:user:paging',1,'2016-12-15 00:00:00','2016-12-15 00:00:00');


INSERT INTO `auth_roles` (`id`, `name`, `created_at`, `updated_at`)
VALUES
	(1,'admin','2016-12-13 00:00:00','2016-12-13 00:00:00'),
	(2,'user','2016-12-13 00:00:00','2016-12-13 00:00:00');


INSERT INTO `club_users` (`id`, `name`, `username`, `password`, `salt`, `role_id`, `outer_id`, `email`, `mobile`, `stu_no`, `qq`, `status`, `created_at`, `updated_at`)
VALUES
	(1,'admin','admin','ZCyqCBiFf5ky6m/BeFJFyGuk6LSVlu5f','grqx5iCM2Ma8KT9x1hja6acW',1,0,'ychp0117@sina.com','13706556271','11206023211','647955380',1,'2016-12-12 00:00:00','2016-12-12 00:00:00'),
	(2,'user','user','ZCyqCBiFf5ky6m/BeFJFyGuk6LSVlu5f','grqx5iCM2Ma8KT9x1hja6acW',2,0,'ychp0117@sina.com','13706556271','11206023211','647955380',1,'2016-12-12 00:00:00','2016-12-12 00:00:00');
