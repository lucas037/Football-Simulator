import model.entity.Tempo;
import model.entity.Info;
import model.entity.Estadio;
import model.entity.Time;
import model.entity.Campeonato;
import model.entity.Jogo;

public class Main {
    public static void main(String args[]) {
        //System.out.println("Hello World!");
        
        Tempo tmp = new Tempo(6, 1, 2023, 19, 58);
        tmp.gerar();
        
        
        
        for (int i = 0; i < 3600; i++) {
            tmp.passarMinuto();
            tmp.exibirJogosHoje();
            
        }
        
        
    }
        
        
}