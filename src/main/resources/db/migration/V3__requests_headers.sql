create table if not exists tb_request (
    id bigserial primary key,
    headers json not null
);