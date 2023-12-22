package br.com.cotiinformatica.entities;

import java.util.Date;
import java.util.UUID;

public class Conta {

	private UUID idConta;
	private String nome;
	private Date data;
	private Double valor;
	private Integer tipo;
	private String descricao;
	private UUID idUsuario;

	public Conta() {
		// TODO Auto-generated constructor stub
	}

	public Conta(UUID idConta, String nome, Date data, Double valor, Integer tipo, String descricao, UUID idUsuario) {
		super();
		this.idConta = idConta;
		this.nome = nome;
		this.data = data;
		this.valor = valor;
		this.tipo = tipo;
		this.descricao = descricao;
		this.idUsuario = idUsuario;
	}

	public UUID getIdConta() {
		return idConta;
	}

	public void setIdConta(UUID idConta) {
		this.idConta = idConta;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public UUID getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(UUID idUsuario) {
		this.idUsuario = idUsuario;
	}

	@Override
	public String toString() {
		return "Conta [idConta=" + idConta + ", nome=" + nome + ", data=" + data + ", valor=" + valor + ", tipo=" + tipo
				+ ", descricao=" + descricao + ", idUsuario=" + idUsuario + "]";
	}

}
