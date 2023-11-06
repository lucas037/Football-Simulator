package model.dao;

import java.sql.*;
import model.entity.Time;
import model.entity.Estadio;
import model.dao.EstadioDAO;
import model.entity.Info;

public class TimeDAO extends BaseDAOImp<Time> {
    @Override
    public Time[] obter(Time[] times) {
        try {
            Connection connection = BaseDAOImp.getConnection();

            String query = "SELECT id, nome, estadio from Time;";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            
            int i = 0;
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                String nomeEstadio = resultSet.getString("estadio");
                
                EstadioDAO esDAO = new EstadioDAO();
                Estadio estadio = esDAO.obter(nomeEstadio);
                
                times[i] = new Time(id, nome, estadio);
                i++;
            }
            

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        BaseDAOImp.closeConnection();
        return times;
    }
    
    @Override
    public int obterTamanho() {
        int total = 377;
        try {
            Connection connection = BaseDAOImp.getConnection();

            String query = "SELECT COUNT(*) AS total FROM Time";
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
