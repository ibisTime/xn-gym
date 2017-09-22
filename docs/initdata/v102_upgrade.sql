ALTER TABLE `tgym_coach` 
ADD COLUMN `province` VARCHAR(32) NULL COMMENT '省' AFTER `adv_pic`,
ADD COLUMN `city` VARCHAR(32) NULL COMMENT '市' AFTER `province`,
ADD COLUMN `area` VARCHAR(32) NULL COMMENT '区' AFTER `city`,
ADD COLUMN `teach_num` int(11) NULL COMMENT '授课量' AFTER `area`;


ALTER TABLE `tgym_per_course_order` 
ADD COLUMN `is_send` VARCHAR(32) NULL COMMENT '是否发送（0否，1是）' AFTER `remark`;

ALTER TABLE `tgym_per_course` 
ADD COLUMN `address` VARCHAR(255) NULL COMMENT '地址' AFTER `sk_end_datetime`,
ADD COLUMN `total_num` int(11) NULL COMMENT '授课人数' AFTER `address`;