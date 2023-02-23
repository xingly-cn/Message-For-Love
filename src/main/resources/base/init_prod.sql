CREATE TABLE `message`
(
    `id`          varchar(255) NOT NULL COMMENT '用户ID',
    `uid`         varchar(255) NOT NULL COMMENT '微信用户ID',
    `phone`       varchar(20)  NOT NULL COMMENT '手机号',
    `status`      char(1)      NOT NULL DEFAULT '0' COMMENT '是否发送',
    `content`     text COMMENT '短信内容',
    `create_time` datetime              DEFAULT NULL COMMENT '创建时间',
    `send_time`   datetime              DEFAULT NULL COMMENT '发送时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB;

CREATE TABLE `user`
(
    `id`      varchar(255) NOT NULL COMMENT '用户ID',
    `uid`     varchar(255) NOT NULL COMMENT '微信用户ID',
    `cost`    int          NOT NULL DEFAULT '0' COMMENT '可发送短信数量',
    `max_num` int          NOT NULL DEFAULT '0' COMMENT '每日发送上线',
    `vip`     char(1)      NOT NULL DEFAULT '0' COMMENT '是否会员',
    `time`    datetime              DEFAULT NULL COMMENT '注册时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB;

CREATE TABLE `remote`
(
    `id`    varchar(255) NOT NULL COMMENT '用户ID',
    `price` varchar(255) NOT NULL DEFAULT '0' COMMENT '价格'
) ENGINE=InnoDB;