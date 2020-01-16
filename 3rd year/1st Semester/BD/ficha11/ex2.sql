-- insert into ListaFaltas (idCromo, Descricao)
-- select Nr, Cromo.Descricao from Caderneta.Cromo
--     where Adquirido = 'N';

CREATE PROCEDURE povoar_faltas()
BEGIN
DECLARE idCromo INT;
DECLARE descricao TEXT;

START TRANSACTION;
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        EXIT PROCEDURE;
    END;

    DECLARE cur1 CURSOR FOR
        Select idCromo, descricao from Cromo
        where Adquirido = 'N';

    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
    OPEN cur1;
        read_loop: LOOP
        FETCH cur1 INTO idCromo, descricao;
        IF done THEN
            LEAVE read_loop;
        END IF;
          INSERT INTO ListaFaltas (idCromo, descricao);
        END LOOP;
        CLOSE cur1;
COMMIT;
