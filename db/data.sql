INSERT INTO `center_apps` (`id`, `name`, `key`, `secret`, `domain`, `created_at`, `updated_at`)
VALUES
	(1, '权限系统', '7b32eceab98f47c8b818cd0697863720', 'e9b4e0364cedbf0f0960d02e6a3e01d4', 'cms.yingchengpeng.com', '2016-12-26 17:48:56', '2016-12-26 17:48:56'),
	(3, 'test1', '99867ed8f969433d808d766b379e4539', '1f6bd18513619a89608ec8078f6b3c02', 'test.wwww.com', '2017-02-02 17:42:09', '2017-02-02 17:42:09');

INSERT INTO `center_authorities` (`id`, `pid`, `name`, `auth`, `perm_key`, `role_key`, `url`, `app_id`, `created_at`, `updated_at`)
VALUES
	(1, 0, '权限首页', 'authc', 'auth:index', NULL, '/', 1, '2016-12-14 00:00:00', '2016-12-14 00:00:00'),
	(2, 0, '权限首页', 'authc', 'auth:index', NULL, '/index', 1, '2016-12-14 00:00:00', '2016-12-14 00:00:00'),
	(3, 0, '应用管理', 'authc', 'auth:app:list', NULL, '/apps', 1, '2016-12-14 00:00:00', '2016-12-14 00:00:00'),
	(4, 3, '应用详情', 'authc', 'auth:app:view', NULL, '/app/view', 1, '2016-12-14 00:00:00', '2016-12-14 00:00:00'),
	(5, 3, '应用更新', 'authc', 'auth:app:update', NULL, '/api/app/update', 1, '2016-12-14 00:00:00', '2016-12-14 00:00:00'),
	(6, 3, '应用添加', 'authc', 'auth:app:add', NULL, '/api/app/add', 1, '2016-12-14 00:00:00', '2016-12-14 00:00:00'),
	(7, 3, '应用删除', 'authc', 'auth:app:del', NULL, '/api/app/del', 1, '2016-12-14 00:00:00', '2016-12-14 00:00:00'),
	(8, 0, '权限管理', 'authc', 'auth:perm:list', NULL, '/perms', 1, '2016-12-14 00:00:00', '2016-12-14 00:00:00'),
	(9, 8, '权限详情', 'authc', 'auth:perm:view', NULL, '/perm/view', 1, '2016-12-14 00:00:00', '2016-12-14 00:00:00'),
	(10, 8, '权限更新', 'authc', 'auth:perm:update', NULL, '/api/perm/update', 1, '2016-12-14 00:00:00', '2016-12-14 00:00:00'),
	(11, 8, '权限添加', 'authc', 'auth:perm:add', NULL, '/api/perm/add', 1, '2016-12-14 00:00:00', '2016-12-14 00:00:00'),
	(12, 8, '权限删除', 'authc', 'auth:perm:del', NULL, '/api/perm/del', 1, '2016-12-14 00:00:00', '2016-12-14 00:00:00'),
	(13, 0, '角色管理', 'authc', 'auth:role:list', NULL, '/roles', 1, '2016-12-14 00:00:00', '2016-12-14 00:00:00'),
	(14, 13, '角色详情', 'authc', 'auth:role:view', NULL, '/role/view', 1, '2016-12-14 00:00:00', '2016-12-14 00:00:00'),
	(15, 13, '角色更新', 'authc', 'auth:role:update', NULL, '/api/role/update', 1, '2016-12-14 00:00:00', '2016-12-14 00:00:00'),
	(16, 13, '角色添加', 'authc', 'auth:role:add', NULL, '/api/role/add', 1, '2016-12-14 00:00:00', '2016-12-14 00:00:00'),
	(17, 13, '角色删除', 'authc', 'auth:role:del', NULL, '/api/role/del', 1, '2016-12-14 00:00:00', '2016-12-14 00:00:00'),
	(18, 13, '应用授权', 'authc', 'auth:role:app:grant', NULL, '/api/role/app/grant', 1, '2016-12-14 00:00:00', '2016-12-14 00:00:00'),
	(19, 13, '应用取消授权', 'authc', 'auth:role:app:grant:cancel', NULL, '/api/role/app/cancelGrant', 1, '2016-12-14 00:00:00', '2016-12-14 00:00:00'),
	(20, 13, '权限授权', 'authc', 'auth:role:perms:grant', NULL, '/api/role/perms/grant', 1, '2016-12-14 00:00:00', '2016-12-14 00:00:00'),
	(21, 0, '用户管理', 'authc', 'auth:user:list', NULL, '/users', 1, '2016-12-14 00:00:00', '2016-12-14 00:00:00'),
	(22, 21, '用户详情', 'authc', 'auth:user:view', NULL, '/user/view', 1, '2016-12-14 00:00:00', '2016-12-14 00:00:00'),
	(23, 21, '用户更新', 'authc', 'auth:user:update', NULL, '/api/user/update', 1, '2016-12-14 00:00:00', '2016-12-14 00:00:00'),
	(24, 21, '用户添加', 'authc', 'auth:user:add', NULL, '/api/user/add', 1, '2016-12-14 00:00:00', '2016-12-14 00:00:00'),
	(25, 21, '用户删除', 'authc', 'auth:user:del', NULL, '/api/user/del', 1, '2016-12-14 00:00:00', '2016-12-14 00:00:00'),
	(26, 21, '分配角色', 'authc', 'auth:user:role:grant', NULL, '/api/user/role/grant', 1, '2016-12-14 00:00:00', '2016-12-14 00:00:00');

