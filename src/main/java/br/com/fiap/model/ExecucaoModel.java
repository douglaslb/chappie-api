package br.com.fiap.model;


import javax.persistence.*;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Entity
@Table(name = "TB_EXECUCAO")
public class ExecucaoModel {
    private long id;
    private AcaoModel acao;
    private String dataExecucao;

    public ExecucaoModel() {

    }

    public ExecucaoModel(long id, AcaoModel acao, String dataExecucao) {
        this.id = id;
        this.acao = acao;
        this.dataExecucao = dataExecucao;
    }

    @Id
    @Column(name = "ID_EXECUCAO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EXECUCAO_SEQ")
    @SequenceGenerator(name = "EXECUCAO_SEQ", sequenceName = "EXECUCAO_SEQ", allocationSize = 1)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne()
    @JoinColumn(name = "ID_ACAO", nullable = false)
    public AcaoModel getAcao() {
        return acao;
    }

    public void setAcao(AcaoModel acao) {
        this.acao = acao;
    }

    @Column(name = "DATA_EXECUCAO")
    public String getdataExecucao() {
        return dataExecucao;
    }

    public void setdataExecucao(String dataExecucao) {
        System.out.println(dataExecucao);
        this.dataExecucao = dataExecucao;
    }

    @Override
	public String toString() {
		return "Execucao [id_execucao=" + id + ", acao=" + acao + ", dataExecucao=" + dataExecucao + "]";
	}
}

