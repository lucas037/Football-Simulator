package model.entity;

import model.bo.JogoBO;
import model.bo.ConfrontoBO;
import model.bo.TimeBO;
import model.bo.EstadioBO;

public class Tempo extends Data {
    private Jogo[] jogos = new Jogo[100];
    private int numeroJogos = 0;
    private int limiteJogos = 100;
    private Jogo[] jogosHoje = new Jogo[100];
    private int numeroJogosDia = 0;
    private int limiteJogosDia = 100;
    private Confronto[] confrontos = new Confronto[100];
    private int numeroConfrontos = 0;
    private int limiteConfrontos = 100;
    private Grupo[] grupos = new Grupo[100];
    private int numeroGrupos = 0;
    private int limiteGrupos = 100;
            
    public Tempo() {
        super(); // invoca o construtor da classe pai "Data"
    }
    
    public Tempo(int dia, int mes, int ano) {
        super(dia, mes, ano);
    }
    
    public Tempo(int dia, int mes, int ano, int hora, int minuto) {
        super(dia, mes, ano, hora, minuto);
    }
    
    public Tempo(int dia, int mes, int ano, int hora, int minuto, String diaSemana) {
        super(dia, mes, ano, hora, minuto, diaSemana);
    }
    
    public void salvar() {
        JogoBO jgBO = new JogoBO();
        jgBO.salvar(jogos, numeroJogos);
        
        ConfrontoBO cftBO = new ConfrontoBO();
        cftBO.salvar(confrontos, numeroConfrontos);
    }
    
    public void gerar() {
        gerarJogos();
        gerarConfrontos();
    }
    
    public void gerarConfrontos() {
        ConfrontoBO cftBO = new ConfrontoBO();
        Confronto[] confrontos = cftBO.obter();
        
        for (Confronto confronto : confrontos) {
            addConfronto(confronto);
        }
    }
    
    public void gerarJogos() {
        JogoBO jgBO = new JogoBO();
        Jogo[] jogos = jgBO.obter();
        
        for (Jogo jgo : jogos) {
            addJogo(jgo);
            
            boolean jogoIniciado = !jgo.getFaseJogo().equals("Em Breve") && !jgo.getFaseJogo().equals("Finalizado") && !jgo.getFaseJogo().equals("Encerrado");
            boolean jogoIniciaHoje = jgo.getData().getData().equals(getData());
            
            if (jogoIniciado || jogoIniciaHoje) {
                addJogoHoje(jgo);
            }
        }
    }
    
    @Override
    public void passarMinuto() {
        super.passarMinuto();
        
        if (getHora().equals("00:00")) {
            removerJogosHoje();
            
            for (int i = 0; i < numeroJogos; i++) {
                Data dt = jogos[i].getData();
                
                if (getData().equals(dt.getData())) {
                    addJogoHoje(jogos[i]);
                }
                else {
                    // caso o jogo tenha iniciado no dia anterior, mas não tenha finalizado, é adicionado aos jogos do dia
                    boolean jogoEncerrado = jogos[i].getFaseJogo().equals("Encerrado!");
                    boolean jogoIniciado = !jogos[i].getFaseJogo().equals("Em Breve");
                    
                    if (!jogoEncerrado && jogoIniciado) {
                        addJogoHoje(jogos[i]);
                    }
                }
            }
        }
        
        for (int i = 0; i < numeroJogosDia; i++) {
            if (jogosHoje[i].getFaseJogo().equals("Em Breve")) {
                Data dt = jogosHoje[i].getData();
                
                if (getHora().equals(dt.getHora())) { // inicia partida caso esteja no horário
                    jogosHoje[i].passarMinuto();
                }
            }
            
            else if (!jogosHoje[i].getFaseJogo().equals("Finalizado")) {
                jogosHoje[i].passarMinuto();
            }
            
            else if (jogosHoje[i].getFaseJogo().equals("Finalizado")) {
                int numJogo = jogosHoje[i].getNumJogo();
                int numConfronto = jogos[numJogo].getNumConfronto();
                int numGrupo = jogos[numJogo].getNumGrupo();
                
                System.out.println(jogos[numJogo].toString());
                
                if (numConfronto >= 0) {
                    if (numJogo == confrontos[numConfronto].getIDJogoA()) { // testa se o numero do jogo é igual ao numero de primeiro jogo do confronto
                        int idJogoB = confrontos[numConfronto].getIDJogoB();
                        int placarA = jogosHoje[i].getPlacarA();
                        int placarB = jogosHoje[i].getPlacarB();
                        jogos[idJogoB].setAgregado(placarB, placarA);
                    }
                    else if (numJogo == confrontos[numConfronto].getIDJogoB()) {
                        int idJogoB = confrontos[numConfronto].getIDJogoB();
                        System.out.println("Classificado Confronto "+numConfronto+": "+jogos[idJogoB].getClassificado().getNome());
                    }
                }
                
                else if (numGrupo >= 0) {
                    grupos[numGrupo].addJogo(jogos[numJogo]);
                    
                    System.out.println(grupos[numGrupo].toString());
                    
                    if (grupos[numGrupo].getStatus().equals("Finalizado")) {
                        System.out.println("Grupo Finalizado");
                        System.exit(0);
                    }
                }
                
                
                
                jogosHoje[i].encerrarJogo();
            }
        }
    }
    
