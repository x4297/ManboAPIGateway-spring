create table if not exists access_log
(
    id          bigserial primary key,
    method      varchar(16),
    url         varchar(2048),
    sip         varchar(16),
    status_code int,
    res         varchar(4096),
    date_time   timestamp without time zone,
    java_method varchar(256)
);

create table if not exists application
(
    id     serial primary key,
    appid  varchar(32),
    secret varchar(128)
);

create table if not exists operate_log
(
    id        bigserial primary key,
    method    varchar(256),
    input     varchar(2048),
    output    varchar(2048),
    error     varchar(4096),
    date_time timestamp without time zone
);