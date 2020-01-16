drop view alinea_3;
create view alinea_3
as select Cromo.Nr, Jogador.Nome, Equipa.Designacao, Cromo.Adquirido from Cromo 
    left join jogador on Jogador.Nr = Cromo.Jogador
    join Equipa on Equipa.Id = Jogador.Equipa
    where Cromo.Adquirido = "N";

select * from Caderneta.alinea_3;