create table if not exists public.users (
    user_id bigint generated always as identity primary key,
    login varchar(20) unique not null,
    password varchar(20) not null
);

create table if not exists public.chatrooms (
    chat_id bigint generated always as identity primary key,
    name varchar(20) not null,
    owner_id bigint not null references users(user_id)
);

create table if not exists public.messages (
    message_id bigint generated always as identity primary key,
    author_id bigint not null references users(user_id),
    chatroom_id bigint not null references chatrooms(chat_id),
    text text,
    dateTime timestamp
);

create table if not exists public.created_chatrooms_list (
    user_id bigint not null references users(user_id),
    chatroom_id bigint not null references chatrooms(chat_id)
);

create table if not exists public.membered_chatrooms_list (
    user_id bigint not null references users(user_id),
    chatroom_id bigint not null references chatrooms(chat_id)
);

create table if not exists public.chatroom_messages_list (
    chatroom_id bigint not null references chatrooms(chat_id),
    message_id bigint not null references messages(message_id)
);

