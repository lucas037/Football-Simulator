public class Main {
    public static void main(String args[]) {
        //System.out.println("Hello World!");
        
        // AJEITAR PASSAR DOS DIAS (SEGUNDA > TERÇA > QUARTA)
        
        Tempo tmp = new Tempo(6, 1, 2023, 20, 58);
        
        Estadio[] estadios = new Estadio[6];
        estadios[0] = new Estadio("Maracanã", 60000);
        estadios[1] = new Estadio("Morumbi", 60000);
        estadios[2] = new Estadio("Allianz Parque", 35000);
        estadios[3] = new Estadio("Neo Quimica Arena", 40000);
        estadios[4] = new Estadio("Vila Belmiro", 20000);
        estadios[5] = new Estadio("Beira-Rio", 30000);
        
        Time[] times = new Time[7];
        times[0] = new Time("Flamengo", estadios[0]);
        times[1] = new Time("Fluminense", estadios[0]);
        times[2] = new Time("São Paulo", estadios[1]);
        times[3] = new Time("Palmeiras", estadios[2]);
        times[4] = new Time("Corinthians", estadios[3]);
        times[5] = new Time("Santos", estadios[4]);
        times[6] = new Time("Internacional", estadios[5]);
        
        Confronto cft = new Confronto(times[3], times[1], new Data(10, 1, 2023, 20, 30), new Data(15, 1, 2023, 20, 30));
        
        Confronto cftB = new Confronto(times[0], times[6], new Data(10, 1, 2023, 17, 30), new Data(11, 1, 2023, 20, 30));
        
        Confronto cftC = new Confronto(times[4], times[5], new Data(10, 1, 2023, 17, 30), new Data(12, 2, 2023, 20, 30));
        
        Confronto cftD = new Confronto(times[2], times[6], new Data(10, 1, 2023, 17, 30), new Data(12, 2, 2023, 20, 30));
        
        for (int i = 0; i < 1; i++) {
            tmp.addConfronto(cft);
            tmp.addConfronto(cftB);
            tmp.addConfronto(cftC);
            tmp.addConfronto(cftD);
        }
        
        
        
        for (int i = 0; i < 15; i++) {
            tmp.passarDia();
        }
    }   
}