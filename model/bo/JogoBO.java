package model.bo;

import model.entity.Jogo;
import model.dao.JogoDAO;

public class JogoBO implements BaseBO<Jogo> {
    @Override
    public Jogo[] obter() {
        int tam = obterTamanho();
        Jogo[] jogos = new Jogo[tam];
        
        JogoDAO jgDAO = new JogoDAO();
        jogos = jgDAO.obter(jogos);
        return jogos;
    }
    
    @Override
    public int obterTamanho() {
        JogoDAO jgDAO = new JogoDAO();
        
        return jgDAO.obterTamanho();
    }
}
