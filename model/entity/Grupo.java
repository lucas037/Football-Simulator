package model.entity;

public class Grupo {
    private int id = -1;
    private int idFase = -1;
    private int[] idClubes;
    private String modelo = "Versus";
    private String enfrentamento = "Casa e Fora";
    private GrupoDesempenho[] desempenhoTimes;
    
    public Grupo(int[] idClubes) {
        this.idClubes = idClubes;
        
        desempenhoTimes = new GrupoDesempenho[idClubes.length];
        for (int i = 0; i < idClubes.length; i++)
            desempenhoTimes[i] = new GrupoDesempenho(idClubes[i]);
        
        
    }
    
    public int getID() {
        return this.id;
    }
    
    public void setID(int id) {
        this.id = id;
    }
    
    public int getIDFase() {
        return this.idFase;
    }
    
    public void setIDFase(int id) {
        this.idFase = id;
    }
    
    public int[] getIDClubes() {
        return this.idClubes;
    }
    
    public void setIDClubes(int[] idClubes) {
        this.idClubes = idClubes;
    }
    
    public String getModelo() {
        return this.modelo;
    }
    
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    
    public String getEnfrentamento() {
        return this.enfrentamento;
    }
    
    public void setEnfrentamento(String enfrentamento) {
        this.enfrentamento = enfrentamento;
    }
    
    public GrupoDesempenho[] getDesempenhoTimes() {
        return this.desempenhoTimes;
    }
    
    public void setDesempenhoTimes(GrupoDesempenho[] desempenhoTimes) {
        this.desempenhoTimes = desempenhoTimes;
    }
    
    public void sortear() {
        
    }
    
    @Override
    public String toString() {
        String str = "";
        
        str += "Jog\t";
        str += "Vit\t";
        str += "Emp\t";
        str += "Dert\t";
        str += "Gols\t";
        str += "Gols Sof.\t";
        str += "Sald. Gols\t";
        str += "Aprov. (%)\t";
        str += "Pontos\n";
        
        for (int i = 0; i < desempenhoTimes.length; i++) {
            str += desempenhoTimes[i].toString();
        }
        
        return str;
    }
}
