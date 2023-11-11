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

public class JogoDAO extends BaseDAOImp<Jogo> {
    public void salvar(Jogo jg) {
        try {
            Connection connection = BaseDAOImp.getConnection();
            
            String query = "DELETE FROM Jogo WHERE id = ?";
            PreparedStatement dec = connection.prepareStatement(query);
            dec.setInt(1, jg.getNumJogo());
            
            dec.executeUpdate();
            
            query = "INSERT INTO Jogo(id, timea, timeb, data, hora, estadio, progresso, tempo, tempoacres, placar_a, placar_b, agregado, jogo_agregado) VALUES (?, ?, ?, CAST(? AS DATE), CAST(? AS TIME), ?, ?, ?,?, ?, ?, ?, ?)";
            dec = connection.prepareStatement(query);
            dec.setInt(1, jg.getNumJogo());
            dec.setString(2, jg.getTimeA().getNome());
            dec.setString(3, jg.getTimeB().getNome());
            dec.setString(4, jg.getData().getData());
            dec.setString(5, jg.getData().getHora());
            dec.setString(6, jg.getEstadio().getNome());
            dec.setString(7, jg.getFaseJogo());
            dec.setInt(8, jg.getTempo());
            dec.setInt(9, jg.getTempoAcrescimo());
            dec.setInt(10, jg.getPlacarA());
            dec.setInt(11, jg.getPlacarB());
            dec.setBoolean(12, jg.getTipoConfronto());
            dec.setInt(13, jg.getJogoAgregado());
            
            dec.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
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
                
                int id = resultSet.getInt("id");
                jogos[i].setNumJogo(id);
                
                String progresso = resultSet.getString("progresso");
                jogos[i].setFaseJogo(progresso);
                
                if (!progresso.equals("Em Breve")) {
                    jogos[i].setPlacar(resultSet.getInt("placar_a"), resultSet.getInt("placar_b"));
                    jogos[i].setTempo(resultSet.getInt("tempo"));
                    jogos[i].setTempoAcrescimo(resultSet.getInt("tempoacres"));
                }
                
                boolean agregado = resultSet.getBoolean("agregado");
                jogos[i].setTipoConfronto(agregado);
                
                if (agregado) {
                    jogos[i].setAgregado(resultSet.getInt("agregado_a"), resultSet.getInt("agregado_b"));
                    jogos[i].setPenalti(resultSet.getInt("penalti_a"), resultSet.getInt("penalti_b"));
                }
                
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
