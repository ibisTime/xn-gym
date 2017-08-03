DROP TABLE IF EXISTS `tgym_activity`;
CREATE TABLE `tgym_activity` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `title` varchar(32) DEFAULT NULL COMMENT '标题',
  `pic` text COMMENT '缩略图',
  `adv_pic` text COMMENT '广告图',
  `slogan` varchar(255) DEFAULT NULL COMMENT '广告语',
  `amount` bigint(11) DEFAULT NULL COMMENT '费用',
  `description` text COMMENT '图文描述',
  `hold_place` varchar(255) DEFAULT NULL COMMENT '举办地',
  `contact` varchar(32) DEFAULT NULL COMMENT '联系方式',
  `location` varchar(32) DEFAULT NULL COMMENT 'ui位置',
  `order_no` int(11) DEFAULT NULL COMMENT 'ui顺序',
  `start_datetime` datetime DEFAULT NULL COMMENT '开始时间',
  `end_datetime` datetime DEFAULT NULL COMMENT '结束时间',
  `total_num` int(11) DEFAULT NULL COMMENT '总人数',
  `remain_num` int(11) DEFAULT NULL COMMENT '剩余人数',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` text COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tgym_activity_order`;
CREATE TABLE `tgym_activity_order` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `activity_code` varchar(32) DEFAULT NULL COMMENT '活动编号',
  `activity_title` varchar(255) DEFAULT NULL COMMENT '活动名称',
  `quantity` int(11) DEFAULT NULL COMMENT '数量',
  `price` bigint(11) DEFAULT NULL COMMENT '单价',
  `amount` bigint(11) DEFAULT NULL COMMENT '总金额',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `pay_type` varchar(32) DEFAULT NULL COMMENT '支付方式',
  `pay_group` varchar(32) DEFAULT NULL COMMENT '支付组号',
  `pay_code` varchar(32) DEFAULT NULL COMMENT '支付编号',
  `pay_amount` bigint(11) DEFAULT NULL COMMENT '支付金额',
  `penalty` bigint(11) DEFAULT NULL COMMENT '违约金额',
  `pay_datetime` datetime DEFAULT NULL COMMENT '支付时间',
  `apply_user` varchar(32) DEFAULT NULL COMMENT '申请人',
  `mobile` varchar(32) DEFAULT NULL COMMENT '手机号',
  `apply_datetime` datetime DEFAULT NULL COMMENT '申请时间',
  `apply_note` varchar(255) DEFAULT NULL COMMENT '申请备注',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` text COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `tgym_coach`;
CREATE TABLE `tgym_coach` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `user_id` varchar(32) DEFAULT NULL COMMENT '教练编号',
  `real_name` varchar(32) DEFAULT NULL COMMENT '真实姓名',
  `pic` text COMMENT '缩略图',
  `gender` varchar(4) DEFAULT NULL COMMENT '性别',
  `age` varchar(32) DEFAULT NULL COMMENT '年龄',
  `duration` int(11) DEFAULT NULL COMMENT '工作年限',
  `star` int(11) DEFAULT NULL COMMENT '星级',
  `star_num` int(11) DEFAULT NULL COMMENT '星数',
  `label` varchar(32) DEFAULT NULL COMMENT '标签',
  `adv_pic` text COMMENT '广告图',
  `sum_com` int(11) DEFAULT NULL COMMENT '评论数',
  `description` text COMMENT '图文描述',
  `location` varchar(32) DEFAULT NULL COMMENT 'UI位置',
  `order_no` int(11) DEFAULT NULL COMMENT 'UI顺序',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `approver` varchar(32) DEFAULT NULL COMMENT '审核人',
  `approve_datetime` datetime DEFAULT NULL COMMENT '审核时间',
  `remark` text,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `tgym_comment`;
CREATE TABLE `tgym_comment` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `score` int(11) DEFAULT NULL COMMENT '星评分数',
  `content` text COMMENT '评论内容',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `commer` varchar(32) DEFAULT NULL COMMENT '评论人',
  `comment_datetime` datetime DEFAULT NULL COMMENT '评论时间',
  `approver` varchar(32) DEFAULT NULL COMMENT '审核人',
  `approve_datetime` datetime DEFAULT NULL COMMENT '审核时间',
  `remark` text COMMENT '备注',
  `product_code` varchar(32) DEFAULT NULL COMMENT '产品编号',
  `coach_code` varchar(32) DEFAULT NULL COMMENT '私教编号',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `tgym_keyword`;
CREATE TABLE `tgym_keyword` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `word` varchar(255) DEFAULT NULL COMMENT '词语',
  `weight` int(11) DEFAULT NULL COMMENT '权重(0-1 0.5以上黑，0.5以下白)',
  `level` varchar(4) DEFAULT NULL COMMENT '作用等级',
  `reaction` varchar(4) DEFAULT NULL COMMENT '反应(1 直接拦截/2 替换**)',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `tgym_org_course`;
CREATE TABLE `tgym_org_course` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `coach_user` varchar(32) DEFAULT NULL COMMENT '任课教练',
  `name` varchar(32) DEFAULT NULL COMMENT '课程名称',
  `sk_start_datetime` datetime DEFAULT NULL COMMENT '上课开始时间',
  `sk_end_datetime` datetime DEFAULT NULL COMMENT '上课结束时间',
  `total_num` int(11) DEFAULT NULL COMMENT '总人数',
  `remain_num` int(11) DEFAULT NULL COMMENT '剩余人数',
  `province` varchar(255) DEFAULT NULL COMMENT '省',
  `city` varchar(255) DEFAULT NULL COMMENT '市',
  `area` varchar(255) DEFAULT NULL COMMENT '区',
  `address` varchar(32) DEFAULT NULL COMMENT '上课地址',
  `contact` varchar(32) DEFAULT NULL COMMENT '联系方式',
  `pic` text COMMENT '缩略图',
  `adv_pic` text COMMENT '广告图',
  `description` text COMMENT '图文描述',
  `price` bigint(11) DEFAULT NULL COMMENT '单价',
  `location` varchar(32) DEFAULT NULL COMMENT 'ui位置',
  `order_no` int(11) DEFAULT NULL COMMENT 'ui顺序',
  `sum_com` int(11) DEFAULT NULL COMMENT '评论数',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` text COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `tgym_org_course_order`;
