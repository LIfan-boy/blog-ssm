
use db_blog;

create table t_visitor(
	`visitor_id` int(32) not null auto_increment comment '访客id',
	`email` varchar(100) not null comment '注册邮箱',
	`nikeName` varchar(100) not null comment '昵称',
	`signContent` varchar(255) not null comment '签名',
	`regTime` datetime not null comment '注册时间',
	primary key (`visitor_id`)
)engine=innoDB default charset=utf8;

insert into t_visitor values(null, '844125097@qq.com', '阿凡达', '天上九头鸟，地上湖北佬！', now());