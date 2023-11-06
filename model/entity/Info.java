package model.entity;

import java.sql.*;
import model.bo.EstadioBO;
import model.bo.TimeBO;

public class Info {
    private Estadio[] estadios;
    private Time[] times;
    private Campeonato[] campeonatos;
    
    public Info() {
        gerarEstadios();
        gerarTimes();
        gerarCampeonatos();
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
    
    private Time[] getTimesCampeonato(String nome) {
        if (nome.equals("Copa do Brasil")) {
            Time[] times = {
                this.times[getNumTime("Flamengo")],
                this.times[getNumTime("Fluminense")],
                this.times[getNumTime("São Paulo")],
                this.times[getNumTime("Palmeiras")]
            };
            
            return times;
        }
       return times; 
    }
    
    private void gerarCampeonatos() {
        Campeonato[] campeonatos = {
            new Campeonato("Copa do Brasil", getTimesCampeonato("Copa do Brasil"))
        };
        
        this.campeonatos = campeonatos;
    }
    
    public Campeonato[] getCampeonatos() {
        return this.campeonatos;
    }
}
