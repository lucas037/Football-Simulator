public class Tempo extends Data {
    Jogo[] jogosHoje = new Jogo[100];
    int numeroDeJogos = 0;
            
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
            System.out.println("");
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
        }
    }
    
    
    
    public void addJogo(Jogo jogo) {
        if (numeroDeJogos < 100) {
            jogosHoje[numeroDeJogos] = jogo;
            numeroDeJogos++;
        }
        else {
            System.out.println("Limite de Jogos em um Dia é 100.");
        }
    }
    
    public void removerJogos() {
        numeroDeJogos = 0;
    }
    
    public void exibirJogos() {
        for (int i = 0; i < numeroDeJogos; i++) {
            System.out.println(jogosHoje[i].toString());
        }
    }
}
