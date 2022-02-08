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
insert into member(login_id, password, name, email)
values ('qwe', '123', '손창현', 'yeowuli2@naver.com');

-- 아이템 --
insert into item(name, image_path, price, stock_quantity, category_id)
values ('컨버스후드티','clothes/1/1.webp', 35000, 10, 12);

insert into item(name, image_path, price, stock_quantity, category_id)
values ('컨버스후드티','clothes/2/1.webp', 35000, 10, 12);

insert into item(name, image_path, price, stock_quantity, category_id)
values ('나이키바지','clothes/3.webp', 45000, 10, 14);

insert into item(name, image_path, price, stock_quantity, category_id)
values ('나이키바지','clothes/4.webp', 45000, 10, 14);

insert into item(name, image_path, price, stock_quantity, category_id)
values ('나이키맨투맨','clothes/5.webp', 65000, 10, 12);

insert into item(name, image_path, price, stock_quantity, category_id)
values ('나이키맨투맨','clothes/6.webp', 65000, 10, 12);

insert into item(name, image_path, price, stock_quantity, category_id)
values ('나이키운동화','clothes/7.webp', 85000, 10, 12);

insert into item(name, image_path, price, stock_quantity, category_id)
values ('나이키운동화','clothes/8.webp', 85000, 10, 22);

insert into item(name, image_path, price, stock_quantity, category_id)
values ('나이키운동화','clothes/9.webp', 85000, 10, 22);

