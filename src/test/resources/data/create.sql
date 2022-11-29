create table if not exists tb_url (
    id bigserial primary key,
    url varchar(2048) not null
);