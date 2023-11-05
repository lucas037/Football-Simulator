package model.entity;

public class Time {
    private String nome;
    private String code = "";
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
    
    public String getNome() {
        return this.nome;
    }
    
    public void setNome(String nome) {
        if (nome != null) {
            this.nome = nome;
        }
    }
    
    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        if (code != null) {
            this.code = code;
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
        
        str += "Nome: "+nome;
        str += "\nCódigo: "+code;
        str += "\n-";
        str += "\nInformações do Estádio:\n";
        str += estadio.toString();
        str += "\n-\n";
        
        return str;
    }
}
