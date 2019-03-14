package parte1po.algoritmos;

import javafx.application.Platform;
import parte1po.TelaPrincipalController;

/**
 *
 * @author Henrique Krupck
 */
public class GnomeSort implements Runnable{
    private int[] vet;
    private int tl;
    private TelaPrincipalController tela;
    private static final int TEMPO = 800;
    private int aux;
    private int gnome;

    public GnomeSort(int[] vet, TelaPrincipalController tela) {
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
            gnome = 0;
            tela.pintar_vet_original("#6A006D", gnome);
            tela.lblAux_setVisible(true);
            tela.exibir_vetor_aux_pos(true, 1);
            Platform.runLater(()->{tela.setLegenda(true, "Gnome:", "Gnome + 1:");});
            Thread.sleep(TEMPO);
            
            
            while(gnome < tl - 1)
            {
                if(vet[gnome] <= vet[gnome + 1])
                {
                    tela.pintar_vet_original("#6A006D", gnome); //Roxo
                    tela.pintar_vet_original("#a9520c", gnome + 1); //Laranja
                    Thread.sleep(TEMPO);
                    tela.pintar_vet_original("#287171", gnome); //Azul
                    tela.pintar_vet_original("#6A006D", gnome + 1); //Roxo
                    Thread.sleep(TEMPO);
                    gnome++;
                }
                else
                {
                    Thread.sleep(300);
                    tela.pintar_vet_original("#6A006D", gnome); //Roxo
                    tela.pintar_vet_original("#a9520c", gnome + 1); //Laranja
                    Thread.sleep(TEMPO);
                    
                    aux = vet[gnome];
                    Platform.runLater(()->{tela.inserir_numero_aux(1, aux);}); Thread.sleep(TEMPO);
                    vet[gnome] = vet[gnome + 1];
                    Platform.runLater(()->{tela.inserir_numero_original(gnome, vet[gnome + 1]);
                                           tela.inserir_numero_original(gnome + 1, aux);
                    }); Thread.sleep(TEMPO);
                    vet[gnome + 1] = aux;
                    
                    tela.pintar_vet_original("#287171", gnome); //Azul
                    tela.pintar_vet_original("#287171", gnome + 1); //Azul
                    tela.pintar_vet_original("#6A006D", gnome - 1); //Roxo
                    if(gnome > 0)
                    {
                        gnome--;
                    }
                }
            }
            
            
            tela.lblAux_setVisible(false);
            tela.exibir_vetor_aux_pos(false, 1);
            Platform.runLater(()->{tela.setLegenda(false, "", "");});
        }catch(Exception er){}
    }
}
