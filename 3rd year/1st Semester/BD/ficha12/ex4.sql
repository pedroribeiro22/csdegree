delimiter $$
create procedure `proc_alinea_4_f10` (in nome VARCHAR(75))
begin
    select * from Cromo as CR
        inner join Jogador as J on J.Nr = CR.Jogador
        inner join Equipa as Eq on J.Equipa = Eq.Id
        where Eq.Designacao = nome 
        order by Cr.PagCaderneta, CR.Nr;
END
$$

CALL proc_alinea_4_f10("Sporting Clube de Braga");