    public void addJogo(Jogo jogo) {
        if (numeroJogos < limiteJogos) {
            jogos[numeroJogos] = jogo;
            
            jogos[numeroJogos].setNumJogo(numeroJogos);  

            numeroJogos++;
        }
        else {
            Jogo[] novoVetorJogos = new Jogo[limiteJogos*2];
            
            for (int i = 0; i < limiteJogos; i++) {
                novoVetorJogos[i] = jogos[i];
                novoVetorJogos[i].toString();
            }
            
            jogos = novoVetorJogos;
            
            limiteJogos = limiteJogos * 2;
            
            System.out.println("Tamanho do Vetor Jogo Aumentou");
            
            addJogo(jogo);
        }
    }
    
    public void addJogoHoje(Jogo jogo) {
        if (numeroJogosDia < limiteJogosDia) {
            jogosHoje[numeroJogosDia] = jogo;
            
            numeroJogosDia++;
        }
        else {
            Jogo[] novoVetorJogos = new Jogo[limiteJogosDia*2];
            
            for (int i = 0; i < limiteJogosDia; i++) {
                novoVetorJogos[i] = jogosHoje[i];
                novoVetorJogos[i].toString();
            }
            
            jogosHoje = novoVetorJogos;
            
            limiteJogosDia = limiteJogosDia * 2;
            
            System.out.println("Tamanho do Vetor Jogo Aumentou");
            
            addJogoHoje(jogo);
        }
    }
    
