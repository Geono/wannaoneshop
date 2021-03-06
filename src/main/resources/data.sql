insert into member(id, name, email, password, mobile, address, reg_date)
values( null, 'kim', 'urstory@gmail.com', '{bcrypt}$2a$10$7Tu2UN/U2C24g8vCRTppwO9hmLUHFRpCBt9nZyYOLRRoufNt5rn4C', '010-1234-1234', '서울시 강남구 테헤란로 152', now());

insert into member(id, name, email, password, mobile, address, reg_date)
values( null, 'kang', 'carami@gmail.com', '{bcrypt}$2a$10$7Tu2UN/U2C24g8vCRTppwO9hmLUHFRpCBt9nZyYOLRRoufNt5rn4C', '010-1234-1234', '서울시 강남구 테헤란로 152', now());

insert into member(id, name, email, password, mobile, address, reg_date)
values( null, 'a', 'a', '{bcrypt}$2a$10$7Tu2UN/U2C24g8vCRTppwO9hmLUHFRpCBt9nZyYOLRRoufNt5rn4C', '010-1234-1234', '서울시 강남구 테헤란로 152', now());

insert into member_role(id, name, member_id)
values( null, 'USER', 1);

insert into member_role(id, name, member_id)
values( null, 'ADMIN', 1);

insert into member_role(id, name, member_id)
values( null, 'USER', 2);

insert into item(id, name, member_id, price, info, description_image_filename, representative_image_filename, total_count, sold_count)
values(null, '워너원 브로마이드', 1, 2000, '사이즈는 60 X 150 입니다.', 'imagefilename', 'representativeimage', 20, 0);

insert into item(id, name, member_id, price, info, description_image_filename, representative_image_filename, total_count, sold_count)
values(null, '워너원 공연티켓 VIP석', 2, 3000, '암표는 사랑입니다.', 'imagefilename', 'representativeimage', 1, 0);
