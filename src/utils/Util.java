/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author krupc
 */
public class Util {
    public static int getMaiorElem(int[] vet)
    {
        int elem = 0;
        for (int i = 0; i < vet.length; i++) {
            if(elem < vet[i])
                elem = vet[i];
        }
        return elem;
    }
    
    //Função responsável por criar uma matriz de 360 cores RGB.
    //Pega todas as cores máximas no formato HSV (saturaçãoe e intensidade máximas) e converte para RGB.
    public static double[][] getVetColors()
    {
        double vet[][] = new double[360][3];
        double var_h, var_i, H, S, V, var_1, var_2, var_3;
        H = 0;
        S = V = 1;
        for(int i = 0; i < 360; i++)
        {
            var_h = H * 6;
            if ( var_h >= 6 ) 
                var_h = 0;
            var_i = (int)var_h;
            var_1 = V * ( 1 - S );
            var_2 = V * ( 1 - S * ( var_h - var_i ) );
            var_3 = V * ( 1 - S * ( 1 - ( var_h - var_i ) ) );

            if ( var_i == 0 ) { 
                vet[i][0] = V; 
                vet[i][1] = var_3 ; 
                vet[i][2] = var_1;
            } else if ( var_i == 1 ) {
                vet[i][0] = var_2; 
                vet[i][1] = V;
                vet[i][2] = var_1;
            } else if ( var_i == 2 ) {
                vet[i][0] = var_1;
                vet[i][1] = V;
                vet[i][2] = var_3; 
            } else if ( var_i == 3 ) {
                vet[i][0] = var_1;
                vet[i][1] = var_2;
                vet[i][2] = V;
            }
            else if ( var_i == 4 ) {
                vet[i][0] = var_3;
                vet[i][1] = var_1;
                vet[i][2] = V;
            }
            else{
                vet[i][0] = V;
                vet[i][1] = var_1;
                vet[i][2] = var_2;
            }
            H += 0.0027778;
        }
        return vet;
    }
}
