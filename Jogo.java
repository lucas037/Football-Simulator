import java.util.Random;

public class Jogo {
    private Time timeA;
    private Time timeB;
    private boolean jogoAgregado = false;
    private int agregadoA = 0;
    private int agregadoB = 0;
    private int penaltiA = 0;
    private int penaltiB = 0;
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
    
    public Jogo(Jogo jogo, Data data) {
        this.jogoAgregado = true;
        this.agregadoA = jogo.getPlacarB();
        this.agregadoB = jogo.getPlacarA();
        this.timeA = jogo.getTimeB();
        this.timeB = jogo.getTimeA();
        this.data = data;
        this.estadio = jogo.getTimeB().getEstadio();
    }
    
    public Time getTimeA() {
        return this.timeA;
    }
    
    public Time getTimeB() {
        return this.timeB;
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
        int numAleatorio = rand.nextInt(11472);
        
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
                if (jogoAgregado && ((agregadoA+placarA)==(agregadoB+placarB))) {
                    tempo = 90;
                    faseJogo = "Segundo Intervalo";
                    tempoIntervalo = 5;
                    
                }
                else {
                    tempo = 0;
                    faseJogo = "Finalizado";
                }

            }
        }
        
        else if (faseJogo.equals("Segundo Intervalo")) {
            if (tempoIntervalo > 0)
                tempoIntervalo--;
            
            if (tempoIntervalo == 0)
                faseJogo = "Prorrogação PT";
        }
    
        if (faseJogo.equals("Prorrogação PT")) {
            if (tempo <= 105)
                tempo++;
            
            if (tempo > 105) {
                faseJogo = "Acres Prorrogação PT";
                tempoAcrescimo = 2;
            }
            
        }
        
        else if (faseJogo.equals("Acres Prorrogação PT")) {
            if (tempoAcrescimo != 0) {
                tempo++;
                tempoAcrescimo--;
            }
            
            if (tempoAcrescimo == 0) {
                tempo = 105;
                faseJogo = "Terceiro Intervalo";
                tempoIntervalo = 5;
            }
        }
        
        else if (faseJogo.equals("Terceiro Intervalo")) {
            if (tempoIntervalo > 0)
                tempoIntervalo--;
            
            if (tempoIntervalo == 0)
                faseJogo = "Prorrogação ST";
        }
    
        if (faseJogo.equals("Prorrogação ST")) {
            if (tempo <= 120)
                tempo++;
            
            if (tempo > 120) {
                faseJogo = "Acres Prorrogação ST";
                tempoAcrescimo = 2;
            }
            
        }
        
        else if (faseJogo.equals("Acres Prorrogação ST")) {
            if (tempoAcrescimo != 0) {
                tempo++;
                tempoAcrescimo--;
            }
            
            if (tempoAcrescimo == 0) {
                if (jogoAgregado && ((agregadoA+placarA)==(agregadoB+placarB))) {
                    tempo = 105;
                    faseJogo = "Quarto Intervalo";
                    tempoIntervalo = 5;
                    
                }
                else {
                    tempo = 0;
                    faseJogo = "Finalizado";
                    tempoIntervalo = 0;
                }
            }
        }
        
        else if (faseJogo.equals("Quarto Intervalo")) {
            if (tempoIntervalo > 0)
                tempoIntervalo--;
            
            if (tempoIntervalo == 0)
                faseJogo = "Penaltis";
        }
    
        if (faseJogo.equals("Penaltis")) {
            for (int i = 0; i < 3; i++) {
                numAleatorio = rand.nextInt(10);
                
                if (numAleatorio < 8) {
                    penaltiA += 1;
                }
                System.out.println(toString());
                
                numAleatorio = rand.nextInt(10);
                
                if (numAleatorio < 8) {
                    penaltiB += 1;
                }
                System.out.println(toString());
            }
            
            if (penaltiA > penaltiB+2 || penaltiB > penaltiA+2) {
                faseJogo = "Finalizado";
            }
            
            // quarta cobrança A
            if (!faseJogo.equals("Finalizado")) {
                numAleatorio = rand.nextInt(10);

                if (numAleatorio < 8) {
                    penaltiA += 1;
                }

                if (penaltiA > penaltiB+2 || penaltiB > penaltiA + 1) {
                    faseJogo = "Finalizado";
                }
                System.out.println(toString());
                
            }
            
            // quarta cobrança B
            if (!faseJogo.equals("Finalizado")) {
                numAleatorio = rand.nextInt(10);

                if (numAleatorio < 8) {
                    penaltiB += 1;
                }

                if (penaltiA > penaltiB+1 || penaltiB > penaltiA + 1) {
                    faseJogo = "Finalizado";
                }
                System.out.println(toString());
                
            }
            
            // quinta cobrança A
            if (!faseJogo.equals("Finalizado")) {
                numAleatorio = rand.nextInt(10);

                if (numAleatorio < 8) {
                    penaltiA += 1;
                }

                if (penaltiA > penaltiB+1 || penaltiB > penaltiA) {
                    faseJogo = "Finalizado";
                }
                System.out.println(toString());
                
            }
            
            // quinta cobrança B
            if (!faseJogo.equals("Finalizado")) {
                numAleatorio = rand.nextInt(10);

                if (numAleatorio < 8) {
                    penaltiB += 1;
                }

                if (penaltiA > penaltiB || penaltiB > penaltiA + 1) {
                    faseJogo = "Finalizado";
                }
                System.out.println(toString());
                
            }
            
            if (!faseJogo.equals("Finalizado")) {
                while (penaltiA == penaltiB) {
                    numAleatorio = rand.nextInt(10);

                    if (numAleatorio < 8) {
                        penaltiA += 1;
                    }
                    System.out.println(toString());

                    numAleatorio = rand.nextInt(10);

                    if (numAleatorio < 8) {
                        penaltiB += 1;
                    }
                    System.out.println(toString());
                }
            }
            

            
            faseJogo = "Finalizado";
            
        }
        
        System.out.println(toString());
        
    }
    
    public String toStringTempo() {
        String str = "";
        
        if (faseJogo.equals("Intervalo") || faseJogo.equals("Segundo Intervalo") || faseJogo.equals("Terceiro Intervalo") || faseJogo.equals("Quarto Intervalo")) {
            str += "Intervalo ";
        }
        
        else if (!faseJogo.equals("Finalizado") && !faseJogo.equals("Em Breve") && !faseJogo.equals("Penaltis")) {
            if (tempoAcrescimo != 0 && tempo != 45 && tempo != 90) {
                if (tempo > 120) {
                    str += "120+";
                    str += (tempo-120);
                }
                else if (tempo > 105) {
                    str += "105+";
                    str += (tempo-105);
                }
                else if (tempo > 90) {
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
        
        if (jogoAgregado) {
            str += "(";
            str += agregadoA+placarA;
            str += "x";
            str += agregadoB+placarB;
            str += ")";
        }
        
        if (penaltiA > 0 || penaltiB > 0) {
            str += " [";
            str += penaltiA;
            str += " x ";
            str += penaltiB;
            str += "]";
        }
        
        str += " "+toStringTempo();
        
        str += "\n";
        if (!gols.equals("")) {
            str += gols+"\n";
        }
        
        return str;
    }
    
    
    
}
