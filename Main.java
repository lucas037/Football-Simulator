public class Main {
    public static void main(String args[]) {
        //System.out.println("Hello World!");
        
        // AJEITAR PASSAR DOS DIAS (SEGUNDA > TERÇA > QUARTA)
        
        Tempo tmp = new Tempo(6, 1, 2023, 20, 58);
        
        Estadio[] estadios = new Estadio[8];
        estadios[0] = new Estadio("Maracanã", 60000);
        estadios[1] = new Estadio("Morumbi", 60000);
        estadios[2] = new Estadio("Allianz Parque", 35000);
        estadios[3] = new Estadio("Neo Quimica Arena", 40000);
        estadios[4] = new Estadio("Vila Belmiro", 20000);
        estadios[5] = new Estadio("Beira-Rio", 30000);
        estadios[6] = new Estadio("Arena do Grêmio", 30000);
        estadios[7] = new Estadio("Mineirão", 30000);
        
        Time[] times = new Time[10];
        times[0] = new Time("Flamengo", estadios[0]);
        times[1] = new Time("Fluminense", estadios[0]);
        times[2] = new Time("São Paulo", estadios[1]);
        times[3] = new Time("Palmeiras", estadios[2]);
        times[4] = new Time("Corinthians", estadios[3]);
        times[5] = new Time("Santos", estadios[4]);
        times[6] = new Time("Internacional", estadios[5]);
        times[7] = new Time("Grêmio", estadios[6]);
        times[8] = new Time("Cruzeiro", estadios[7]);
        times[9] = new Time("Atlético Mineiro", estadios[7]);
        
        
        
        for (int i = 0; i < 1; i++) {
            tmp.passarDia();
        }
        
        Data dj = new Data(9, 1, 2023, 17, 30);
        Data dk = new Data(10, 1, 2023, 17, 30);
        Data dl = new Data(17, 1, 2023, 17, 30);
        Data dm = new Data(18, 1, 2023, 17, 30);
           
        Data[] dtt = {dj, dl, dj, dl, dj, dl, dk, dm, dk, dm};
        Fase fs = new Fase("Oitavas", "Mata-Mata", times, 10, dtt);
        tmp = fs.sorteio(tmp);
        
        
        System.out.println("===");
        for (int i = 0; i < 15; i++) {
            tmp.passarDia();
        }
        tmp.exibirConfrontos();
    }   
}