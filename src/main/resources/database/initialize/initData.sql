-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'admin', '$2a$10$KaXuBSj3SI3lxx.Sb57uo.pkdUhemCQ01.gYVxD6JOH9TF1mCzwp2', '13662005880@163.com', NULL, NULL, b'1', '2018-11-29 11:33:06');
-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES (1, 'ROLE_ADMIN', 'with all privileges');
INSERT INTO `t_role` VALUES (2, 'ROLE_USER', 'with privileges related with normal user');
INSERT INTO `t_role` VALUES (3, 'ROLE_ANONYMOUS', 'with privileges related with user that not signon ');
-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES (1, 1, 1, '2018-11-29 11:37:05');