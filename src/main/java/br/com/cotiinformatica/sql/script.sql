create table usuario(
	idusuario		uuid			primary key,
	nome			varchar(100)	not null,
	email			varchar(50)		not null unique,
	senha			varchar(40)		not null
);

create table conta(
	idconta			uuid			primary key,
	nome			varchar(100)	not null,
	data			date			not null,
	valor			decimal(10,2)	not null,
	tipo			integer			not null,
	descricao		varchar(500)	not null,
	idusuario		uuid			not null,
	foreign key(idusuario) references usuario(idusuario)
);