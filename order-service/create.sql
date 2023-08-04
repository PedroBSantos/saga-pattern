create table order_items (amount integer not null, id serial not null, product_unit_price float(53) not null, id_order uuid, product_id uuid not null, primary key (id));
create table orders (created_at timestamp(6) not null, customer_id uuid not null, id uuid not null, status varchar(255) check (status in ('CREATED','PROCESSING','CANCELED','FINISHED')), primary key (id));
alter table if exists order_items add constraint FKj18ef1agdhkb3f8rmgrrgdbvu foreign key (id_order) references orders;
