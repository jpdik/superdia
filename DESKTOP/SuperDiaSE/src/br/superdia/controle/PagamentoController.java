package br.superdia.controle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class PagamentoController {
	 @FXML
	    private TextField valorCompraTextField;

	    @FXML
	    private TextField numeroCartaoTextField;

	    @FXML
	    private Button concluirButton;

	    @FXML
	    private Button cancelarButton;

	    @FXML
	    private TextField trocoTextField;

	    @FXML
	    private TextField valorRecebidoTextField;

	    @FXML
	    void concluirButtonOnAction() {
	    	System.out.println("Clicou em concluir");
	    }
	    
	    @FXML
	    void cancelarButtonOnAction() {
	    	System.out.println("Clicou em CANCELAR");
	    }

	    @FXML
	    void concluirOnKeyPressed(KeyEvent event) {
	    	System.out.println("Pressionou a tecla: " + event.getText());
	    }	   

	    @FXML
	    void cancelarOnKeyPressed(KeyEvent event) {
	    	System.out.println("Pressionou a tecla: " + event.getText());
	    }
}

