INSERT INTO `center_apps` (`id`, `name`, `key`, `secret`, `domain`, `created_at`, `updated_at`)
VALUES
  (1,'权限系统','7b32eceab98f47c8b818cd0697863720','e9b4e0364cedbf0f0960d02e6a3e01d4','cms.yingchengpeng.com','2016-12-26 17:48:56','2016-12-26 17:48:56'),
  (3,'test1','99867ed8f969433d808d766b379e4539','1f6bd18513619a89608ec8078f6b3c02','test.wwww.com','2017-02-02 17:42:09','2017-02-02 17:42:09');

INSERT INTO `center_authorities` (`id`, `name`, `auth`, `perm_key`, `role_key`, `url`, `app_id`, `created_at`, `updated_at`)
VALUES
  (1,'权限首页','authc','auth:index',NULL,'/cms/index','1','2016-12-14 00:00:00','2016-12-14 00:00:00'),
  (2,'应用列表','authc','auth:app:list',NULL,'/cms/apps','1','2016-12-14 00:00:00','2016-12-14 00:00:00'),
  (3,'应用详情','authc','auth:app:view',NULL,'/cms/app/view','1','2016-12-14 00:00:00','2016-12-14 00:00:00'),
  (4,'应用更新','authc','auth:app:update',NULL,'/api/cms/app/update','1','2016-12-14 00:00:00','2016-12-14 00:00:00'),
  (5,'应用添加','authc','auth:app:add',NULL,'/api/cms/app/add','1','2016-12-14 00:00:00','2016-12-14 00:00:00'),
  (6,'应用删除','authc','auth:app:del',NULL,'/api/cms/app/del','1','2016-12-14 00:00:00','2016-12-14 00:00:00'),
  (7,'权限列表','authc','auth:perm:list',NULL,'/cms/perms','1','2016-12-14 00:00:00','2016-12-14 00:00:00'),
  (8,'权限详情','authc','auth:perm:view',NULL,'/cms/perm/view','1','2016-12-14 00:00:00','2016-12-14 00:00:00'),
  (9,'权限更新','authc','auth:perm:update',NULL,'/api/cms/perm/update','1','2016-12-14 00:00:00','2016-12-14 00:00:00'),
  (10,'权限添加','authc','auth:perm:add',NULL,'/api/cms/perm/add','1','2016-12-14 00:00:00','2016-12-14 00:00:00'),
  (11,'权限删除','authc','auth:perm:del',NULL,'/api/cms/perm/del','1','2016-12-14 00:00:00','2016-12-14 00:00:00'),
  (12,'角色列表','authc','auth:role:list',NULL,'/cms/roles','1','2016-12-14 00:00:00','2016-12-14 00:00:00'),
  (13,'角色详情','authc','auth:role:view',NULL,'/cms/role/view','1','2016-12-14 00:00:00','2016-12-14 00:00:00'),
  (14,'角色更新','authc','auth:role:update',NULL,'/api/cms/role/update','1','2016-12-14 00:00:00','2016-12-14 00:00:00'),
  (15,'角色添加','authc','auth:role:add',NULL,'/api/cms/role/add','1','2016-12-14 00:00:00','2016-12-14 00:00:00'),
  (16,'角色删除','authc','auth:role:del',NULL,'/api/cms/role/del','1','2016-12-14 00:00:00','2016-12-14 00:00:00'),
  (17,'应用授权','authc','auth:role:app:grant',NULL,'/api/cms/role/app/grant','1','2016-12-14 00:00:00','2016-12-14 00:00:00'),
  (18,'应用取消授权','authc','auth:role:app:grant:cancel',NULL,'/api/cms/role/app/cancelGrant','1','2016-12-14 00:00:00','2016-12-14 00:00:00'),
  (19,'权限授权','authc','auth:role:perms:grant',NULL,'/api/cms/role/perms/grant','1','2016-12-14 00:00:00','2016-12-14 00:00:00'),
  (20,'用户列表','authc','auth:user:list',NULL,'/cms/users','1','2016-12-14 00:00:00','2016-12-14 00:00:00'),
  (21,'用户详情','authc','auth:user:view',NULL,'/cms/user/view','1','2016-12-14 00:00:00','2016-12-14 00:00:00'),
  (22,'用户更新','authc','auth:user:update',NULL,'/api/cms/user/update','1','2016-12-14 00:00:00','2016-12-14 00:00:00'),
  (23,'用户添加','authc','auth:user:add',NULL,'/api/cms/user/add','1','2016-12-14 00:00:00','2016-12-14 00:00:00'),
  (24,'用户删除','authc','auth:user:del',NULL,'/api/cms/user/del','1','2016-12-14 00:00:00','2016-12-14 00:00:00'),
  (25,'分配角色','authc','auth:user:role:grant',NULL,'/api/cms/user/role/grant','1','2016-12-14 00:00:00','2016-12-14 00:00:00');

INSERT INTO `center_role_apps` (`id`, `role_id`, `app_id`, `created_at`, `updated_at`)
VALUES
  (1,2,1,'2016-12-17 00:00:00','2016-12-17 00:00:00');

INSERT INTO `center_role_authorities` (`id`, `role_id`, `authority_id`, `authority_key`, `app_id`, `created_at`, `updated_at`)
VALUES
  (1,2,3,'auth:user:*',1,'2016-12-15 00:00:00','2016-12-15 00:00:00');

INSERT INTO `center_roles` (`id`, `name`, `created_at`, `updated_at`)
VALUES
  (1,'admin','2016-12-13 00:00:00','2016-12-13 00:00:00'),
  (2,'manager','2016-12-13 00:00:00','2016-12-13 00:00:00'),
  (3,'user','2016-12-13 00:00:00','2016-12-13 00:00:00');

INSERT INTO `center_users` (`id`, `name`, `username`, `password`, `salt`, `role_id`, `outer_id`, `email`, `mobile`, `stu_no`, `qq`, `status`, `created_at`, `updated_at`)
VALUES
  (1,'admin','admin','ZCyqCBiFf5ky6m/BeFJFyGuk6LSVlu5f','grqx5iCM2Ma8KT9x1hja6acW',1,0,'ychp0117@sina.com','13706556271','11206023211','647955380',1,'2016-12-12 00:00:00','2016-12-12 00:00:00'),
  (2,'manager','manager','ZCyqCBiFf5ky6m/BeFJFyGuk6LSVlu5f','grqx5iCM2Ma8KT9x1hja6acW',2,0,'ychp0117@sina.com','13706556271','11206023211','647955380',1,'2016-12-12 00:00:00','2016-12-12 00:00:00');
