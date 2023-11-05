import model.entity.Tempo;
import model.entity.Info;
import model.entity.Estadio;
import model.entity.Time;
import model.entity.Campeonato;

public class Main {
    public static void main(String args[]) {
        //System.out.println("Hello World!");
        
        // AJEITAR PASSAR DOS DIAS (SEGUNDA > TERÇA > QUARTA)
        
        Tempo tmp = new Tempo(6, 1, 2023, 20, 58);
        Info dados = new Info();
        
        Estadio[] estadios = dados.getEstadios();
        Time[] times = dados.getTimes();
        Campeonato[] campeonatos = dados.getCampeonatos();
        
        for (int i = 0; i < times.length; i++) {
            System.out.println(times[i].toString());
        }
        
        //campeonatos[0].exibirTimes();
        
        
    }
        
        
}