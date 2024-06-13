create schema if not exists gym;

drop table if exists gym.trainings cascade;
drop table if exists gym.users cascade;
drop table if exists gym.accessors cascade;
drop table if exists gym.exercises_categories cascade;
drop table if exists gym.exercises cascade;


create table if not exists gym.users
(
    id          uuid primary key,
    nickname    varchar(20),
    mail        varchar(100),
    is_verified boolean,
    is_locked   boolean,
    password    varchar(100),
    gender      varchar(10),
    role        varchar(10)
);

create table if not exists gym.accessors
(
    id                           uuid primary key,
    user_id                      uuid,
    access_token                 varchar(30),
    access_token_expiration_date timestamp,

    constraint accessors__user_id__users foreign key (user_id) references gym.users (id)
);

create table if not exists gym.trainings
(
    id          uuid primary key,
    water_drunk integer,
    date        timestamp,
    photo       varchar
);

create table if not exists gym.exercises_categories
(
    id            uuid primary key,
    user_id       uuid,
    category_name varchar(20),

    constraint exercises_categories__user_id__users foreign key (user_id) references gym.users (id)
);

create table if not exists gym.exercises
(
    id                uuid primary key,
    category_id       uuid,
    training_id       uuid,
    time_performed_at timestamp,
    time_of_exercise  timestamp,
    repetitions       integer,
    weight            integer,
    difficulty        integer,

    constraint exercises__category_id__categories foreign key (category_id) references gym.exercises_categories (id),
    constraint exercises__training_id__training foreign key (training_id) references gym.trainings (id)
);
