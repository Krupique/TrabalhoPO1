package parte1po.algoritmos;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import parte1po.TelaPrincipalController;
import utils.Desenhar;

/**
 *
 * @author Henrique Krupck
 */
public class TimSort implements Runnable{
    private int[] vet;
    private int tl;
    private TelaPrincipalController tela;
    GraphicsContext contexto;
    private int interv = 30;
    private double cores[][];
    private int larg;
    private static final int TEMPO = 5;

    public TimSort(int[] vet, TelaPrincipalController tela, double cores[][], GraphicsContext contexto, int larg) {
        this.vet = vet;
        this.tela = tela;
        this.tl = vet.length;
        this.cores = cores;
        this.larg = larg;
        this.contexto = contexto;
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
    
    public void insercao_direta(int[] vet, int esq, int dir)
    {
        try
        {
            int aux, pos;

            for (int i = esq; i < dir; i++) {
                aux = vet[i];
                pos = i;

                while(pos > esq && aux < vet[pos - 1]){
                    Desenhar.desenhar(pos * 2, 0, larg, 360, contexto, Color.BLACK);
                    Desenhar.desenhar(pos * 2, 360 - vet[pos - 1], larg, vet[pos - 1], contexto, new Color(cores[vet[pos - 1]][0], cores[vet[pos - 1]][1], cores[vet[pos - 1]][2], 1));
                    Thread.sleep(TEMPO + 5);
                    vet[pos] = vet[pos - 1];
                    pos--;
                }
                vet[pos] = aux;
                Desenhar.desenhar(pos * 2, 0, larg, 360, contexto, Color.BLACK);
                Desenhar.desenhar(pos * 2, 360 - vet[pos], larg, vet[pos], contexto, new Color(cores[vet[pos]][0], cores[vet[pos]][1], cores[vet[pos]][2], 1));
                Thread.sleep(TEMPO + 5);
            }
        }catch(Exception er){}
    }
    
	
    public void merge_rec(int[] aux, int esq, int dir)
    {
        int meio;
        if(esq < dir)
        {
            meio = (esq + dir)/2;
            merge_rec(aux, esq, meio);
            merge_rec(aux, meio + 1, dir);
            fusao(aux, esq, meio, meio + 1, dir);
        }
    }
    
    private void fusao(int[] aux, int ini1, int fim1, int ini2, int fim2)
    {
        try
        {
            int k = 0, i = ini1, j = ini2;
            while(i <= fim1 && j <= fim2)
                aux[k++] = (vet[i] < vet[j]) ? vet[i++] : vet[j++];

            while(i <= fim1)
                aux[k++] = vet[i++];

            while(j <= fim2)
                aux[k++] = vet[j++];


            for (int l = 0; l < k; l++) {
                vet[l + ini1] = aux[l];
                Desenhar.desenhar((l + ini1) * 2, 0, larg, 360, contexto, Color.BLACK);
                Desenhar.desenhar((l + ini1) * 2, 360 - vet[l + ini1], larg, vet[l + ini1], contexto, new Color(cores[vet[l + ini1]][0], cores[vet[l + ini1]][1], cores[vet[l + ini1]][2], 1));

                Thread.sleep(TEMPO);
            }
            
        }catch(Exception er){}
    }
    
    public void sort()
    {
        try
        {
            int[] aux = new int[tl];
        
            for (int i = 0; i < tl; i+=interv)
                insercao_direta(vet, i, i + interv);
            Thread.sleep(TEMPO);

            while(interv < tl)
            {
                if(2 * interv > tl)
                    interv = tl;
                else
                    interv+=interv;

                for (int i = 0; i < tl; i+=interv) {
                    if(interv + i < tl)
                        merge_rec(aux, i, i + interv - 1);
                    else
                        merge_rec(aux, i, tl - 1);
                }
            }
        }catch(Exception er){}
    }
}
