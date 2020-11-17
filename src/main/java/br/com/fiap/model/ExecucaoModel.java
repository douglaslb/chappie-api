package br.com.fiap.model;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TB_EXECUCAO")
public class ExecucaoModel {
    private long id;
    private AcaoModel acao;
    private Date dataExecucao;

    public ExecucaoModel() {

    }

    public ExecucaoModel(long id, AcaoModel acao, Date dataExecucao) {
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
    public Date getdataExecucao() {
        return dataExecucao;
    }

    public void setdataExecucao(Date dataExecucao) {
        this.dataExecucao = dataExecucao;
    }
}

