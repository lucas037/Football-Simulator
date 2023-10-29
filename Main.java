public class Main {
    public static void main(String args[]) {
        //System.out.println("Hello World!");
        
        // AJEITAR PASSAR DOS DIAS (SEGUNDA > TERÇA > QUARTA)
        
        Tempo tmp = new Tempo(6, 1, 2023, 23, 58);
        
        Estadio[] estadios = new Estadio[5];
        estadios[0] = new Estadio("Maracanã", 60000);
        estadios[1] = new Estadio("Morumbi", 60000);
        estadios[2] = new Estadio("Allianz Parque", 35000);
        estadios[3] = new Estadio("Neo Quimica Arena", 40000);
        estadios[4] = new Estadio("Vila Belmiro", 20000);
        
        Time[] times = new Time[6];
        times[0] = new Time("Flamengo", estadios[0]);
        times[1] = new Time("Fluminense", estadios[0]);
        times[2] = new Time("São Paulo", estadios[1]);
        times[3] = new Time("Palmeiras", estadios[2]);
        times[4] = new Time("Corinthians", estadios[3]);
        times[5] = new Time("Santos", estadios[4]);
        
        Jogo jogoA = new Jogo(times[0], times[3], new Data(7, 1, 2023, 20, 0));
        Jogo jogoB = new Jogo(times[2], times[4], new Data(7, 1, 2023, 20, 30));
        Jogo jogoC = new Jogo(times[1], times[5], new Data(7, 1, 2023, 20, 30));
        Jogo jogoD = new Jogo(times[1], times[5], new Data(8, 1, 2023, 20, 30));
        
        Jogo[] jogosAno = new Jogo[100];
        int quantJogos = 0;
        
        tmp.addJogo(jogoA);
        tmp.addJogo(jogoB);
        tmp.addJogo(jogoC);
        tmp.addJogo(jogoD);
        
        
        for (int i = 0; i < 2; i++) {
            tmp.passarDia();
        }
    }   
}