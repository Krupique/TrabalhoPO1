package utils;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Henrique Krupck
 */
public class GeradorVetor {
    
    //Arrumar gerar_counting, gerar_radix
    //Gerar vetor no espaço de 1 - 11
    public static int[] gerar_counting(int tf) //Gera vetor com elementos de no máximo tf, porém pode repetir.
    {
        int[] vet = new int[tf];
        ArrayList<Integer> lista = new ArrayList<>();
        ArrayList<Integer> uni1 = new ArrayList<>();
        ArrayList<Integer> uni2 = new ArrayList<>();
        ArrayList<Integer> uni3 = new ArrayList<>();
        for (int i = 1; i < tf; i++) {
            uni1.add(i);
            
        }
        for (int i = tf/2 - 1; i < tf; i++) {
            uni2.add(i);
        }
        for (int i = 1; i < tf - 3; i++) {
            uni3.add(i);
            
        }
        Collections.shuffle(uni1);
        Collections.shuffle(uni2);
        Collections.shuffle(uni3);
        
        for (int i = 0; i < tf / 2; i++) {
            lista.add(uni1.get(i));
            lista.add(uni2.get(i));
            lista.add(uni3.get(i));
        }
        Collections.shuffle(lista);
        for (int i = 0; i < tf; i++)
            vet[i] = lista.get(i);
        
        return vet;
    }
    
    public static int[] gerar_radix(int tf) //Gera vetor com elementos de no máximo 999.
    {
        int[] vet = new int[tf];
        
        ArrayList<Integer> lista = new ArrayList<>();
        ArrayList<Integer> uni = new ArrayList<>();
        ArrayList<Integer> dez = new ArrayList<>();
        ArrayList<Integer> cent = new ArrayList<>();
        for (int i = 0; i < 10; i++)
            uni.add(i);
        for (int i = 0; i < 89; i++)
            dez.add(i);
        for (int i = 0; i < 899; i++)
            cent.add(i);
        Collections.shuffle(uni);
        Collections.shuffle(dez);
        Collections.shuffle(cent);
        
        for (int i = 0; i < tf / 2; i++)
        {
            lista.add(cent.get(i));
            lista.add(dez.get(i));
            lista.add(uni.get(i));
        }
        Collections.shuffle(lista);
        for (int i = 0; i < tf; i++)
            vet[i] = lista.get(i);
        
        return vet;
    }
    
    public static int[] gerar_comb(int tf) //Gera vetor com elementos de no máximo tf, sem repetir elementos.
    {
        int[] vet = new int[tf];
        ArrayList<Integer> lista = new ArrayList<>();
        for (int i = 0; i < tf; i++)
            lista.add(i);
        
        Collections.shuffle(lista);
        for (int i = 0; i < tf; i++)
            vet[i] = lista.get(i);
        
        return vet;
    }
}
