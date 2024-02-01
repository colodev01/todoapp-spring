create table todo (
    id bigserial primary key,
    name text,
    completed boolean not null default false
)