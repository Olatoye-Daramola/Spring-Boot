set foreign_key_checks = 0;      --for MySql

truncate table product;         --add cascade fro postgresql
truncate table item;
truncate table cart;
truncate table cart_item_list;
truncate table app_user;

insert into product (id, name, price, quantity)
values(12, 'Luxury Map', 2340, 3),
      (13, 'Macbook Air', 1340, 4),
      (14, 'Rocking chair', 4140, 5),
      (15, 'Purple T-shirt', 6380, 7);

insert into item (id, product_id, quantity_added_to_cart)
values(102, 14, 2),
      (122, 15, 3),
      (133, 12, 1);

insert into cart (id, total_price)
values(345, 0.0),
       (355, 0.0),
       (366, 0.0);

insert into app_user(id, first_name, last_name, email, my_cart_id)
values(5005, 'John', 'Badmus', 'john@myspace.com', 345),
      (5010, 'Chris', 'Tuck', 'chris@myspace.com', 355),
      (5011, 'Goodnews', 'Confidence', 'goodnews@myspace.com', 366);

insert into cart_item_list (cart_id, item_list_id)
values(345, 102),
       (345, 122),
       (365, 133);


set foreign_key_checks = 1;