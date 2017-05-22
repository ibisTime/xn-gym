ALTER TABLE tforum_splate ADD is_default INT(11) NULL

UPDATE tforum_splate SET is_default=0;


insert into `tforum_banner` (`code`, `name`, `url`, `pic`, `location`, `order_no`, `belong`, `company_code`, `remark`) values('17','adv1','page:mall','timg_1492758758079.jpg','0','9','1','0','');


ALTER TABLE tr_activity ADD single_num INT(11) NULL after sign_num;