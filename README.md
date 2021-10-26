# AgendamentoBarberShop
Sistema de agendamentos para uma barbearia capaz de criar, editar, listar e deletar um agendamento.

## Informações
Para esse projeto, foi utilizado o Eclipse IDE 2021-09 juntamente com o servidor Apache Tomcat 9.0.44 e o banco de dados MySQL 5.7.35, além disso foram utilizadas as seguintes bibliotecas(Todas encontradas na pasta Barbearia/src/main/WEB-INF/lib):

- javax.faces-2.2.10.jar
- mysql-connector-java-8.0.27.jar
- primefaces-10.0.0.jar

## Instruções
1. Executar o script barbearia.sql para gerar a tabela
2. Abrir o projeto na IDE
3. Mudar usuário e senha do MySQL no arquivo Barbearia/src/main/java/com/desafio/capgov/dao/ConnectionFactory.java
4. Se necessário, baixar e instalar o Tomcat
5. Integrar o projeto no Tomcat e iniciar o servidor
6. A aplicação pode ser acessada em http://localhost:8080/Barbearia/
