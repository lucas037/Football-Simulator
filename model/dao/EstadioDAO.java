package model.dao;

import java.sql.*;
import model.entity.Estadio;

public class EstadioDAO extends BaseDAOImp<Estadio> {
    @Override
    public Estadio[] obter(Estadio[] estadios) {
        try {
            Connection connection = BaseDAOImp.getConnection();

            String query = "SELECT * FROM Estadio";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            
            int i = 0;
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                int capacidade = resultSet.getInt("capacidade");

                estadios[i] = new Estadio(id, nome, capacidade);
                i++;
            }
            

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        BaseDAOImp.closeConnection();
        return estadios;
    }
    
    public Estadio obter(String nome) {
        Estadio estadio = new Estadio();
        try {
            Connection connection = BaseDAOImp.getConnection();

            String query = "SELECT * FROM Estadio WHERE nome = '"+nome+"';";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            
            resultSet.next();
            int id = resultSet.getInt("id");
            String nm = resultSet.getString("nome");
            int capacidade = resultSet.getInt("capacidade");

            estadio = new Estadio(id, nome, capacidade);
            

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        BaseDAOImp.closeConnection();
        return estadio;
    }
    
    public Estadio obter(int id) {
        Estadio estadio = new Estadio();
        try {
            Connection connection = BaseDAOImp.getConnection();

            String query = "SELECT * FROM Estadio WHERE id = '"+id+"';";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            
            resultSet.next();
            String nm = resultSet.getString("nome");
            int capacidade = resultSet.getInt("capacidade");

            estadio = new Estadio(id, nm, capacidade);
            

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        BaseDAOImp.closeConnection();
        return estadio;
    }
    
    @Override
    public int obterTamanho() {
        int total = 377;
        try {
            Connection connection = BaseDAOImp.getConnection();

            String query = "SELECT COUNT(*) AS total FROM Estadio";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            
            resultSet.next();
            total = resultSet.getInt("total");
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return total;
    }
}