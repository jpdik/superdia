package br.superdia.controle;

import br.com.interfacebean.ICarrinho;
import br.com.modelo.ItemVenda;
import br.superdia.app.App;
import br.superdia.app.OnChangeScreen;
import br.superdia.enumeracoes.Tela;
import javafx.fxml.FXML;
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
    	primaryStage = App.gerenciadorDeJanelas.getPrimaryStage();
    }

	@FXML
    private void initialize() {
		App.gerenciadorDeJanelas.addOnChangeScreenListener(new OnChangeScreen() {
			
			@Override
			public void onScreenChanged(String newScreen, Object userData) {
				System.out.println("Pagamento Nova tela:" + newScreen + ", " + userData);
				iCarrinho = (ICarrinho) userData;				
			}
		});		   	
	}
	
    @FXML
    void concluirButtonOnAction() {
    	concluirCompra();
    }
    
    @FXML
    void cancelarButtonOnAction() {
    	cancelarCompra();
    }

    @FXML
    void trocoOnMouseCliked() {
    	calculaTroco();    	
    }
    
    @FXML
    void valorRecebidoOnKeyPressed(KeyEvent event) {
    	if(event.getCode() == KeyCode.ENTER) {
    		calculaTroco();    		
    	}else if(event.getCode() == KeyCode.ESCAPE) {
    		cancelarButton.requestFocus();
    	}
    }
    
    @FXML
    void cancelarOnKeyPressed(KeyEvent event) {
    	if(event.getCode() == KeyCode.ENTER) {
    		cancelarCompra();	
    	}else if(event.getCode() == KeyCode.ESCAPE) {
    		valorRecebidoTextField.requestFocus();
    	}
    }
    @FXML
    void concluirOnKeyPressed(KeyEvent event) {
    	if(event.getCode() == KeyCode.ENTER) {
    		concluirCompra();
    		System.out.println("Apertou ENTER no botao concluir");
    	}else if(event.getCode() == KeyCode.ESCAPE) {
    		valorRecebidoTextField.requestFocus();
    	}
    }
    
    private void cancelarCompra() {
    	App.gerenciadorDeJanelas.changeScreen(Tela.CAIXA.getTela());
    	primaryStage = App.gerenciadorDeJanelas.getPrimaryStage();
    	
		primaryStage.setTitle("Caixa");
		primaryStage.centerOnScreen();
		limpaCampos();
		App.gerenciadorDeJanelas.getCaixaController().getTabelaVendas().requestFocus();		
    }

    private void concluirCompra() {
    	if(trocoTextField.getText().isEmpty()) {
    		alertMessage("ERRO", "Troco", "Sistema não calculou o troco.", AlertType.ERROR);
    	}else {
    		limpaCampos();
    		//Alterando campos da janela caixa
    		App.gerenciadorDeJanelas.getCaixaController().getListTabelaVendas().clear();
    		App.gerenciadorDeJanelas.getCaixaController().getValorTotalCompraTextField().clear();
    		App.gerenciadorDeJanelas.getCaixaController().getTabelaVendas().setItems(App.gerenciadorDeJanelas.getCaixaController().getListTabelaVendas());	    		
    		App.gerenciadorDeJanelas.getCaixaController().getListTabelaEstoque().clear();
    		App.gerenciadorDeJanelas.getCaixaController().getTabelaEstoque().setItems(App.gerenciadorDeJanelas.getCaixaController().getListTabelaEstoque());	 
    		App.gerenciadorDeJanelas.getCaixaController().getListTabelaVendas().clear();
    	    		
    		alertMessage("Finalizado", null, "Compra concluida com SUCESSO.", AlertType.INFORMATION);	    		
    		
    		System.out.println("Usuario Logado: " + App.gerenciadorDeJanelas.getUsuarioLogado());
    		for (ItemVenda iterable_element : iCarrinho.listaTodos()) {
				System.out.println("No Pagamento Item Venda: " + iterable_element.getProduto().getNome());
			}
    		
    		iCarrinho.finalizaCompra(App.gerenciadorDeJanelas.getUsuarioLogado());
    		
    		App.gerenciadorDeJanelas.getCaixaController().atualizarOnMouseClicked();
    		App.gerenciadorDeJanelas.changeScreen(Tela.CAIXA.getTela());
			primaryStage = App.gerenciadorDeJanelas.getPrimaryStage();
			primaryStage.setTitle("Caixa");
			primaryStage.centerOnScreen();
    	}    	
    }
    
    private String trocaVirgulaPorPonto(String valor) {
		return valor.replace(',', '.');
	}
	
	private void alertMessage(String titulo, String header, String conteudo, AlertType alertType) {
		App.gerenciadorDeJanelas.getLoginController().alertMessage(titulo, header, conteudo, alertType);
	}
			
	private void calculaTroco() {
		Double valorRecebido, valorCompra;
		try {
			valorRecebido = Double.parseDouble(trocaVirgulaPorPonto(valorRecebidoTextField.getText()));
			valorCompra = App.gerenciadorDeJanelas.getCaixaController().atualizaValorTotalCompra();
			
			if(valorRecebido < valorCompra) {
				alertMessage("ERRO", "Valor Menor", "O valor recebido e menor que o valor da compra.", AlertType.ERROR);
				valorRecebidoTextField.requestFocus();
    		}else {
    			Double troco = valorRecebido - valorCompra;
    			trocoTextField.setText("R$ " + String.format("%.2f", troco));
    			concluirButton.requestFocus();
    		}
			
		} catch (NumberFormatException e) {
			alertMessage("ERRO", "Valor Recebido.", e.getMessage(), AlertType.ERROR);
			valorRecebidoTextField.requestFocus();
			System.err.println("Classe PagamentoController método calculaTroco: " + e.getMessage());
		}
    	
	}		
	
	private void limpaCampos() {
		numeroCartaoTextField.clear();
    	valorRecebidoTextField.clear();
    	valorCompraTextField.clear();
    	trocoTextField.clear();
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

	public ICarrinho getiCarrinho() {
		return iCarrinho;
	}	
}

