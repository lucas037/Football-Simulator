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
    public void salvar(Confronto cft) {
        try {
            Connection connection = BaseDAOImp.getConnection();
            
            String query = "DELETE FROM Confronto WHERE id = ?";
            PreparedStatement dec = connection.prepareStatement(query);
            dec.setInt(1, cft.getID());
            
            dec.executeUpdate();
            
            query = "INSERT INTO Confronto(id, id_fase, id_jogo_a, id_jogo_b, estadio_jogo_a, estadio_jogo_b, data_jogo_a, data_jogo_b, hora_jogo_a, hora_jogo_b) VALUES (?, ?, ?, ?, ?, ?, CAST(? AS DATE), CAST(? AS DATE), CAST(? AS TIME), CAST(? AS TIME))";
            dec = connection.prepareStatement(query);
            dec.setInt(1, cft.getID());
            dec.setInt(2, cft.getIDFase());
            dec.setInt(3, cft.getJogoA().getNumConfronto());
            dec.setString(5, cft.getJogoA().getEstadio().getNome());
            dec.setString(7, cft.getJogoA().getData().getData());
            dec.setString(9, cft.getJogoA().getData().getHora());
            
            if (cft.getJogoB() != null) { // caso jogo de volta ainda não exista, selecionada id como 0.
                dec.setInt(4, cft.getJogoB().getNumConfronto());
                dec.setString(6, cft.getJogoB().getEstadio().getNome());
                dec.setString(8, cft.getJogoB().getData().getData());
                dec.setString(10, cft.getJogoB().getData().getHora());
            }
            else {
                dec.setInt(4, -377);
                dec.setString(6, "N/A");
                dec.setString(8, "1-1-1000");
                dec.setString(10, "00:00");
            }
            
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
                confrontos[i] = new Confronto();
                
                int id = resultSet.getInt("id");
                int idFase = resultSet.getInt("id_fase");
                int idJogoA = resultSet.getInt("id_jogo_a");
                int idJogoB = resultSet.getInt("id_jogo_b");
                
                confrontos[i].setID(id);
                confrontos[i].setIDFase(idFase);
                confrontos[i].setIDJogoA(idJogoA);
                confrontos[i].setIDJogoB(idJogoB);
                
                int estadioA = resultSet.getInt("estadio_jogo_a");
                int estadioB = resultSet.getInt("estadio_jogo_b");
                
                confrontos[i].setNumEstadioA(estadioA);
                confrontos[i].setNumEstadioB(estadioB);
                
                System.out.println(confrontos[i].getIDJogoB());
                
                System.exit(0);
                
                
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
