package com.tasklist.dtos;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

public class TaskDto implements Serializable {

	private Long id;
	
	private String titulo;
	
	private String descricao;
	
	private Long status;

	private Date data_criacao;
	
	private Date data_edicao;
	
	private Date data_remocao;

	private Date data_conclusao;

	public TaskDto() {

	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@NotNull(message = "O Titulo da task é uma informação obrigatória")
	@Length(min = 3, max = 40, message = "O Titulo da task deve possuir entre 3 e 40 caracteres")
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

	@Override
	public String toString() {
		return "TaskDto [id=" + id + ", descricao=" + descricao + ", status=" + status + ", data_criacao="
				+ data_criacao + ", data_edicao=" + data_edicao + ", data_remocao=" + data_remocao + ", data_conclusao="
				+ data_conclusao + "]";
	}
}