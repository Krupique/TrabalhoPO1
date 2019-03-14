package parte1po;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import parte1po.algoritmos.BucketSort;
import parte1po.algoritmos.CombSort;
import parte1po.algoritmos.CountingSort;
import parte1po.algoritmos.GnomeSort;
import parte1po.algoritmos.RadixSort;
import parte1po.algoritmos.TimSort;
import utils.Desenhar;
import utils.GeradorVetor;
import utils.Util;

/**
 *
 * @author Henrique Krupck
 */
public class TelaPrincipalController implements Initializable {
    
    @FXML
    private Label lblAlgoritmo;
    @FXML
    private JFXButton bt0;
    @FXML
    private JFXButton bt1;
    @FXML
    private JFXButton bt2;
    @FXML
    private JFXButton bt3;
    @FXML
    private JFXButton bt4;
    @FXML
    private JFXButton bt5;
    @FXML
    private JFXButton bt6;
    @FXML
    private JFXButton bt7;
    @FXML
    private JFXButton bt8;
    @FXML
    private JFXButton bt9;
    @FXML
    private JFXButton bt10;
    @FXML
    private JFXButton bt11;
    @FXML
    private JFXComboBox<String> cbAlgoritmo;
    @FXML
    private JFXButton btGerarVetor;
    @FXML
    private JFXButton btCancelar;
    @FXML
    private JFXButton brSair;
    @FXML
    private JFXButton btAux0;
    @FXML
    private JFXButton btAux1;
    @FXML
    private JFXButton btAux2;
    @FXML
    private JFXButton btAux3;
    @FXML
    private JFXButton btAux4;
    @FXML
    private JFXButton btAux5;
    @FXML
    private JFXButton btAux6;
    @FXML
    private JFXButton btAux7;
    @FXML
    private JFXButton btAux8;
    @FXML
    private JFXButton btAux9;
    @FXML
    private JFXButton btAux10;
    @FXML
    private JFXButton btAux11;
    @FXML
    private JFXButton btRes0;
    @FXML
    private JFXButton btRes1;
    @FXML
    private JFXButton btRes2;
    @FXML
    private JFXButton btRes3;
    @FXML
    private JFXButton btRes4;
    @FXML
    private JFXButton btRes5;
    @FXML
    private JFXButton btRes6;
    @FXML
    private JFXButton btRes7;
    @FXML
    private JFXButton btRes8;
    @FXML
    private JFXButton btRes9;
    @FXML
    private JFXButton btRes10;
    @FXML
    private JFXButton btRes11;
    @FXML
    private JFXButton btIniciar;
    @FXML
    private Label lblAuxiliar;
    @FXML
    private Label lblLegenda;
    @FXML
    private Label lblLegendaRoxa;
    @FXML
    private Label lblLegendaLaranja;
    @FXML
    private JFXButton btLegendaLaranja;
    @FXML
    private JFXButton btLegendaRoxa;
    @FXML
    private JFXButton btBucket0;
    @FXML
    private JFXButton btBucket3;
    @FXML
    private JFXButton btBucket2;
    @FXML
    private JFXButton btBucket1;
    @FXML
    private Label lblExpoente;
    @FXML
    private Canvas canvas;
    
