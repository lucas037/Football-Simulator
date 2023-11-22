package model.entity.Resources;

public class Gerador {
    public int[] gerarIndices(int tam) {
        int[] indices = new int[tam];
        
        for (int i = 0; i < tam; i++)
            indices[i] = i;
        
        return indices;
    }
}
