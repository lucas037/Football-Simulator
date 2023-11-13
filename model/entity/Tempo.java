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
            }
                
            // Aqui faz-se, através do banco de dados, tempo obter todos os eventos do dia (jogos e outros)
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
            
            TimeBO tmBO = new TimeBO();
            EstadioBO esBO = new EstadioBO();
            
            Time timeA = tmBO.obter(confronto.getIDTimeA());
            Time timeB = tmBO.obter(confronto.getIDTimeB());
            Data dataA = confronto.getDataA();
            
            // caso o estádio não esteja definido, definide como o do time da casa
            Estadio estadioA = new Estadio();
            if (confronto.getIDEstadioA() >= 0) {
                estadioA = esBO.obter(confronto.getIDEstadioA());
            }
            else {
                int idEstadioA = timeA.getEstadio().getID();
                estadioA = esBO.obter(idEstadioA);
            }
            
            Jogo jogoA = new Jogo(timeA, timeB, dataA, estadioA);
            addJogo(jogoA);
            
            jogos[numeroJogos-1].setNumConfronto(numeroConfrontos);
            confrontos[numeroConfrontos].setIDJogoA(numeroJogos-1);
            
            // Adiciona jogos da volta, caso exista
            if (confronto.getTipo().equals("Casa e Fora")) {
                Data dataB = confronto.getDataB();

                // caso o estádio não esteja definido, definide como o do time da casa
                Estadio estadioB = new Estadio();
                if (confronto.getIDEstadioB() >= 0) {
                    estadioB = esBO.obter(confronto.getIDEstadioB());
                }
                else {
                    int idEstadioB = timeB.getEstadio().getID();
                    estadioB = esBO.obter(idEstadioB);
                }

                Jogo jogoB = new Jogo(timeB, timeA, dataB, estadioB);
                addJogo(jogoB);
            
                jogos[numeroJogos-1].setNumConfronto(numeroConfrontos);
                jogos[numeroJogos-1].setTipoConfronto(true);
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
            
            addConfronto(confronto);
            
            
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
    
    public void removerJogosHoje() {
        numeroJogosDia = 0;
    }
}
