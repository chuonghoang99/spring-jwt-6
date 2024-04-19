alter table users
    alter column username type varchar(51);

alter table users
    add unique (username);