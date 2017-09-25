ALTER TABLE `tgym_coach` 
ADD COLUMN `province` VARCHAR(32) NULL COMMENT '省' AFTER `adv_pic`,
ADD COLUMN `city` VARCHAR(32) NULL COMMENT '市' AFTER `province`,
ADD COLUMN `area` VARCHAR(32) NULL COMMENT '区' AFTER `city`,
ADD COLUMN `teach_num` int(11) NULL COMMENT '授课量' AFTER `area`;
update tgym_coach set teach_num='0';
update tgym_coach set status='3' where status='1';

ALTER TABLE `tgym_per_course_order` 
ADD COLUMN `is_send` VARCHAR(32) NULL COMMENT '是否发送（0否，1是）' AFTER `remark`;
update tgym_per_course_order set is_send='0';
ALTER TABLE `tgym_per_course` 
ADD COLUMN `address` VARCHAR(255) NULL COMMENT '地址' AFTER `sk_end_datetime`,
ADD COLUMN `total_num` int(11) NULL COMMENT '授课人数' AFTER `address`;
update tgym_per_course set total_num='5';
UPDATE tgym_per_course,(SELECT CODE FROM tgym_coach WHERE `type`='1')t2 SET address='义乌'  WHERE coach_code=t2.code AND address IS NULL;

UPDATE tgym_coach, (SELECT to_user,COUNT(*)AS coun FROM tgym_per_course_order WHERE STATUS='8' GROUP BY to_user) t
SET teach_num=t.coun WHERE user_id=t.to_user;