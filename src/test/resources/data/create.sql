create table if not exists tb_url (
    id bigserial primary key,
    url varchar(2048) not null,
    created_at timestamp not null default current_timestamp,
    expired_at timestamp,
    expire boolean not null default true,
    ttl smallint
);

create table if not exists tb_request (
    id bigserial primary key,
    headers json not null,
    url_id bigint references tb_url not null
);