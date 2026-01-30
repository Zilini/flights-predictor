create table airline (

    id bigint not null auto_increment,
    op_unique_carrier char(3) not null,
    airline_name varchar(100) not null,

    primary key (id)

);