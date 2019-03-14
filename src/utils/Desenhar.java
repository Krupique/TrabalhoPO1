package utils;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
/**
 *
 * @author Henrique K.
 */

/*
    Função desenhar.
    x = Desenha a COLUNA a partir da posição. Esquerda para direita.
    y = Desenha a LINHA a partir da posição. Cima para baixo.
    largura = Largura da coluna.
    altura = Altura da coluna.
    contexto = Contexto canvas (É a tela onde será desenhado).
    cor = cor no formato Color. Para selecionar uma cor desejada use new Color(r, g, b, opacidade).
*/
public class Desenhar {
    
    public static void desenhar(int x, int y, int largura, int altura, GraphicsContext contexto, Color cor)
    {
        contexto.setFill(cor);
        contexto.fillRect(x, y, largura, altura); 
    }
    
    public static void limpar(GraphicsContext contexto, Color cor)
    {
        contexto.setFill(cor);
        contexto.fillRect(0, 0, contexto.getCanvas().getWidth(), contexto.getCanvas().getHeight()); 
    }
}
