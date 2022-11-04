use book_db;
#책
create table book
(
    bno  bigint auto_increment,
    stock int default 0 null,
    isbn varchar(50) null,
    title varchar(200) not null ,
    pubDate datetime null,
    categoryName varchar(200) null,
    author varchar(200) not null ,
    description text null,
    price int not null ,
    mileage int null,
    cover varchar(100) null,
    publisher varchar(50) null,
    constraint 테이블_name_pk
        primary key (bno)
);

create index book__title
    on book (title);

create index book__categoryName
    on book (categoryName);

create index book__pubDate
    on book (pubDate);

create index book__price
    on book (price);

#카테고리
create table category
(
    ca_no        bigint auto_increment,
    categoryName varchar(100) not null,
    constraint category_pk
        primary key (ca_no)
);

#카테고리_북
create table category_book
(
    ca_no bigint not null,
    bno   bigint not null,
    constraint category_book_book_null_fk
        foreign key (bno) references book (bno),
    constraint category_book_category_null_fk
        foreign key (ca_no) references category (ca_no)
);

#회원
create table user(
     id varchar(50),
     pwd varchar(50) not null ,
     name varchar(50) not null ,
     pay_amount int default 0 null,
     regdate datetime default now(),
     constraint 테이블_user_pk
         primary key (id)
);

#주문
create table orders(
    order_id bigint auto_increment,
    id varchar(50) not null ,
    order_book_id bigint not null,
    order_status varchar(50) not null ,
    constraint orders_pk
        primary key (order_id)
);

#주문_상품
create table orders_book(
    order_book_id bigint auto_increment,
    order_id bigint not null ,
    bno bigint not null ,
    order_price int not null ,
    order_quantity int not null ,
    constraint orders_book_pk
        primary key (order_book_id)
);

#auto-increment 1로 수정
# ALTER TABLE book AUTO_INCREMENT=1;
# ALTER TABLE category_book AUTO_INCREMENT=1;