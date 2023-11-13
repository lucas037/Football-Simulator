package model.entity;

public class Confronto {
    private int id = -377;
    private int idFase = -377;
    private String tipo = "Jogo Único";
    private final Time timeA;
    private final Time timeB;
    private Estadio estadioA;
    private Estadio estadioB;
    private Data dataPartidaA;
    private Data dataPartidaB;
    private int idJogoA;
    private int idJogoB;
    private Jogo jogoA;
    private Jogo jogoB;
    private int numJogoA = 0;
    private int numJogoB = 0;
    private int numEstadioA = 0;
    private int numEstadioB = 0;
    
    public Confronto() {
        this.timeA = new Time();
        this.timeB = new Time();
    }
    
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
    
    public int getID() {
        return this.id;
    }
    
    public void setID(int id) {
        this.id = id;
    }
    
    public int getIDFase() {
        return this.idFase;
    }
    
    public int setIDFase(int id) {
        return this.idFase;
    }
    
    public int getIDJogoA() {
        return this.idJogoA;
    }
    
    public void setIDJogoA(int id) {
        this.idJogoA = id;
    }
    
    public int getIDJogoB() {
        return this.idJogoB;
    }
    
    public void setIDJogoB(int id) {
        this.idJogoB = id;
    }
    
    public void getIDJogoB(int id) {
        this.idJogoB = id;
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
    
    public int getNumEstadioA() {
        return this.numEstadioA;
    }
    
    public void setNumEstadioA(int num) {
        this.numEstadioA = num;
    }
    
    public int getNumEstadioB() {
        return this.numEstadioB;
    }
    
    public void setNumEstadioB(int num) {
        this.numEstadioB = num;
    }
    
    public void exibirConfronto() {
        System.out.println(jogoA.toString());
        
        if (jogoB != null)
            System.out.println(jogoB.toString());
        else
            System.out.println("Segundo Jogo ainda não definido.");
    }
    
}
