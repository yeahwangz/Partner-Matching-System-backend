-- 创建用户表
-- auto-generated definition
create table user
(
    userName     varchar(256)                                  null comment '用户名称',
    id           bigint auto_increment comment 'id'
        primary key,
    tags         varchar(1024)                                 null comment '标签 json 列表',
    userAccount  varchar(256)                                  not null comment '账号',
    avatarUrl    varchar(1024) default 'src/assets/01geek.jpg' not null comment '头像',
    gender       tinyint                                       null comment '性别 0--女  1--男',
    userPassword varchar(512)                                  not null comment '密码',
    phone        varchar(128)                                  null comment '电话',
    email        varchar(512)                                  null comment '邮箱',
    userStatus   int           default 0                       not null comment '状态 0 - 正常',
    createTime   datetime      default CURRENT_TIMESTAMP       null comment '创建时间',
    updateTime   datetime      default CURRENT_TIMESTAMP       null on update CURRENT_TIMESTAMP,
    isDelete     tinyint       default 0                       not null comment '是否删除',
    userRole     int           default 0                       not null comment '用户角色 0 - 普通用户 1 - 管理员',
    profile      varchar(512)                                  null comment '个人简介',
    constraint unique_userAccount
        unique (userAccount)
)
    comment '用户';

-- 创建标签用户表，所有需要用到的标签，
-- 这些标签由用户创建并存储于属性列 userId
-- auto-generated definition
create table tag
(
    id         bigint auto_increment comment 'id'
        primary key,
    tagName    varchar(256)                       null comment '标签名称',
    userId     bigint                             null comment '用户 id',
    parentId   bigint                             null comment '父标签 id',
    isParent   tinyint                            null comment '0 - 不是，1 - 父标签',
    createTime datetime default CURRENT_TIMESTAMP null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    isDelete   tinyint  default 0                 not null comment '是否删除',
    constraint uniIdx_tagName
        unique (tagName) comment '唯一标签名'
)
    comment '标签';

create index idx_userId
    on tag (userId)
    comment '创建标签用户';

-- 用户id-标签表
create table user_tag_forSearch
(
    userId     bigint                                  null comment '用户id',
    id           bigint auto_increment comment 'id'
        primary key,
    tags         varchar(1024)                                 null comment '标签 json 列表',
    constraint unique_userAccount
        unique (userId)
)
    comment '用户id-标签表';

