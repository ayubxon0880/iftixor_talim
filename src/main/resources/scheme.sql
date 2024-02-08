CREATE TABLE role
(
    id   serial primary key,
    name varchar(32) unique
);

CREATE TABLE authority
(
    id   serial primary key,
    name varchar(128) unique
);

INSERT INTO role(name)
VALUES ('ROLE_SUPER_ADMIN');
INSERT INTO role(name)
VALUES ('ROLE_ADMIN');
INSERT INTO role(name)
VALUES ('ROLE_TEACHER');
INSERT INTO role(name)
VALUES ('ROLE_STUDENT');

INSERT INTO authority(name)
VALUES ('UPDATE_OWN');
INSERT INTO authority(name)
VALUES ('READ_OWN');


INSERT INTO authority(name)
VALUES ('CREATE_ALL');
INSERT INTO authority(name)
VALUES ('READ_ALL');
INSERT INTO authority(name)
VALUES ('UPDATE_ALL');
INSERT INTO authority(name)
VALUES ('DELETE_ALL');

INSERT INTO authority(name)
VALUES ('CRUD_STUDENT');
INSERT INTO authority(name)
VALUES ('CRUD_TEACHER');
INSERT INTO authority(name)
VALUES ('CRUD_GROUP');
INSERT INTO authority(name)
VALUES ('CRUD_ATTENDANCE');
INSERT INTO authority(name)
VALUES ('CRUD_GRADE');

INSERT INTO authority(name)
VALUES ('CREATE_ATTENDANCE');
INSERT INTO authority(name)
VALUES ('UPDATE_ATTENDANCE');
INSERT INTO authority(name)
VALUES ('READ_ATTENDANCE');
INSERT INTO authority(name)
VALUES ('CREATE_GRADE');
INSERT INTO authority(name)
VALUES ('UPDATE_GRADE');
INSERT INTO authority(name)
VALUES ('READ_GRADE');
INSERT INTO authority(name)
VALUES ('READ_OWN_STUDENT');

CREATE TABLE role_authority
(
    role_id      bigint,
    authority_id bigint,
    foreign key (role_id) references role (id) on delete cascade,
    foreign key (authority_id) references authority (id) on delete cascade
);

INSERT INTO role_authority(ROLE_ID, AUTHORITY_ID)
VALUES (1, 3);
INSERT INTO role_authority(ROLE_ID, AUTHORITY_ID)
VALUES (1, 4);
INSERT INTO role_authority(ROLE_ID, AUTHORITY_ID)
VALUES (1, 5);
INSERT INTO role_authority(ROLE_ID, AUTHORITY_ID)
VALUES (1, 6);

INSERT INTO role_authority(ROLE_ID, AUTHORITY_ID)
VALUES (2, 1);
INSERT INTO role_authority(ROLE_ID, AUTHORITY_ID)
VALUES (2, 2);
INSERT INTO role_authority(ROLE_ID, AUTHORITY_ID)
VALUES (2, 7);
INSERT INTO role_authority(ROLE_ID, AUTHORITY_ID)
VALUES (2, 8);
INSERT INTO role_authority(ROLE_ID, AUTHORITY_ID)
VALUES (2, 9);
INSERT INTO role_authority(ROLE_ID, AUTHORITY_ID)
VALUES (2, 10);
INSERT INTO role_authority(ROLE_ID, AUTHORITY_ID)
VALUES (2, 11);

INSERT INTO role_authority(ROLE_ID, AUTHORITY_ID)
VALUES (3, 12);
INSERT INTO role_authority(ROLE_ID, AUTHORITY_ID)
VALUES (3, 13);
INSERT INTO role_authority(ROLE_ID, AUTHORITY_ID)
VALUES (3, 14);
INSERT INTO role_authority(ROLE_ID, AUTHORITY_ID)
VALUES (3, 15);
INSERT INTO role_authority(ROLE_ID, AUTHORITY_ID)
VALUES (3, 16);
INSERT INTO role_authority(ROLE_ID, AUTHORITY_ID)
VALUES (3, 17);
INSERT INTO role_authority(ROLE_ID, AUTHORITY_ID)
VALUES (3, 18);

INSERT INTO role_authority(ROLE_ID, AUTHORITY_ID)
VALUES (4, 1);
INSERT INTO role_authority(ROLE_ID, AUTHORITY_ID)
VALUES (4, 2);


CREATE TABLE users
(
    id         serial primary key,
    username   varchar(64) unique,
    email      varchar(256) unique CHECK (email ~* '^ [A-Za-z0-9._+%-]+@ [A-Za-z0-9.-]+ [.] [A-Za-z]+$'),
    password   varchar(255),
    created_at date not null default current_date,
    role_id    integer,
    foreign key (role_id) references role (id) on delete set null
);

