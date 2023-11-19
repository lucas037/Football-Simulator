package model.entity.Resources;

import java.util.Random;

public class Aleatory {
    Random rand = new Random();
    
    public Aleatory() {}
    
    public int randrange(int num) {
        return rand.nextInt(num);
    }
    
    public int randrange(int num1, int num2) {
        return rand.nextInt(num2 - num1) + num1;
    }
    
    public int[] shuffle(int[] lista) {
        int indiceAletoario = 0;
        int valorArmazenado = 0;
        for (int i = 0; i < lista.length; i++) {
             indiceAletoario = randrange(lista.length);
             valorArmazenado = lista[indiceAletoario];
             
             lista[indiceAletoario] = lista[i];
             lista[i] = valorArmazenado;
        }
        
        return lista;
    }
    
}
