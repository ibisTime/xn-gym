CREATE TABLE `tduijie_caigopool` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `type` varchar(4) DEFAULT NULL COMMENT '类型',
  `rate` decimal(18,8) DEFAULT NULL COMMENT '1个三方币，兑换rate个菜狗币',
  `amount` bigint(20) DEFAULT NULL COMMENT '余额',
  `used_amount` bigint(20) DEFAULT NULL COMMENT '被使用的金额',
  `add_user` varchar(32) DEFAULT NULL COMMENT '最后入金人',
  `add_datetime` datetime DEFAULT NULL COMMENT '最后入金时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `tduijie_caigopool_back` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `pool_code` varchar(32) DEFAULT NULL COMMENT '对应池编号',
  `pool_name` varchar(255) DEFAULT NULL COMMENT '对应池名称',
  `from_amount` bigint(20) DEFAULT NULL COMMENT '三方系统的金额',
  `from_currency` varchar(32) DEFAULT NULL COMMENT '三方系统的币种',
  `from_user` varchar(32) DEFAULT NULL COMMENT '三方系统的用户（手机号）',
  `to_amount` bigint(20) DEFAULT NULL COMMENT '我方系统的金额',
  `to_currency` varchar(32) DEFAULT NULL COMMENT '我方系统的币种',
  `to_user` varchar(32) DEFAULT NULL COMMENT '我方系统的用户（手机号）',
  `create_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `tfund_stock` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `user_id` varchar(32) DEFAULT NULL COMMENT '拥有者',
  `fund_code` varchar(32) DEFAULT NULL COMMENT '从哪个池中拿的钱',
  `cost_amount` bigint(20) DEFAULT NULL COMMENT '成本金额',
  `cost_currency` varchar(32) DEFAULT NULL COMMENT '成本币种',
  `back_interval` int(11) DEFAULT NULL COMMENT '返利间隔',
  `profit_amount` bigint(20) DEFAULT NULL COMMENT '收益金额',
  `profit_currency` varchar(32) DEFAULT NULL COMMENT '收益币种',
  `back_count` int(11) DEFAULT NULL COMMENT '已返还次数',
  `back_amount` bigint(20) DEFAULT NULL COMMENT '已返还福利',
  `today_amount` bigint(20) DEFAULT NULL COMMENT '今日可得福利',
  `next_back_date` datetime DEFAULT NULL COMMENT '下次返还时间',
  `create_datetime` datetime DEFAULT NULL COMMENT '生成时间',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `tfund_stock_back` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `fund_code` varchar(32) DEFAULT NULL COMMENT '从哪个池中拿的钱',
  `stock_code` varchar(32) DEFAULT NULL COMMENT '因为哪个分红权',
  `to_user` varchar(32) DEFAULT NULL COMMENT '返还给谁的',
  `to_amount` bigint(20) DEFAULT NULL COMMENT '返还金额',
  `to_currency` varchar(32) DEFAULT NULL COMMENT '返还币种',
  `create_datetime` datetime DEFAULT NULL COMMENT '生成时间',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `tmall_cart` (
  `code` varchar(32) NOT NULL COMMENT '购物车编号',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户编号',
  `product_code` varchar(32) DEFAULT NULL COMMENT '产品编号',
  `quantity` int(11) DEFAULT NULL COMMENT '数量',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  PRIMARY KEY (`code`) COMMENT '购物车'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `tmall_category` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `type` varchar(4) DEFAULT NULL COMMENT '类型(1 商品 2 商铺)',
  `parent_code` varchar(32) DEFAULT NULL COMMENT '父节点',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `pic` varchar(255) DEFAULT NULL COMMENT '图片',
  `order_no` int(11) DEFAULT NULL COMMENT '序号',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  PRIMARY KEY (`code`) COMMENT '分类'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `tmall_order` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `type` varchar(4) DEFAULT NULL COMMENT '类型',
  `to_user` varchar(32) DEFAULT NULL COMMENT '向谁要货',
  `receiver` varchar(255) DEFAULT NULL COMMENT '收件人姓名',
  `re_mobile` varchar(32) DEFAULT NULL COMMENT '收件人电话',
  `re_address` varchar(255) DEFAULT NULL COMMENT '收货地址',
  `receipt_type` varchar(4) DEFAULT NULL COMMENT '发票类型(1 个人，2 企业)',
  `receipt_title` varchar(32) DEFAULT NULL COMMENT '发票抬头',
  `apply_user` varchar(32) DEFAULT NULL COMMENT '下单人',
  `apply_note` varchar(255) DEFAULT NULL COMMENT '下单备注',
  `apply_datetime` datetime DEFAULT NULL COMMENT '下单时间',
  `amount1` bigint(20) DEFAULT NULL COMMENT '金额1',
  `amount2` bigint(20) DEFAULT NULL COMMENT '金额2',
  `amount3` bigint(20) DEFAULT NULL COMMENT '金额3',
  `yunfei` bigint(20) DEFAULT NULL COMMENT '运费',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `pay_group` varchar(32) DEFAULT NULL COMMENT '支付组号',
  `pay_code` varchar(32) DEFAULT NULL COMMENT '支付编号',
  `pay_datetime` datetime DEFAULT NULL COMMENT '支付时间',
  `pay_amount1` bigint(20) DEFAULT NULL COMMENT '支付金额1',
  `pay_amount1_1` bigint(20) DEFAULT NULL COMMENT '支付金额1_1（多种币种合并时使用）',
  `pay_amount2` bigint(20) DEFAULT NULL COMMENT '支付金额2',
  `pay_amount3` bigint(20) DEFAULT NULL COMMENT '支付金额3',
  `prompt_times` int(11) DEFAULT NULL COMMENT '催货次数',
  `deliverer` varchar(32) DEFAULT NULL COMMENT '发货人编号',
  `delivery_datetime` datetime DEFAULT NULL COMMENT '发货时间',
  `logistics_company` varchar(32) DEFAULT NULL COMMENT '物流公司编号',
  `logistics_code` varchar(32) DEFAULT NULL COMMENT '物流单号',
  `pdf` varchar(255) DEFAULT NULL COMMENT '物流单',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  PRIMARY KEY (`code`) COMMENT '订单'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `tmall_product` (
  `code` varchar(32) NOT NULL COMMENT '商品编号',
  `category` varchar(32) DEFAULT NULL COMMENT '大类',
  `type` varchar(32) DEFAULT NULL COMMENT '小类',
  `name` varchar(255) DEFAULT NULL COMMENT '商品名称',
  `slogan` varchar(255) DEFAULT NULL COMMENT '广告语',
  `adv_pic` varchar(255) DEFAULT NULL COMMENT '广告图',
  `pic` text COMMENT 'pic',
  `description` text COMMENT '图文描述',
  `original_price` bigint(20) DEFAULT NULL COMMENT '原价',
  `price1` bigint(20) DEFAULT NULL COMMENT '价格1（人民币）',
  `price2` bigint(20) DEFAULT NULL COMMENT '价格2（虚拟币1）',
  `price3` bigint(20) DEFAULT NULL COMMENT '价格3（虚拟币2）',
  `location` varchar(32) DEFAULT NULL COMMENT '位置(0 普通 1 热门)',
  `order_no` int(11) DEFAULT NULL COMMENT '相对位置编号',
  `status` varchar(4) DEFAULT NULL COMMENT '产品状态',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `bought_count` int(11) DEFAULT NULL COMMENT '已购买数量',
  `company_code` varchar(32) DEFAULT NULL COMMENT '所属公司',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  PRIMARY KEY (`code`) COMMENT '产品'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `tmall_product_order` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `order_code` varchar(32) DEFAULT NULL COMMENT '订单编号',
  `product_code` varchar(32) DEFAULT NULL COMMENT '商品编号',
  `quantity` int(11) DEFAULT NULL COMMENT '数量',
  `price1` bigint(20) DEFAULT NULL COMMENT '价格1',
  `price2` bigint(20) DEFAULT NULL COMMENT '价格2',
  `price3` bigint(20) DEFAULT NULL COMMENT '价格3',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  PRIMARY KEY (`code`) COMMENT '订单产品'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `tmall_product_specs` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `product_code` varchar(32) DEFAULT NULL COMMENT '商品编号',
  `dkey` varchar(64) DEFAULT NULL COMMENT '规格参数名',
  `dvalue` varchar(255) DEFAULT NULL COMMENT '规格参数值',
  `order_no` int(11) DEFAULT NULL COMMENT '相对位置编号',
  `company_code` varchar(32) DEFAULT NULL COMMENT '所属公司',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `to2o_purchase` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户编号',
  `store_code` varchar(32) DEFAULT NULL COMMENT '商家编号',
  `ticket_code` varchar(32) DEFAULT NULL COMMENT '折扣券',
  `price` bigint(20) DEFAULT NULL COMMENT '服务总价',
  `back_amount` bigint(20) DEFAULT NULL COMMENT '返现金额',
  `back_currency` varchar(32) DEFAULT NULL COMMENT '返现币种',
  `create_datetime` datetime DEFAULT NULL COMMENT '消费时间',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `pay_type` varchar(4) DEFAULT NULL COMMENT '支付方式',
  `pay_group` varchar(32) DEFAULT NULL COMMENT '支付组号',
  `pay_code` varchar(32) DEFAULT NULL COMMENT '支付编号',
  `pay_amount1` bigint(20) DEFAULT NULL COMMENT '人民币',
  `pay_amount2` bigint(20) DEFAULT NULL COMMENT '虚拟币1',
  `pay_amount3` bigint(20) DEFAULT NULL COMMENT '虚拟币2',
  `pay_datetime` datetime DEFAULT NULL COMMENT '支付时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  PRIMARY KEY (`code`) COMMENT '服务痕迹'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `to2o_stock_back` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `fund_code` varchar(32) DEFAULT NULL COMMENT '从哪个池中拿的钱',
  `stock_code` varchar(32) DEFAULT NULL COMMENT '因为哪个分红权',
  `to_user` varchar(32) DEFAULT NULL COMMENT '返还给谁的',
  `to_amount` bigint(20) DEFAULT NULL COMMENT '返还金额',
  `to_currency` varchar(32) DEFAULT NULL COMMENT '返还币种',
  `create_datetime` datetime DEFAULT NULL COMMENT '生成时间',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `to2o_store` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `level` varchar(32) DEFAULT NULL COMMENT '店铺等级',
  `type` varchar(32) DEFAULT NULL COMMENT '类型',
  `slogan` varchar(255) DEFAULT NULL COMMENT '广告语',
  `adv_pic` text COMMENT '店铺缩略图',
  `pic` text COMMENT '店铺图片',
  `description` text COMMENT '商家描述',
  `province` varchar(32) DEFAULT NULL COMMENT '省',
  `city` varchar(32) DEFAULT NULL COMMENT '市',
  `area` varchar(32) DEFAULT NULL COMMENT '区',
  `address` varchar(255) DEFAULT NULL COMMENT '具体地址',
  `longitude` varchar(255) DEFAULT NULL COMMENT '经度',
  `latitude` varchar(255) DEFAULT NULL COMMENT '维度',
  `book_mobile` varchar(32) DEFAULT NULL COMMENT '预定联系电话',
  `sms_mobile` varchar(32) DEFAULT NULL COMMENT '短信手机号',
  `pdf` varchar(255) DEFAULT NULL COMMENT '附件',
  `ui_location` varchar(32) DEFAULT NULL COMMENT 'UI位置',
  `ui_order` int(11) DEFAULT NULL COMMENT 'UI序号',
  `legal_person_name` varchar(64) DEFAULT NULL COMMENT '法人姓名',
  `user_referee` varchar(32) DEFAULT NULL COMMENT '推荐人',
  `rate1` decimal(18,8) DEFAULT NULL COMMENT '使用券的折扣',
  `rate2` decimal(18,8) DEFAULT NULL COMMENT '不使用券的折扣',
  `rate3` decimal(18,8) DEFAULT NULL COMMENT '返点比例',
  `is_default` char(1) DEFAULT NULL COMMENT '是否默认',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `owner` varchar(32) DEFAULT NULL COMMENT '店铺主人',
  `contract_no` varchar(255) DEFAULT NULL COMMENT '合同编号',
  `total_rmb_num` bigint(20) DEFAULT '0' COMMENT '累计人民币数量',
  `total_jf_num` bigint(20) DEFAULT '0' COMMENT '累计积分数量',
  `total_dz_num` int(11) DEFAULT '0' COMMENT '累计点赞数',
  `total_sc_num` int(11) DEFAULT '0' COMMENT '累计收藏数',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  PRIMARY KEY (`code`) COMMENT '店铺表'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `to2o_store_action` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `type` varchar(4) DEFAULT NULL COMMENT '互动类型(1 点赞 2 收藏)',
  `action_user` varchar(32) DEFAULT NULL COMMENT '互动人',
  `action_datetime` datetime DEFAULT NULL COMMENT '互动时间',
  `store_code` varchar(32) DEFAULT NULL COMMENT '店铺编号',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  PRIMARY KEY (`code`) COMMENT '店铺交互'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `to2o_store_ticket` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `name` varchar(64) DEFAULT NULL COMMENT '名称',
  `type` varchar(4) DEFAULT NULL COMMENT '类型(1 满减 2 返现)',
  `key1` bigint(20) DEFAULT NULL COMMENT 'key1',
  `key2` bigint(20) DEFAULT NULL COMMENT 'key2',
  `description` text COMMENT '使用详情',
  `price` bigint(20) DEFAULT NULL COMMENT '销售价格',
  `currency` varchar(4) DEFAULT NULL COMMENT '销售币种',
  `validate_start` datetime DEFAULT NULL COMMENT '有效期起',
  `validate_end` datetime DEFAULT NULL COMMENT '有效期止',
  `create_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  `status` varchar(4) DEFAULT NULL COMMENT '状态（待上架，已上架，已下架，期满作废）',
  `store_code` varchar(32) DEFAULT NULL COMMENT '所属商家编号',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  PRIMARY KEY (`code`) COMMENT '店铺折扣券'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `to2o_user_ticket` (
  `code` varchar(32) NOT NULL DEFAULT '' COMMENT '编号',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户编号',
  `ticket_code` varchar(32) DEFAULT NULL COMMENT '折扣券编号',
  `create_datetime` datetime DEFAULT NULL COMMENT '购买时间',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  PRIMARY KEY (`code`) COMMENT '用户折扣券'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `tsys_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `type` varchar(32) DEFAULT NULL COMMENT '类型',
  `ckey` varchar(32) DEFAULT NULL COMMENT 'key值',
  `cvalue` text COMMENT '值',
  `updater` varchar(32) NOT NULL COMMENT '更新人',
  `update_datetime` datetime NOT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  PRIMARY KEY (`id`) COMMENT '系统参数'
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

CREATE TABLE `tsys_dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号（自增长）',
  `type` char(1) NOT NULL COMMENT '类型（0=下拉框意义 1=下拉框选项）',
  `parent_key` varchar(32) DEFAULT NULL COMMENT '父key',
  `dkey` varchar(32) NOT NULL COMMENT 'key',
  `dvalue` varchar(64) NOT NULL COMMENT '值',
  `updater` varchar(32) NOT NULL COMMENT '更新人',
  `update_datetime` datetime NOT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  PRIMARY KEY (`id`) COMMENT '数据字典'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
