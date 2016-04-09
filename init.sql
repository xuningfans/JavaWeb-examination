
/*	数据库表结构：
	用户表		*/
create table person
(
id int auto_increment primary key, 
userName varchar(100),
password varchar(100),
nickName varchar(50),
userType tinyint(3)
) 
ENGINE=InnoDB  DEFAULT CHARSET=utf8;

/*	内容表	*/
create table content
(
id int auto_increment primary key,  
price double(8,2),
title varchar(100),
icon varchar(255),
abstract varchar(200),
text blob)
ENGINE=InnoDB  DEFAULT CHARSET=utf8;

/*	交易记录表	*/
create table trx(
id int auto_increment primary key,  
contentId int,
personId int,
price double(8,2),
time bigint)
ENGINE=InnoDB  DEFAULT CHARSET=utf8;

/*	用户数据：	*/
insert into `person` (`id`, `userName`, `password`, `nickName`, `userType`) values('1','buyer','37254660e226ea65ce6f1efd54233424','buyer','0');
insert into `person` (`id`, `userName`, `password`, `nickName`, `userType`) values('2','seller','981c57a5cfb0f868e064904b8745766f','seller','1');