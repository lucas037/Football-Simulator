package model.bo;

import model.entity.Jogo;
import model.dao.JogoDAO;

public class JogoBO implements BaseBO<Jogo> {
    public void salvar(Jogo[] jgs, int tamanho) {
        limpar();
        JogoDAO jgDAO = new JogoDAO();
        for (int i = 0; i < tamanho; i++) {
            jgDAO.salvar(jgs[i]);
            
        }
    }
    
    public void limpar() {
        JogoDAO jgDAO = new JogoDAO();
        jgDAO.limpar();
    }
    
    @Override
    public Jogo[] obter() {
        int tam = obterTamanho();
        Jogo[] jogos = new Jogo[tam];
        
        JogoDAO jgDAO = new JogoDAO();
        jogos = jgDAO.obter(jogos);
        return jogos;
    }
    
    public Jogo obter(int num) {
        JogoDAO jgDAO = new JogoDAO();
        return jgDAO.obter(num);
    }
    
    @Override
    public int obterTamanho() {
        JogoDAO jgDAO = new JogoDAO();
        
        return jgDAO.obterTamanho();
    }
}