    private static final int LARG = 2;
    private GraphicsContext contexto;
    private int[]vetor;
    private int[]vetTim;
    private boolean flagVet = false;
    Thread th = new Thread();
    private double[][] vetCores;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        init_CB();
        inicializa();
        vetor = new int[12];
        vetCores = Util.getVetColors();
        vetTim = new int[360];
    }   
    
    public void inicializa()
    {
        lblAux_setVisible(false);
        lblAlgoritmo.setText("");
        limpar_botoes_principal();
        limpar_botoes_aux();
        limpar_botoes_res();
        exibir_botoes_aux(false);
        exibir_botoes_res(false);
        pintar_todo_vet_original("#287171");
        flagVet = false;
        setLegenda(false, "", "");
        exibir_bucket(false);
        setExpoente(false, "");
        setCanvas(false);
    }
    
    
    public void setExpoente(boolean value, String text)
    {
        lblExpoente.setVisible(value);
        lblExpoente.setText(text);
    }
    
    @FXML
    private void evtGerarVetor(ActionEvent event) {
        
        if(cbAlgoritmo.getSelectionModel().getSelectedIndex() == 2)
            vetor = GeradorVetor.gerar_radix(12);
        else if(cbAlgoritmo.getSelectionModel().getSelectedIndex() == 0)
            vetor = GeradorVetor.gerar_counting(12);
        else if(cbAlgoritmo.getSelectionModel().getSelectedIndex() == 5)
        {
            vetor = GeradorVetor.gerar_comb(12);
            vetTim = GeradorVetor.gerar_comb(360);
            Desenhar.limpar(contexto, Color.BLACK);
            for (int i = 0, j = 0; i < vetTim.length; i++, j += 2)
                Desenhar.desenhar(j, 360 - (vetTim[i] * 1), LARG, vetTim[i] * 2, contexto, new Color(vetCores[vetTim[i]][0], vetCores[vetTim[i]][1], vetCores[vetTim[i]][2], 1));
        }
        else
            vetor = GeradorVetor.gerar_comb(12);
        inserir_vetor_bt(vetor);
        flagVet = true;
    }

    @FXML
    private void evtCancelar(ActionEvent event) {
        cbAlgoritmo.getSelectionModel().select(-1);
        inicializa();
        th.interrupt();
    }

    @FXML
    private void evtSair(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    private void evtIniciar(ActionEvent event) {
        if(cbAlgoritmo.getSelectionModel().getSelectedIndex() != -1)
        {
            if(flagVet)
                call_sort();
            else
            {
                Alert a = new Alert(Alert.AlertType.WARNING, "Ih parça, deu ruim! Você precisa gerar um vetor!", ButtonType.OK);
                a.showAndWait();
            }
                   
        }
        else
        {
            Alert a = new Alert(Alert.AlertType.WARNING, "Ih parça, deu ruim! Você precisa selecionar algum algoritmo!", ButtonType.OK);
            a.showAndWait();
        }
    }
    
    public void call_sort()
    {
        switch(cbAlgoritmo.getSelectionModel().getSelectedIndex())
        {
            case 0: //Counting Sort
                th = new Thread(new CountingSort(vetor, this));
                th.start();
            break;
            
            case 1: //Bucket Sort
                th = new Thread(new BucketSort(vetor, this));
                th.start();
            break;
            
            case 2: //Radix Sort
                th = new Thread(new RadixSort(vetor, this));
                th.start();
            break;
            
            case 3: //Comb Sort
                th = new Thread(new CombSort(vetor, this));
                th.start();
            break;
            
            case 4: //Gnome Sort
                th = new Thread(new GnomeSort(vetor, this));
                th.start();
            break;
            
            case 5: //Tim Sort
                th = new Thread(new TimSort(vetTim, this, vetCores, contexto, LARG));
                th.start();
            break;
        } 
    }
    
    public void setCanvas(boolean value)
    {
        canvas.setVisible(value);
    }
    
    public void lblAux_setVisible(boolean value)
    {
        lblAuxiliar.setVisible(value);
    }

    @FXML
    private void evtSelecionarAlgoritmo(ActionEvent event) {
        inicializa();
        lblAlgoritmo.setText(cbAlgoritmo.getSelectionModel().getSelectedItem());
        if(cbAlgoritmo.getSelectionModel().getSelectedIndex() == 5)
        {
            setCanvas(true);
            contexto = canvas.getGraphicsContext2D();
            Desenhar.desenhar(0, 0, 720, 360, contexto, Color.BLACK);
        }
        else
            setCanvas(false);
    }
    
    public void init_CB()
    {
        cbAlgoritmo.getItems().add("Counting Sort");
        cbAlgoritmo.getItems().add("Bucket Sort");
        cbAlgoritmo.getItems().add("Radix Sort");
        cbAlgoritmo.getItems().add("Comb Sort");
        cbAlgoritmo.getItems().add("Gnome Sort");
        cbAlgoritmo.getItems().add("Tim Sort");
    }

    public void setLegenda(boolean value, String textRx, String textLj)
    {
        lblLegenda.setVisible(value);
        lblLegendaLaranja.setVisible(value);
        lblLegendaRoxa.setVisible(value);
        
        lblLegendaRoxa.setText(textRx);
        lblLegendaLaranja.setText(textLj);
        
        btLegendaRoxa.setVisible(value);
        btLegendaLaranja.setVisible(value);
    }
    
    public void limpar_botoes_principal()
    {
        bt0.setText(" ");
        bt1.setText(" ");
        bt2.setText(" ");
        bt3.setText(" ");
        bt4.setText(" ");
        bt5.setText(" ");
        bt6.setText(" ");
        bt7.setText(" ");
        bt8.setText(" ");
        bt9.setText(" ");
        bt10.setText(" ");
        bt11.setText(" ");
    }
    
    public void limpar_botoes_aux()
    {
        btAux0.setText(" ");
        btAux1.setText(" ");
        btAux2.setText(" ");
        btAux3.setText(" ");
        btAux4.setText(" ");
        btAux5.setText(" ");
        btAux6.setText(" ");
        btAux7.setText(" ");
        btAux8.setText(" ");
        btAux9.setText(" ");
        btAux10.setText(" ");
        btAux11.setText(" ");
    }
    
    public void limpar_botoes_res()
    {
        btRes0.setText(" ");
        btRes1.setText(" ");
        btRes2.setText(" ");
        btRes3.setText(" ");
        btRes4.setText(" ");
        btRes5.setText(" ");
        btRes6.setText(" ");
        btRes7.setText(" ");
        btRes8.setText(" ");
        btRes9.setText(" ");
        btRes10.setText(" ");
        btRes11.setText(" ");
    }
    
    public void exibir_botoes_aux(boolean value)
    {
        btAux0.setVisible(value);
        btAux1.setVisible(value);
        btAux2.setVisible(value);
        btAux3.setVisible(value);
        btAux4.setVisible(value);
        btAux5.setVisible(value);
        btAux6.setVisible(value);
        btAux7.setVisible(value);
        btAux8.setVisible(value);
        btAux9.setVisible(value);
        btAux10.setVisible(value);
        btAux11.setVisible(value);
    }
    
    public void exibir_botoes_res(boolean value)
    {
        btRes0.setVisible(value);
        btRes1.setVisible(value);
        btRes2.setVisible(value);
        btRes3.setVisible(value);
        btRes4.setVisible(value);
        btRes5.setVisible(value);
        btRes6.setVisible(value);
        btRes7.setVisible(value);
        btRes8.setVisible(value);
        btRes9.setVisible(value);
        btRes10.setVisible(value);
        btRes11.setVisible(value);
    }
    
    public void inserir_vetor_bt(int[] vet)
    {
        bt0.setText("" + vet[0]);
        bt1.setText("" + vet[1]);
        bt2.setText("" + vet[2]);
        bt3.setText("" + vet[3]);
        bt4.setText("" + vet[4]);
        bt5.setText("" + vet[5]);
        bt6.setText("" + vet[6]);
        bt7.setText("" + vet[7]);
        bt8.setText("" + vet[8]);
        bt9.setText("" + vet[9]);
        bt10.setText("" + vet[10]);
        bt11.setText("" + vet[11]);
    }
    
    public void inserir_vetor_aux(int[] vet)
    {
        btAux0.setText("" + vet[0]);
        btAux1.setText("" + vet[1]);
        btAux2.setText("" + vet[2]);
        btAux3.setText("" + vet[3]);
        btAux4.setText("" + vet[4]);
        btAux5.setText("" + vet[5]);
        btAux6.setText("" + vet[6]);
        btAux7.setText("" + vet[7]);
        btAux8.setText("" + vet[8]);
        btAux9.setText("" + vet[9]);
        btAux10.setText("" + vet[10]);
        btAux11.setText("" + vet[11]);
    }
    
    public void inserir_vetor_res(int[] vet)
    {
        btRes0.setText("" + vet[0]);
        btRes1.setText("" + vet[1]);
        btRes2.setText("" + vet[2]);
        btRes3.setText("" + vet[3]);
        btRes4.setText("" + vet[4]);
        btRes5.setText("" + vet[5]);
        btRes6.setText("" + vet[6]);
        btRes7.setText("" + vet[7]);
        btRes8.setText("" + vet[8]);
        btRes9.setText("" + vet[9]);
        btRes10.setText("" + vet[10]);
        btRes11.setText("" + vet[11]);
    }
    
    public void exibir_vetor_original_pos(boolean value, int pos)
    {
        if(pos == 0)
            bt0.setVisible(value);
        else if(pos == 1)
            bt1.setVisible(value);
        else if(pos == 2)
            bt2.setVisible(value);
        else if(pos == 3)
            bt3.setVisible(value);
        else if(pos == 4)
            bt4.setVisible(value);
        else if(pos == 5)
            bt5.setVisible(value);
        else if(pos == 6)
            bt6.setVisible(value);
        else if(pos == 7)
            bt7.setVisible(value);
        else if(pos == 8)
            bt8.setVisible(value);
        else if(pos == 9)
            bt9.setVisible(value);
        else if(pos == 10)
            bt10.setVisible(value);
        else if(pos == 11)
            bt11.setVisible(value);
    }
    
    
    public void exibir_vetor_aux_pos(boolean value, int pos)
    {
        if(pos == 0)
            btAux0.setVisible(value);
        else if(pos == 1)
            btAux1.setVisible(value);
        else if(pos == 2)
            btAux2.setVisible(value);
        else if(pos == 3)
            btAux3.setVisible(value);
        else if(pos == 4)
            btAux4.setVisible(value);
        else if(pos == 5)
            btAux5.setVisible(value);
        else if(pos == 6)
            btAux6.setVisible(value);
        else if(pos == 7)
            btAux7.setVisible(value);
        else if(pos == 8)
            btAux8.setVisible(value);
        else if(pos == 9)
            btAux9.setVisible(value);
        else if(pos == 10)
            btAux10.setVisible(value);
        else if(pos == 11)
            btAux11.setVisible(value);
    }
    
    public void exibir_vetor_res_pos(boolean value, int pos)
    {
        if(pos == 0)
            btRes0.setVisible(value);
        else if(pos == 1)
            btRes1.setVisible(value);
        else if(pos == 2)
            btRes2.setVisible(value);
        else if(pos == 3)
            btRes3.setVisible(value);
        else if(pos == 4)
            btRes4.setVisible(value);
        else if(pos == 5)
            btRes5.setVisible(value);
        else if(pos == 6)
            btRes6.setVisible(value);
        else if(pos == 7)
            btRes7.setVisible(value);
        else if(pos == 8)
            btRes8.setVisible(value);
        else if(pos == 9)
            btRes9.setVisible(value);
        else if(pos == 10)
            btRes10.setVisible(value);
        else if(pos == 11)
            btRes11.setVisible(value);
    }
    
    public void inserir_numero_aux(int pos, int num)
    {
        if(pos == 0)
            btAux0.setText("" + num);
        else if(pos == 1)
            btAux1.setText("" + num);
        else if(pos == 2)
            btAux2.setText("" + num);
        else if(pos == 3)
            btAux3.setText("" + num);
        else if(pos == 4)
            btAux4.setText("" + num);
        else if(pos == 5)
            btAux5.setText("" + num);
        else if(pos == 6)
            btAux6.setText("" + num);
        else if(pos == 7)
            btAux7.setText("" + num);
        else if(pos == 8)
            btAux8.setText("" + num);
        else if(pos == 9)
            btAux9.setText("" + num);
        else if(pos == 10)
            btAux10.setText("" + num);
        else if(pos == 11)
            btAux11.setText("" + num);
        
        //System.out.println("pos: " + pos);
    }
    
    public void inserir_numero_res(int pos, int num)
    {
        if(pos == 0)
            btRes0.setText("" + num);
        else if(pos == 1)
            btRes1.setText("" + num);
        else if(pos == 2)
            btRes2.setText("" + num);
        else if(pos == 3)
            btRes3.setText("" + num);
        else if(pos == 4)
            btRes4.setText("" + num);
        else if(pos == 5)
            btRes5.setText("" + num);
        else if(pos == 6)
            btRes6.setText("" + num);
        else if(pos == 7)
            btRes7.setText("" + num);
        else if(pos == 8)
            btRes8.setText("" + num);
        else if(pos == 9)
            btRes9.setText("" + num);
        else if(pos == 10)
            btRes10.setText("" + num);
        else if(pos == 11)
            btRes11.setText("" + num);
    }
    
    public void inserir_numero_original(int pos, int num)
    {
        if(pos == 0)
            bt0.setText("" + num);
        else if(pos == 1)
            bt1.setText("" + num);
        else if(pos == 2)
            bt2.setText("" + num);
        else if(pos == 3)
            bt3.setText("" + num);
        else if(pos == 4)
            bt4.setText("" + num);
        else if(pos == 5)
            bt5.setText("" + num);
        else if(pos == 6)
            bt6.setText("" + num);
        else if(pos == 7)
            bt7.setText("" + num);
        else if(pos == 8)
            bt8.setText("" + num);
        else if(pos == 9)
            bt9.setText("" + num);
        else if(pos == 10)
            bt10.setText("" + num);
        else if(pos == 11)
            bt11.setText("" + num);
    }
    
    public void pintar_vet_original(String cor, int pos)
    {
        if(pos == 0)
            bt0.setStyle("-fx-background-color:" + cor);
        else if(pos == 1)
            bt1.setStyle("-fx-background-color:" + cor);
        else if(pos == 2)
            bt2.setStyle("-fx-background-color:" + cor);
        else if(pos == 3)
            bt3.setStyle("-fx-background-color:" + cor);
        else if(pos == 4)
            bt4.setStyle("-fx-background-color:" + cor);
        else if(pos == 5)
            bt5.setStyle("-fx-background-color:" + cor);
        else if(pos == 6)
            bt6.setStyle("-fx-background-color:" + cor);
        else if(pos == 7)
            bt7.setStyle("-fx-background-color:" + cor);
        else if(pos == 8)
            bt8.setStyle("-fx-background-color:" + cor);
        else if(pos == 9)
            bt9.setStyle("-fx-background-color:" + cor);
        else if(pos == 10)
            bt10.setStyle("-fx-background-color:" + cor);
        else if(pos == 11)
            bt11.setStyle("-fx-background-color:" + cor);
    }
    
    public void pintar_vet_aux(String cor, int pos)
    {
        if(pos == 0)
            btAux0.setStyle("-fx-background-color:" + cor);
        else if(pos == 1)
            btAux1.setStyle("-fx-background-color:" + cor);
        else if(pos == 2)
            btAux2.setStyle("-fx-background-color:" + cor);
        else if(pos == 3)
            btAux3.setStyle("-fx-background-color:" + cor);
        else if(pos == 4)
            btAux4.setStyle("-fx-background-color:" + cor);
        else if(pos == 5)
            btAux5.setStyle("-fx-background-color:" + cor);
        else if(pos == 6)
            btAux6.setStyle("-fx-background-color:" + cor);
        else if(pos == 7)
            btAux7.setStyle("-fx-background-color:" + cor);
        else if(pos == 8)
            btAux8.setStyle("-fx-background-color:" + cor);
        else if(pos == 9)
            btAux9.setStyle("-fx-background-color:" + cor);
        else if(pos == 10)
            btAux10.setStyle("-fx-background-color:" + cor);
        else if(pos == 11)
            btAux11.setStyle("-fx-background-color:" + cor);
    }
    
    public void pintar_vet_res(String cor, int pos)
    {
        if(pos == 0)
            btRes0.setStyle("-fx-background-color:" + cor);
        else if(pos == 1)
            btRes1.setStyle("-fx-background-color:" + cor);
        else if(pos == 2)
            btRes2.setStyle("-fx-background-color:" + cor);
        else if(pos == 3)
            btRes3.setStyle("-fx-background-color:" + cor);
        else if(pos == 4)
            btRes4.setStyle("-fx-background-color:" + cor);
        else if(pos == 5)
            btRes5.setStyle("-fx-background-color:" + cor);
        else if(pos == 6)
            btRes6.setStyle("-fx-background-color:" + cor);
        else if(pos == 7)
            btRes7.setStyle("-fx-background-color:" + cor);
        else if(pos == 8)
            btRes8.setStyle("-fx-background-color:" + cor);
        else if(pos == 9)
            btRes9.setStyle("-fx-background-color:" + cor);
        else if(pos == 10)
            btRes10.setStyle("-fx-background-color:" + cor);
        else if(pos == 11)
            btRes11.setStyle("-fx-background-color:" + cor);
    }
    
    public void pintar_todo_vet_original(String cor)
    {
        bt0.setStyle("-fx-background-color:" + cor);
        bt1.setStyle("-fx-background-color:" + cor);
        bt2.setStyle("-fx-background-color:" + cor);
        bt3.setStyle("-fx-background-color:" + cor);
        bt4.setStyle("-fx-background-color:" + cor);
        bt5.setStyle("-fx-background-color:" + cor);
        bt6.setStyle("-fx-background-color:" + cor);
        bt7.setStyle("-fx-background-color:" + cor);
        bt8.setStyle("-fx-background-color:" + cor);
        bt9.setStyle("-fx-background-color:" + cor);
        bt10.setStyle("-fx-background-color:" + cor);
        bt11.setStyle("-fx-background-color:" + cor);
    }
    
    public void exibir_bucket_pos(boolean value, int pos)
    {
        if(pos == 0)
            btBucket0.setVisible(value);
        else if(pos == 1)
            btBucket1.setVisible(value);
        else if(pos == 2)
            btBucket2.setVisible(value);
        else if(pos == 3)
            btBucket3.setVisible(value);
    }
    
    public void inserir_bucket_final(String text, int pos)
    {
        if(pos == 0)
            btBucket0.setText(btBucket0.getText() + "   " + text);
        else if(pos == 1)
            btBucket1.setText(btBucket1.getText() + "   " + text);
        else if(pos == 2)
            btBucket2.setText(btBucket2.getText() + "   " + text);
        else if(pos == 3)
            btBucket3.setText(btBucket3.getText() + "   " + text);
    }
    
    public void inserir_bucket(String text, int pos)
    {
        if(pos == 0)
            btBucket0.setText(text);
        else if(pos == 1)
            btBucket1.setText(text);
        else if(pos == 2)
            btBucket2.setText(text);
        else if(pos == 3)
            btBucket3.setText(text);
    }
    
    public void exibir_bucket(boolean value)
    {
        btBucket0.setVisible(value);
        btBucket1.setVisible(value);
        btBucket2.setVisible(value);
        btBucket3.setVisible(value);
    }
    
    public void pintar_vet_bucket(String cor, int pos)
    {
        if(pos == 0)
            btBucket0.setStyle("-fx-background-color:" + cor);
        else if(pos == 1)
            btBucket1.setStyle("-fx-background-color:" + cor);
        else if(pos == 2)
            btBucket2.setStyle("-fx-background-color:" + cor);
        else if(pos == 3)
            btBucket3.setStyle("-fx-background-color:" + cor);
    }
}
