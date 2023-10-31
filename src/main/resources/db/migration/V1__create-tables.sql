create table usuarios(
    CPF varchar(11) not null,
    nome varchar(100) not null,
    sobrenome varchar(100) not null,
    email varchar(100) not null,
    senha varchar(255) not null,
    nascimento date not null,
    ativo tinyint not null,

    primary key(CPF)
);

create table Contas(
    id bigint not null auto_increment,
    abertura date not null,
    limite bigint not null,
    usuario_CPF varchar(11) not null,

    primary key(id),
    constraint fk_contas_usuarios foreign key(usuario_CPF) references usuarios(CPF)
);

create table Compras(
    id bigint not null auto_increment,
    estabelecimento varchar(255) not null,
    valor bigint not null,
    data date not null,
    conta_id bigint not null,

    primary key(id),
    constraint fk_compras_contas foreign key(conta_id) references Contas(id)
);

create table Faturas(
    id bigint not null auto_increment,
    valor bigint not null,
    status varchar(50) not null,
    vencimento date not null,
    compra_id bigint not null,

    primary key(id),
    constraint fk_faturas_compras foreign key(compra_id) references Compras(id)
);





