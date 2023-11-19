import java.util.Set;
import model.entity.Tempo;
import model.entity.Info;

import model.entity.Resources.Aleatory;
import model.entity.Grupo;


public class Main {
    public static void main(String args[]) {
        //System.out.println("Hello World!");
        
        Aleatory al = new Aleatory();
        
        int[] lista = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        
        for (int i = 0; i < lista.length; i++) {
            System.out.print(lista[i]+ " ");
        }
        System.out.println("\n");
        
        int[][] quant = new int[10][10];
        for (int j = 0; j < 10000; j++) {
            for (int i = 0 ; i < lista.length; i++) {
                lista[i] = i;
            }
            lista = al.shuffle(lista);
            
            for (int k = 0; k < lista.length; k++) {
                for (int i = 0; i < lista.length; i++) {
                    //System.out.print(lista[i]+ " ");

                    if (lista[i] == k)
                        quant[k][i]++;
                }
                
            }
            
            
            
        }
        
        for (int j = 0; j < 10; j++) {
            System.out.print("\n"+j+":\n");
            int kk = 0;
            for (int i = 0; i < 10; i++) {
                System.out.println(i+": "+quant[j][i]);
                kk += quant[j][i];
            }
            System.out.println(kk+" :)");
            
        }
        
        System.exit(0);
        
        int[] times = {1, 11, 9, 14, 20, 2};
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