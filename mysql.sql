# 创建商品表
CREATE TABLE `product_info`(`product_id` VARCHAR(32) NOT NULL,`product_name` VARCHAR(64) NOT NULL COMMENT '商品名称',
`product_price` DECIMAL(8,2) NOT NULL COMMENT '单价',`product_stock` INT NOT NULL COMMENT '单价',
`product_description` VARCHAR(64) COMMENT '描述',
`product_icon` VARCHAR(512) COMMENT '商品小图',
`category_type` int NOT NULL COMMENT '类目编号',
`product_status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '0正常，1下架',
`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
`update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP on UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
PRIMARY key(`product_id`)
) COMMENT '商品表';

# 创建类目表
CREATE table `product_category`(`category_id` int not null auto_increment,
`category_name` VARCHAR(64) NOT NULL COMMENT "类目名称",
`category_type` int NOT null COMMENT '类目编号',
`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
`update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP on UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
PRIMARY key(`category_id`),
unique key `uqe_category_type` (`category_type`)
) COMMENT '类目表';


CREATE table `order_master`(`order_id` varchar(255) not null ,
`buyer_name` VARCHAR(64) NOT NULL COMMENT "买家名字",
`buyer_phone`VARCHAR(32) NOT null COMMENT '买家电话',
`buyer_address`VARCHAR(128) NOT null COMMENT '买家地址',
`buyer_openid`VARCHAR(64) NOT null COMMENT '买家微信openid',
`order_amount` DECIMAL(8,2)  NOT null COMMENT '订单总金额',
`order_status` TINYINT(3)  NOT NULL DEFAULT '0' COMMENT '订单状态，默认0新下单',
`pay_status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '支付状态,默认0未支付',
`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
`update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP on UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
PRIMARY key(`order_id`),
key `idx_buyer_openid` (`buyer_openid`)
) COMMENT '订单表';

# 创建订单详情表
CREATE table `order_detail`(`detail_id` int not null ,
`order_id` VARCHAR(32) NOT NULL,
`product_id` VARCHAR(32) NOT NULL,
`product_name` VARCHAR(64) NOT NULL COMMENT "商品名称",
`product_price` decimal(8,2) NOT null COMMENT '商品价格',
`product_quantity` int NOT null COMMENT '商品数量',
`product_icon` VARCHAR(512) COMMENT '商品小图',
`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
`update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP on UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
PRIMARY key(`detail_id`),
key `idx_order_id` (`order_id`)
) COMMENT '订单详情表';

