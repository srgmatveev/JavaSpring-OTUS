DROP SEQUENCE  author_seq  IF EXISTS ;
DROP SEQUENCE  book_seq  IF EXISTS ;
DROP SEQUENCE  genre_seq  IF EXISTS ;
DROP SEQUENCE  person_seq  IF EXISTS ;
DROP SEQUENCE  comments_seq IF EXISTS ;
create sequence author_seq start with 1 increment by 1;
create sequence book_seq start with 1 increment by 1;
create sequence genre_seq start with 1 increment by 1;
create sequence person_seq start with 1 increment by 1;
create sequence comments_seq start with 1 increment by 1;

DROP TABLE BOOK_AUTHOR_REF IF EXISTS;
DROP TABLE BOOK_GENRE_REF IF EXISTS;
DROP TABLE BOOK_COMMENTS IF EXISTS;
DROP TABLE AUTHOR IF EXISTS;
DROP TABLE BOOK IF EXISTS;
DROP TABLE GENRE IF EXISTS;
DROP TABLE PERSON IF EXISTS;



create table author (
  author_id bigint not null,
  author_name varchar(255),
  author_surname varchar(255),
primary key (author_id)
);

create table book (
  book_id bigint not null,
  book_name varchar(255),
primary key (book_id)
);

create table book_author_ref (
  book_id bigint not null,
  author_id bigint not null,
primary key (book_id, author_id)
);

create table book_comments (
  id bigint not null,
  message varchar(255),
  registered_at timestamp,
  book_id bigint,
  person_id bigint,
primary key (id)
);

create table book_genre_ref (
  book_id bigint not null,
  genre_id bigint not null,
primary key (book_id, genre_id)
);

create table genre (
  genre_id bigint not null,
  genre_name varchar(255),
primary key (genre_id)
);

create table person (
  person_id bigint not null,
  name varchar(255),
  sur_name varchar(255),
primary key (person_id)
);

alter table book_author_ref add constraint
  FK_BOOK_AUTHOR_TO_AUTHOR foreign key (author_id) references author;
alter table book_author_ref add constraint
  FK_BOOK_AUTHOR_TO_BOOK foreign key (book_id) references book;
alter table book_comments add constraint
  FK_BOOK_COMMENT_TO_BOOK foreign key (book_id) references book;
alter table book_comments add constraint
  FK_BOOK_COMMENT_TO_PERSON foreign key (person_id) references person;
alter table book_genre_ref add constraint
  FK_BOOK_GENRE_TO_GENRE foreign key (genre_id) references genre;
alter table book_genre_ref add constraint
  FK_BOOK_GENRE_TO_BOOK foreign key (book_id) references book;
