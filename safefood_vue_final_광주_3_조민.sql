use springweb;

use fooddb;

create table board (
	num varchar(20) primary key,
	title  varchar(20) not null,
    content varchar(200),
    id varchar(20) default 20
);

select * from board;
drop table board;

create table qna (
	num varchar(10) primary key,
	img  varchar(50) not null,
    title varchar(30),
    name varchar(20),
    status varchar(10),
    date varchar(20),
    content varchar(500)
);
insert into qna values('1', '사과', '질문합니다', '민', '답변완료', '2019-11-20','사과가 썩었어요');
update qna set date = '2019-11-28' where num ='3';
select * from qna;
drop table qna;

create table announce (
	anum varchar(10) primary key,
    atitle varchar(500) not null,
    awriter varchar(30),
    acontent varchar(500),
    adate varchar(30),
    acnt varchar(30),
    alike varchar(30),
    areply varchar(500)
);
select * from announce;
drop table announce;

drop table board;

select * from board;

create database fooddb;
use fooddb;
create table food (
  code int primary key,
  name varchar(30),
  maker varchar(30),
  material varchar(600),
  image varchar(50),
  supportpereat varchar(30),
  calory varchar(30),
  carbo varchar(30),
  protein varchar(30),
  fat varchar(30),
  sugar varchar(30),
  natrium varchar(30),
  chole varchar(30),
  fattyacid varchar(30),
  transfat varchar(30)
);
select*from food;
drop table food;
create table memtb(
id varchar(20) primary key,
pw varchar(20),
name varchar(20),
addr varchar(200),
email varchar(50),
tel varchar(50),
alinfo varchar(500),
likefood varchar(500)
);

insert into memtb values('ssafy', 'ssafy', '이준호', '광산구', 'cross9308@naver.com', '01029982503','대두','');
insert into memtb values('ssafy2', 'ssafy2', '김지원', '광산구', 'cross9301@naver.com', '01029982504','대두','');
insert into memtb values('ssafy3', 'ssafy3', '김현정', '광산구', 'cross9302@naver.com', '01029982505','대두','');
insert into memtb values('ssafy4', 'ssafy4', '최성용', '광산구', 'cross9303@naver.com', '01029982506','대두','');
select*from memtb;
select*from food;
select * from food where code = 1;
drop table memtb;