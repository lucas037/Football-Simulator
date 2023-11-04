
public class Campeonato {
    private String nome;
    private Time[] times;
    private Fase[] fases;
    
    public Campeonato(String nome, Time[] times) {
        setNome(nome);
        setTimes(times);
    }
    
    public String getNome() {
        return this.nome;
    }
    
    public void setNome(String nome) {
        if (nome != null) {
            this.nome = nome;
        }
    }
    
    public void setTimes(Time[] times) {
        this.times = times;
    }
    
    public void exibirTimes() {
        for (int i = 0; i < times.length; i++) {
            System.out.println(times[i].getNome());
        }
    }
}
