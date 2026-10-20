Create database agendamentoEquip;
use agendamentoEquip;

create table ReservaArmario(
	id int primary key identity,
	numeroArmario int,
	nomeAluno varchar(100),
	dataInicio Date,
	dataFim Date,
	ativo bit not null default 0
)
