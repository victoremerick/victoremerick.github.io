package com.tasklist.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

@Entity
@Component
@Table(name="task")
public class Task implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID", nullable=false)
	private Long id;

	@Column(name="TITULO", nullable=false)
	private String titulo;
	
	@Lob
	@Column(name="DESCRICAO", nullable=false)
	private String descricao;
	
	@Column(name="STATUS", nullable=false)
	private Long status;

	@Column(name="DATA_CRIACAO")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using=DateSerializer.class)
	private Date data_criacao;
	
	@Column(name="DATA_EDICAO")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using=DateSerializer.class)
	private Date data_edicao;
	
	@Column(name="DATA_REMOCAO")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using=DateSerializer.class)
	private Date data_remocao;

	@Column(name="DATA_CONCLUSAO")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using=DateSerializer.class)
	private Date data_conclusao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Date getData_criacao() {
		return data_criacao;
	}

	public void setData_criacao(Date data_criacao) {
		this.data_criacao = data_criacao;
	}

	public Date getData_edicao() {
		return data_edicao;
	}

	public void setData_edicao(Date data_edicao) {
		this.data_edicao = data_edicao;
	}

	public Date getData_remocao() {
		return data_remocao;
	}

	public void setData_remocao(Date data_remocao) {
		this.data_remocao = data_remocao;
	}

	public Date getData_conclusao() {
		return data_conclusao;
	}

	public void setData_conclusao(Date data_conclusao) {
		this.data_conclusao = data_conclusao;
	}
	
}
