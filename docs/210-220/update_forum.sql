ALTER TABLE `tforum_post` 
ADD COLUMN `nickname` VARCHAR(32) NULL COMMENT '昵称' AFTER `publisher`,
ADD COLUMN `photo` TEXT NULL COMMENT '头像' AFTER `nickname`,
ADD COLUMN `login_name` VARCHAR(32) NULL COMMENT '登陆名' AFTER `photo`;

ALTER TABLE `tforum_comment` 
ADD COLUMN `nickname` VARCHAR(32) NULL COMMENT '昵称' AFTER `commer`,
ADD COLUMN `photo` TEXT NULL COMMENT '头像' AFTER `nickname`,
ADD COLUMN `login_name` VARCHAR(32) NULL COMMENT '登陆名' AFTER `photo`;

ALTER TABLE `tforum_post_talk` 
ADD COLUMN `nickname` VARCHAR(32) NULL COMMENT '昵称' AFTER `talker`,
ADD COLUMN `photo` TEXT NULL COMMENT '头像' AFTER `nickname`,
ADD COLUMN `login_name` VARCHAR(32) NULL COMMENT '登陆名' AFTER `photo`;



