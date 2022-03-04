-- ================ Root 카테고리
insert into categories(category_name, parent_id) values ('의류', 0);

-- =============== Level 2 카테고리
-- 패션
insert into categories(category_name, parent_id) values ('여성', 1);  -- 2
insert into categories(category_name, parent_id) values ('남성', 1);  -- 3
insert into categories(category_name, parent_id) values ('아동', 1);  -- 4
insert into categories(category_name, parent_id) values ('스포츠', 1); -- 5
insert into categories(category_name, parent_id) values ('잡화', 1); -- 6

-- =============== Level 3 카테고리행
-- === 패션
-- 여성
insert into categories(category_name, parent_id) values ('티', 2); -- 7
insert into categories(category_name, parent_id) values ('원피스', 2);
insert into categories(category_name, parent_id) values ('블라우스', 2);
insert into categories(category_name, parent_id) values ('바지/레깅스', 2);

-- 남성
insert into categories(category_name, parent_id) values ('티', 3); -- 11
insert into categories(category_name, parent_id) values ('맨투맨/후드', 3);
insert into categories(category_name, parent_id) values ('셔츠', 3);
insert into categories(category_name, parent_id) values ('바지/청바지', 3);

-- 아동
insert into categories(category_name, parent_id) values ('여아', 4); -- 15
insert into categories(category_name, parent_id) values ('남아', 4);
insert into categories(category_name, parent_id) values ('공용', 4);

-- 스포츠
insert into categories(category_name, parent_id) values ('여성', 5); --18
insert into categories(category_name, parent_id) values ('남성', 5);
insert into categories(category_name, parent_id) values ('유아', 5);

-- 신발/가방/잡화
insert into categories(category_name, parent_id) values ('시계', 6); --21
insert into categories(category_name, parent_id) values ('신발', 6);
insert into categories(category_name, parent_id) values ('가방', 6);
insert into categories(category_name, parent_id) values ('모자', 6);

-- 멤버 --
insert into member(login_id, password, name, email, city, street, zipcode)
values ('qwe', '123', '손창현', 'yeowuli2@naver.com', '수원시', '서둔로', '110-110');

-- 아이템 1--
insert into item(name, price, stock_quantity, category_id)
values ('컨버스후드티', 35000, 10, 12);

insert into image(image_path, item_id)
values ('clothes/1/main.JPG',1);

insert into image(image_path, item_id)
values ('clothes/1/IMG_0040.JPG',1);

insert into image(image_path, item_id)
values ('clothes/1/IMG_0041.JPG',1);

insert into image(image_path, item_id)
values ('clothes/1/IMG_0042.JPG',1);

insert into image(image_path, item_id)
values ('clothes/1/IMG_0043.JPG',1);

-- 아이템 2--
insert into item(name, price,stock_quantity, category_id)
values ('컨버스후드티', 35000, 10, 12);

insert into image(image_path, item_id)
values ('clothes/2/main.JPG',2);

insert into image(image_path, item_id)
values ('clothes/2/IMG_0031.JPG',2);

insert into image(image_path, item_id)
values ('clothes/2/IMG_0032.JPG',2);

insert into image(image_path, item_id)
values ('clothes/2/IMG_0033.JPG',2);

insert into image(image_path, item_id)
values ('clothes/2/IMG_0034.JPG',2);

-- 아이템 3--
insert into item(name, price, stock_quantity, category_id)
values ('나이키바지', 45000, 10, 14);

insert into image(image_path, item_id)
values ('clothes/3/main.JPG',3);

insert into image(image_path, item_id)
values ('clothes/3/IMG_0024.JPG',3);

insert into image(image_path, item_id)
values ('clothes/3/IMG_0025.JPG',3);

insert into image(image_path, item_id)
values ('clothes/3/IMG_0026.JPG',3);

insert into image(image_path, item_id)
values ('clothes/3/IMG_0027.JPG',3);

-- 아이템 4--
insert into item(name, price, stock_quantity, category_id)
values ('나이키바지', 45000, 10, 14);

insert into image(image_path, item_id)
values ('clothes/4/main.JPG',4);

insert into image(image_path, item_id)
values ('clothes/4/IMG_0017.JPG',4);

insert into image(image_path, item_id)
values ('clothes/4/IMG_0018.JPG',4);

insert into image(image_path, item_id)
values ('clothes/4/IMG_0019.JPG',4);

insert into image(image_path, item_id)
values ('clothes/4/IMG_0020.JPG',4);

-- 아이템 5--
insert into item(name, price, stock_quantity, category_id)
values ('나이키맨투맨', 65000, 10, 12);

insert into image(image_path, item_id)
values ('clothes/5/main.JPG',5);

insert into image(image_path, item_id)
values ('clothes/5/IMG_0027.JPG',5);

insert into image(image_path, item_id)
values ('clothes/5/IMG_0028.JPG',5);

insert into image(image_path, item_id)
values ('clothes/5/IMG_0029.JPG',5);

insert into image(image_path, item_id)
values ('clothes/5/IMG_0030.JPG',5);

-- 아이템 6--
insert into item(name, price, stock_quantity, category_id)
values ('나이키맨투맨', 65000, 10, 12);

insert into image(image_path, item_id)
values ('clothes/6/main.JPG',6);

insert into image(image_path, item_id)
values ('clothes/6/IMG_0034.JPG',6);

insert into image(image_path, item_id)
values ('clothes/6/IMG_0035.JPG',6);

insert into image(image_path, item_id)
values ('clothes/6/IMG_0036.JPG',6);

insert into image(image_path, item_id)
values ('clothes/6/IMG_0037.JPG',6);

-- 아이템 7--
insert into item(name, price, stock_quantity, category_id)
values ('나이키운동화', 85000, 10, 12);

insert into image(image_path, item_id)
values ('clothes/7/main.JPG',7);

insert into image(image_path, item_id)
values ('clothes/7/1.JPG',7);

insert into image(image_path, item_id)
values ('clothes/7/2.JPG',7);

insert into image(image_path, item_id)
values ('clothes/7/3.JPG',7);

insert into image(image_path, item_id)
values ('clothes/7/4.JPG',7);

-- 아이템 8--
insert into item(name, price, stock_quantity, category_id)
values ('나이키운동화', 85000, 10, 22);

insert into image(image_path, item_id)
values ('clothes/8/main.JPG',8);

insert into image(image_path, item_id)
values ('clothes/8/1.JPG',8);

insert into image(image_path, item_id)
values ('clothes/8/2.JPG',8);

insert into image(image_path, item_id)
values ('clothes/8/3.JPG',8);

insert into image(image_path, item_id)
values ('clothes/8/4.JPG',8);

-- 아이템 9--
insert into item(name, price, stock_quantity, category_id)
values ('나이키운동화', 85000, 10, 22);

insert into image(image_path, item_id)
values ('clothes/9/main.JPG',9);

insert into image(image_path, item_id)
values ('clothes/9/1.JPG',9);

insert into image(image_path, item_id)
values ('clothes/9/2.JPG',9);

insert into image(image_path, item_id)
values ('clothes/9/3.JPG',9);

insert into image(image_path, item_id)
values ('clothes/9/4.JPG',9);



