package com.desafio.capgov.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

//Classe que implementa o agendamento
public class Agendamento {
	
	// Atributos
	private Long id;
	private String cliente;
	private String telefone;
	private String servico;
	private Calendar data;
	private String observacoes;
	
	// Getters e Setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getServico() {
		return servico;
	}
	public void setServico(String servico) {
		this.servico = servico;
	}
	public Calendar getData() {
		return data;
	}
	public void setData(Calendar data) {
		this.data = data;
	}
	public String getObservacoes() {
		return observacoes;
	}
	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	
	//toString, hashCode e equals
	@Override
	public String toString() {
		SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		return "Agendamento [id=" + id + 
				", cliente=" + cliente + 
				", telefone=" + telefone + 
				", servico=" + servico + 
				", data=" + fmt.format(data.getTime()) + 
				", observacoes=" + observacoes + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Agendamento other = (Agendamento) obj;
		return Objects.equals(id, other.id);
	}
}
