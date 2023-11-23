import java.util.Set;
import model.entity.Tempo;
import model.entity.Info;

import model.entity.Resources.Aleatory;
import model.entity.Resources.Gerador;
import model.entity.Grupo;


public class Main {
    public static void main(String args[]) {
        //System.out.println("Hello World!");
        
        Aleatory al = new Aleatory();
        
        Tempo tmp = new Tempo(29, 1, 2024, 0, 0);
        
        tmp.gerar();
        
        Gerador gr = new Gerador();
        int[] indices = gr.gerarIndices(21);
        indices = al.shuffle(indices);
        
        int[] times = {indices[0], indices[1], indices[2], indices[3], indices[4]};
        Grupo grupo = new Grupo(times);
        tmp.addGrupo(grupo);
        
        for (int i = 0; i < 25; i++) {
            tmp.passarDia();
            
            System.out.println("");
            System.out.println(tmp.getData("-", "abc", false));
            
        }
        
        tmp.exibirGrupos();
        //tmp.salvar();
        
        System.exit(0);
        
        
        
    }
        
        
}