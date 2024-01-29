create table problem
(
    id       uuid primary key,
    question varchar(2048) not null,
    title    varchar       not null,
    is_paid  bool          not null
);

create table topic
(
    name varchar primary key
);

create table problem_topic
(
    topic_id   varchar references topic,
    problem_id uuid references problem,
    primary key (topic_id, problem_id)
);