CREATE TABLE `tgym_org_course_order` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `org_course_code` varchar(32) DEFAULT NULL COMMENT '团课编号',
  `org_course_name` varchar(255) DEFAULT NULL COMMENT '团课名称',
  `quantity` int(11) DEFAULT NULL COMMENT '数量',
  `price` bigint(11) DEFAULT NULL COMMENT '单价',
  `amount` bigint(11) DEFAULT NULL COMMENT '总金额',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `pay_type` varchar(32) DEFAULT NULL COMMENT '支付方式',
  `pay_group` varchar(32) DEFAULT NULL COMMENT '支付组号',
  `pay_code` varchar(32) DEFAULT NULL COMMENT '支付编号',
  `pay_amount` bigint(11) DEFAULT NULL COMMENT '支付金额',
  `penalty` bigint(11) DEFAULT NULL COMMENT '违约金额',
  `pay_datetime` datetime DEFAULT NULL COMMENT '支付时间',
  `apply_user` varchar(32) DEFAULT NULL COMMENT '申请人',
  `mobile` varchar(32) DEFAULT NULL COMMENT '手机号',
  `apply_datetime` datetime DEFAULT NULL COMMENT '申请时间',
  `apply_note` varchar(255) DEFAULT NULL COMMENT '申请备注',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` text COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `tgym_per_course`;
CREATE TABLE `tgym_per_course` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `sk_cycle` int(11) DEFAULT NULL COMMENT '上课周期',
  `sk_start_datetime` varchar(32) DEFAULT NULL COMMENT '上课开始时间',
  `sk_end_datetime` varchar(32) DEFAULT NULL COMMENT '上课结束时间',
  `pic` text COMMENT '缩略图',
  `adv_pic` text COMMENT '广告图',
  `description` text COMMENT '图文描述',
  `price` bigint(11) DEFAULT NULL COMMENT '单价',
  `location` varchar(32) DEFAULT NULL COMMENT 'ui位置',
  `order_no` int(11) DEFAULT NULL COMMENT 'ui顺序',
  `sum_com` int(11) DEFAULT NULL COMMENT '评论数',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `coach_code` varchar(32) DEFAULT NULL COMMENT '教练编号',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tgym_per_course_order`;
CREATE TABLE `tgym_per_course_order` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `to_user` varchar(32) DEFAULT NULL COMMENT '私教编号',
  `per_course_code` varchar(32) DEFAULT NULL COMMENT '私课编号',
  `course_name` varchar(32) DEFAULT NULL COMMENT '私课名称',
  `appoint_datetime` datetime DEFAULT NULL COMMENT '预约时间',
  `sk_datetime` varchar(32) DEFAULT NULL COMMENT '上课时间',
  `xk_datetime` varchar(32) DEFAULT NULL COMMENT '下课时间',
  `address` varchar(255) DEFAULT NULL COMMENT '上课地址',
  `quantity` int(11) DEFAULT NULL COMMENT '数量',
  `price` bigint(11) DEFAULT NULL COMMENT '单价',
  `amount` bigint(11) DEFAULT NULL COMMENT '总金额',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `sk_start_datetime` datetime DEFAULT NULL COMMENT '上课时间',
  `sk_end_datetime` datetime DEFAULT NULL COMMENT '下课时间',
  `pay_type` varchar(32) DEFAULT NULL COMMENT '支付方式',
  `pay_group` varchar(32) DEFAULT NULL COMMENT '支付组号',
  `pay_code` varchar(32) DEFAULT NULL COMMENT '支付编号',
  `pay_amount` bigint(11) DEFAULT NULL COMMENT '支付金额',
  `penalty` bigint(11) DEFAULT NULL COMMENT '违约金额',
  `pay_datetime` datetime DEFAULT NULL COMMENT '支付时间',
  `apply_user` varchar(32) DEFAULT NULL COMMENT '申请人',
  `mobile` varchar(32) DEFAULT NULL COMMENT '手机号',
  `apply_datetime` datetime DEFAULT NULL COMMENT '申请时间',
  `apply_note` varchar(255) DEFAULT NULL COMMENT '申请备注',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` text COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tgym_praise_item`;
CREATE TABLE `tgym_praise_item` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `name` varchar(32) DEFAULT NULL COMMENT '项目名称',
  `score` int(11) DEFAULT NULL COMMENT '得分',
  `comment_code` varchar(32) DEFAULT NULL COMMENT '评论编号',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tsys_config`;
CREATE TABLE `tsys_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `type` varchar(32) DEFAULT NULL COMMENT '类型',
  `ckey` varchar(32) DEFAULT NULL COMMENT 'key值',
  `cvalue` varchar(255) DEFAULT NULL COMMENT '值',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tsys_dict`;
CREATE TABLE `tsys_dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号（自增长）',
  `type` char(1) DEFAULT NULL COMMENT '类型（第一层/第二层）',
  `parent_key` varchar(32) DEFAULT NULL COMMENT '父key',
  `dkey` varchar(32) DEFAULT NULL COMMENT 'key',
  `dvalue` varchar(255) DEFAULT NULL COMMENT '值',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
