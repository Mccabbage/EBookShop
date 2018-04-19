CREATE TABLE seckill(
seckill_id BIGINT(20) AUTO_INCREMENT COMMENT '主键Id',
NAME VARCHAR(20) NOT NULL COMMENT '商品名称',
stock INT NOT NULL COMMENT '库存',
start_time TIMESTAMP NOT NULL COMMENT '秒杀开始时间',
end_time TIMESTAMP NOT NULL COMMENT '秒杀结束时间',
create_time TIMESTAMP NOT NULL COMMENT '秒杀创建时间',
PRIMARY KEY (seckill_id),
KEY idx_create_time(create_time)
)ENGINE=INNODB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT '秒杀表';

INSERT INTO
seckill(NAME,stock,start_time,end_time,create_time)
VALUES
('MAC PRO',10,'2018-04-15 00:00:00','2018-04-16 00:00:00',NOW()),
('iPad',10,'2018-04-15 00:00:00','2018-04-16 00:00:00',NOW());

CREATE TABLE `success_killed` (
  `seckill_id` bigint(20) NOT NULL,
  `user_phone` bigint(20) NOT NULL,
  `state` tinyint(4) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`seckill_id`,`user_phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='秒杀成功表';
