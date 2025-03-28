create table category
(
    id         bigint auto_increment
        primary key,
    name       varchar(64)                        not null,
    parent_id  bigint                             null,
    icon       varchar(256)                       null,
    sort       int      default 0                 null,
    created_at datetime default CURRENT_TIMESTAMP null,
    is_deleted tinyint  default 0                 null comment '0-未删除, 1-已删除'
);

create table tag
(
    id         bigint auto_increment
        primary key,
    name       varchar(64)                        not null,
    category   varchar(32)                        null,
    created_at datetime default CURRENT_TIMESTAMP null,
    is_deleted tinyint  default 0                 null comment '0-未删除, 1-已删除',
    constraint name
        unique (name)
);

create table user
(
    id            bigint auto_increment
        primary key,
    username      varchar(64)                        not null,
    email         varchar(128)                       null,
    phone         varchar(16)                        null,
    password_hash varchar(256)                       not null,
    avatar        varchar(256)                       null,
    signature     varchar(512)                       null,
    gender        int                                null,
    birth_date    datetime(6)                        null,
    level         int      default 0                 null comment '用户等级',
    experience    int      default 0                 null comment '经验值',
    bili_coin     int      default 0                 null comment 'B币余额',
    vip_level     int                                null,
    status        int                                null,
    created_at    datetime default CURRENT_TIMESTAMP null,
    updated_at    datetime                           null on update CURRENT_TIMESTAMP,
    is_deleted    tinyint  default 0                 null comment '0-未删除, 1-已删除',
    constraint email
        unique (email),
    constraint phone
        unique (phone),
    constraint username
        unique (username)
);

create table coin_account
(
    id         bigint auto_increment
        primary key,
    user_id    bigint            not null,
    balance    int     default 0 null,
    updated_at datetime          null on update CURRENT_TIMESTAMP,
    is_deleted tinyint default 0 null comment '0-未删除, 1-已删除',
    constraint user_id
        unique (user_id),
    constraint coin_account_ibfk_1
        foreign key (user_id) references user (id)
);

create table collection_folder
(
    id          bigint auto_increment
        primary key,
    user_id     bigint                             not null,
    name        varchar(64)                        not null,
    description varchar(256)                       null,
    is_public   tinyint  default 1                 null comment '0-私密,1-公开',
    created_at  datetime default CURRENT_TIMESTAMP null,
    is_deleted  tinyint  default 0                 null comment '0-未删除, 1-已删除',
    constraint collection_folder_ibfk_1
        foreign key (user_id) references user (id)
);

create index user_id
    on collection_folder (user_id);

create table follow
(
    id               bigint auto_increment
        primary key,
    user_id          bigint                             not null,
    followed_user_id bigint                             not null,
    created_at       datetime default CURRENT_TIMESTAMP null,
    is_deleted       tinyint  default 0                 null comment '0-未删除, 1-已删除',
    constraint user_followed
        unique (user_id, followed_user_id),
    constraint follow_ibfk_1
        foreign key (user_id) references user (id),
    constraint follow_ibfk_2
        foreign key (followed_user_id) references user (id)
);

create index followed_user_id
    on follow (followed_user_id);

create table membership
(
    id         bigint auto_increment
        primary key,
    user_id    bigint            not null,
    level      tinyint           not null comment '会员等级',
    start_date datetime          not null,
    end_date   datetime          not null,
    auto_renew tinyint default 0 null,
    is_deleted tinyint default 0 null comment '0-未删除, 1-已删除',
    constraint membership_ibfk_1
        foreign key (user_id) references user (id)
);

create index user_id
    on membership (user_id);

create table message
(
    id           bigint auto_increment
        primary key,
    from_user_id bigint                             null,
    to_user_id   bigint                             not null,
    type         tinyint                            not null comment '1-系统通知,2-私信,3-回复',
    content      text                               not null,
    is_read      tinyint  default 0                 null,
    created_at   datetime default CURRENT_TIMESTAMP null,
    is_deleted   tinyint  default 0                 null comment '0-未删除, 1-已删除',
    constraint message_ibfk_1
        foreign key (to_user_id) references user (id)
);

create index to_user_id
    on message (to_user_id);

create table payment
(
    id         bigint auto_increment
        primary key,
    user_id    bigint                             not null,
    amount     decimal(10, 2)                     not null,
    type       tinyint                            not null comment '1-会员续费,2-B币充值,3-创作者收益',
    status     tinyint  default 0                 null comment '0-处理中,1-成功,2-失败',
    created_at datetime default CURRENT_TIMESTAMP null,
    is_deleted tinyint  default 0                 null comment '0-未删除, 1-已删除',
    constraint payment_ibfk_1
        foreign key (user_id) references user (id)
);

create index user_id
    on payment (user_id);

create table user_auth
(
    id            bigint auto_increment
        primary key,
    user_id       bigint            not null,
    identity_type varchar(32)       not null,
    identifier    varchar(128)      not null,
    credential    varchar(256)      null,
    is_deleted    tinyint default 0 null comment '0-未删除, 1-已删除',
    constraint identity_type_identifier
        unique (identity_type, identifier),
    constraint user_auth_ibfk_1
        foreign key (user_id) references user (id)
);

create index user_id
    on user_auth (user_id);

