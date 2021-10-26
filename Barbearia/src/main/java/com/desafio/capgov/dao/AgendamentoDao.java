package com.desafio.capgov.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.desafio.capgov.model.Agendamento;

// Classe que executa as querys do banco de dados
public class AgendamentoDao {
	private Connection connection;
	
	// Método para criar uma conexão com o banco
	public void abreConexao() {
		connection = new ConnectionFactory().getConnection();
	}
	
	// Método para fechar a conexão com o banco
	public void fechaConexao() {
		try {
			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException("Não foi possível fechar a conexão: " + e);
		}
	}
	
	// Método para adicionar um novo agendamento no banco
	public void adiciona(Agendamento agendamento) {
		this.abreConexao();
		String sql = "insert into agendamentos "
				+ "(cliente, telefone, servico, data, observacoes) " 
				+ "values (?,?,?,?,?)";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, agendamento.getCliente());
			stmt.setString(2, agendamento.getTelefone());
			stmt.setString(3, agendamento.getServico());
			stmt.setTimestamp(4, new Timestamp(agendamento.getData().getTimeInMillis()));
			stmt.setString(5, agendamento.getObservacoes());
		
			stmt.execute();
			stmt.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			this.fechaConexao();
		}
	}
	
	// Método para retornar uma lista com os dados do banco
	public List<Agendamento> lista() {
		this.abreConexao();
		List<Agendamento> agendamentos = new ArrayList<Agendamento>();
		String sql = "select * from agendamentos";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				Agendamento agendamento = new Agendamento();
				
				agendamento.setId(rs.getLong("id"));
				agendamento.setCliente(rs.getString("cliente"));
				agendamento.setTelefone(rs.getString("telefone"));
				agendamento.setServico(rs.getString("servico"));
				
				Calendar calendar = Calendar.getInstance();
				calendar.setTimeInMillis(rs.getTimestamp("data").getTime());
				agendamento.setData(calendar);
				
				agendamento.setObservacoes(rs.getString("observacoes"));
				
				agendamentos.add(agendamento);
			}
			
			rs.close();
			stmt.close();
			
			return agendamentos;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			this.fechaConexao();
		}
	}
	
	// Método para agendamentos pelo nome do cliente
	public List<Agendamento> pesquisa(String nome) {
		this.abreConexao();
		List<Agendamento> agendamentos = new ArrayList<Agendamento>();
		String sql = "select * from agendamentos where cliente like ?%";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, nome);
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				Agendamento agendamento = new Agendamento();
				
				agendamento.setId(rs.getLong("id"));
				agendamento.setCliente(rs.getString("cliente"));
				agendamento.setTelefone(rs.getString("telefone"));
				agendamento.setServico(rs.getString("servico"));
				
				Calendar calendar = Calendar.getInstance();
				calendar.setTimeInMillis(rs.getTimestamp("data").getTime());
				agendamento.setData(calendar);
				
				agendamento.setObservacoes(rs.getString("observacoes"));
				
				agendamentos.add(agendamento);
			}
			
			rs.close();
			stmt.close();
		
			return agendamentos;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			this.fechaConexao();
		}
	}
	
	// Método para alterar um agendamento do banco
	public void altera(Agendamento agendamento) {
		this.abreConexao();
		String sql = "update agendamentos "
				+ "set cliente=?, telefone=?, servico=?, "
				+ "data=?, observacoes=?"
				+ " where id=?";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, agendamento.getCliente());
			stmt.setString(2, agendamento.getTelefone());
			stmt.setString(3, agendamento.getServico());
			stmt.setTimestamp(4, new Timestamp(agendamento.getData().getTimeInMillis()));
			stmt.setString(5, agendamento.getObservacoes());
			stmt.setLong(6, agendamento.getId());
			
			stmt.execute();
			stmt.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			this.fechaConexao();
		}
	}
	
	// Método para remover um agendamento do banco
	public void remove(Agendamento agendamento) {
		this.abreConexao();
		String sql = "delete from agendamentos where id=?";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setLong(1, agendamento.getId());
			
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			this.fechaConexao();
		}
	}
}