    public void addConfronto(Confronto confronto) {
        if (numeroConfrontos < limiteConfrontos) {
            confrontos[numeroConfrontos] = confronto;
            confrontos[numeroConfrontos].setID(numeroConfrontos);
            
            if(confronto.getIDJogoA() < 0) {
                // informações Jogo A
                TimeBO tmBO = new TimeBO();
                int idTimeA = confronto.getIDTimeA();
                int idTimeB = confronto.getIDTimeB();
                Time timeA = tmBO.obter(idTimeA);
                Time timeB = tmBO.obter(idTimeB);
                
                Data dataA = confronto.getDataA();
                
                int idEstadioA = confronto.getIDEstadioA();
                Estadio estadioA = new Estadio();
                EstadioBO esBO = new EstadioBO();
                if (idEstadioA < 0) {
                    estadioA = esBO.obter(idTimeA);
                    idEstadioA = estadioA.getID();
                }
                else {
                    estadioA = esBO.obter(idEstadioA);
                }
                confrontos[numeroConfrontos].setIDEstadioA(idEstadioA);
                
                Jogo jg = new Jogo(timeA, timeB, dataA, estadioA);
                addJogo(jg);
                jogos[numeroJogos-1].setNumConfronto(numeroConfrontos);
                confrontos[numeroConfrontos].setIDJogoA(numeroJogos-1);
                
                
                // informações Jogo B
                Data dataB = confronto.getDataB();
                
                int idEstadioB = confronto.getIDEstadioB();
                Estadio estadioB = new Estadio();
                if (idEstadioB < 0) {
                    estadioB = esBO.obter(idTimeB);
                    idEstadioB = estadioB.getID();
                }
                else {
                    estadioB = esBO.obter(idEstadioB);
                }
                confrontos[numeroConfrontos].setIDEstadioB(idEstadioB);
                
                jg = new Jogo(timeB, timeA, dataB, estadioB);
                jg.setTipoConfronto(true);
                addJogo(jg);
                jogos[numeroJogos-1].setNumConfronto(numeroConfrontos);
                confrontos[numeroConfrontos].setIDJogoB(numeroJogos-1);
            }

            numeroConfrontos++;
        }
        else {
            Confronto[] novoVetorConfrontos = new Confronto[limiteConfrontos*2];
            
            for (int i = 0; i < limiteConfrontos; i++) {
                novoVetorConfrontos[i] = confrontos[i];
                novoVetorConfrontos[i].toString();
            }
            
            confrontos = novoVetorConfrontos;
            
            limiteConfrontos = limiteConfrontos * 2;
            
            System.out.println("Tamanho do Vetor Confrontos Aumentou");
            
            addConfronto(confronto);
        }
    }
    
    public void addGrupo(Grupo grupo) {
        if (numeroGrupos < limiteGrupos) {
            TimeBO tmBO = new TimeBO();
            
            grupos[numeroGrupos] = grupo;
            grupos[numeroGrupos].setID(numeroGrupos);
            
            int[][][] idTimesPartida = grupo.getidTimesPartidas();
            int[][] idPartidas = new int[idTimesPartida.length][idTimesPartida[0].length];
            
            int dia = 1;
            
            for (int i = 0; i < idTimesPartida.length; i++) {
                for (int j = 0; j < idTimesPartida[i].length; j++) {
                    int idA = idTimesPartida[i][j][0];
                    int idB = idTimesPartida[i][j][1];
                    
                    Time timeA = tmBO.obter(idA);
                    Time timeB = tmBO.obter(idB);
                    
                    Jogo jg = new Jogo(timeA, timeB, new Data(dia, 2, 2024, 0, 0));
                    dia++;
                    
                    addJogo(jg);
                    jogos[numeroJogos-1].setNumGrupo(numeroGrupos);
                    idPartidas[i][j] = numeroJogos-1;
                }
            }
            numeroGrupos++;
        
            System.out.println(grupos[numeroGrupos-1].toString());
        }
        else {
            Grupo[] novoVetorGrupos = new Grupo[limiteGrupos*2];
            
            for (int i = 0; i < limiteGrupos; i++) {
                novoVetorGrupos[i] = grupos[i];
                novoVetorGrupos[i].toString();
            }
            
            grupos = novoVetorGrupos;
            
            limiteGrupos = limiteGrupos * 2;
            
            System.out.println("Tamanho do Vetor Grupos Aumentou");
            
            addGrupo(grupo);
        }
    }
    
    public void exibirJogos() {
        for (int i = 0; i < numeroJogos; i++) {
            System.out.print(jogos[i].toString());
        }
    }
    
    public void exibirJogosHoje() {
        for (int i = 0; i < numeroJogosDia; i++) {
            System.out.print(jogosHoje[i].toString());
        }
    }
    
    public void exibirConfrontos() {
        for (int i = 0; i < numeroConfrontos; i++) {
            System.out.print(confrontos[i].toString());
        }
    }
    
    public void exibirGrupos() {
        for (int i = 0; i < numeroGrupos; i++) {
            System.out.print(grupos[i].toString());
        }
    }
    
    public void removerJogosHoje() {
        numeroJogosDia = 0;
    }
}