# 创建article表
CREATE TABLE `articleVO`(`id` INT(11) NOT NULL auto_increment,
                       `author` VARCHAR(32) NOT NULL,
                       `title` VARCHAR(32) NOT NULL,
                       `content` VARCHAR(512) NOT NULL,
                       `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP on
                           UPDATE CURRENT_TIMESTAMP,PRIMARY key(`id`)
);
# 创建message表
CREATE TABLE `message`  (
                            `id` bigint(20) NOT NULL AUTO_INCREMENT,
                            `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                            `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

# 用户信息表
CREATE table `user_info`(`user_id` int not null auto_increment,
`user_name` VARCHAR(64) NOT NULL COMMENT "用户名",
`user_password` VARCHAR(64) NOT null COMMENT '密码',
`user_phone` bigint NOT null COMMENT '手机号码',
`user_address` VARCHAR(256) COMMENT '地址',
`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
`update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP on UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
PRIMARY key(`user_id`),
unique key `uqe_user_id` (`user_id`)
) COMMENT '用户信息表';

# 部门表
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept` (
  `deptno` int(10) NOT NULL COMMENT '部门编号',
  `dname` varchar(14) DEFAULT NULL COMMENT '部门名称',
  `loc` varchar(13) DEFAULT NULL COMMENT '部门位置',
  PRIMARY KEY (`deptno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# 插入部门信息
INSERT INTO `dept` VALUES ('10', '后勤部门', 'NEW YORK');
INSERT INTO `dept` VALUES ('20', '开发部门', 'DALLAS');
INSERT INTO `dept` VALUES ('30', '销售部门', 'CHICAGO');
INSERT INTO `dept` VALUES ('40', '推广部门', 'BOSTON');

# 员工表emp
DROP TABLE IF EXISTS `emp`;
CREATE TABLE `emp` (
  `empno` int(4) NOT NULL,
  `ename` varchar(10) DEFAULT NULL,
  `job` varchar(9) DEFAULT NULL,
  `mgr` int(4) DEFAULT NULL COMMENT '直属领导工号',
  `hiredate` date DEFAULT NULL COMMENT '入职日期',
  `sal` double(7,2) DEFAULT NULL,
  `comm` double(7,2) DEFAULT NULL COMMENT '补贴',
  `deptno` int(2) DEFAULT NULL,
  PRIMARY KEY (`empno`)
) COMMENT '员工表emp' ENGINE=InnoDB DEFAULT CHARSET=utf8;
# 插入语句
INSERT INTO `emp` VALUES ('7369', '张三', 'CLERK', '7902', '1980-12-17', '800.00', null, '20');
INSERT INTO `emp` VALUES ('7499', '李四', 'SALESMAN', '7698', '1981-02-20', '1600.00', '300.00', '30');
INSERT INTO `emp` VALUES ('7521', '王二', 'SALESMAN', '7698', '1981-02-22', '1250.00', '500.00', '30');
INSERT INTO `emp` VALUES ('7566', '李武', 'MANAGER', '7839', '1981-04-02', '2975.00', null, '20');
INSERT INTO `emp` VALUES ('7654', '周六', 'SALESMAN', '7698', '1981-09-28', '1250.00', '1400.00', '30');
INSERT INTO `emp` VALUES ('7698', '刘振', 'MANAGER', '7839', '1981-05-01', '2850.00', null, '30');
INSERT INTO `emp` VALUES ('7782', '曹阳', 'MANAGER', '7839', '1981-06-09', '2450.00', null, '10');
INSERT INTO `emp` VALUES ('7788', '爱丽丝', 'ANALYST', '7566', '1987-04-19', '3000.00', null, '20');
INSERT INTO `emp` VALUES ('7839', '哪吒', 'PRESIDENT', null, '1981-11-17', '5000.00', null, '10');
INSERT INTO `emp` VALUES ('7844', '赵云', 'SALESMAN', '7698', '1981-09-08', '1500.00', null, '30');
INSERT INTO `emp` VALUES ('7876', '妲己', 'CLERK', '7788', '1987-05-23', '1100.00', null, '20');
INSERT INTO `emp` VALUES ('7900', '刘备', 'CLERK', '7698', '1981-12-03', '950.00', null, '30');
INSERT INTO `emp` VALUES ('7902', '猴子', 'ANALYST', '7566', '1981-12-03', '3000.00', null, '20');
INSERT INTO `emp` VALUES ('7934', '安其拉', 'CLERK', '7782', '1982-01-23', '950.00', null, '10');

# 薪水等级表salgrade
DROP TABLE IF EXISTS `salgrade`;
CREATE TABLE `salgrade` (
  `grade` int(11) DEFAULT NULL COMMENT '薪水等级',
  `losal` int(11) DEFAULT NULL COMMENT '薪水最低',
  `hisal` int(11) DEFAULT NULL COMMENT '薪水最高'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `salgrade` VALUES ('1', '700', '1200');
INSERT INTO `salgrade` VALUES ('2', '1201', '1400');
INSERT INTO `salgrade` VALUES ('3', '1401', '2000');
INSERT INTO `salgrade` VALUES ('4', '2001', '3000');
INSERT INTO `salgrade` VALUES ('5', '3001', '5000');


DROP TABLE IF EXISTS `nbateam`;
CREATE TABLE `nbateam` (
  `nba_id` int(11) NOT NULL auto_increment COMMENT 'nba球队id',
  `nba_name` VARCHAR (11) DEFAULT NULL COMMENT '球队名称',
  `nba_player` VARCHAR (11) DEFAULT NULL COMMENT '球员名字',
  primary key(`nba_id`)
)  ENGINE=InnoDB DEFAULT CHARSET=utf8
insert into nbateam (nba_name, nba_player) values ("骑士队","詹姆斯")