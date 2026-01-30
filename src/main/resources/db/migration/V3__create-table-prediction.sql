<<<<<<< HEAD
create table prediction (

    id bigint not null auto_increment,
    request_id bigint not null,
    prevision varchar(50) not null,
    probability double not null,
    status varchar(50) not null,

    primary key(id),

    constraint fk_prediction_request
            foreign key (request_id)
            references request(id),

    constraint uq_prediction_request
            unique (request_id)

=======
create table response(
    id bigint not null auto_increment,
    prevision varchar(100),
    probability double,
    status varchar(100),
    request_id bigint,  /* <--- ¡AQUÍ ESTABA EL ERROR! (Faltaba esta coma) */
    
    primary key(id),
    
    constraint fk_prediction_request foreign key (request_id) references request(id)
>>>>>>> efcab86d77a3e7aff7effaef866f586486944dd2
);