create table video
(
    id                  bigint auto_increment
        primary key,
    user_id             bigint                             not null,
    title               varchar(128)                       not null,
    description         text                               null,
    cover_url           varchar(512)                       null,
    video_url           varchar(512)                       null,
    duration            int                                null,
    category_id         bigint                             null,
    tags                varchar(256)                       null,
    bullet_screen_count int      default 0                 null comment '弹幕数',
    copyright           varchar(256)                       null comment '版权信息',
    source              varchar(64)                        null comment '来源（原创/转载）',
    status              tinyint  default 0                 null comment '0-待审核, 1-审核通过, 2-已发布, 3-已下架, 4-审核失败',
    created_at          datetime default CURRENT_TIMESTAMP null,
    updated_at          datetime                           null on update CURRENT_TIMESTAMP,
    is_deleted          tinyint  default 0                 null comment '0-未删除, 1-已删除',
    resolution          varchar(16)                        null comment '视频分辨率，如1080p',
    file_size           bigint                             null comment '视频文件大小（字节）',
    is_active           tinyint  default 1                 null comment '0-不可用, 1-可用',
    deleted_at          datetime                           null comment '删除时间',
    upload_status       tinyint  default 0                 null comment '0-未开始, 1-上传中, 2-上传完成, 3-合并完成, 4-上传失败',
    chunk_total         int      default 0                 null comment '总分片数',
    chunk_uploaded      int      default 0                 null comment '已上传分片数',
    constraint video_ibfk_1
        foreign key (user_id) references user (id),
    constraint video_ibfk_2
        foreign key (category_id) references category (id)
);

create table collection_item
(
    id         bigint auto_increment
        primary key,
    folder_id  bigint                             not null,
    video_id   bigint                             not null,
    created_at datetime default CURRENT_TIMESTAMP null,
    is_deleted tinyint  default 0                 null comment '0-未删除, 1-已删除',
    constraint folder_video
        unique (folder_id, video_id),
    constraint collection_item_ibfk_1
        foreign key (folder_id) references collection_folder (id),
    constraint collection_item_ibfk_2
        foreign key (video_id) references video (id)
);

create index video_id
    on collection_item (video_id);

create table comment
(
    id         bigint auto_increment
        primary key,
    video_id   bigint                             not null,
    user_id    bigint                             not null,
    content    varchar(1024)                      not null,
    parent_id  bigint                             null,
    status     tinyint  default 0                 null comment '0-正常,1-已删除',
    created_at datetime default CURRENT_TIMESTAMP null,
    is_deleted tinyint  default 0                 null comment '0-未删除, 1-已删除',
    like_count int      default 0                 null,
    constraint comment_ibfk_1
        foreign key (video_id) references video (id),
    constraint comment_ibfk_2
        foreign key (user_id) references user (id)
);

create index user_id
    on comment (user_id);

create index video_id
    on comment (video_id);

create table history
(
    id         bigint auto_increment
        primary key,
    user_id    bigint                             not null,
    video_id   bigint                             not null,
    progress   int      default 0                 null,
    viewed_at  datetime default CURRENT_TIMESTAMP null,
    is_deleted tinyint  default 0                 null comment '0-未删除, 1-已删除',
    constraint user_video
        unique (user_id, video_id),
    constraint history_ibfk_1
        foreign key (user_id) references user (id),
    constraint history_ibfk_2
        foreign key (video_id) references video (id)
);

create index video_id
    on history (video_id);

create table user_video_interaction
(
    id         bigint auto_increment
        primary key,
    user_id    bigint                             not null,
    video_id   bigint                             not null,
    type       tinyint                            not null comment '1-点赞,2-投币,3-收藏,4-分享',
    created_at datetime default CURRENT_TIMESTAMP null,
    is_deleted tinyint  default 0                 null comment '0-未删除, 1-已删除',
    constraint user_video_type
        unique (user_id, video_id, type),
    constraint user_video_interaction_ibfk_1
        foreign key (user_id) references user (id),
    constraint user_video_interaction_ibfk_2
        foreign key (video_id) references video (id)
);

create index video_id
    on user_video_interaction (video_id);

create index idx_category_created
    on video (category_id, created_at);

create index idx_deleted
    on video (is_deleted);

create index user_id
    on video (user_id);

create table video_chunk
(
    id          bigint auto_increment
        primary key,
    video_id    bigint                             not null,
    chunk_index int                                not null comment '分片序号，从0开始',
    chunk_size  bigint                             not null comment '分片大小（字节）',
    chunk_hash  varchar(64)                        null comment '分片哈希值，用于校验',
    status      tinyint  default 0                 null comment '0-未上传, 1-已上传, 2-上传失败',
    created_at  datetime default CURRENT_TIMESTAMP null,
    updated_at  datetime                           null on update CURRENT_TIMESTAMP,
    constraint video_chunk_idx
        unique (video_id, chunk_index),
    constraint video_chunk_ibfk_1
        foreign key (video_id) references video (id)
);

create index idx_video_status
    on video_chunk (video_id, status);

create table video_tag
(
    id         bigint auto_increment
        primary key,
    video_id   bigint            not null,
    tag_id     bigint            not null,
    is_deleted tinyint default 0 null comment '0-未删除, 1-已删除',
    constraint video_tag
        unique (video_id, tag_id),
    constraint video_tag_ibfk_1
        foreign key (video_id) references video (id),
    constraint video_tag_ibfk_2
        foreign key (tag_id) references tag (id)
);

create index tag_id
    on video_tag (tag_id);

create table watch_later
(
    id         bigint auto_increment
        primary key,
    user_id    bigint                             not null,
    video_id   bigint                             not null,
    added_at   datetime default CURRENT_TIMESTAMP null,
    is_deleted tinyint  default 0                 null comment '0-未删除, 1-已删除',
    constraint user_video
        unique (user_id, video_id),
    constraint watch_later_ibfk_1
        foreign key (user_id) references user (id),
    constraint watch_later_ibfk_2
        foreign key (video_id) references video (id)
);

create index video_id
    on watch_later (video_id);


