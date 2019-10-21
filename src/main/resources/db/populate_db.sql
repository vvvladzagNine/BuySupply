DELETE FROM request;
DELETE FROM estimate;
DELETE FROM offer;
DELETE FROM category;
DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password,city) VALUES
('UserB', 'userB@yandex.ru', 'passwordB','Moscow'),
('UserS', 'userS@yandex.ru', 'passwordS','Moscow'),
('Admin', 'admin@gmail.com', 'admin','Moscow');

INSERT INTO user_roles (role, user_id) VALUES
('ROLE_USER', 100000),
('ROLE_USER', 100001),
('ROLE_ADMIN', 100002);

INSERT INTO category (name,unit,type) VALUES
('Food','KG','Box'),
('CLOTHES','Thing','Pack')
                                            ;
INSERT INTO offer(cost,amount,description,buy_offer,category_id,date_time,offerer_id) VALUES
(300,100,'Pizza',false,100003,'2015-05-31',100001),
(100,40,'Cake',true,100003,'2015-05-30',100000);


INSERT INTO request(offer_id,requester_id,message,responced) VALUES
(100005,100000,'Hi, I would like to buy your shit',false);

INSERT INTO estimate(estimated_id,estimator_id,comment,stars,date_time) VALUES
(100001,100000,'It is a forgery :(',1,'2016-05-31'),
(100000,100001,'Offer then reject !!!',1,'2016-05-31');
