insert into member(id, name, email, password, mobile, address, reg_date)
values( null, 'kim', 'urstory@gmail.com', '{bcrypt}$2a$10$7Tu2UN/U2C24g8vCRTppwO9hmLUHFRpCBt9nZyYOLRRoufNt5rn4C', '010-1234-1234', '서울시 강남구 테헤란로 152', now());

insert into member(id, name, email, password, mobile, address, reg_date)
values( null, 'kang', 'carami@gmail.com', '{bcrypt}$2a$10$7Tu2UN/U2C24g8vCRTppwO9hmLUHFRpCBt9nZyYOLRRoufNt5rn4C', '010-1234-1234', '서울시 강남구 테헤란로 152', now());

insert into member_role(id, name, member_id)
values( null, 'USER', 1);

insert into member_role(id, name, member_id)
values( null, 'ADMIN', 1);

insert into member_role(id, name, member_id)
values( null, 'USER', 2);