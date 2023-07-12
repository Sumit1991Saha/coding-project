create database if not exists openapi;
use openapi;

drop table if exists user;
create table user (
    id bigint not null auto_increment,
    firstname varchar(100) not null,
    lastname varchar(100) not null,
    primary key (id)

)engine=InnoDB;

drop table if exists calendar;
create table calendar (
    id bigint not null auto_increment,
    user_id bigint not null,
    primary key (id),
    foreign key (user_id) references user (id)
)engine=InnoDB;


insert into user (firstname, lastname) values ("Sumit", "Saha");
insert into user (firstname, lastname) values ("John", "Gruber");
insert into user (firstname, lastname) values ("Melcum", "Marshal");