INSERT INTO `center_role_apps` (`id`, `role_id`, `app_id`, `created_at`, `updated_at`)
VALUES
	(1, 2, 1, '2016-12-17 00:00:00', '2016-12-17 00:00:00');

INSERT INTO `center_role_authorities` (`id`, `role_id`, `authority_id`, `authority_key`, `app_id`, `created_at`, `updated_at`)
VALUES
	(2, 2, 1, 'auth:index', 1, '2017-02-23 15:30:54', '2017-02-23 15:30:54'),
	(3, 2, 2, 'auth:index', 1, '2017-02-23 15:30:54', '2017-02-23 15:30:54'),
	(5, 2, 13, 'auth:role:list', 1, '2017-02-23 15:32:30', '2017-02-23 15:32:30'),
	(6, 2, 21, 'auth:user:list', 1, '2017-02-23 15:32:30', '2017-02-23 15:32:30');

INSERT INTO `center_roles` (`id`, `name`, `code`, `created_at`, `updated_at`)
VALUES
	(1, '超级管理员', 'root', '2016-12-13 00:00:00', '2016-12-13 00:00:00'),
	(2, 'manager', 'manager', '2016-12-13 00:00:00', '2016-12-13 00:00:00'),
	(3, 'user', 'user', '2016-12-13 00:00:00', '2016-12-13 00:00:00');

INSERT INTO `center_user_roles` (`id`, `user_id`, `role_json`, `created_at`, `updated_at`)
VALUES
	(1, 1, '[\"root\"]', '2017-05-20 00:00:00', '2017-05-20 00:00:00');

INSERT INTO `center_users` (`id`, `name`, `username`, `password`, `salt`, `outer_id`, `email`, `mobile`, `stu_no`, `qq`, `status`, `created_at`, `updated_at`)
VALUES
	(1, '超级管理员', 'root', 'ZCyqCBiFf5ky6m/BeFJFyGuk6LSVlu5f', 'grqx5iCM2Ma8KT9x1hja6acW', 0, 'ychp0117@sina.com', '13706556271', '11206023211', '647955380', 1, '2016-12-12 00:00:00', '2016-12-12 00:00:00'),
	(2, 'manager', 'manager', 'ZCyqCBiFf5ky6m/BeFJFyGuk6LSVlu5f', 'grqx5iCM2Ma8KT9x1hja6acW', 0, 'ychp0117@sina.com', '13706556271', '11206023211', '647955380', 1, '2016-12-12 00:00:00', '2016-12-12 00:00:00');
