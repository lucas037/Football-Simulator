public class Estadio {
    private String nome;
    private int capacidade;
    private String dono;
    
    public Estadio() {
        setNome(null);
        setCapacidade(0);
        setDono(null);
    }
    
    public Estadio(String nome) {
        setNome(nome);
        setCapacidade(0);
        setDono(null);
    }
    
    public Estadio(String nome, int capacidade) {
        setNome(nome);
        setCapacidade(capacidade);
        setDono(null);
    }
    
    public Estadio(String nome, int capacidade, String dono) {
        setNome(nome);
        setCapacidade(capacidade);
        setDono(dono);
    }
    
    public void setNome(String nome) {
        if (nome != null) {
            this.nome = nome;
        }
        else
            this.nome = "Sem Nome";
    }
    
    public String getNome() {
        return this.nome;
    }
    
    public void setCapacidade(int capacidade) {
        if (capacidade > 0) {
            this.capacidade = capacidade;
        }
        else
            this.capacidade = 200;
    }
    
    public int getCapacidade() {
        return this.capacidade;
    }
    
    public void setDono(String dono) {
        if (dono != null) {
            this.dono = dono;
        }
        else
            this.dono = "Sem Nome";
    }
    
    public String getDono() {
        return this.dono;
    }
    
    @Override
    public String toString() {
        String str = "";
        
        str += "Nome: "+nome;
        str += "\nCapacidade: "+capacidade;
        str += "\nDono: "+dono;
        
        return str;
    }
}
