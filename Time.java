public class Time {
    private String nome;
    private String logo = "";
    private String estadio = "";
    
    public Time() {
        setNome("No Name");
    }
    
    public Time(String nome) {
        setNome(nome);
    }
    
    public Time(String nome, String estadio) {
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
    
    public String getLogo() {
        return this.logo;
    }
    
    public void setLogo(String logo) {
        if (logo != null) {
            this.logo = logo;
        }
    }
    
    public void setEstadio(String estadio) {
        if (estadio != null) {
            this.estadio = estadio;  
        }
    }
}
