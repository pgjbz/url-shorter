alter table tb_url add column created_at timestamp not null default current_timestamp;
alter table tb_url add column expired_at timestamp;
alter table tb_url add column expire boolean not null default true;
alter table tb_url add column ttl smallint;