package model.dao;

import java.sql.*;
import model.entity.Jogo;
import model.entity.Data;
import model.entity.Time;
import model.bo.TimeBO;
import model.entity.Estadio;
import model.bo.EstadioBO;

public class JogoDAO extends BaseDAOImp<Jogo> {
    @Override
    public Jogo[] obter(Jogo[] jogos) {
        try {
            Connection connection = BaseDAOImp.getConnection();

            String query = "SELECT * FROM Jogo";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            
            int i = 0;
            while (resultSet.next()) {
                String nomeA = resultSet.getString("timea");
                String nomeB = resultSet.getString("timeb");
                
                TimeBO tmBO = new TimeBO();
                Time timeA = tmBO.obter(nomeA);
                Time timeB = tmBO.obter(nomeB);

                String nomeEstadio = resultSet.getString("estadio");
                
                EstadioBO esBO = new EstadioBO();
                Estadio estadio = esBO.obter(nomeEstadio);

                java.sql.Date nData = resultSet.getDate("data");
                java.sql.Time nHora = resultSet.getTime("hora");
                
                Data data = new Data(nData, nHora);
                
                jogos[i] = new Jogo(timeA, timeB, data, estadio);
                
                i++;
            }
            

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        BaseDAOImp.closeConnection();
        return jogos;
    }
    
    @Override
    public int obterTamanho() {
        int total = 377;
        try {
            Connection connection = BaseDAOImp.getConnection();

            String query = "SELECT COUNT(*) AS total FROM Jogo";
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
