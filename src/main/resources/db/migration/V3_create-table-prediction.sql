create table prediction (

    id bigint not null auto_increment,
    request_id bigint not null,
    prevision varchar(50) not null,
    probability double not null,
    status varchar(50) not null,

    primary key(id)

    constraint fk_prediction_request
            foreign key (request_id)
            references request(id),

    constraint uq_prediction_request
            unique (request_id)

);