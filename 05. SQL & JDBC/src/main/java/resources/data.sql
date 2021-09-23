insert into users (login, password)
values ('kubar', 'asd@asd');
insert into users (login, password)
values ('kek', 'asd@asd');
insert into users (login, password)
values ('lol', 'asd@asd');
insert into users (login, password)
values ('pen', 'asd@asd');
insert into users (login, password)
values ('mer', 'asd@asd');

insert into chatrooms (name, owner)
values ('announcements', 1);
insert into chatrooms (name, owner)
values ('adm', 1);
insert into chatrooms (name, owner)
values ('bocal', 3);
insert into chatrooms (name, owner)
values ('adm', 2);
insert into chatrooms (name, owner)
values ('internships', 5);

insert into messages (author, chatroom, text, datetime)
values (1, 1, 'hi!', current_timestamp);
insert into messages (author, chatroom, text, datetime)
values (3, 2, 'hi, adm!', current_timestamp);
insert into messages (author, chatroom, text, datetime)
values (2, 4, 'it is my channel', current_timestamp);
insert into messages (author, chatroom, text, datetime)
values (5, 5, 'go v Sber!', current_timestamp);
insert into messages (author, chatroom, text, datetime)
values (5, 5, 'go v Sber srochna!', current_timestamp);
