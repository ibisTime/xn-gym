truncate tstd_account_jour;
DROP TABLE IF EXISTS `tstd_account_jour;

ALTER TABLE tstd_user ADD COLUMN div_rate DECIMAL(18,8) NULL;
ALTER TABLE tstd_user ADD COLUMN `create_datetime` DATETIME NULL COMMENT '注册时间';
ALTER TABLE tstd_user DROP COLUMN amount;

insert into `tsys_menu` (`code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `parent_code`, `system_code`) values('SM2017042109425468113','默认设置','2','/default','5','admin',now(),'','CSWSM2016100916145734721','CD-CCSW000008');
insert into `tsys_menu` (`code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `parent_code`, `system_code`) values('SM2017042110135269036','加载页管理','1','/view/load2.html?b=1','4','admin',now(),'','CSWSM2016092821584799672','CD-CCSW000008');
insert into `tsys_menu` (`code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `parent_code`, `system_code`) values('SM2017042117463625683','加载页管理','1','/view/load.htm','4','admin',now(),'','CSWSM201610091501476418','CD-CCSW000008');
insert into `tsys_menu` (`code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `parent_code`, `system_code`) values('SM2017042119433145410','修改','2','/edit','1','admin',now(),'','SM2017042117463625683','CD-CCSW000008');
insert into `tsys_menu` (`code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `parent_code`, `system_code`) values('SM2017042119440646869','详情','2','/detail','2','admin',now(),'','SM2017042117463625683','CD-CCSW000008');



