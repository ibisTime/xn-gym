insert into `tstd_account` (`account_number`, `user_id`, `real_name`, `type`, `status`, `currency`, `amount`, `frozen_amount`, `md5`, `add_amount`, `in_amount`, `out_amount`, `create_datetime`, `last_order`, `system_code`, `company_code`) values('CD-ZWZJ000012','SYS_USER_ZWZJ_TG','平台托管账户','P','0','CNY','0','0','50195e99973a8cd5018254ae7c221913','0','0','0',now(),NULL,'CD-ZWZJ000012','CD-ZWZJ000012');
insert into `tstd_account` (`account_number`, `user_id`, `real_name`, `type`, `status`, `currency`, `amount`, `frozen_amount`, `md5`, `add_amount`, `in_amount`, `out_amount`, `create_datetime`, `last_order`, `system_code`, `company_code`) values('CZWZJA2016100000000000000','SYS_USER_ZWZJ','平台人民币账户','P','0','CNY','0','0','457f317acb0ddbf9fd4e8c8c0f464e58','0','0','0',now(),NULL,'CD-ZWZJ000012','CD-ZWZJ000012');
insert into `tstd_account` (`account_number`, `user_id`, `real_name`, `type`, `status`, `currency`, `amount`, `frozen_amount`, `md5`, `add_amount`, `in_amount`, `out_amount`, `create_datetime`, `last_order`, `system_code`, `company_code`) values('CZWZJA2016100000000000001','SYS_USER_ZWZJ','平台积分账户','P','0','JF','0','0','83e4b31e11ae00ae485a88ac79fff75e','0','0','0',now(),NULL,'CD-ZWZJ000012','CD-ZWZJ000012');



