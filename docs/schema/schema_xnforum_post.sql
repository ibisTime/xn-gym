-- ----------------------------
--  Table structure for `tforum_post`
-- ----------------------------
DROP TABLE IF EXISTS `tforum_post`;
CREATE TABLE `tforum_post` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `title` text CHARACTER SET utf8mb4 COMMENT '标题',
  `content` text CHARACTER SET utf8mb4 COMMENT '内容',
  `pic` text COMMENT '图片',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `publisher` varchar(32) DEFAULT NULL COMMENT '发布人',
  `publish_datetime` datetime DEFAULT NULL COMMENT '发布时间',
  `approver` varchar(32) DEFAULT NULL COMMENT '审核人',
  `approve_datetime` datetime DEFAULT NULL COMMENT '审核时间',
  `approve_note` varchar(255) DEFAULT NULL COMMENT '审核说明',
  `location` varchar(4) DEFAULT NULL COMMENT '位置(A 置顶 B 精华 C头条)',
  `order_no` int(11) DEFAULT NULL COMMENT '序号',
  `plate_code` varchar(32) DEFAULT NULL COMMENT '板块编号',
  `is_lock` char(1) DEFAULT NULL COMMENT '是否锁帖',
  `sum_comment` int(11) DEFAULT NULL COMMENT '评论数',
  `sum_like` int(11) DEFAULT NULL COMMENT '点赞数',
  `sum_read` int(11) DEFAULT NULL COMMENT '阅读数',
  `sum_reward` int(11) DEFAULT NULL COMMENT '打赏人数',
  `remark` text DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tforum_post_talk`
-- ----------------------------
DROP TABLE IF EXISTS `tforum_post_talk`;
CREATE TABLE `tforum_post_talk` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `type` varchar(4) DEFAULT NULL COMMENT '类型(1 点赞 2 收藏 3 打赏 4 举报)',
  `post_code` varchar(32) DEFAULT NULL COMMENT '帖子编号',
  `talker` varchar(32) DEFAULT NULL COMMENT '操作人',
  `talk_datetime` datetime DEFAULT NULL COMMENT '操作时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tforum_comment`
-- ----------------------------
DROP TABLE IF EXISTS `tforum_comment`;
CREATE TABLE `tforum_comment` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `content` text CHARACTER SET utf8mb4 COMMENT '内容',
  `parent_code` varchar(32) DEFAULT NULL COMMENT '上级编号',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `commer` varchar(32) DEFAULT NULL COMMENT '评论人',
  `comm_datetime` datetime DEFAULT NULL COMMENT '评论时间',
  `approver` varchar(32) DEFAULT NULL COMMENT '审核人',
  `approve_datetime` datetime DEFAULT NULL COMMENT '审核时间',
  `approve_note` varchar(255) DEFAULT NULL COMMENT '审核意见',
  `post_code` varchar(32) DEFAULT NULL COMMENT '帖子编号',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;