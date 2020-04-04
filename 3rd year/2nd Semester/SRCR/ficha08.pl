
%------------------------------------------------

%SICStusPROLOG:Declaracoesiniciais



:-set_prolog_flag(discontiguous_warnings,off).

:-set_prolog_flag(single_var_warnings,off).

:-set_prolog_flag(unknown,fail).



%------------------------------------------------

%SICStusPROLOG:definicoesiniciais

:-op(900,xfy,'::').

:-dynamic servico/2.
:-dynamic ato/4.

%------------------------------------------------

% Representação de conhecimento negativo

-servico(Nome,Enfermeira) :-
    nao(servico(Nome,Enfermeira)).

nao(excecao(servico(Nome,Enfermeira))).

servico(Ortopedia,Amelia).
servico(Obstetricia,Ana).
servico(Obstetricia,Maria).
servico(Obstetricia,Mariana).
servico(Geriatria,Sofia).
servico(Geriatria,Susana).
servico(#007,Teodora).
servico(@NP9,Zulmira).

nuloInterdito(@NP9).
excecaoServico(servico(Servico,Enfermeira)) :-
    servico(@NP9,Enfermeira).

% INCERTOS
nuloIncerto(#007).
excecaoServico(servico(Servico,Enfermeira)) :-
    servico(#007,teodora).

% IMPRECISOS
excecaoData(ato(penso,ana,josefa,segunda)).
excecaoData(ato(penso,ana,josefa,sexta)).

% INTERDITO

ato(Penso,Ana,Joana,sabado).
ato(Gesso,Amelia,Jose,domingo).

nuloIncerto(#017)
excecaoAto(ato(Ato,Enfermeira,Utente,Data)) :-
    ato(#017,Mariana,Jose,domingo).

nuloIncerto(#121).
nuloIncerto(#251).
excecaoAto(ato(Ato,Enfermeira,Utente,Data)) :-
    ato(Domicilio,Maria,#121,#251).

ato(Domicilio,Susana,{Joao,Jose},segunda).

nuloIncerto(#313).
excecaoAto(ato(Ato,Enfermeira,Utente,Data)) :-
    ato(Sutura,#313,Josue,segunda).


ato(Sutura,{Maria,Mariana},Josefa,{Terca,Sexta}).

excecaoNome(ato(Sutura,Maria,Josefa,{Terca,Sexta})).
excecaoNome(ato(Sutura,Mariana,Josefa,{Terca,Sexta})).
excecaoDia(ato(Sutura,{Maria,Mariana},Josefa,Terca)).
excecaoDia(ato(Sutura,{Maria,Mariana},Josefa,Sexta)).

ato(Penso,Ana,Jacinta,{segunda,sexta}).
excecaoDia(ato(Penso,Ana,Jacinta,segunda)).


