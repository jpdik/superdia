package br.superdia.controle;

import java.io.IOException;

import br.superdia.app.Main;
import br.superdia.enumeracoes.Tela;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

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

	    private Stage primaryStage;
	    private Scene scene;
		private Alert alert;
	    
		
		
	    public PagamentoController() {
			primaryStage = Main.getPrimaryStage();
	    	System.err.println("PagamentoController --> construtor default: " + Main.usuarioLogado);
	    }

		@FXML
	    private void initialize() {

	    	System.err.println("PagamentoController --> initialize: " + Main.usuarioLogado);
			alert = new Alert(null);
	    	Main.addOnChangeScreenListener(new Main.OnChangeScreen() {				
				@Override
				public void onScreenChanged(String newScreen, Object userData) {
					System.out.println("Pagamento Nova tela:" + newScreen + ", " + userData);
					
				}
			});
	    	
		}
	    
	    @FXML
	    void concluirButtonOnAction() {
	    	System.out.println("Clicou em concluir");
	    	
	    	numeroCartaoTextField.clear();
	    	valorRecebidoTextField.clear();
	    	valorCompraTextField.clear();
	    	trocoTextField.clear();	
	    	
	    	alert.setAlertType(AlertType.INFORMATION);
			alert.setTitle("Sucesso");
			alert.setHeaderText("Finalizar Compra.");
			alert.setContentText("Compra finalizada com SUCESSO!");
			alert.show();
			
			/*limpar os campos da janela caixa*/
			
			
			Main.changeScreen(Tela.CAIXA.getTela());
			primaryStage.setTitle("Caixa");
			primaryStage.centerOnScreen();
	    }
	    
	    @FXML
	    void cancelarButtonOnAction() {
	    	System.out.println("Clicou em CANCELAR");
	    	Main.changeScreen(Tela.CAIXA.getTela());
			primaryStage = Main.getPrimaryStage();
			primaryStage.setTitle("Caixa");
			primaryStage.centerOnScreen();
	    }
	    	    
	    @FXML
	    void concluirOnKeyPressed(KeyEvent event) {
	    	
	    	System.out.println("Concluir --> keyEvent.getCode(): " + event.getCode());
	    }

	   	public TextField getValorCompraTextField() {
			return valorCompraTextField;
		}

		public void setValorCompraTextField(TextField valorCompraTextField) {
			this.valorCompraTextField = valorCompraTextField;
		}

		public TextField getNumeroCartaoTextField() {
			return numeroCartaoTextField;
		}

		public void setNumeroCartaoTextField(TextField numeroCartaoTextField) {
			this.numeroCartaoTextField = numeroCartaoTextField;
		}

		public Button getConcluirButton() {
			return concluirButton;
		}

		public void setConcluirButton(Button concluirButton) {
			this.concluirButton = concluirButton;
		}

		public Button getCancelarButton() {
			return cancelarButton;
		}

		public void setCancelarButton(Button cancelarButton) {
			this.cancelarButton = cancelarButton;
		}

		public TextField getTrocoTextField() {
			return trocoTextField;
		}

		public void setTrocoTextField(TextField trocoTextField) {
			this.trocoTextField = trocoTextField;
		}

		public TextField getValorRecebidoTextField() {
			return valorRecebidoTextField;
		}

		public void setValorRecebidoTextField(TextField valorRecebidoTextField) {
			this.valorRecebidoTextField = valorRecebidoTextField;
		}

		public Stage getPrimaryStage() {
			return primaryStage;
		}

		public void setPrimaryStage(Stage primaryStage) {
			this.primaryStage = primaryStage;
		}
	    
	    
}

