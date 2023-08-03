create table t_team
(
    id   integer auto_increment,
    name varchar(50),
    rank integer,
    league varchar(50),
    primary key (id)
);

create table t_player
(
    id integer auto_increment,
    number integer,
    firstname varchar(50),
    lastname  varchar(50),
    team integer,
    position varchar(50),
    FOREIGN KEY (team) REFERENCES t_team(id),
    PRIMARY KEY (id)
);
