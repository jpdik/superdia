package br.superdia.controle;

import br.com.interfacebean.ICarrinho;
import br.com.modelo.ItemVenda;
import br.superdia.app.App;
import br.superdia.enumeracoes.Tela;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

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
	    private ICarrinho iCarrinho = null;
		
	    public PagamentoController() {
			primaryStage = App.getPrimaryStage();
	    }

		@FXML
	    private void initialize() {
	    	App.addOnChangeScreenListener(new App.OnChangeScreen() {				
				@Override
				public void onScreenChanged(String newScreen, Object userData) {
					System.out.println("Pagamento Nova tela:" + newScreen + ", " + userData);
					iCarrinho = (ICarrinho) userData;
				}
			});	    	
		}
	    
		private String trocaVirgulaPorPonto(String valor) {
			return valor.replace(',', '.');
		}
		
		private void alertMessage(String titulo, String header, String conteudo, AlertType alertType) {
			Alert alert = new Alert(alertType);
    		alert.setTitle(titulo);
    		alert.setHeaderText(header);
    		alert.setContentText(conteudo);
    		alert.getModality();    		
    		alert.showAndWait();
		}
				
		private void calculaTroco() {
			Double valorRecebido, valorCompra;
			try {
				valorRecebido = Double.parseDouble(trocaVirgulaPorPonto(valorRecebidoTextField.getText()));
				valorCompra = Double.parseDouble(valorCompraTextField.getText());
				
				if(valorRecebido < valorCompra) {
					alertMessage("ERRO", "Valor Menor", "O valor recebido e menor que o valor da compra.", AlertType.ERROR);
	    			System.out.println("exibir mensagem de erro informando que o valor recebido é menor do que o valor da compra");
	    		}else {
	    			Double troco = valorRecebido - valorCompra;
	    			trocoTextField.setText(String.format("%.2f", troco));
	    		}
				
				System.out.println(valorRecebido);
			} catch (NumberFormatException e) {
				alertMessage("ERRO", "Valor Recebido.", e.getMessage(), AlertType.ERROR);
				System.err.println("Aqui: " + e.getMessage());
			}
	    	
		}		
		
		private void limpaCampos() {
			numeroCartaoTextField.clear();
	    	valorRecebidoTextField.clear();
	    	valorCompraTextField.clear();
	    	trocoTextField.clear();
		}
		
	    @FXML
	    void concluirButtonOnAction() {
	    	//calculaTroco();
	    	if(trocoTextField.getText().isEmpty()) {
	    		alertMessage("ERRO", "Troco", "Sistema não calculou o troco.", AlertType.ERROR);
	    	}else {
	    		limpaCampos();
	    		//Alterando campos da janela caixa
	    		App.caixaController.getListTabelaVendas().clear();
	    		App.caixaController.getValorTotalCompraTextField().clear();
	    		App.caixaController.getTabelaVendas().setItems(App.caixaController.getListTabelaVendas());	    		
	    		App.caixaController.getListTabelaEstoque().clear();
	    		App.caixaController.getTabelaEstoque().setItems(App.caixaController.getListTabelaEstoque());
	    		
	    		
	    		
	    		alertMessage("Finalizado", null, "Compra concluida com SUCESSO.", AlertType.INFORMATION);	    		
	    		
	    		System.out.println("Usuario Logado: " + App.usuarioLogado);
	    		for (ItemVenda iterable_element : iCarrinho.listaTodos()) {
					System.out.println("No Pagamento Item Venda: " + iterable_element.getProduto().getNome());
				}
	    		
	    		iCarrinho.finalizaCompra(App.usuarioLogado);
	    		
	    		App.caixaController.atualizarOnMouseClicked();
	    		App.changeScreen(Tela.CAIXA.getTela());
				primaryStage = App.getPrimaryStage();
				primaryStage.setTitle("Caixa");
				primaryStage.centerOnScreen();				
	    	}
	    }
	    
	    @FXML
	    void cancelarButtonOnAction() {
	    	System.out.println("Clicou em CANCELAR");
	    	App.changeScreen(Tela.CAIXA.getTela());
			primaryStage = App.getPrimaryStage();
			primaryStage.setTitle("Caixa");
			primaryStage.centerOnScreen();
			limpaCampos();
	    }

	    @FXML
	    void trocoOnMouseCliked() {
	    	calculaTroco();
	    }
	    
	    @FXML
	    void valorRecebidoOnKeyPressed(KeyEvent event) {
	    	if(event.getCode() == KeyCode.ENTER) {
	    		calculaTroco();
	    	}
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

