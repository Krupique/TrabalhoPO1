package parte1po.algoritmos;

import javafx.application.Platform;
import javafx.scene.paint.Color;
import parte1po.TelaPrincipalController;

/**
 *
 * @author Henrique Krupck
 */
public class BucketSort implements Runnable{

    private int[] vet;
    private int tl;
    private TelaPrincipalController tela;
    private static final int TEMPO = 500;
    private int i;
    private int j;
    private int k;
    private String aux;

    public BucketSort(int[] vet, TelaPrincipalController tela) {
        this.vet = vet;
        this.tela = tela;
        this.tl = vet.length;
    }
    
    @Override
    public void run()
    {
        try
        {
            sort();
        }catch(Exception er)
        {
            System.out.println("Erro: " + er.getMessage());
        }
    }
    
    public void sort()
    {
        try
        {
            aux = "";
            int[][] bucket = new int[tl / 3][3];
            
            
            for (i = 0; i < tl/3; i++) {
                tela.exibir_bucket_pos(true, i);
                Thread.sleep(TEMPO - 400);
            }
            
            for (i = 0; i < tl; i++) {
                k = (int)Math.floor(vet[i] / 3);
                j = 0;
                while(bucket[k][j] != 0)
                    j++;
                bucket[k][j] = vet[i];
                
                Platform.runLater(()->{tela.inserir_bucket_final("" + vet[i], k);});
                Thread.sleep(200);
                tela.pintar_vet_original("#a9520c", i);
                tela.pintar_vet_bucket("#a9520c", k);
                Thread.sleep(500);
                
                tela.pintar_vet_original("#287171", i);
                tela.pintar_vet_bucket("#287171", k);  
            }
            
            
            for (i = 0; i < bucket.length; i++)
            {
                bucket[i] = insercao_direta(bucket[i]);
                for (j = 0; j < 3; j++)
                    aux = aux + bucket[i][j] + "   ";
                
                aux = aux.substring(0, aux.length() - 3);
                Thread.sleep(10);
                
                Platform.runLater(()->{tela.inserir_bucket(aux, i);});
                Thread.sleep(200);
                tela.pintar_vet_bucket("#a9520c", i);
                Thread.sleep(500);
                tela.pintar_vet_bucket("#287171", i);
                aux = " ";
            }
            
            for (i = 0; i < tl; i++) {
                tela.exibir_vetor_res_pos(true, i);
            }
            
            for (i = 0, k = 0; k < bucket.length; k++, i++) {
                tela.pintar_vet_bucket("#a9520c", k);
                
                vet[i] = bucket[k][0];
                tela.pintar_vet_res("#a9520c", i++);
                vet[i] = bucket[k][1];
                tela.pintar_vet_res("#a9520c", i++);
                vet[i] = bucket[k][2];
                tela.pintar_vet_res("#a9520c", i);
                
                Platform.runLater(()->{
                    tela.inserir_numero_res(i - 2, vet[i - 2]);
                    tela.inserir_numero_res(i - 1, vet[i - 1]);
                    tela.inserir_numero_res(i, vet[i]);
                });
                
                
                Thread.sleep(500);
                tela.pintar_vet_bucket("#287171", k);
                tela.pintar_vet_res("#287171", i-2);
                tela.pintar_vet_res("#287171", i-1);
                tela.pintar_vet_res("#287171", i);
                Thread.sleep(500);  
            }
            
            
        }catch(Exception er){}
    }
    
    public int[] insercao_direta(int[] vet)
    {
        int aux, pos;
        for (int i = 1; i < vet.length; i++) {
            for (pos = i, aux = vet[i]; pos > 0 && aux < vet[pos - 1]; pos--)
                vet[pos] = vet[pos - 1];
            vet[pos] = aux;
            
        }
        return vet;
    }
    
}
