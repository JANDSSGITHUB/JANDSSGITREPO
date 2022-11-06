create table movies
(
    movie_id   INT not null primary key,
    movie_title varchar(50) not null,
    movie_cost INT,
    movie_year INT,
	actor_id int,
	constraint fk_actors
	foreign key(actor_id)
	REFERENCES actors(actor_id)
);
create table review
(
    review_id int not null primary key,
    date_posted timestamp ,
    description varchar(45),
    rating int,
	movie_id int,
	constraint fk_movie
	foreign key(movie_id)
	REFERENCES movies(movie_id)

);
5:12
create table actors
(
    actor_id      BIGINT not null primary key,
    first_name varchar(25) ,
    last_name   varchar(255),
    gender  char(1),
	age INT
);
create table admin
(
    admin_id   integer     not null
        primary key,
    first_name varchar(45),
    last_name  varchar(45),
    password   varchar(45) not null,
    phone_number varchar(45)
);