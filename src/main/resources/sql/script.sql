DROP TABLE tb_acao;
DROP SEQUENCE ACAO_SEQ;

CREATE TABLE tb_acao (
    id_acao          NUMBER(10) NOT NULL PRIMARY KEY,
    nome             VARCHAR2(40) NOT NULL,
    descricao        VARCHAR2(100) NOT NULL,
    ativo            NUMBER(2) NOT NULL
);

CREATE SEQUENCE ACAO_SEQ START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER tr_insert_id_acao
                  BEFORE INSERT ON tb_acao FOR EACH ROW
BEGIN

SELECT acao_seq.NEXTVAL
INTO :NEW.id_acao
FROM DUAL;
END;
/

------------------------------------------------------


DROP TABLE tb_execucao;
DROP SEQUENCE EXECUCAO_SEQ;

CREATE TABLE tb_execucao (
    id_execucao      NUMBER(10) NOT NULL PRIMARY KEY,
    id_acao     NUMBER(10) NOT NULL,
    data_execucao DATE NOT NULL,
    CONSTRAINT FK_ID_ACAO FOREIGN KEY (id_acao) REFERENCES tb_acao(id_acao)
);

CREATE SEQUENCE execucao_seq START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER tr_insert_id_execucao
                  BEFORE INSERT ON tb_execucao FOR EACH ROW
BEGIN

SELECT execucao_seq.NEXTVAL
INTO :NEW.id_execucao
FROM DUAL;
END;
/


commit;

