package model.entity;

import model.entity.Resources.Aleatory;

public class Grupo {
    private int id = -1;
    private int idFase = -1;
    private int[] idClubes;
    private String modelo = "Versus";
    private String enfrentamento = "Casa e Fora";
    private GrupoDesempenho[] desempenhoTimes;
    private int[][] idPartidas;
    
    public Grupo(int[] idClubes) {
        this.idClubes = idClubes;
        
        gerarDesempenho(idClubes);
        
        sortear();
    }
    
    public Grupo(int[] idClubes, String enfrentamento) {
        this.idClubes = idClubes;
        this.enfrentamento = enfrentamento;
        
        gerarDesempenho(idClubes);
        
        sortear();
        
    }
    
    public Grupo(int[] idClubes, String enfrentamento, String modelo) {
        this.idClubes = idClubes;
        this.enfrentamento = enfrentamento;
        this.modelo = modelo;
        
        gerarDesempenho(idClubes);
        
        sortear();
        
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
    
    public void setIDFase(int id) {
        this.idFase = id;
    }
    
    public int[] getIDClubes() {
        return this.idClubes;
    }
    
    public void setIDClubes(int[] idClubes) {
        this.idClubes = idClubes;
    }
    
    public String getModelo() {
        return this.modelo;
    }
    
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    
    public String getEnfrentamento() {
        return this.enfrentamento;
    }
    
    public void setEnfrentamento(String enfrentamento) {
        this.enfrentamento = enfrentamento;
    }
    
    public GrupoDesempenho[] getDesempenhoTimes() {
        return this.desempenhoTimes;
    }
    
    public void setDesempenhoTimes(GrupoDesempenho[] desempenhoTimes) {
        this.desempenhoTimes = desempenhoTimes;
    }
    
    private void gerarDesempenho(int[] idClubes) {
        desempenhoTimes = new GrupoDesempenho[idClubes.length];
        for (int i = 0; i < idClubes.length; i++)
            desempenhoTimes[i] = new GrupoDesempenho(idClubes[i]);
    }
    
    private int[] aumentarTamanho(int[] lista) {
        // adiciona +1 ao tamanho da lista.
        // serve para quando numero de times no grupo for par.
        
        int tamanho = lista.length + 1;
        int[] listaB = new int[tamanho];
        
        listaB[0] = -1;
        System.arraycopy(lista, 0, listaB, 1, lista.length);
        
        return listaB;
    }
    
    public void sortear() {
        Aleatory random = new Aleatory();
        
        int numRodadasTurno = 0;
        int numRodadas = 0;
        int numJogosRodada = 0;
        int[] idClubesSort = random.shuffle(idClubes);
        
        if (modelo.equals("Champions League")) {}
        
        else {
            if (idClubes.length % 2 == 0) {
                numRodadas = idClubes.length - 1;
                numJogosRodada = idClubes.length/2;
            }
            else {
                idClubesSort = aumentarTamanho(idClubesSort);
                numRodadas = idClubes.length;
                numJogosRodada = idClubes.length/2;
            }
            
            numRodadasTurno = numRodadas;
            if (enfrentamento.equals("Casa e Fora")) {
                numRodadas *= 2;
            }
        }
        
        idPartidas = new int[numRodadas][numJogosRodada];
        int[][][] idClubesPartidas = new int[numRodadas][numJogosRodada][2];
        
        // gera poteA e poteB (poteA sempre 1 unidade maior que B.
        int[] poteA = new int[idClubesSort.length/2];
        int[] poteB = new int[idClubesSort.length/2];
        
        // organiza primeiros valores da lista no pote A.
        System.arraycopy(idClubesSort, 0, poteA, 0, poteA.length);
        
        // organiza ultimos valores da lista no pote B.
        System.arraycopy(idClubesSort, poteA.length, poteB, 0, poteB.length);
        
        // coleta de id dos times em cada jogos em cada rodada.
        for (int i = 0; i < numRodadasTurno; i++) {
            int inicial = 0;
            if (poteA[0] == -1)
                inicial = 1;
            
            for (int j = inicial; j < numJogosRodada + inicial; j++) {
                idClubesPartidas[i][j - inicial][0] = poteA[j];
                idClubesPartidas[i][j - inicial][1] = poteB[j];
            }
            
            // "giro" clubes para próxima rodada
            int valorArmazenado = poteA[poteA.length - 1];

            for (int j = poteA.length - 1; j >= 2; j--)
                poteA[j] = poteA[j - 1];
            
            poteA[1] = poteB[0];
            
            for(int j = 0; j < poteB.length - 1; j++)
                poteB[j] = poteB[j + 1];
            poteB[poteB.length - 1] = valorArmazenado;
        }
        
        for (int i = 0; i < idClubesPartidas.length; i++) {
            System.out.println("\n- Rodada "+(i+1)+" - ");
            for (int j = 0; j < idClubesPartidas[0].length; j++) {
                System.out.println(idClubesPartidas[i][j][0]+" v "+idClubesPartidas[i][j][1]);
            }
            System.out.println("\n");
        }
        
        System.exit(0);
        System.out.println(idPartidas.length);
        System.exit(0);
    }
    
    @Override
    public String toString() {
        String str = "";
        
        str += "Jog\t";
        str += "Vit\t";
        str += "Emp\t";
        str += "Dert\t";
        str += "Gols\t";
        str += "Gols Sof.\t";
        str += "Sald. Gols\t";
        str += "Aprov. (%)\t";
        str += "Pontos\t\t";
        str += "Time\n";
        
        return str;
    }
}
