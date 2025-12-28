create table request (

    id bigint not null auto_increment,
    flight_datetime timestamp not null,
    op_unique_carrier char(6) not null,
    origin char(3) not null,
    dest char(3) not null,
    distance double not null,

    primary key (id)

);