package model.entity;

import model.bo.JogoBO;

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
    
    public void gerar() {
        gerarJogos();
    }
    
    public void gerarJogos() {
        JogoBO jgBO = new JogoBO();
        Jogo[] jogos = jgBO.obter();
        
        for (Jogo jgo : jogos) {
            addJogo(jgo);
            
            if (!jgo.getFaseJogo().equals("Em Breve") && !jgo.getFaseJogo().equals("Finalizado") && !jgo.getFaseJogo().equals("Encerrado")) {
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
                
                if (numJogo == confrontos[numConfronto].getNumJogoA()) { // testa se o numero do jogo é igual ao numero de primeiro jogo do confronto
                    confrontos[numConfronto].setJogoB(confrontos[numConfronto].getJogoA().getPlacarA(), confrontos[numConfronto].getJogoA().getPlacarB());
                    confrontos[numConfronto].setNumJogoB(numeroJogos); // seta o numero do jogo
                    addJogo(confrontos[numConfronto].getJogoB()); // adiciona a lista de jogos
                }
                else if (numJogo == confrontos[numConfronto].getNumJogoB()) {
                    System.out.println(numJogo+": Classificado: "+confrontos[numConfronto].getJogoB().getClassificado().getNome());
                    
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
            
            confronto.setNumConfrontoJogoA(numeroConfrontos);
            confronto.setNumJogoA(numeroJogos);
            
            addJogo(confronto.getJogoA());
            
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
            System.out.print(confrontos[i].getJogoA().toString());
            System.out.println(confrontos[i].getJogoB().toString());
        }
    }
    
    public void removerJogosHoje() {
        numeroJogosDia = 0;
    }
}
