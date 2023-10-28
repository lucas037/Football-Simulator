public class Main {
    public static void main(String args[]) {
        //System.out.println("Hello World!");
        
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
        
        Jogo jogoA = new Jogo(times[5], times[1], new Data(7, 1, 2023, 20, 0));
        
        System.out.println(jogoA.toString());
        int golsAAntigo = 0;
        int golsBAntigo = 0;
        
        for (int i = 0; i < 120; i++) {
            golsAAntigo = jogoA.getPlacarA();
            golsBAntigo = jogoA.getPlacarB();
            
            jogoA.passarMinuto();
            
            if (golsAAntigo != jogoA.getPlacarA() || golsBAntigo != jogoA.getPlacarB())
                System.out.println(jogoA.toString());
        }
    }   
}