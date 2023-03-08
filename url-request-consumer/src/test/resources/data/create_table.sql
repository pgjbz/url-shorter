create table if not exists tb_url (
    id bigserial primary key
);

create schema if not exists requests;

create table if not exists requests.tb_requests (
    id bigserial,
    url_id bigint not null references public.tb_url,
    headers json
)