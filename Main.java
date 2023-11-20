import java.util.Set;
import model.entity.Tempo;
import model.entity.Info;

import model.entity.Resources.Aleatory;
import model.entity.Grupo;


public class Main {
    public static void main(String args[]) {
        //System.out.println("Hello World!");
        
        Aleatory al = new Aleatory();
        
        int[] times = {1, 4, 5, 19, 12, 2, 3, 6, 7, 8};
        Grupo grupo = new Grupo(times);
        
        System.out.println(grupo.toString());
        
        System.exit(0);
        
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