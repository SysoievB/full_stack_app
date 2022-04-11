create table students
(
    id     bigint not null auto_increment,
    email  varchar(255),
    gender varchar(255),
    name   varchar(255),
    primary key (id)
) engine=InnoDB;
