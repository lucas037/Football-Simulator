package model.bo;

import model.entity.Estadio;
import model.dao.EstadioDAO;

public class EstadioBO implements BaseBO<Estadio> {
    @Override
    public Estadio[] obter() {
        int tam = obterTamanho();
        Estadio[] estadios = new Estadio[tam];
        
        EstadioDAO esDAO = new EstadioDAO();
        estadios = esDAO.obter(estadios);
        return estadios;
    }
    
    @Override
    public int obterTamanho() {
        EstadioDAO esDAO = new EstadioDAO();
        return esDAO.obterTamanho();
    }
}
