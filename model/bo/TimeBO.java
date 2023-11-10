package model.bo;

import model.entity.Time;
import model.dao.TimeDAO;

public class TimeBO implements BaseBO<Time> {
    @Override
    public Time[] obter() {
        int tam = obterTamanho();
        Time[] times = new Time[tam];
        
        TimeDAO tmDAO = new TimeDAO();
        times = tmDAO.obter(times);
        return times;
    }
    
    public Time obter(String nome) {
        TimeDAO tmDAO = new TimeDAO();
        return tmDAO.obter(nome);
    }
    
    @Override
    public int obterTamanho() {
        TimeDAO tmDAO = new TimeDAO();
        return tmDAO.obterTamanho();
    }
}
