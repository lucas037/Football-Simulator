package model.dao;

import java.sql.*;
import model.entity.Estadio;

public class EstadioDAO extends BaseDAOImp<Estadio> {
    @Override
    public Estadio[] obter(Estadio[] estadios) {
        try {
            Connection connection = BaseDAOImp.getConnection();

            String query = "SELECT nome, capacidade FROM Estadio";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            
            int i = 0;
            while (resultSet.next()) {
                String nome = resultSet.getString("nome");
                int capacidade = resultSet.getInt("capacidade");

                estadios[i] = new Estadio(nome, capacidade);
                i++;
            }
            

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        BaseDAOImp.closeConnection();
        return estadios;
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