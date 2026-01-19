create table request (

    id bigint not null auto_increment,
    fl_date timestamp not null,
    op_unique_carrier char(2) not null,
    origin char(3) not null,
    dest char(3) not null,
    distance double not null,

    primary key (id),

    constraint uq_request_unique
    unique (fl_date, op_unique_carrier, origin, dest, distance)

);