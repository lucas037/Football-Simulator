package model.entity;

import model.entity.Resources.Aleatory;
import model.entity.Resources.Gerador;

public class Grupo {
    private int id = -1;
    private int idFase = -1;
    private int[] idClubes;
    private String status = "Em Breve";
    private String modelo = "Versus";
    private String enfrentamento = "Casa e Fora";
    private GrupoDesempenho[] desempenhoTimes;
    private int numRodadas;
    private int numJogosRodada;
    private int jogosEncerrados = 0;
    private int[][] idPartidas;
    
    private int[][][] idTimesPartidas;
    
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
    
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String modelo) {
        this.modelo = status;
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
    
    public int[][][] getidTimesPartidas() {
        return this.idTimesPartidas;
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
        int[] indicesSorteio = new int[idClubes.length];
        
        for (int i = 0; i < indicesSorteio.length; i++) {
            indicesSorteio[i] = i;
        }
        
        if (modelo.equals("Champions League")) {}
        
        else {
            if (idClubes.length % 2 == 0) {
                numRodadas = idClubes.length - 1;
                numJogosRodada = idClubes.length/2;
            }
            else {
                indicesSorteio = aumentarTamanho(indicesSorteio);
                numRodadas = idClubes.length;
                numJogosRodada = idClubes.length/2;
            }
            
            numRodadasTurno = numRodadas;
            if (enfrentamento.equals("Casa e Fora")) {
                numRodadas *= 2;
            }
        }
        
        this.numRodadas = numRodadas;
        this.numJogosRodada = numJogosRodada;
        
        idPartidas = new int[numRodadas][numJogosRodada];
        int[][][] indiceClubesPartida = new int[numRodadas][numJogosRodada][2];
        
        // gera poteA e poteB (poteA sempre 1 unidade maior que B.
        int[] poteA = new int[indicesSorteio.length/2];
        int[] poteB = new int[indicesSorteio.length/2];
        
        // organiza primeiros valores da lista no pote A.
        System.arraycopy(indicesSorteio, 0, poteA, 0, poteA.length);
        
        // organiza ultimos valores da lista no pote B.
        System.arraycopy(indicesSorteio, poteA.length, poteB, 0, poteB.length);
        
        // coleta de id dos times em cada jogos em cada rodada.
        for (int i = 0; i < numRodadasTurno; i++) {
            int inicial = 0;
            if (poteA[0] == -1)
                inicial = 1;
            
            for (int j = inicial; j < numJogosRodada + inicial; j++) {
                indiceClubesPartida[i][j - inicial][0] = poteA[j];
                indiceClubesPartida[i][j - inicial][1] = poteB[j];
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
        
        // troca ordem de rodadas
        int[][][] copiaIndiceClubesPartida = new int[indiceClubesPartida.length][indiceClubesPartida[0].length][2];
        
        int[] indices = new int[numRodadasTurno];
        for (int i = 0; i < indices.length; i++)
            indices[i] = i;
        
        indices = random.shuffle(indices);
        
        for (int i = 0; i < indices.length; i++) {
            copiaIndiceClubesPartida[i] = indiceClubesPartida[indices[i]];
        }
        
        indiceClubesPartida = copiaIndiceClubesPartida;
        
        
        // organiza mandos de campo no grupo
        int[][] mandosCampo = new int[idClubes.length][2]; // jogos seguidos fora / total jogos fora
        
        int rodadasSemRepeticao = indiceClubesPartida.length;
        if (enfrentamento.equals("Casa e Fora"))
            rodadasSemRepeticao /= 2;
        
        for (int i = 0; i < rodadasSemRepeticao; i++) {
            for (int j = 0; j < indiceClubesPartida[0].length; j++) {
                int indiceCasa = indiceClubesPartida[i][j][0];
                int indiceFora = indiceClubesPartida[i][j][1];
                
                // caso o timeB tenha mais jogos seguidos fora do que o timeA, ele jogará em casa.
                boolean timeB_maisJogosSeguidosFora = mandosCampo[indiceFora][0] > mandosCampo[indiceCasa][0];
                boolean igualdadeJogosSeguidoresFora = mandosCampo[indiceFora][0] == mandosCampo[indiceCasa][0];
                boolean timeB_maisJogosFora = mandosCampo[indiceFora][1] > mandosCampo[indiceCasa][1];
                if (timeB_maisJogosSeguidosFora || (igualdadeJogosSeguidoresFora && timeB_maisJogosFora)) {
                    indiceClubesPartida[i][j][0] = indiceFora;
                    indiceClubesPartida[i][j][1] = indiceCasa;
                    indiceCasa = indiceClubesPartida[i][j][0];
                    indiceFora = indiceClubesPartida[i][j][1];
                }
                
                mandosCampo[indiceCasa][0] = 0;
                mandosCampo[indiceFora][0]++;
                mandosCampo[indiceFora][1]++;
            }
        }
        
        // organiza segundo turno, caso exista
        if (enfrentamento.equals("Casa e Fora")) {
            if (modelo.equals("Versus")) {
                for (int i = 0; i < rodadasSemRepeticao; i++) {
                    for (int j = 0; j < indiceClubesPartida[0].length; j++) {
                        indiceClubesPartida[i+rodadasSemRepeticao][j][0] = indiceClubesPartida[i][j][1];
                        indiceClubesPartida[i+rodadasSemRepeticao][j][1] = indiceClubesPartida[i][j][0];
                    }
                }
            }
            else {
                for (int i = 0; i < rodadasSemRepeticao; i++) {
                    for (int j = 0; j < indiceClubesPartida[0].length; j++) {
                        indiceClubesPartida[i+rodadasSemRepeticao][j][0] = indiceClubesPartida[numRodadasTurno - i - 1][j][1];
                        indiceClubesPartida[i+rodadasSemRepeticao][j][1] = indiceClubesPartida[numRodadasTurno - i - 1][j][0];
                    }
                }
            }
        }
        
        //aleatoriza ordem dos jogos em cada rodada
        Gerador gerador = new Gerador();
        indices = gerador.gerarIndices(numJogosRodada);

        for (int i = 0; i < numRodadas; i++) {
            indices = random.shuffle(indices);

            int[][] copiaRodada = new int[numJogosRodada][2];
            
            for (int j = 0; j < numJogosRodada; j++) {
                copiaRodada[j] = indiceClubesPartida[i][indices[j]];
            }
            
            indiceClubesPartida[i] = copiaRodada;
        }
        
        // organiza id de times por partida
        this.idTimesPartidas = new int[numRodadas][numJogosRodada][2];
        for (int i = 0; i < numRodadas; i++) {
            for (int j = 0; j < numJogosRodada; j++) {
                idTimesPartidas[i][j][0] = idClubes[indiceClubesPartida[i][j][0]];
                idTimesPartidas[i][j][1] = idClubes[indiceClubesPartida[i][j][1]];
            }
        }
    }
    
    public void addJogo(Jogo jg) {
        int idA = jg.getTimeA().getID();
        int idB = jg.getTimeB().getID();
        
        int indiceA = -1;
        int indiceB = -1;
        
        for (int i = 0; i < idClubes.length; i++) {
            if (idClubes[i] == idA)
                indiceA = i;
            else if (idClubes[i] == idB)
                indiceB = i;
        }
        
        int placarA = jg.getPlacarA();
        int placarB = jg.getPlacarB();
        
        desempenhoTimes[indiceA].addResultado(placarA, placarB);
        desempenhoTimes[indiceB].addResultado(placarB, placarA);
        
        jogosEncerrados++;
        
        if (status.equals("Em Breve")) {
            status = "Em Andamento";
        }
        else if (jogosEncerrados == (numRodadas*numJogosRodada)) {
            status = "Finalizado";
        }
    }
    
    @Override
    public String toString() {
        String str = "";
        
        str += "Grupo "+id+": \n";
        
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

        for (int i = 0; i < desempenhoTimes.length; i++)
            str += desempenhoTimes[i].toString();
        
        str += "\nStatus: "+status+"\n"; 
        
        return str;
    }
}
