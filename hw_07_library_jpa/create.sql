create sequence author_seq start with 1 increment by 1
create sequence book_seq start with 1 increment by 1
create sequence genre_seq start with 1 increment by 1
create sequence person_seq start with 1 increment by 1
create table author (author_id bigint not null, author_name varchar(255), author_surname varchar(255), primary key (author_id))
create table author_books (author_author_id bigint not null, books_book_id bigint not null, primary key (author_author_id, books_book_id))
create table book (book_id bigint not null, book_name varchar(255), primary key (book_id))
create table book_author_ref (book_id bigint not null, author_id bigint not null, primary key (book_id, author_id))
create table book_comments (id bigint not null, registered_at timestamp, book_id bigint, person_id bigint, primary key (id))
create table book_genre_ref (book_id bigint not null, genre_id bigint not null, primary key (book_id, genre_id))
create table genre (genre_id bigint not null, genre_name varchar(255), primary key (genre_id))
create table genre_books (genre_genre_id bigint not null, books_book_id bigint not null, primary key (genre_genre_id, books_book_id))
create table person (person_id bigint not null, name varchar(255), sur_name varchar(255), primary key (person_id))
alter table author_books add constraint FKhr5m3bp4u97vs63yh4rmtu6b7 foreign key (books_book_id) references book
alter table author_books add constraint FKo3dp04ldismw6cwhbv30gi110 foreign key (author_author_id) references author
alter table book_author_ref add constraint FK5q2yq4re299o204swqcudk1bu foreign key (author_id) references author
alter table book_author_ref add constraint FKawvxiqrqapwtiq086rdtoha0e foreign key (book_id) references book
alter table book_comments add constraint FK4xt8nqqhxhlkt026ed9cvki2j foreign key (book_id) references book
alter table book_comments add constraint FKef4rk122o3und9vhd18kodl7g foreign key (person_id) references person
alter table book_genre_ref add constraint FKplym0scdokr3u7dw7admfhumm foreign key (genre_id) references genre
alter table book_genre_ref add constraint FKljw8ier4bnw3u27c62ic8vs78 foreign key (book_id) references book
alter table genre_books add constraint FKem0ptbotakcevejuwucnsm34s foreign key (books_book_id) references book
alter table genre_books add constraint FKjgb20no8e8mowtqy7ic47y1lq foreign key (genre_genre_id) references genre
create sequence author_seq start with 1 increment by 1
create sequence book_seq start with 1 increment by 1
create sequence genre_seq start with 1 increment by 1
create sequence person_seq start with 1 increment by 1
create table author (author_id bigint not null, author_name varchar(255), author_surname varchar(255), primary key (author_id))
create table author_books (author_author_id bigint not null, books_book_id bigint not null, primary key (author_author_id, books_book_id))
create table book (book_id bigint not null, book_name varchar(255), primary key (book_id))
create table book_author_ref (book_id bigint not null, author_id bigint not null, primary key (book_id, author_id))
create table book_comments (id bigint not null, registered_at timestamp, book_id bigint, person_id bigint, primary key (id))
create table book_genre_ref (book_id bigint not null, genre_id bigint not null, primary key (book_id, genre_id))
create table genre (genre_id bigint not null, genre_name varchar(255), primary key (genre_id))
create table genre_books (genre_genre_id bigint not null, books_book_id bigint not null, primary key (genre_genre_id, books_book_id))
create table person (person_id bigint not null, name varchar(255), sur_name varchar(255), primary key (person_id))
alter table author_books add constraint FKhr5m3bp4u97vs63yh4rmtu6b7 foreign key (books_book_id) references book
alter table author_books add constraint FKo3dp04ldismw6cwhbv30gi110 foreign key (author_author_id) references author
alter table book_author_ref add constraint FK5q2yq4re299o204swqcudk1bu foreign key (author_id) references author
alter table book_author_ref add constraint FKawvxiqrqapwtiq086rdtoha0e foreign key (book_id) references book
alter table book_comments add constraint FK4xt8nqqhxhlkt026ed9cvki2j foreign key (book_id) references book
alter table book_comments add constraint FKef4rk122o3und9vhd18kodl7g foreign key (person_id) references person
alter table book_genre_ref add constraint FKplym0scdokr3u7dw7admfhumm foreign key (genre_id) references genre
alter table book_genre_ref add constraint FKljw8ier4bnw3u27c62ic8vs78 foreign key (book_id) references book
alter table genre_books add constraint FKem0ptbotakcevejuwucnsm34s foreign key (books_book_id) references book
alter table genre_books add constraint FKjgb20no8e8mowtqy7ic47y1lq foreign key (genre_genre_id) references genre
create sequence author_seq start with 1 increment by 1
create sequence book_seq start with 1 increment by 1
create sequence genre_seq start with 1 increment by 1
create sequence person_seq start with 1 increment by 1
create table author (author_id bigint not null, author_name varchar(255), author_surname varchar(255), primary key (author_id))
create table author_books (author_author_id bigint not null, books_book_id bigint not null, primary key (author_author_id, books_book_id))
create table book (book_id bigint not null, book_name varchar(255), primary key (book_id))
create table book_author_ref (book_id bigint not null, author_id bigint not null, primary key (book_id, author_id))
create table book_comments (id bigint not null, registered_at timestamp, book_id bigint, person_id bigint, primary key (id))
create table book_genre_ref (book_id bigint not null, genre_id bigint not null, primary key (book_id, genre_id))
create table genre (genre_id bigint not null, genre_name varchar(255), primary key (genre_id))
create table genre_books (genre_genre_id bigint not null, books_book_id bigint not null, primary key (genre_genre_id, books_book_id))
create table person (person_id bigint not null, name varchar(255), sur_name varchar(255), primary key (person_id))
alter table author_books add constraint FKhr5m3bp4u97vs63yh4rmtu6b7 foreign key (books_book_id) references book
alter table author_books add constraint FKo3dp04ldismw6cwhbv30gi110 foreign key (author_author_id) references author
alter table book_author_ref add constraint FK5q2yq4re299o204swqcudk1bu foreign key (author_id) references author
alter table book_author_ref add constraint FKawvxiqrqapwtiq086rdtoha0e foreign key (book_id) references book
alter table book_comments add constraint FK4xt8nqqhxhlkt026ed9cvki2j foreign key (book_id) references book
alter table book_comments add constraint FKef4rk122o3und9vhd18kodl7g foreign key (person_id) references person
alter table book_genre_ref add constraint FKplym0scdokr3u7dw7admfhumm foreign key (genre_id) references genre
alter table book_genre_ref add constraint FKljw8ier4bnw3u27c62ic8vs78 foreign key (book_id) references book
alter table genre_books add constraint FKem0ptbotakcevejuwucnsm34s foreign key (books_book_id) references book
alter table genre_books add constraint FKjgb20no8e8mowtqy7ic47y1lq foreign key (genre_genre_id) references genre
create sequence author_seq start with 1 increment by 1
create sequence book_seq start with 1 increment by 1
create sequence genre_seq start with 1 increment by 1
create sequence person_seq start with 1 increment by 1
create table author (author_id bigint not null, author_name varchar(255), author_surname varchar(255), primary key (author_id))
create table book (book_id bigint not null, book_name varchar(255), primary key (book_id))
create table book_author_ref (book_id bigint not null, author_id bigint not null, primary key (book_id, author_id))
create table book_comments (id bigint not null, message varchar(255), registered_at timestamp, book_id bigint, person_id bigint, primary key (id))
create table book_genre_ref (book_id bigint not null, genre_id bigint not null, primary key (book_id, genre_id))
create table genre (genre_id bigint not null, genre_name varchar(255), primary key (genre_id))
create table person (person_id bigint not null, name varchar(255), sur_name varchar(255), primary key (person_id))
alter table book_author_ref add constraint FK5q2yq4re299o204swqcudk1bu foreign key (author_id) references author
alter table book_author_ref add constraint FKawvxiqrqapwtiq086rdtoha0e foreign key (book_id) references book
alter table book_comments add constraint FK4xt8nqqhxhlkt026ed9cvki2j foreign key (book_id) references book
alter table book_comments add constraint FKef4rk122o3und9vhd18kodl7g foreign key (person_id) references person
alter table book_genre_ref add constraint FKplym0scdokr3u7dw7admfhumm foreign key (genre_id) references genre
alter table book_genre_ref add constraint FKljw8ier4bnw3u27c62ic8vs78 foreign key (book_id) references book
