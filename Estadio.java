public class Estadio {
    private String nome;
    private int capacidade;
    private String dono;
    
    public void setNome(String nome) {
        if (nome != null) {
            this.nome = nome;
        }
    }
    
    public String getNome() {
        return this.nome;
    }
    
    public void setCapacidade(int capacidade) {
        if (capacidade > 0) {
            this.capacidade = capacidade;
        }
    }
    
    public int getCapacidade() {
        return this.capacidade;
    }
    
    public void setDono(String dono) {
        if (dono != null) {
            this.dono = dono;
        }
    }
    
    public String getDono() {
        return this.dono;
    }
}
