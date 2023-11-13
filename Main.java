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
        
        Tempo tmp = new Tempo(30, 1, 2024, 19, 59);
        tmp.gerar();
        
        System.exit(0);
        
        Info inf = new Info();
        
        //Confronto cf = new Confronto(inf.getTimes()[3], inf.getTimes()[1], new Data(31, 1, 2024, 20, 0), new Data(7, 2, 2024, 21, 0));
        //tmp.addConfronto(cf);
        
        tmp.salvar();
        
        for (int i = 0; i < 3; i++) {
            tmp.passarMinuto();
            
            System.out.println("");
            System.out.println(tmp.getData("-", "abc", false));
            
            tmp.exibirJogosHoje();
        }
        
        tmp.salvar();
        
        
    }
        
        
}