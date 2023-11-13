package model.entity;

import java.util.Random;

public class Jogo {
    private int numJogo = -377;
    private int numConfronto = -377;
    private final Time timeA;
    private final Time timeB;
    private int tempo = 0;
    private String faseJogo = "Em Breve";
    private int placarA = 0;
    private int placarB = 0;
    private boolean jogoAgregado = false;
    private int agregado;
    private int agregadoA = 0;
    private int agregadoB = 0;
    private Time classificado;
    private int penaltiA = 0;
    private int penaltiB = 0;
    private int tempoAcrescimo = 0;
    private int tempoIntervalo = 0;
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
    
    public Jogo(Time timeA, int agregadoA, Time timeB, int agregadoB, Data data) {
        this.timeA = timeA;
        this.timeB = timeB;
        this.agregadoA = agregadoA;
        this.agregadoB = agregadoB;
        this.data = data;
        this.estadio = timeA.getEstadio();
    }
    
    public Jogo(Time timeA, Time timeB, Data data, Estadio estadio) {
        this.timeA = timeA;
        this.timeB = timeB;
        this.data = data;
        this.estadio = estadio;
    }
    
    public Jogo(Time timeA, int agregadoA, Time timeB, int agregadoB, Data data, Estadio estadio) {
        this.timeA = timeA;
        this.timeB = timeB;
        this.agregadoA = agregadoA;
        this.agregadoB = agregadoB;
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
    
    public int getNumJogo() {
        return numJogo;
    }
    
    public void setNumJogo(int num) {
        this.numJogo = num;
    }
    
    public String getFaseJogo() {
        return this.faseJogo;
    }
    
    public void setFaseJogo(String faseJogo) {
        this.faseJogo = faseJogo;
    }
    
    public boolean getTipoConfronto() {
        return this.jogoAgregado;
    }
    
    public void setTipoConfronto(boolean tipoConfronto) {
        this.jogoAgregado = tipoConfronto;
    }
    
    public int getJogoAgregado() {
        return this.agregado;
    }
    
    public void setJogoAgregado(int agregado) {
        this.agregado = agregado;
    } 
    
    public Time getTimeA() {
        return this.timeA;
    }
    
    public Time getTimeB() {
        return this.timeB;
    }
    
    public Estadio getEstadio() {
        return this.estadio;
    }
    
    public void setEstadio(Estadio estadio) {
        this.estadio = estadio;
    }
    
    public int getTempo() {
        return this.tempo;
    }
    
    public void setTempo(int tempo) {
        this.tempo = tempo;
    }
    
    public int getTempoAcrescimo() {
        return this.tempoAcrescimo;
    }
    
    public void setTempoAcrescimo(int tempo) {
        this.tempoAcrescimo = tempo;
    }
    
    public int getTempoIntervalo() {
        return this.tempoIntervalo;
    }
    
    public void setTempoIntervalo(int tempo) {
        this.tempoIntervalo = tempo;
    }
    
    public void setPlacar(int a, int b) {
        this.placarA = a;
        this.placarB = b;
    }
    
    public void setAgregado(int a, int b) {
        this.agregadoA = a;
        this.agregadoB = b;
    }
    
    public void setPenalti(int a, int b) {
        this.penaltiA = a;
        this.penaltiB = b;
    }
    
    public int getPlacarA() {
        return this.placarA;
    }
    
    public int getPlacarB() {
        return this.placarB;
    }
    
    public Data getData() {
        return this.data;
    }
    
    public Time getClassificado() {
        return this.classificado;
    }
    
    public void setJogoAgregado() {
        jogoAgregado = true;
    }
    
    public int getNumConfronto() {
        return this.numConfronto;
    }
    
    public void setNumConfronto(int num) {
        this.numConfronto = num;
    }
    
    public void encerrarJogo() {
        if (faseJogo.equals("Finalizado")) {
            faseJogo = "Encerrado!";
        }
        else {
            System.out.println("Impossível encerrar um confronto ainda não finalizado.");
        }
    }
    
    public void passarMinuto()  {
        int numAleatorio = rand.nextInt(72);
        
        if (!faseJogo.equals("Em Breve") && !faseJogo.equals("Intervalo") && !faseJogo.equals("Segundo Intervalo") && !faseJogo.equals("Terceiro Intervalo") && !faseJogo.equals("Quarto Intervalo") && !faseJogo.equals("Finalizado") && !faseJogo.equals("Encerrado!")) { // aleatoriamente adiciona gols em tempos de jogo possíveis
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
                
                numAleatorio = rand.nextInt(10);
                
                if (numAleatorio < 8) {
                    penaltiB += 1;
                }
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
                
            }
            
            if (!faseJogo.equals("Finalizado")) {
                while (penaltiA == penaltiB) {
                    numAleatorio = rand.nextInt(10);

                    if (numAleatorio < 8) {
                        penaltiA += 1;
                    }

                    numAleatorio = rand.nextInt(10);

                    if (numAleatorio < 8) {
                        penaltiB += 1;
                    }
                }
            }
            

            
            faseJogo = "Finalizado";
            
        }
        
        
        if (faseJogo.equals("Finalizado")) {
            if (jogoAgregado == true) {
                if (penaltiA > penaltiB) {
                    classificado = timeA;
                }
                else if (penaltiB > penaltiA) {
                    classificado = timeB;
                }
                else if ((agregadoA+placarA)>(agregadoB+placarB)) {
                    classificado = timeA;
                }
                else if ((agregadoB+placarB)>(agregadoA+placarA)) {
                    classificado = timeB;
                }
            }
        }
        
    }
    
    public String toStringTempo() {
        String str = "";
        
        if (faseJogo.equals("Intervalo") || faseJogo.equals("Segundo Intervalo") || faseJogo.equals("Terceiro Intervalo") || faseJogo.equals("Quarto Intervalo")) {
            str += "Intervalo ";
        }
        
        else if (!faseJogo.equals("Encerrado!") && !faseJogo.equals("Finalizado") && !faseJogo.equals("Em Breve") && !faseJogo.equals("Penaltis")) {
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
            str += " (";
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
        
        str += numJogo+"\n";
        
        return str;
    }
    
    
    
}
