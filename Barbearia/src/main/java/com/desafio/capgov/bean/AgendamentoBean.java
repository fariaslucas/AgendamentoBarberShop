package com.desafio.capgov.bean;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

import com.desafio.capgov.dao.AgendamentoDao;
import com.desafio.capgov.model.Agendamento;

@ManagedBean
@ViewScoped
public class AgendamentoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Agendamento agendamento;
	private List<Agendamento> agendamentos;
	private Date dataSelecionada;
	
	// Getters e Setters
	
	public Date getDataSelecionada() {
		return dataSelecionada;
	}

	public void setDataSelecionada(Date dataSelecionada) {
		this.dataSelecionada = dataSelecionada;
	}

	public Agendamento getAgendamento() {
		return agendamento;
	}
	
	public void setAgendamento(Agendamento agendamento) {
		this.agendamento = agendamento;
	}
	
	public List<Agendamento> getAgendamentos() {
		return agendamentos;
	}
	
	public void setAgendamentos(List<Agendamento> agendamentos) {
		this.agendamentos = agendamentos;
	}

	// Método para listar os agendamentos
	public void listar() {
		AgendamentoDao agendaDao = new AgendamentoDao();
		
		agendamentos = agendaDao.lista();
		
		// Se não foram encontrados agendametos, indica para o usuário
		if (agendamentos == null || agendamentos.size() == 0) {
			FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nenhum agendamento encontrado", "Erro");
			FacesContext.getCurrentInstance().addMessage(null, mensagem);
		}
		
	}
	
	// Método para criar um novo agendamento
	public void novo() {
		this.agendamento = new Agendamento();
	}
	
	// Método para salvar o agendamento
	public void salvar() {
		// Se a data selecionada já passou, insere uma mensagem de erro
		if (dataSelecionada.before(new Date())) {
			FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Insira uma data válida", "Erro");
			FacesContext.getCurrentInstance().addMessage(null, mensagem);
			PrimeFaces.current().ajax().update("formAgendamento:mensagem");
			return;
		}
		
		AgendamentoDao agendaDao = new AgendamentoDao();
		
		/* Se o id for nulo, então o agendamento será adicionado no banco.
		 * Caso contrário, o agendamento será alterado. */
		if (agendamento.getId() == null) {
			agendaDao.adiciona(agendamento);
			agendamento = new Agendamento();
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Agendamento concluído"));
		} else {
			agendaDao.altera(agendamento);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Agendamento editado"));
		}
		
		// Esconde novamente o dialog e atualiza a tela com a mensagem de sucesso
		PrimeFaces.current().executeScript("PF('agendamentoDialog').hide()");
		PrimeFaces.current().ajax().update("formAgendamento:mensagem", "formAgendamento:dtAgendamentos");
		this.listar();
	}

	// Método para converter o Date recebido em Calendar
	public void converter() {
		Calendar calendar = Calendar.getInstance();
		
		// Se a data selecionada já passou, insere uma mensagem de erro
		if (dataSelecionada.before(new Date())) {
			FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Insira uma data válida", "Erro");
			FacesContext.getCurrentInstance().addMessage(null, mensagem);
			PrimeFaces.current().ajax().update("formAgendamento:mensagem");
			return;
		}
		
		calendar.setTime(dataSelecionada);
		agendamento.setData(calendar);
	}
	
	// Método para excluir um agendamento
	public void excluir() {
		AgendamentoDao agendaDao = new AgendamentoDao();
		agendaDao.remove(agendamento);
        agendamento = new Agendamento();
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Agendamento excluído"));
        
        PrimeFaces.current().ajax().update("formAgendamento:mensagem","formAgendamento:dtAgendamentos");
        this.listar();	
	}
	
}
