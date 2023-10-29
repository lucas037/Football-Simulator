import java.util.Random;

public class Jogo {
    private Time timeA;
    private Time timeB;
    private int placarA = 0;
    private int placarB = 0;
    private int tempo = 0;
    private int tempoAcrescimo = 0;
    private int tempoIntervalo = 0;
    private String faseJogo = "Em Breve";
    private Estadio estadio;
    private Data data;
    private String gols = "";
    Random rand = new Random();
    
    public Jogo(Time timeA, Time timeB, Data data) {
        this.timeA = timeA;
        this.timeB = timeB;
        this.data = data;
        this.estadio = timeA.getEstadio();
    }
    
    public Jogo(Time timeA, Time timeB, Data data, Estadio estadio) {
        this.timeA = timeA;
        this.timeB = timeB;
        this.data = data;
        this.estadio = estadio;
    }
    
    public int getPlacarA() {
        return this.placarA;
    }
    
    public int getPlacarB() {
        return this.placarB;
    }
    
    public String getFaseJogo() {
        return this.faseJogo;
    }
    
    public Data getData() {
        return this.data;
    }
    
    public void passarMinuto()  {
        int numAleatorio = rand.nextInt(72);
        
        if (!faseJogo.equals("Em Breve") && !faseJogo.equals("Intervalo") && !faseJogo.equals("Finalizado")) { // aleatoriamente adiciona gols em tempos de jogo possíveis
            if (numAleatorio == 0) {
                placarA++;
            }
            else if (numAleatorio == 1) {
                placarB++;
            }
        }
        
        if (faseJogo.equals("Em Breve")) {
            faseJogo = "Primeiro Tempo";
        }
    
        if (faseJogo.equals("Primeiro Tempo")) {
            if (tempo <= 45)
                tempo++;
            
            if (tempo > 45) {
                faseJogo = "Acres Primeiro Tempo";
                tempoAcrescimo = 3;
            }
                
            
        }
        
        else if (faseJogo.equals("Acres Primeiro Tempo")) {
            if (tempoAcrescimo != 0) {
                tempo++;
                tempoAcrescimo--;
            }
            
            if (tempoAcrescimo == 0) {
                tempo = 45;
                faseJogo = "Intervalo";
                tempoIntervalo = 15;
            }
        }
        
        else if (faseJogo.equals("Intervalo")) {
            if (tempoIntervalo > 0)
                tempoIntervalo--;
            
            if (tempoIntervalo == 0)
                faseJogo = "Segundo Tempo";
        }
    
        if (faseJogo.equals("Segundo Tempo")) {
            if (tempo <= 90)
                tempo++;
            
            if (tempo > 90) {
                faseJogo = "Acres Segundo Tempo";
                tempoAcrescimo = 5;
            }
            
        }
        
        else if (faseJogo.equals("Acres Segundo Tempo")) {
            if (tempoAcrescimo != 0) {
                tempo++;
                tempoAcrescimo--;
            }
            
            if (tempoAcrescimo == 0) {
                tempo = 0;
                faseJogo = "Finalizado";
            }
        }
        
        System.out.println(toString());
        
    }
    
    public String toStringTempo() {
        String str = "";
        
        if (faseJogo.equals("Intervalo")) {
            str += "Intervalo ";
        }
        
        else if (!faseJogo.equals("Finalizado") && !faseJogo.equals("Em Breve")) {
            if (tempoAcrescimo != 0 && tempo != 45 && tempo != 90) {
                if (tempo > 90) {
                    str += "90+";
                    str += (tempo-90);
                }
                else if (tempo > 45) {
                    str += "45+";
                    str += (tempo-45);
                }
            }
            else
                str += tempo;

            str += "'";  
        }
        
        return str;
    }
    
    @Override
    public String toString() {
        String str = "";
        
        str += estadio.getNome() + " • ";
        str += data.getData("/", "abc", false) + " • ";
        str += data.getHora()+ "\n";
        
        str += timeA.getNome()+" ";
        
        if (!faseJogo.equals("Em Breve"))
            str += placarA+" ";
        str += "v ";
        if (!faseJogo.equals("Em Breve"))
            str += placarB+" ";
        str += timeB.getNome();
        
        str += " "+toStringTempo()+"\n";
        
        if (gols != "") {
            str += gols+"\n";
        }
        
        return str;
    }
    
    
    
}