insert into `tstd_channel_bank` (`bank_code`, `bank_name`, `channel_type`, `status`, `channel_bank`, `max_order`, `order_amount`, `day_amount`, `month_amount`, `remark`) values('ICBC','中国工商银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL);
insert into `tstd_channel_bank` (`bank_code`, `bank_name`, `channel_type`, `status`, `channel_bank`, `max_order`, `order_amount`, `day_amount`, `month_amount`, `remark`) values('ABC','中国农业银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL);
insert into `tstd_channel_bank` (`bank_code`, `bank_name`, `channel_type`, `status`, `channel_bank`, `max_order`, `order_amount`, `day_amount`, `month_amount`, `remark`) values('CCB','中国建设银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL);
insert into `tstd_channel_bank` (`bank_code`, `bank_name`, `channel_type`, `status`, `channel_bank`, `max_order`, `order_amount`, `day_amount`, `month_amount`, `remark`) values('BOC','中国银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL);
insert into `tstd_channel_bank` (`bank_code`, `bank_name`, `channel_type`, `status`, `channel_bank`, `max_order`, `order_amount`, `day_amount`, `month_amount`, `remark`) values('BCM','中国交通银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL);
insert into `tstd_channel_bank` (`bank_code`, `bank_name`, `channel_type`, `status`, `channel_bank`, `max_order`, `order_amount`, `day_amount`, `month_amount`, `remark`) values('CIB','兴业银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL);
insert into `tstd_channel_bank` (`bank_code`, `bank_name`, `channel_type`, `status`, `channel_bank`, `max_order`, `order_amount`, `day_amount`, `month_amount`, `remark`) values('CITIC','中信银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL);
insert into `tstd_channel_bank` (`bank_code`, `bank_name`, `channel_type`, `status`, `channel_bank`, `max_order`, `order_amount`, `day_amount`, `month_amount`, `remark`) values('CEB','中国光大银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL);
insert into `tstd_channel_bank` (`bank_code`, `bank_name`, `channel_type`, `status`, `channel_bank`, `max_order`, `order_amount`, `day_amount`, `month_amount`, `remark`) values('PAB','平安银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL);
insert into `tstd_channel_bank` (`bank_code`, `bank_name`, `channel_type`, `status`, `channel_bank`, `max_order`, `order_amount`, `day_amount`, `month_amount`, `remark`) values('PSBC','中国邮政储蓄银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL);
insert into `tstd_channel_bank` (`bank_code`, `bank_name`, `channel_type`, `status`, `channel_bank`, `max_order`, `order_amount`, `day_amount`, `month_amount`, `remark`) values('SHB','上海银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL);
insert into `tstd_channel_bank` (`bank_code`, `bank_name`, `channel_type`, `status`, `channel_bank`, `max_order`, `order_amount`, `day_amount`, `month_amount`, `remark`) values('SPDB','浦东发展银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL);
insert into `tstd_channel_bank` (`bank_code`, `bank_name`, `channel_type`, `status`, `channel_bank`, `max_order`, `order_amount`, `day_amount`, `month_amount`, `remark`) values('CIB','兴业银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL);


insert into `tsys_config` (`type`, `ckey`, `cvalue`, `updater`, `update_datetime`, `remark`, `company_code`, `system_code`) values('0','CNYRechageTimes','5','admin',now(),'用户每月最多取现次数','CD-ZWZJ000012','CD-ZWZJ000012');
insert into `tsys_config` (`type`, `ckey`, `cvalue`, `updater`, `update_datetime`, `remark`, `company_code`, `system_code`) values('0','BUSERQXFL','0.01','admin',now(),'B端用户取现费率','CD-ZWZJ000012','CD-ZWZJ000012');
insert into `tsys_config` (`type`, `ckey`, `cvalue`, `updater`, `update_datetime`, `remark`, `company_code`, `system_code`) values('1','CZSJF','10','admin',now(),'充值送积分','CD-ZWZJ000012','CD-ZWZJ000012');

insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`, `system_code`) values('0','','account_type','账户类型','admin',now(),NULL,'CD-ZWZJ000012');
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`, `system_code`) values('1','account_type','C','C端用户','admin',now(),NULL,'CD-ZWZJ000012');
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`, `system_code`) values('1','account_type','B','B端用户','admin',now(),NULL,'CD-ZWZJ000012');
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`, `system_code`) values('1','account_type','P','平台用户','admin',now(),NULL,'CD-ZWZJ000012');
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`, `system_code`) values('0','','withdraw_status','取现订单状态','admin',now(),NULL,'CD-ZWZJ000012');
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`, `system_code`) values('1','withdraw_status','1','待审批','admin',now(),NULL,'CD-ZWZJ000012');
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`, `system_code`) values('1','withdraw_status','2','审批不通过','admin',now(),NULL,'CD-ZWZJ000012');
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`, `system_code`) values('1','withdraw_status','3','审批通过待支付','admin',now(),NULL,'CD-ZWZJ000012');
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`, `system_code`) values('1','withdraw_status','4','支付失败','admin',now(),NULL,'CD-ZWZJ000012');
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`, `system_code`) values('0','','charge_status','充值订单状态','admin',now(),NULL,'CD-ZWZJ000012');
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`, `system_code`) values('1','charge_status','1','待支付','admin',now(),NULL,'CD-ZWZJ000012');
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`, `system_code`) values('1','charge_status','2','支付失败','admin',now(),NULL,'CD-ZWZJ000012');
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`, `system_code`) values('1','charge_status','3','支付成功','admin',now(),NULL,'CD-ZWZJ000012');
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`, `system_code`) values('0','','jour_status','流水状态','admin',now(),NULL,'CD-ZWZJ000012');
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`, `system_code`) values('1','jour_status','1','待对账','admin',now(),NULL,'CD-ZWZJ000012');
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`, `system_code`) values('1','jour_status','3','已对账且账已平','admin',now(),NULL,'CD-ZWZJ000012');
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`, `system_code`) values('1','jour_status','4','账不平待调账','admin',now(),NULL,'CD-ZWZJ000012');
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`, `system_code`) values('1','jour_status','5','已调账','admin',now(),NULL,'CD-ZWZJ000012');
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`, `system_code`) values('1','jour_status','6','无需对账','admin',now(),NULL,'CD-ZWZJ000012');
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`, `system_code`) values('0','','channel_type','渠道类型','admin',now(),NULL,'CD-ZWZJ000012');
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`, `system_code`) values('1','channel_type','0','内部账','admin',now(),NULL,'CD-ZWZJ000012');
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`, `system_code`) values('1','channel_type','35','微信H5支付','admin',now(),NULL,'CD-ZWZJ000012');
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`, `system_code`) values('1','channel_type','90','人工线下','admin',now(),NULL,'CD-ZWZJ000012');
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`, `system_code`) values('0','','biz_type','业务类型','admin',now(),NULL,'CD-ZWZJ000012');
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`, `system_code`) values('1','biz_type','11','充值','admin',now(),NULL,'CD-ZWZJ000012');
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`, `system_code`) values('1','biz_type','-11','取现','admin',now(),NULL,'CD-ZWZJ000012');
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`, `system_code`) values('1','biz_type','19','蓝补','admin',now(),NULL,'CD-ZWZJ000012');
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`, `system_code`) values('1','biz_type','-19','红冲','admin',now(),NULL,'CD-ZWZJ000012');
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`, `system_code`) values('0','','currency','货币','admin',now(),NULL,'CD-ZWZJ000012');
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`, `system_code`) values('1','currency','CNY','人民币','admin',now(),NULL,'CD-ZWZJ000012');
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`, `system_code`) values('1','withdraw_status','5','支付成功','admin',now(),NULL,'CD-ZWZJ000012');
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`, `system_code`) values('1','currency','JF','积分','admin',now(),NULL,'CD-ZWZJ000012');
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`, `system_code`) values('1','biz_type','HDGM','活动购买','admin',now(),NULL,'CD-ZWZJ000012');
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`, `system_code`) values('1','biz_type','HDGMTK','活动购买退款','admin',now(),NULL,'CD-ZWZJ000012');
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`, `system_code`) values('1','biz_type','TKGM','团课购买','admin',now(),NULL,'CD-ZWZJ000012');
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`, `system_code`) values('1','biz_type','TKGMTK','团课购买退款','admin',now(),NULL,'CD-ZWZJ000012');
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`, `system_code`) values('1','biz_type','SKGM','私课购买','admin',now(),NULL,'CD-ZWZJ000012');
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`, `system_code`) values('1','biz_type','SKGMTK','私课购买退款','admin',now(),NULL,'CD-ZWZJ000012');
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`, `system_code`) values('1','biz_type','HDGMSJF','活动购买送积分','admin',now(),NULL,'CD-ZWZJ000012');
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`, `system_code`) values('1','biz_type','SKGMSJF','私课购买加积分','admin',now(),NULL,'CD-ZWZJ000012');
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`, `system_code`) values('1','biz_type','KCGMSJF','团课购买加积分','admin',now(),NULL,'CD-ZWZJ000012');
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`, `system_code`) values('1','biz_type','CZSJF','充值送积分','admin',now(),NULL,'CD-ZWZJ000012');
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`, `system_code`) values('1','biz_type','TJ','推荐送积分','admin',now(),NULL,'CD-ZWZJ000012');
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`, `system_code`) values('1','biz_type','ZWZJ_XXFK','提现回录','admin',now(),'','CD-ZWZJ000012');
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`, `system_code`) values('1','biz_type','TTJFC','团课教练分成','admin',now(),NULL,'CD-ZWZJ000012');


insert into `tstd_company_channel` (`company_code`, `company_name`, `channel_type`, `status`, `channel_company`, `private_key1`, `private_key2`, `private_key3`, `private_key4`, `private_key5`, `page_url`, `error_url`, `back_url`, `fee`, `remark`, `system_code`) values('CD-ZWZJ000012','自玩自健','35','1','1429521602','r2jgDFSdiikklwlllejlwjio3242342n','wx9b874d991d7e50d5','aa1832cf32722e0fb977a6c9f6aafa20',NULL,NULL,NULL,NULL,'',NULL,NULL,'CD-ZWZJ000012');
