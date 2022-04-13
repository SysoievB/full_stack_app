create table students
(
    id     bigint       not null auto_increment,
    email  varchar(255) not null,
    gender varchar(255) not null,
    name   varchar(255) not null,
    primary key (id)
) engine = InnoDB;
alter table students
    add constraint UK_e2rndfrsx22acpq2ty1caeuyw unique (email);
