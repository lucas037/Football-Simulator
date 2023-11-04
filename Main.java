public class Main {
    public static void main(String args[]) {
        //System.out.println("Hello World!");
        
        // AJEITAR PASSAR DOS DIAS (SEGUNDA > TERÇA > QUARTA)
        
        Tempo tmp = new Tempo(6, 1, 2023, 20, 58);
        Info dados = new Info();
        
        Estadio[] estadios = dados.getEstadios();
        Time[] times = dados.getTimes();
        Campeonato[] campeonatos = dados.getCampeonatos();
        
        campeonatos[0].exibirTimes();
        
        //Campeonato cmp = new Campeonato("Copa do Brasil", 8, 3);
        
        
    }   
}