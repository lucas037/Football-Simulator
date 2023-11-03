public class Tempo extends Data {
    private Jogo[] jogosHoje = new Jogo[100];
    private int numeroDeJogos = 0;
    private int limiteJogosDia = 100;
    private Jogo[] jogosAno = new Jogo[100];
    private int numeroDeJogosAno = 0;
    private int limiteJogosAno = 100;
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
    
    
    
    @Override
    public void passarMinuto() {
        super.passarMinuto();
        
        if (getHora().equals("00:00")) {
            removerJogosHoje();
            
            for (int i = 0; i < numeroDeJogosAno; i++) {
                Data dt = jogosAno[i].getData();
                
                if (getData().equals(dt.getData())) {
                    addJogoHoje(jogosAno[i]);
                }
            }
                
            // Aqui faz-se, através do banco de dados, tempo obter todos os eventos do dia (jogos e outros)
        }
        
        for (int i = 0; i < numeroDeJogos; i++) {
            if (jogosHoje[i].getFaseJogo().equals("Em Breve")) {
                Data dt = jogosHoje[i].getData();
                boolean aux = true;
                
                if (!getData().equals(dt.getData())) {
                    aux = false;
                }
                
                else if (!getHora().equals(dt.getHora())) {
                    aux = false;
                }
                
                if (aux) { // inicia partida caso esteja no horário
                    jogosHoje[i].passarMinuto();
                }
            }
            
            else if (!jogosHoje[i].getFaseJogo().equals("Finalizado")) {
                jogosHoje[i].passarMinuto();
            }
            
            else if (jogosHoje[i].getFaseJogo().equals("Finalizado")) {
                int numJogo = jogosHoje[i].getNumJogo();
                
                // caso o jogo que acabou de ser finalizado seja um confronto de casa e fora ou não.
                for (int j = 0; j < numeroConfrontos; j++) {
                    if (numJogo == confrontos[j].getNumJogoA()) {
                        confrontos[j].setJogoB(confrontos[j].getJogoA().getPlacarA(), confrontos[j].getJogoA().getPlacarB());
                        confrontos[j].setNumJogoB(numeroDeJogosAno);
                        addJogo(confrontos[j].getJogoB());
                    }
                    else if (numJogo == confrontos[j].getNumJogoB()) {
                        System.out.println(numJogo+": Classificado: "+confrontos[j].getJogoB().getClassificado().getNome());
                    }
                }
                
                jogosHoje[i].encerrarJogo();
            }
        }
    }
    
    public void addJogo(Jogo jogo) {
        if (numeroDeJogosAno < limiteJogosAno) {
            jogosAno[numeroDeJogosAno] = jogo;
            
            jogosAno[numeroDeJogosAno].setNumJogo(numeroDeJogosAno);
            
            numeroDeJogosAno++;
        }
        else {
            Jogo[] novoVetorJogos = new Jogo[limiteJogosAno*2];
            
            for (int i = 0; i < limiteJogosAno; i++) {
                novoVetorJogos[i] = jogosAno[i];
                novoVetorJogos[i].toString();
            }
            
            jogosAno = novoVetorJogos;
            
            limiteJogosAno = limiteJogosAno * 2;
            
            System.out.println("Tamanho do Vetor Jogo Aumentou");
            
            addJogo(jogo);
        }
    }
    
    public void addJogoHoje(Jogo jogo) {
        if (numeroDeJogos < limiteJogosDia) {
            jogosHoje[numeroDeJogos] = jogo;
            
            numeroDeJogos++;
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
        for (int i = 0; i < numeroDeJogosAno; i++) {
            System.out.print(jogosAno[i].toString());
        }
    }
    
    public void exibirConfrontos() {
        for (int i = 0; i < numeroConfrontos; i++) {
            System.out.print(confrontos[i].getJogoA().toString());
            System.out.println(confrontos[i].getJogoB().toString());
        }
    }
    
    public void removerJogosHoje() {
        numeroDeJogos = 0;
    }
}
