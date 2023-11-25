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
        
        //tmp.gerar();
        
        Gerador gr = new Gerador();
        
        int[] indices = gr.gerarIndices(21);
        indices = al.shuffle(indices);
        int[] times = {indices[0], indices[1], indices[2], indices[3]};
        Grupo grupo = new Grupo(times);
        tmp.addGrupo(grupo);
        
        int[] timesB = {indices[4], indices[5], indices[6], indices[7], indices[8]};
        Grupo grupoB = new Grupo(timesB);
        tmp.addGrupo(grupoB);
        
        tmp.salvar();
        System.exit(0);
        
        for (int i = 0; i < 30; i++) {
            tmp.passarDia();
            
            System.out.println("");
            System.out.println(tmp.getData("-", "abc", false));
            
        }
        
        tmp.salvar();
        
        tmp.exibirGrupos();
        tmp.exibirJogos();
        
        System.exit(0);
        
        
        
    }
        
        
}