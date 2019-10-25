create table order_can(id int auto_increment primary key, 
user_id int  not null,
user_name varchar(30) not null,
order_cans int not null,
ordered_date timestamp not null default current_timestamp);