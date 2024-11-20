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

-- 队伍表
-- auto-generated definition
create table team
(
    id            bigint auto_increment comment 'id'
        primary key,
    teamName      varchar(1024) default ''                      not null comment '团队名',
    profile       varchar(512)                                  null comment '队伍简介',
    tags          varchar(1024)                                 null comment '队伍标签',
    avatarUrl     varchar(1024) default 'src/assets/01geek.jpg' not null comment '队伍头像',
    currentMember varchar(1024) default ''                      not null comment '当前成员，json数组格式字符串',
    historyMember varchar(2048) default ''                      not null comment '历史成员，json数组格式字符串',
    historyLeader varchar(1024) default ''                      not null comment '历史队长，json数组格式字符串',
    maxMemberNum  int           default 5                       not null comment '队伍人数上限',
    isDelete      tinyint       default 0                       not null comment '队伍是否已解散，0-存在，1-已解散',
    createTime    datetime      default CURRENT_TIMESTAMP       null comment '创建时间',
    updateTime    datetime      default CURRENT_TIMESTAMP       null on update CURRENT_TIMESTAMP comment '更新时间',
    deleteTime    datetime                                      null comment '删除时间'
)
    comment '队伍表';

-- 队伍id-标签表
create table team_tag_forSearch
(
    teamId     bigint                                  null comment '队伍id',
    id           bigint auto_increment comment 'id'
        primary key,
    tags         varchar(1024)                                 null comment '标签 json 列表',
    constraint unique_userAccount
        unique (teamId)
)
    comment '队伍id-标签表';

create definer = root@localhost trigger delete_table_team_tag_forSearch_by_team
    after delete
    on team
    for each row
BEGIN
    -- team_tag_forSearch 表更新数据
    delete from team_tag_forsearch
    where teamId = OLD.id;
END;

create definer = root@localhost trigger insert_table_team_tag_forSearch_by_team
    after insert
    on team
    for each row
BEGIN
    -- 向 team_tag_forsearch 表插入数据
    INSERT INTO team_tag_forsearch (teamId, tags)
    VALUES (NEW.id, NEW.tags);
END;

create definer = root@localhost trigger update_table_team_tag_forSearch_by_team
    after update
    on team
    for each row
BEGIN
    -- team_tag_forSearch 表更新数据
    update team_tag_forsearch
    set tags = NEW.tags
    where teamId = NEW.id;
END;




