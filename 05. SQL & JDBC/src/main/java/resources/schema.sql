-- create schema if not exists chat;

create table if not exists public.users
(
    id       bigint generated always as identity primary key,
    login    varchar(20) not null,
    password varchar(20) not null
--  list of created & membered chatrooms
);

create table if not exists public.chatrooms
(
    id    bigint generated always as identity primary key,
    name  varchar(20) not null,
    owner bigint      not null references users (id)
--  list of messages );
);

create table if not exists public.messages
(
    id       bigint generated always as identity primary key,
    author   bigint not null references users (id),
    chatroom bigint not null references chatrooms (id),
    text     text,
    datetime timestamp
);

-- create table if not exists public.created_chatrooms_list (
--     user_id bigint not null references users(user_id),
--     chatroom_id bigint not null references chatrooms(chat_id)
-- );
--
-- create table if not exists public.membered_chatrooms_list (
--     user_id bigint not null references users(user_id),
--     chatroom_id bigint not null references chatrooms(chat_id)
-- );
--
-- create table if not exists public.chatroom_messages_list (
--     chatroom_id bigint not null references chatrooms(chat_id),
--     message_id bigint not null references messages(message_id)
-- );
