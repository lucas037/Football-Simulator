import model.entity.Tempo;
import model.entity.Info;
import model.entity.Estadio;
import model.entity.Time;
import model.entity.Campeonato;
import model.entity.Jogo;
import model.entity.Confronto;
import model.entity.Data;

public class Main {
    public static void main(String args[]) {
        //System.out.println("Hello World!");
        
        Tempo tmp = new Tempo(29, 1, 2024, 0, 0);
        
        tmp.gerar();
        
        Info inf = new Info();
        
        /*
        Confronto cf = new Confronto(1, 2, new Data(31, 1, 2024, 23, 30), new Data(7, 2, 2024, 21, 0));
        tmp.addConfronto(cf);
        Jogo jg = new Jogo(new Time("Flamengo"), new Time("Palmeiras"), new Data(3, 2, 2024, 0, 30));
        tmp.addJogo(jg);
        cf = new Confronto(3, 4, new Data(30, 1, 2024, 20, 0), new Data(7, 2, 2024, 21, 0));
        tmp.addConfronto(cf);
        */
        
        
        for (int i = 0; i < 23; i++) {
            tmp.passarDia();
            
            System.out.println("");
            System.out.println(tmp.getData("-", "abc", false));
            
        }
        
        //tmp.salvar();
        
        
        
        System.exit(0);
        
        
        
    }
        
        
}