import model.entity.Tempo;
import model.entity.Info;
import model.entity.Estadio;
import model.entity.Time;
import model.entity.Campeonato;
import model.entity.Jogo;

public class Main {
    public static void main(String args[]) {
        //System.out.println("Hello World!");
        
        Tempo tmp = new Tempo(26, 1, 2024, 20, 56);
        tmp.gerar();
        
        Info inf = new Info();
        
        for (int i = 0; i < 2; i++) {
            tmp.passarDia();
            System.out.println("");
            tmp.exibirData();
            tmp.exibirHora();
            tmp.exibirJogosHoje();
        }
        
        //tmp.salvar();
        
        
    }
        
        
}