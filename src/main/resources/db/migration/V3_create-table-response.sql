create table response (

    id bigint not null auto_increment,
    forecast varchar(50) not null,
    probability double not null,
    estimate varchar(50) not null,

    primary key(id)

);