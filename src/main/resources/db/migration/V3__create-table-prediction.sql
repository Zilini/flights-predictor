create table response(
    id bigint not null auto_increment,
    prevision varchar(100),
    probability double,
    status varchar(100),
    request_id bigint,  /* <--- ¡AQUÍ ESTABA EL ERROR! (Faltaba esta coma) */
    
    primary key(id),
    
    constraint fk_prediction_request foreign key (request_id) references request(id)
);