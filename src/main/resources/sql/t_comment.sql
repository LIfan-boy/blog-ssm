
use db_blog;

create table t_comment(
	`comment_id` int(32) not null auto_increment comment '评论id',
	`visitor_id` int(32) not null comment '访客id（评论者id）',
	`comment_date` datetime not null comment '评论时间',
	`comment_content` text comment '评论内容',
	`audit_status` int(10) not null comment '审核状态',
	`blog_id` int(32) not null comment '外键关联博客',
	PRIMARY KEY (`comment_id`),
   	KEY `blog_id` (`blog_id`),
   	foreign key (`blog_id`) references `t_blog` (`id`)
)engine=innoDB default charset=utf8;