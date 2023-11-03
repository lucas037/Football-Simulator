import java.util.Random;

public class Fase {
    private String nome;
    private String etapa;
    private String tipo;
    private Time[] times = new Time[100];
    private int numTimes;
    private int numTimesEsperado;
    private Data[] datas = new Data[100];
    private Confronto[] confrontos = new Confronto[100];
    private int numConfrontos;
    private Grupo[] grupo = new Grupo[100];
    private int numGrupos;
    private int tamGrupos;
    private String tipoSorteio;
    private Time[] classificados = new Time[100];
    private int numClassificadosEsperado;
    private int numClassificados;
    Random rand = new Random();
    
    public Fase(String nome, String tipo, Time[] times, int numTimesEsperado) {
        defaultConstrutor(nome, tipo, times, numTimesEsperado);
        setDatas(null);
    }
    
    public Fase(String nome, String tipo, Time[] times, int numTimesEsperado, Data[] datas) {
        defaultConstrutor(nome, tipo, times, numTimesEsperado);
        setDatas(datas);
    }
    
    private void defaultConstrutor(String nome, String tipo, Time[] times, int numTimesEsperado) {
        setNome(nome);
        setTipo(tipo);
        setTimesEsperados(numTimesEsperado);
        
        if (times != null)
            this.numTimes = times.length;
        
        if (tipo.equals("Mata-Mata")) {
            setTimesMataMata(times, this.numTimes);
        }
    }
    
    public void setNome(String nome) {
        if (nome != null)
            this.nome = nome;
        else
            this.nome = "Fase Sem Nome";
    }
    
    public void setTipo(String tipo) {
        if (tipo.equals("Mata-Mata") || tipo.equals("Grupos"))
            this.tipo = tipo;
        else
            this.tipo = "Mata-Mata";
    }
    
    public void setTimesEsperados(int num) {
        if (num > 0) {
            this.numTimesEsperado = num;
        }
        else {
            System.out.println("Número de times em uma fase não pode ser menor do que 1.");
        }
    }
    
    public void setDatas(Data[] datas) {
        if (datas != null) {
            this.datas = datas;
        }
        else {
            for (int i = 0; i < this.numTimes; i++) {
                this.datas[i] = new Data();
            }
        }
    }
    
    public void setTimesMataMata(Time[] times, int numTimes) {
        int aux = 0; // adiciona um time caso o numero de times na fase seja ímpar.
        if (this.numTimesEsperado % 2 != 0)
            aux = 1;
        
        if (times == null) { // adiciona todos os times como genéricos
            for (int i = 0; i < numTimes+aux; i++) {
                this.times[i] = new Time("Time "+i, new Estadio("Estadio"));
            }
        }
        else {
            System.arraycopy(times, 0, this.times, 0, numTimes);
            
            if (numTimes < this.numTimesEsperado) {
                for (int i = numTimes; i < this.numTimesEsperado+aux; i++) {
                    this.times[i] = new Time("Time "+i, new Estadio("Estadio"));
                }
            }
        }
    }
    
    public Tempo sorteio(Tempo tempo) {
        
        if (tipo.equals("Mata-Mata")) {
            int[] numTimesSorteio = new int[numTimesEsperado];
            
            for (int i = 0; i < numTimesEsperado; i++) {
                numTimesSorteio[i] = i;
            }
            
            while (numTimesSorteio.length > 1) {
                int numAleatorio = rand.nextInt(numTimesSorteio.length);
                int numAleatorioB = numAleatorio;
                
                while (numAleatorio == numAleatorioB)
                    numAleatorioB = rand.nextInt(numTimesSorteio.length);
                
                confrontos[numConfrontos] = new Confronto(times[numTimesSorteio[numAleatorio]], times[numTimesSorteio[numAleatorioB]], this.datas[numConfrontos*2], this.datas[numConfrontos*2+1]);
                tempo.addConfronto(confrontos[numConfrontos]); // adiciona confronto à lista de confrontos
                
                numConfrontos++;
                
                if (numTimesSorteio.length <= 2) {
                    int[] toCopy = new int[1];
                    numTimesSorteio = toCopy;
                }
                else {
                    int[] toCopy = new int[numTimesSorteio.length - 2];
                    
                    int iCopy = 0;
                    for (int i = 0; i < numTimesSorteio.length; i++) {
                       if (i != numAleatorio && i != numAleatorioB) {
                            toCopy[iCopy] = numTimesSorteio[i];
                            iCopy++;
                            
                        }
                    }
                    
                    numTimesSorteio = toCopy;
                    
                }
            }
            
        }
        
        return tempo;
    }
    
    public Time[] getClassificados() {
        return this.classificados;
    }
}