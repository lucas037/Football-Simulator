import model.entity.Tempo;
import model.entity.Info;
import model.entity.Estadio;
import model.entity.Time;
import model.entity.Campeonato;
import model.entity.Jogo;

import model.bo.JogoBO;
import model.bo.EstadioBO;

public class Main {
    public static void main(String args[]) {
        //System.out.println("Hello World!");
        
        Tempo tmp = new Tempo(6, 1, 2023, 20, 58);
        
        JogoBO jgBO = new JogoBO();
        Jogo[] jgos = jgBO.obter();
        
        for (int i = 0; i < jgos.length; i++) {
            System.out.println(jgos[i].toString());
        }
        
        
    }
        
        
}