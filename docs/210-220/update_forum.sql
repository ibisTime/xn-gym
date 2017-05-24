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

UPDATE csw_forum.tforum_post t4,(SELECT t1.`user_id`,t1.`nickname`,t1.`login_name`,t3.`photo` FROM std_user.tstd_user t1 RIGHT JOIN std_user.tstd_user_ext t3 ON t3.user_id=t1.user_id RIGHT JOIN csw_forum.tforum_post t2
ON t1.`user_id`=t2.publisher WHERE t1.system_code='CD-CCSW000008')t5 SET t4.`login_name`=t5.login_name,t4.`photo`=t5.photo,t4.`nickname`=t5.nickname 
WHERE t4.publisher=t5.user_id;

UPDATE csw_forum.tforum_post_talk t4,(SELECT t1.`user_id`,t1.`nickname`,t1.`login_name`,t3.`photo` FROM std_user.tstd_user t1 RIGHT JOIN std_user.tstd_user_ext t3 ON t3.user_id=t1.user_id RIGHT JOIN csw_forum.tforum_post_talk t2
ON t1.`user_id`=t2.talker WHERE t1.system_code='CD-CCSW000008')t5 SET t4.`login_name`=t5.login_name,t4.`photo`=t5.photo,t4.`nickname`=t5.nickname 
WHERE t4.talker=t5.user_id;

UPDATE csw_forum.tforum_comment t4,(SELECT t1.`user_id`,t1.`nickname`,t1.`login_name`,t3.`photo` FROM std_user.tstd_user t1 RIGHT JOIN std_user.tstd_user_ext t3 ON t3.user_id=t1.user_id RIGHT JOIN csw_forum.tforum_comment t2
ON t1.`user_id`=t2.commer WHERE t1.system_code='CD-CCSW000008')t5 SET t4.`login_name`=t5.login_name,t4.`photo`=t5.photo,t4.`nickname`=t5.nickname 
WHERE t4.commer=t5.user_id;

ALTER TABLE tr_activity ADD single_num INT(11) NULL after sign_num;
UPDATE csw_activity.`tr_activity` SET single_num=limit_num;

ALTER TABLE `csw_activity`.`tr_activity` 
CHANGE COLUMN `description` `description` TEXT NULL DEFAULT NULL COMMENT '图文描述' ;

