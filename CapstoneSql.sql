create table moderator
(
    login_id int not null primary key,
    password varchar(45)
);

create table course
(
    course_id int not null primary key,
    course_name varchar(45),
	author varchar(45),
	duration int,
	availability int
);

create table cart
(
    item_id int not null primary key,
	course_id int,
    course_name varchar(45)
);

select * from moderator;

select * from course;

select * from cart;



