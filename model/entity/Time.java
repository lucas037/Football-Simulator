package model.entity;

public class Time {
    private int id;
    private String nome;
    private Estadio estadio;
    
    public Time() {
        setNome("No Name");
    }
    
    public Time(String nome) {
        setNome(nome);
    }
    
    public Time(String nome, Estadio estadio) {
        setNome(nome);
        setEstadio(estadio);
    }
    
    public Time(int id, String nome, Estadio estadio) {
        setID(id);
        setNome(nome);
        setEstadio(estadio);
    }
    
    public int getID() {
        return this.id;
    }
    
    public void setID(int id) {
        if (id >= 0) {
            this.id = id;
        }
    }
    
    public String getNome() {
        return this.nome;
    }
    
    public void setNome(String nome) {
        if (nome != null) {
            this.nome = nome;
        }
    }
    
    public Estadio getEstadio() {
        return this.estadio;
    }
    
    public void setEstadio(Estadio estadio) {
        if (estadio.getNome() != null) {
            this.estadio = estadio;  
        }
    }
    
    @Override
    public String toString() {
        String str = "";
        
        str += "ID: "+id;
        str += "\nNome: "+nome;
        str += "\n-";
        str += "\nInformações do Estádio:\n";
        if (estadio != null)
            str += estadio.toString();
        str += "\n-\n";
        
        return str;
    }
}