CREATE TABLE status
(
    id   serial primary key,
    name varchar(32)
);

INSERT INTO status(name)
values ('IN_PROGRESS');
INSERT INTO status(name)
values ('FINISHED');


CREATE TABLE student
(
    id        integer primary key references users (id) on delete cascade unique,
    full_name varchar(128) not null,
    phone     varchar(128) not null,
    status_id integer,
    foreign key (status_id) references status (id) on delete set null
);

CREATE TABLE teacher
(
    id        integer primary key references users (id) on delete cascade unique,
    full_name varchar(128) not null,
    phone     varchar(128) not null
);

CREATE TABLE groups
(
    id         serial primary key,
    direction  varchar(128) not null,
    payment    float        not null,
    degree     varchar(42),
    teacher_id integer,
    days       varchar(45)  not null,
    start_time time,
    end_time   time,
    created_at date         not null default current_date,
    foreign key (teacher_id) references teacher (id) on delete set null
);

CREATE TABLE payment
(
    id           serial primary key,
    payment_date date not null default current_date,
    student_id   integer,
    group_id     integer,
    payment      float,
    is_payed     boolean,
    foreign key (student_id) references student (id) on delete set null,
    foreign key (group_id) references groups (id) on delete set null
);

create table student_group
(
    student_id bigint,
    group_id   integer,
    foreign key (student_id) references student (id) on delete cascade,
    foreign key (group_id) references groups (id) on delete cascade
);

CREATE TABLE attendance
(
    id              serial primary key,
    attendance_date date not null default current_date,
    student_id      integer,
    group_id        integer,
    attendance      varchar(32),
    unique (attendance_date, student_id, group_id),
    foreign key (student_id) references student (id) on delete set null,
    foreign key (group_id) references groups (id) on delete set null
);

CREATE TABLE quiz_result
(
    id         serial primary key,
    student_id integer,
    group_id   integer,
    test_count integer,
    correct    integer,
    degree     varchar(32),
    test_date  date,

    foreign key (student_id) references student (id) on delete set null,
    foreign key (group_id) references student (id) on delete set null
);

CREATE TABLE news
(
    id serial primary key,
    title varchar(225),
    body varchar(500),
    image_path varchar(500)
);

CREATE TABLE hashtag
(
    id serial primary key,
    name varchar(128),
    news_id integer,
    foreign key (news_id) references news(id)
);


create table answer (
                        id bigserial not null,
                        answer varchar(255),
                        is_correct boolean,
                        primary key (id)
);

create table answer_data (
                             id bigserial not null,
                             correct_answers integer,
                             created_date_time timestamp(6),
                             question_list_id bigint,
                             student_id bigint,
                             primary key (id)
);

create table question (
                          id bigserial not null,
                          title varchar(255),
                          primary key (id)
);

create table question_answers (
                                  question_id bigint not null,
                                  answers_id bigint not null
);

create table question_list (
                               id bigserial not null,
                               tag varchar(255),
                               primary key (id)
);
create table question_list_questions (
                                         question_list_id bigint not null,
                                         questions_id bigint not null
);

alter table if exists question_answers
    drop constraint if exists UK_4qtn1pf4ea4ougou3ewipk9qx;

alter table if exists question_answers
    add constraint UK_4qtn1pf4ea4ougou3ewipk9qx unique (answers_id);

alter table if exists question_list_questions
    drop constraint if exists UK_mdytn0d8if7dj3jmynccqv27a;

alter table if exists question_list_questions
    add constraint UK_mdytn0d8if7dj3jmynccqv27a unique (questions_id);

alter table if exists answer_data
    add constraint FK9th56m8wpu7x06u94uhbfjoel
        foreign key (question_list_id)
            references question_list;

alter table if exists answer_data
    add constraint FK7nemg4iknfvd7vfufhrqe3vve
        foreign key (student_id)
            references student;

alter table if exists question_answers
    add constraint FKnr1xcvup15w03kboejfervq1y
        foreign key (answers_id)
            references answer;

alter table if exists question_answers
    add constraint FKlglw0r110cw97aje0b0pa4q51
        foreign key (question_id)
            references question;

alter table if exists question_list_questions
    add constraint FK1a2jkhww5panvbd8bl29m3ydr
        foreign key (questions_id)
            references question;

alter table if exists question_list_questions
    add constraint FK2kahyvnx0i6bl74c9gj5meyaj
        foreign key (question_list_id)
            references question_list
