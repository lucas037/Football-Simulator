package model.entity;

import model.bo.TimeBO;

public class GrupoDesempenho {
    private int idTime;
    private int pontos;
    private int jogos;
    private int vitorias;
    private int empates;
    private int derrotas;
    private int gols;
    private int golsSofridos;
    private int saldoGols;
    private Double aproveitamento = 0.0;
    
    public GrupoDesempenho(int id) {
        this.idTime = id;
    }
    
    public int getIDTimes() {
        return this.idTime;
    }
    
    public void setIDTimes(int idTime) {
        this.idTime = idTime;
    }
    
    public int getPontos() {
        return this.pontos;
    }
    
    public void setPontos(int pontos) {
        this.pontos = pontos;
    }
    
    public int getJogos() {
        return this.jogos;
    }
    
    public void setJogos(int jogos) {
        this.jogos = jogos;
    }
    
    public int getVitorias() {
        return this.vitorias;
    }
    
    public void setVitorias(int vitorias) {
        this.vitorias = vitorias;
    }
    
    public int getEmpates() {
        return this.empates;
    }
    
    public void setEmpates(int empates) {
        this.empates = empates;
    }
    
    public int getDerrotas() {
        return this.derrotas;
    }
    
    public void setDerrotas(int derrotas) {
        this.derrotas = derrotas;
    }
    
    public int getGols() {
        return this.gols;
    }
    
    public void setGols(int gols) {
        this.gols = gols;
    }
    
    public int getGolsSofridos() {
        return this.golsSofridos;
    }
    
    public void setGolsSofridos(int golsSofridos) {
        this.golsSofridos = golsSofridos;
    }
    
    public int getSaldoGols() {
        return this.saldoGols;
    }
    
    public void setSaldoGols(int saldoGols) {
        this.saldoGols = saldoGols;
    }
    
    public Double getAproveitamento() {
        return this.aproveitamento;
    }
    
    public void setAproveitamento(Double aproveitamento) {
        this.aproveitamento = aproveitamento;
    }
    
    public void addResultado(int pro, int contra) {
        gols += pro;
        golsSofridos += contra;
        saldoGols = gols - golsSofridos;
        jogos++;
        
        if (pro > contra) {
            vitorias++;
            pontos += 3;
        }
        else if (contra > pro)
            derrotas++;
        else {
            empates++;
            pontos += 1;
        }
        
        if (jogos > 0)
            aproveitamento = 100*((double) pontos / (3*jogos));
    }
    
    @Override
    public String toString() {
        TimeBO tmBO = new TimeBO();
        Time time = tmBO.obter(idTime);
        String nomeTime = time.getNome();
        
        String str = "";
        
        str += jogos+"\t";
        str += vitorias+"\t";
        str += empates+"\t";
        str += derrotas+"\t";
        str += gols+"\t";
        str += golsSofridos+"\t\t";
        str += saldoGols+"\t\t";
        str += String.format("%.2f", aproveitamento)+"%\t\t";
        str += pontos+"\t\t";
        str += nomeTime+"\n";
        
        return str;
    }
}
