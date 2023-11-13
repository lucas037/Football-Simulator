package model.bo;

import model.entity.Confronto;
import model.dao.ConfrontoDAO;

public class ConfrontoBO implements BaseBO<Confronto> {
    public void salvar(Confronto[] cft, int tamanho) {
        ConfrontoDAO cftDAO = new ConfrontoDAO();
        for (int i = 0; i < tamanho; i++) {
            cftDAO.salvar(cft[i]);
            
        }
    }
    
    @Override
    public Confronto[] obter() {
        int tam = obterTamanho();
        Confronto[] confrontos = new Confronto[tam];
        
        ConfrontoDAO cftDAO = new ConfrontoDAO();
        
        confrontos = cftDAO.obter(confrontos);
        
        return confrontos;
    }
    
    @Override
    public int obterTamanho() {
        ConfrontoDAO cftDAO = new ConfrontoDAO();
        
        return cftDAO.obterTamanho();
    }
}
