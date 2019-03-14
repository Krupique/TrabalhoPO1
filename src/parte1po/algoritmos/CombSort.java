package parte1po.algoritmos;

import javafx.application.Platform;
import parte1po.TelaPrincipalController;

/**
 *
 * @author Henrique Krupck
 */
public class CombSort implements Runnable{
    private int[] vet;
    private int tl;
    private TelaPrincipalController tela;
    private static final int TEMPO = 500;
    private int aux;
    private int interv;
    private int i;

    public CombSort(int[] vet, TelaPrincipalController tela) {
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
            
            interv = getProxInterv(tl);
            boolean troca = true;
            tela.lblAux_setVisible(true);
            tela.exibir_vetor_aux_pos(true, 1);
            Platform.runLater(()->{tela.setLegenda(true, "i:", "i + interv:");});
            Thread.sleep(TEMPO);
            
            while(interv != 1 || troca == true)
            {
                interv = getProxInterv(interv);
                troca = false;
                
                for (i = 0; i < tl - interv; i++) {
                    tela.pintar_vet_original("#6A006D", i); //Roxo
                    tela.pintar_vet_original("#a9520c", i + interv); //Laranja
                    Thread.sleep(TEMPO);
                    
                    if(vet[i] > vet[i + interv])
                    {
                        aux = vet[i];
                        Platform.runLater(()->{tela.inserir_numero_aux(1, aux);}); 
                        Thread.sleep(TEMPO);
                        vet[i] = vet[i + interv];
                        Platform.runLater(()->{
                            tela.inserir_numero_original(i, vet[i + interv]);
                            tela.inserir_numero_original(i + interv, aux);
                        }); Thread.sleep(TEMPO);
                        vet[i + interv] = aux;
                        troca = true;
                    }
                    tela.pintar_vet_original("#287171", i); //Azul
                    tela.pintar_vet_original("#287171", i + interv); //Azul
                }
            }
            
            tela.lblAux_setVisible(false);
            tela.exibir_vetor_aux_pos(false, 1);
            Platform.runLater(()->{tela.setLegenda(false, "", "");});
        }catch(Exception er){}
    }
    
    
    public int getProxInterv(int interv)
    {
        interv = (interv *10) / 13;
        if(interv < 1)
            return 1;
        return interv;
    }
}
