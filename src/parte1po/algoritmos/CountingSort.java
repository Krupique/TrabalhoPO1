package parte1po.algoritmos;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import parte1po.TelaPrincipalController;

/**
 *
 * @author Henrique Krupck
 */
public class CountingSort implements Runnable{
    private static final int TEMPO = 500;
    private int[] vet;
    private int tl;
    private TelaPrincipalController tela;
    private int i;

    public CountingSort(int[] vet, TelaPrincipalController tela) {
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
            Thread.sleep(TEMPO);
        }catch(Exception er)
        {
            System.out.println("Erro: " + er.getMessage());
        }
        
    }
    
    public void sort()
    {
        try
        {
            int[] cont = new int[tl];
            int[] res = new int[tl];
            
            for (i = 0; i < tl; i++) {
                tela.exibir_vetor_aux_pos(true, i);
                Thread.sleep(TEMPO - 400);
            }
            
            for (i = 0; i < tl; i++) {
                cont[i] = 0;
            }
            Platform.runLater(()->{tela.inserir_vetor_aux(cont);});
            Thread.sleep(TEMPO - 400);
            
            for (i = 0; i < tl; i++) {
                ++cont[vet[i]];
                
                Platform.runLater(()->{tela.inserir_vetor_aux(cont);});
                Thread.sleep(100);
                tela.pintar_vet_original("#a9520c", i); //Laranja
                tela.pintar_vet_aux("#a9520c", vet[i]); //Laranja
                Thread.sleep(TEMPO);
                tela.pintar_vet_original("#287171", i); //Azul
                tela.pintar_vet_aux("#287171", vet[i]); //Azul
                Platform.runLater(()->{tela.inserir_vetor_aux(cont);});
            }
            
            for (i = 1; i < tl; i++) {
                cont[i] += cont[i - 1];
                Platform.runLater(()->{tela.inserir_vetor_aux(cont);});
                Thread.sleep(100);
                
                tela.pintar_vet_aux("#a9520c", i); //Laranja
                tela.pintar_vet_aux("#6A006D", i - 1); //Roxo
                Thread.sleep(TEMPO);
                
                tela.pintar_vet_aux("#287171", i); //Azul
                tela.pintar_vet_aux("#287171", i - 1); //Azul
            }
            
            for (i = 0; i < tl; i++) {
                tela.exibir_vetor_res_pos(true, i);
                Thread.sleep(TEMPO - 450);
            }
            
            for (i = 0; i < tl; i++) {
                res[cont[vet[i]] - 1] = vet[i];
                Platform.runLater(()->{
                        tela.inserir_numero_aux(vet[i], cont[vet[i]]); 
                        tela.inserir_vetor_res(res);
                });
                Thread.sleep(100);
                
                tela.pintar_vet_res("#a9520c", cont[vet[i] - 1]); //Laranja
                tela.pintar_vet_aux("#a9520c", vet[i] - 1); //Laranja
                tela.pintar_vet_original("#a9520c", i); //Laranja});
                
                Thread.sleep(TEMPO);
                tela.pintar_vet_res("#287171", cont[vet[i] - 1]); //Azul
                tela.pintar_vet_aux("#287171", vet[i] - 1); //Azul
                tela.pintar_vet_original("#287171", i); //Azul
                
                --cont[vet[i]];
            }
            
        }catch(Exception er){}
        
    }
}
