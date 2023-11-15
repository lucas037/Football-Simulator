import model.entity.Tempo;
import model.entity.Info;
import model.entity.Estadio;
import model.entity.Time;
import model.entity.Jogo;
import model.entity.Confronto;
import model.entity.Data;

public class Main {
    public static void main(String args[]) {
        //System.out.println("Hello World!");
        
        Tempo tmp = new Tempo(29, 1, 2024, 0, 0);
        
        tmp.gerar();
        
        Info inf = new Info();
        
        for (int i = 0; i < 25; i++) {
            tmp.passarDia();
            
            System.out.println("");
            System.out.println(tmp.getData("-", "abc", false));
            
        }
        
        tmp.salvar();
        
        System.exit(0);
        
        
        
    }
        
        
}