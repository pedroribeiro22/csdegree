drop function function_alinea_7_f10;

Delimiter %%

create function `function_alinea_7_f10` (cromo_id INT)
    returns varchar(200)

begin
    declare tipo varchar(20);
    declare nome varchar(75);
    declare equipa varchar(75);

    set tipo = (select TipoCromo.Descricao from TipoCromo)
                    join Cromo on TipoCromo.Nr = Cromo.Nr
                    where Cromo.Nr = cromo_id);

    set nome = (select Jogador.Nome from Jogador
                    join Cromo on Cromo.Jogador = Jogador.Nr
                    where Cromo.Nr = cromo_id);

    set equipa = (select Equipa.Designacao from Equipa
                    join Jogador on Jogador.Equipa = Equipa.id
                    join Cromo on Cromo.Jogador = Jogador.Nr
                    where Cromo.Nr = cromo_id);

    return Contat_WS(",", tipo, nome, equipa);

end
%%

select function_alinea_7_f10(2);
