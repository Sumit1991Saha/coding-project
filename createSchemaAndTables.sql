drop database openapi;
create database openapi;
use openapi;

drop table if exists calendar_meeting_mapping;
drop table if exists available_slot;
drop table if exists meeting;
drop table if exists calendar;
drop table if exists user;

create table user (
    id bigint not null auto_increment,
    firstname varchar(100) not null,
    lastname varchar(100) not null,
    primary key (id)

)engine=InnoDB;

create table calendar (
    id bigint not null auto_increment,
    user_id bigint not null,
    primary key (id),
    foreign key (user_id) references user (id)
)engine=InnoDB;

create table available_slot (
    id bigint not null auto_increment,
    calendar_id bigint not null,
    start_time varchar(100) not null,
    end_time varchar(100) not null,
    primary key (id),
    foreign key (calendar_id) references calendar (id)
)engine=InnoDB;

create table meeting (
    id bigint not null auto_increment,
    start_time varchar(100) not null,
    end_time varchar(100) not null,
    primary key (id)
)engine=InnoDB;

create table calendar_meeting_mapping (
    calendar_id bigint not null,
    meeting_id bigint not null,
    foreign key (calendar_id) references calendar (id),
    foreign key (meeting_id) references meeting (id) on delete cascade,
    unique (calendar_id, meeting_id)
) engine=InnoDB;
