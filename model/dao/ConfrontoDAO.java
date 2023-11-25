package model.dao;

import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import model.entity.Jogo;
import model.entity.Data;
import model.entity.Time;
import model.bo.TimeBO;
import model.entity.Estadio;
import model.bo.EstadioBO;
import model.entity.Jogo;

import model.bo.JogoBO;
import model.entity.Confronto;

public class ConfrontoDAO extends BaseDAOImp<Confronto> {
    public void limpar() {
        try {
            Connection connection = BaseDAOImp.getConnection();
            
            String query = "DELETE FROM Confronto;";
            PreparedStatement dec = connection.prepareStatement(query);
            dec.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    public void salvar(Confronto cft) {
        try {
            Connection connection = BaseDAOImp.getConnection();
            
            String query = "DELETE FROM Confronto WHERE id = ?";
            PreparedStatement dec = connection.prepareStatement(query);
            dec.setInt(1, cft.getID());
            
            dec.executeUpdate();
            
            query = "INSERT INTO Confronto(id, id_fase, tipo, id_time_a, id_time_b, id_jogo_a, id_jogo_b, estadio_jogo_a, estadio_jogo_b, data_jogo_a, data_jogo_b, hora_jogo_a, hora_jogo_b) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, CAST(? AS DATE), CAST(? AS DATE), CAST(? AS TIME), CAST(? AS TIME))";
            dec = connection.prepareStatement(query);
            dec.setInt(1, cft.getID());
            dec.setInt(2, cft.getIDFase());
            dec.setString(3, cft.getTipo());
            dec.setInt(4, cft.getIDTimeA());
            dec.setInt(5, cft.getIDTimeB());
            dec.setInt(6, cft.getIDJogoA());
            dec.setInt(7, cft.getIDJogoB());
            dec.setInt(8, cft.getIDEstadioA());
            dec.setInt(9, cft.getIDEstadioB());
            dec.setString(10, cft.getDataA().getData());
            dec.setString(11, cft.getDataB().getData());
            dec.setString(12, cft.getDataA().getHora());
            dec.setString(13, cft.getDataB().getHora());
            
            dec.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public Confronto[] obter(Confronto[] confrontos) {
        try {
            Connection connection = BaseDAOImp.getConnection();

            String query = "SELECT * FROM Confronto";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            
            int i = 0;
            while (resultSet.next()) {
                
                int idTimeA = resultSet.getInt("id_time_a");
                int idTimeB = resultSet.getInt("id_time_b");
                int idEstadioA = resultSet.getInt("estadio_jogo_a");
                int idEstadioB = resultSet.getInt("estadio_jogo_b");
                
                java.sql.Date nData = resultSet.getDate("data_jogo_a");
                java.sql.Time nHora = resultSet.getTime("hora_jogo_a");
                Data dataA = new Data(nData, nHora);
                
                nData = resultSet.getDate("data_jogo_b");
                nHora = resultSet.getTime("hora_jogo_b");
                Data dataB = new Data(nData, nHora);
                
                confrontos[i] = new Confronto(idTimeA, idTimeB, dataA, dataB, idEstadioA, idEstadioB);
                
                int id = resultSet.getInt("id");
                int idFase = resultSet.getInt("id_fase");
                confrontos[i].setID(id);
                confrontos[i].setIDFase(idFase);
                
                int idJogoA = resultSet.getInt("id_jogo_a");
                int idJogoB = resultSet.getInt("id_jogo_b");
                confrontos[i].setIDJogoA(idJogoA);
                confrontos[i].setIDJogoB(idJogoB);
                
                i++;
            }
            

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        BaseDAOImp.closeConnection();
        return confrontos;
    }
    
    @Override
    public int obterTamanho() {
        int total = 377;
        try {
            Connection connection = BaseDAOImp.getConnection();

            String query = "SELECT COUNT(*) AS total FROM Confronto";
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
