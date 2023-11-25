package model.dao;

import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import model.entity.Grupo;

public class GrupoDAO extends BaseDAOImp<Grupo> {
    public void limpar() {
        try {
            Connection connection = BaseDAOImp.getConnection();
            
            String query = "DELETE FROM Grupo;";
            PreparedStatement dec = connection.prepareStatement(query);
            dec.executeUpdate();
            
            query = "DELETE FROM Grupo_Clubes;";
            dec = connection.prepareStatement(query);
            dec.executeUpdate();
            
            query = "DELETE FROM Grupo_Partida;";
            dec = connection.prepareStatement(query);
            dec.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    public void salvar(Grupo gp) {
        try {
            Connection connection = BaseDAOImp.getConnection();
            
            String query = "INSERT INTO Grupo(id, id_fase, status, rodadas, jogos_rodada, jogos_encerrados) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement dec = connection.prepareStatement(query);
            
            dec.setInt(1, gp.getID());
            dec.setInt(2, gp.getIDFase());
            dec.setString(3, gp.getStatus());
            dec.setInt(4, gp.getNumRodadas());
            dec.setInt(5, gp.getNumJogosRodada());
            dec.setInt(6, gp.getNumJogosEncerrados());
            
            dec.executeUpdate();
            
            // armazenamento do id dos clubes do grupo.
            
            int idGrupo = gp.getID();
            int[] idClubes = gp.getIDClubes();
            
            for (int i = 0; i < idClubes.length; i++) {
                query = "INSERT INTO Grupo_Clubes(id_grupo, id_clube) VALUES (?, ?)";
                dec = connection.prepareStatement(query);

                dec.setInt(1, idGrupo);
                dec.setInt(2, idClubes[i]);

                dec.executeUpdate();
                
            }
            
            // armazenamento dos id's das partidas
            
            int[][] idPartidas = gp.getIDPartidas();
            
            for (int i = 0; i < idPartidas.length; i++) {
                
                for (int j = 0; j < idPartidas[0].length; j++) {
                    query = "INSERT INTO Grupo_Partida(id_grupo, rodada, id_partida) VALUES (?, ?, ?)";
                    dec = connection.prepareStatement(query);

                    dec.setInt(1, idGrupo);
                    dec.setInt(2, i);
                    dec.setInt(3, idPartidas[i][j]);

                    dec.executeUpdate();
                }
                
            }
            
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public Grupo[] obter(Grupo[] grupos) {
        return grupos;
    }
    
    @Override
    public int obterTamanho() {
        return 0;
    }
}
