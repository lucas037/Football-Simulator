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
    
    @Override
    public int obterTamanho() {
        TimeDAO tmDAO = new TimeDAO();
        return tmDAO.obterTamanho();
    }
}
