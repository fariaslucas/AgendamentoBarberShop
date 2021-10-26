package com.desafio.capgov.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe para criar a conexão com o banco de dados. 
 * Foi utilizado o mysql connector 8.0.27.
 * Se necessário, mudar user e password no DriverManager.getConnection.
 */
public class ConnectionFactory {
	public Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection("jdbc:mysql://localhost/barbearia", 
					"desafio", "capgov");
		} catch (SQLException | ClassNotFoundException e) {
			throw new RuntimeException("Ocorreu um erro na conexão com o banco: " + e);
		}
	}
}