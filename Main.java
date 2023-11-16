import model.entity.Tempo;
import model.entity.Info;
import model.entity.Estadio;
import model.entity.Time;
import model.entity.Jogo;
import model.entity.Confronto;
import model.entity.Data;

import model.entity.Grupo;

public class Main {
    public static void main(String args[]) {
        //System.out.println("Hello World!");
        
        int[] times = {1, 11, 9, 14, 20, 2};
        Grupo grupo = new Grupo(times);
        
        grupo.getDesempenhoTimes()[0].addResultado(1, 0); grupo.getDesempenhoTimes()[1].addResultado(0, 1);
        grupo.getDesempenhoTimes()[2].addResultado(0, 0); grupo.getDesempenhoTimes()[3].addResultado(0, 0);
        grupo.getDesempenhoTimes()[4].addResultado(4, 1); grupo.getDesempenhoTimes()[5].addResultado(1, 4);
        
        grupo.getDesempenhoTimes()[0].addResultado(2, 3); grupo.getDesempenhoTimes()[3].addResultado(3, 2);
        grupo.getDesempenhoTimes()[1].addResultado(0, 3); grupo.getDesempenhoTimes()[4].addResultado(3, 0);
        grupo.getDesempenhoTimes()[2].addResultado(0, 3); grupo.getDesempenhoTimes()[5].addResultado(3, 0);
        
        grupo.getDesempenhoTimes()[0].addResultado(2, 1); grupo.getDesempenhoTimes()[5].addResultado(1, 2);
        grupo.getDesempenhoTimes()[1].addResultado(0, 1); grupo.getDesempenhoTimes()[4].addResultado(1, 0);
        grupo.getDesempenhoTimes()[2].addResultado(0, 0); grupo.getDesempenhoTimes()[3].addResultado(0, 0);
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