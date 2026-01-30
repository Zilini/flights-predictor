create table airports (

    id bigint not null  auto_increment,
    airport_iata char(3) not null,
    airport_name varchar(255),
    country varchar(50),
    city_name varchar(100),
    latitude decimal(10,4) not null,
    longitude decimal(10,4) not null,
    elevation decimal(6,2),
    time_zone varchar(50) not null,
    google_maps varchar(255) not null,

    primary key(id)

);