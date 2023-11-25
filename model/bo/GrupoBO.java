package model.bo;

import model.dao.GrupoDAO;
import model.entity.Grupo;

public class GrupoBO implements BaseBO<Grupo> {
    public void salvar(Grupo[] gp, int tamanho) {
        GrupoDAO gpDAO = new GrupoDAO();
        gpDAO.limpar();
        
        for (int i = 0; i < tamanho; i++) {
            gpDAO.salvar(gp[i]);
        }
        System.exit(0);
    }
    
    @Override
    public Grupo[] obter() {
        Grupo[] grupos = new Grupo[2];
        
        return grupos;
    }
    
    @Override
    public int obterTamanho() {
        return 0;
    }
}
