package br.com.fiap.model;

import jdk.jfr.BooleanFlag;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "TB_ACAO")
public class AcaoModel {
    private long id;
    private String nome;
    private String descricao;
    private Boolean ativo;

    public AcaoModel() {
    }

    public AcaoModel(long id, String nome, String descricao, boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.ativo = ativo;
    }

    @Id
    @Column(name = "ID_ACAO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACAO_SEQ")
    @SequenceGenerator(name = "ACAO_SEQ", sequenceName = "ACAO_SEQ", allocationSize = 1)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "NOME")
    @NotNull(message = "Nome obrigatório")
    @Size(max = 40, message = "NOME deve conter no máximo 40 caracteres")
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Column(name = "DESCRICAO")
    @NotNull(message = "Descrição obrigatória")
    @Size(max = 400, message = "DESCRIÇÃO deve conter no máximo 100 caracteres")
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Column(name = "ATIVO")
    @NotNull(message = "Ativo (status) é obrigatório")
    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    @Override
	public String toString() {
		return "AcaoModel [id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", descricao=" + descricao + ", ativo="
				+ ativo + "]";
	}
}