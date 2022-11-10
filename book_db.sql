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

create table book_info
(
    bno         bigint auto_increment,
    pubDate     date         not null,
    author      varchar(200) not null,
    description text         null,
    publisher   varchar(50)  null,
    constraint book_info_pk
        primary key (bno)
);

create index book_info_pubDate_index
    on book_info (pubDate);


create table book_images
(
    bno              bigint auto_increment,
    storeFileName    varchar(100)           not null,
    originalFileName varchar(100)           not null,
    imgCategory      varchar(20)            not null,
    ext              varchar(20)            null,
    size             bigint                 null,
    regDate          datetime default now() null,
    constraint book_images_pk
        primary key (bno)
);

create table review
(
    rno     bigint auto_increment,
    bno     bigint      null,
    id      varchar(30) null,
    content text        null,
    prno    bigint      null,
    constraint review_pk
        primary key (rno)
);


#auto-increment 1로 수정
# ALTER TABLE book AUTO_INCREMENT=1;
# ALTER TABLE book_images AUTO_INCREMENT=1;
# ALTER TABLE book_info AUTO_INCREMENT=1;
# ALTER TABLE category_book AUTO_INCREMENT=1;

# alter table review
#     add constraint review_book_null_fk
#         foreign key (bno) references book (bno)
#             on delete cascade;