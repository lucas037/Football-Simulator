public class Confronto {
    String tipo = "Jogo Único";
    private final Time timeA;
    private final Time timeB;
    private Estadio estadioA;
    private Estadio estadioB;
    Data dataPartidaA;
    Data dataPartidaB;
    Jogo jogoA;
    Jogo jogoB;
    int numJogoA = 0;
    int numJogoB = 0;
    
    public Confronto(Time timeA, Time timeB, Data dataPartida) {
        this.timeA = timeA;
        this.timeB = timeB;
        this.dataPartidaA = dataPartida;
        jogoA = new Jogo(timeA, timeB, dataPartida);
    }
    
    public Confronto(Time timeA, Time timeB, Data dataPartida, Estadio estadio) {
        this.timeA = timeA;
        this.timeB = timeB;
        this.dataPartidaA = dataPartida;
        jogoA = new Jogo(timeA, timeB, dataPartida, estadio);
    }
    
    public Confronto(Time timeA, Time timeB, Data dataPartidaA, Data dataPartidaB) {
        this.timeA = timeA;
        this.timeB = timeB;
        this.dataPartidaA = dataPartidaA;
        this.dataPartidaB = dataPartidaB;
        jogoA = new Jogo(timeA, timeB, dataPartidaA);
    }
    
    public Confronto(Time timeA, Time timeB, Data dataPartidaA, Data dataPartidaB, Estadio estadioA, Estadio estadioB) {
        this.timeA = timeA;
        this.timeB = timeB;
        this.dataPartidaA = dataPartidaA;
        this.dataPartidaB = dataPartidaB;
        jogoA = new Jogo(timeA, timeB, dataPartidaA, estadioA);
    }
    
    public Jogo getJogoA() {
        return this.jogoA;
    }
    
    public Jogo getJogoB() {
        return this.jogoB;
    }
    
    public void setJogoB(int agregadoA, int agregadoB) {
        if (estadioB != null) {
            jogoB = new Jogo(timeB, agregadoB, timeA, agregadoA, dataPartidaB, estadioB);
            jogoB.setJogoAgregado();
        }
        else {
            jogoB = new Jogo(timeB, agregadoB, timeA, agregadoA, dataPartidaB);
            jogoB.setJogoAgregado();
        }
    }
    
    public int getNumJogoA() {
        return this.numJogoA;
    }
    
    public void setNumJogoA(int num) {
        this.numJogoA = num;
    }
    
    public int getNumJogoB() {
        return this.numJogoB;
    }
    
    public void setNumJogoB(int num) {
        this.numJogoB = num;
    }
    
    public void setNumConfrontoJogoA(int num) {
        jogoA.setNumConfronto(num);
    }
    
    public void setNumConfrontoJogoB() {
        jogoB.setNumConfronto(jogoA.getNumConfronto());
    }
    
    public void exibirConfronto() {
        System.out.println(jogoA.toString());
        
        if (jogoB != null)
            System.out.println(jogoB.toString());
        else
            System.out.println("Segundo Jogo ainda não definido.");
    }
    
}
