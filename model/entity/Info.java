package model.entity;

import java.sql.*;
import model.bo.EstadioBO;
import model.bo.TimeBO;

public class Info {
    private Estadio[] estadios;
    private Time[] times;
    
    public Info() {
        gerarEstadios();
        gerarTimes();
    }
    
    public Info(String str) {
        if (str.equals("Estádio")) {
            gerarEstadios();
        }
    }
    
    private void gerarEstadios() {
        EstadioBO esBO = new EstadioBO();
        
        this.estadios = esBO.obter();
        
 
    }
    
    public Estadio[] getEstadios() {
        return this.estadios;
    }
    
    public int getNumEstadio(String nome) {
        for (int i = 0; i < estadios.length; i++) {
            if (estadios[i].getNome().equals(nome)) {
                return i;
            }
        }
        
        return 0;
    }
    
    private void gerarTimes() {
        TimeBO tmBO = new TimeBO();
        
        this.times = tmBO.obter();
    }
    
    public Time[] getTimes() {
        return this.times;
    }
    
    public int getNumTime(String nome) {
        for (int i = 0; i < times.length; i++) {
            if (times[i].getNome().equals(nome)) {
                return i;
            }
        }
        
        return 0;
    }
}
