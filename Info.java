public class Info {
    private Estadio[] estadios;
    private Time[] times;
    private Campeonato[] campeonatos;
    
    public Info() {
        gerarEstadios();
        gerarTimes();
        gerarCampeonatos();
    }
    
    private void gerarEstadios() {
        Estadio[] estadios = {
            new Estadio("Arena", 90000),
            new Estadio("Maracanã", 78838),
            new Estadio("Morumbi", 66795),
            new Estadio("Allianz Parque", 43713),
            new Estadio("Neo Quimica Arena", 49205),
            new Estadio("Vila Belmiro", 16000),
            new Estadio("Beira-Rio", 50842),
            new Estadio("Arena do Grêmio", 55396),
            new Estadio("Mineirão", 61927)
        };
        
        this.estadios = estadios;
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
        Time[] times = {
            new Time("Time", estadios[getNumEstadio("")]),
            new Time("Flamengo", estadios[getNumEstadio("Maracanã")]),
            new Time("Fluminense", estadios[getNumEstadio("Maracanã")]),
            new Time("São Paulo", estadios[getNumEstadio("Morumbi")]),
            new Time("Palmeiras", estadios[getNumEstadio("Allianz Parque")]),
            new Time("Corinthians", estadios[getNumEstadio("Neo Quimica Arena")]),
            new Time("Santos", estadios[getNumEstadio("Vila Belmiro")]),
            new Time("Internacional", estadios[getNumEstadio("Beira-Rio")]),
            new Time("Grêmio", estadios[getNumEstadio("Arena do Grêmio")]),
            new Time("Cruzeiro", estadios[getNumEstadio("Mineirão")]),
            new Time("Atlético Mineiro", estadios[getNumEstadio("Mineirão")]),
            new Time("Red Bull Bragantino", estadios[getNumEstadio("")]),
            new Time("Athletico Paranaense", estadios[getNumEstadio("")]),
            new Time("Coritiba", estadios[getNumEstadio("")]),
            new Time("Fortaleza", estadios[getNumEstadio("")]),
            new Time("Ceará", estadios[getNumEstadio("")]),
            new Time("Bahia", estadios[getNumEstadio("")]),
            new Time("Vitória", estadios[getNumEstadio("")]),
        };
        
        this.times = times;
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
