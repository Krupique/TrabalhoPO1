package parte1po.algoritmos;

import javafx.application.Platform;
import parte1po.TelaPrincipalController;
import utils.Util;

/**
 *
 * @author Henrique Krupck
 */
public class RadixSort implements Runnable{
    private int[] vet;
    private int tl;
    private TelaPrincipalController tela;
    private static final int TEMPO = 300;
    private int aux;
    private int i;

    public RadixSort(int[] vet, TelaPrincipalController tela) {
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
            int maior = Util.getMaiorElem(vet);
            tela.lblAux_setVisible(true);
            tela.exibir_vetor_aux_pos(true, 1);
            Platform.runLater(()->{tela.setLegenda(true, "Pos i", "Pos i + 1"); tela.setExpoente(true, "Expoente: -");});
            Thread.sleep(TEMPO);
            
            for (int i = 1; maior/i > 0; i *= 10) {
                bubble_sort(vet, tl, i);
            }
        }catch(Exception er){}
    }
    
    private void bubble_sort(int vet[], int n, int exp)
    {
        try
        {
            Platform.runLater(()->{tela.setExpoente(true, "Expoente: " + exp);});
            Thread.sleep(5);
            
            for(int tl1 = n; tl1 > 0; tl1--)
            {
                for(i = 0; i < tl1 - 1; i++)
                {
                    tela.pintar_vet_original("#6A006D", i); //Roxo
                    tela.pintar_vet_original("#a9520c", i + 1); //Laranja
                    Thread.sleep(TEMPO);
                    
                    if((vet[i]/exp)%10 > (vet[i + 1]/exp)%10)
                    {
                        aux = vet[i];
                        Platform.runLater(()->{tela.inserir_numero_aux(1, aux);}); Thread.sleep(TEMPO);
                        vet[i] = vet[i + 1];
                        Platform.runLater(()->{
                            tela.inserir_numero_original(i, vet[i + 1]);
                            tela.inserir_numero_original(i + 1, aux);
                        }); Thread.sleep(TEMPO);
                        vet[i + 1] = aux;
                    }
                    tela.pintar_vet_original("#287171", i); //Azul
                    tela.pintar_vet_original("#287171", i + 1); //Azul
                }
            }
        }catch(Exception er){}
            
    }
}
