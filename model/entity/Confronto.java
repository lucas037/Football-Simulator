package model.entity;

public class Confronto {
    private int id = -377;
    private int idFase = -377;
    private String tipo = "Jogo Único";
    private int idJogoA = -1;
    private int idJogoB = -1;
    private Data dataA;
    private Data dataB;
    private int idTimeA = -1;
    private int idTimeB = -1;
    private int idEstadioA = -1;
    private int idEstadioB = -1;
    
    public Confronto(int idTimeA, int idTimeB, Data data) {
        setIDTimeA(idTimeA);
        setIDTimeB(idTimeB);
        setDataA(data);
    }
    
    public Confronto(int idTimeA, int idTimeB, Data data, int idEstadio) {
        setIDTimeA(idTimeA);
        setIDTimeB(idTimeB);
        setDataA(data);
        setIDEstadioA(idEstadio);
    }
    
    public Confronto(int idTimeA, int idTimeB, Data dataA, Data dataB) {
        setTipo("Casa e Fora");
        setIDTimeA(idTimeA);
        setIDTimeB(idTimeB);
        setDataA(dataA);
        setDataB(dataB);
    }
    
    public Confronto(int idTimeA, int idTimeB, Data dataA, Data dataB, int idEstadioA, int idEstadioB) {
        setTipo("Casa e Fora");
        setIDTimeA(idTimeA);
        setIDTimeB(idTimeB);
        setDataA(dataA);
        setDataB(dataB);
        setIDEstadioA(idEstadioA);
        setIDEstadioB(idEstadioB);
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
    
    public void setIDFase(int idFase) {
        this.idFase = idFase;
    }
    
    public String getTipo() {
        return this.tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
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
    
    public Data getDataA() {
        return this.dataA;
    }
    
    public void setDataA(Data data) {
        this.dataA = data;
    }
    
    public Data getDataB() {
        return this.dataB;
    }
    
    public void setDataB(Data data) {
        this.dataB = data;
    }
    
    public int getIDTimeA() {
        return this.idTimeA;
    }
    
    public void setIDTimeA(int num) {
        this.idTimeA = num;
    }
    
    public int getIDTimeB() {
        return this.idTimeB;
    }
    
    public void setIDTimeB(int num) {
        this.idTimeB = num;
    }
    
    public int getIDEstadioA() {
        return this.idEstadioA;
    }
    
    public void setIDEstadioA(int num) {
        this.idEstadioA = num;
    }
    
    public int getIDEstadioB() {
        return this.idEstadioB;
    }
    
    public void setIDEstadioB(int num) {
        this.idEstadioB = num;
    }
    
    public String toString() {
        String str = "";
        str += "Id: "+id+"\n";
        str += "Id Fase: "+idFase+"\n";
        str += "Tipo: "+tipo+"\n";
        str += "Id Jogos: ("+idJogoA+", "+idJogoB+")\n";
        str += "Id Time: ("+idTimeA+", "+idTimeB+")\n";
        str += "Id Estádio: ("+idEstadioA+", "+idEstadioB+")\n";
        
        str += "Data Jogo 1: "+dataA.toString()+"\n";
        if (dataB != null)
            str += "Data Jogo 2: "+dataB.toString()+"\n";
        
        return str;
    }
    
}
