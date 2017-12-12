package br.superdia.controle;

import br.com.interfacebean.ICarrinho;
import br.superdia.app.SuperdiaApp;
import br.superdia.app.utils.GerenciadorDeJanelas;
import br.superdia.app.utils.OnChangeScreen;
import br.superdia.enumeracoes.Tela;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
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
    
    @FXML
    private Pane trocoPane;

    @FXML
    private Pane valorPane;
    
    @FXML
    private AnchorPane cabecacalhoAnchorPane;
    
    @FXML
    private AnchorPane rodapeAnchorPane;

    private ICarrinho iCarrinho = null;
    private Stage primaryStage;
    private GerenciadorDeJanelas gerenciadorDeJanelas;
    private CaixaController caixaController;
	
    public PagamentoController() {
    	gerenciadorDeJanelas = SuperdiaApp.getGerenciadorDeJanelas();
    	primaryStage = gerenciadorDeJanelas.getPrimaryStage();    	
    	caixaController = gerenciadorDeJanelas.getCaixaController();
    }
    	
	public TextField getValorCompraTextField() {
		return valorCompraTextField;
	}

	public TextField getNumeroCartaoTextField() {
		return numeroCartaoTextField;
	}

	public Button getConcluirButton() {
		return concluirButton;
	}

	public Button getCancelarButton() {
		return cancelarButton;
	}

	public TextField getTrocoTextField() {
		return trocoTextField;
	}

	public TextField getValorRecebidoTextField() {
		return valorRecebidoTextField;
	}

	public Pane getTrocoPane() {
		return trocoPane;
	}

	public Pane getValorPane() {
		return valorPane;
	}

	public AnchorPane getCabecacalhoAnchorPane() {
		return cabecacalhoAnchorPane;
	}

	public AnchorPane getRodapeAnchorPane() {
		return rodapeAnchorPane;
	}

	public ICarrinho getiCarrinho() {
		return iCarrinho;
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public GerenciadorDeJanelas getGerenciadorDeJanelas() {
		return gerenciadorDeJanelas;
	}

	public CaixaController getCaixaController() {
		return caixaController;
	}

	@FXML
    private void initialize() {
		gerenciadorDeJanelas.addOnChangeScreenListener(new OnChangeScreen() {
			@Override
			public void onScreenChanged(String newScreen, Object userData) {
				if(newScreen.equals(Tela.PAGAMENTO.getTela())) {
					System.out.println("Pagamento Nova tela:" + newScreen + ", " + userData);
					iCarrinho = (ICarrinho) userData;
				}
			}
		});		   	
	}
	
    @FXML
    private void concluirButtonOnAction() {
    	concluirCompra();
    }
    
    @FXML
    private void cancelarButtonOnAction() {
    	cancelarCompra();
    }

    @FXML
    private void trocoOnMouseCliked() {
    	calculaTroco();    	
    }
    
    @FXML
    private void valorRecebidoOnKeyPressed(KeyEvent event) {
    	if(event.getCode() == KeyCode.ENTER) {
    		calculaTroco();    		
    	}else if(event.getCode() == KeyCode.ESCAPE) {
    		cancelarButton.requestFocus();
    	}
    }
    
    @FXML
    private void cancelarOnKeyPressed(KeyEvent event) {
    	if(event.getCode() == KeyCode.ENTER) {
    		cancelarCompra();	
    	}else if(event.getCode() == KeyCode.ESCAPE) {
    		valorRecebidoTextField.requestFocus();
    	}
    }
    
    @FXML
    private void concluirOnKeyPressed(KeyEvent event) {
    	if(event.getCode() == KeyCode.ENTER) {
    		concluirCompra();
    	}else if(event.getCode() == KeyCode.ESCAPE) {
    		valorRecebidoTextField.requestFocus();
    	}
    }
    
    private void cancelarCompra() {
    	gerenciadorDeJanelas.changeScreen(Tela.CAIXA.getTela());
    	caixaController.getTabelaVendas().requestFocus();
		primaryStage.setTitle("Caixa");
		primaryStage.centerOnScreen();
		limpaCampos();
    }

    private void concluirCompra() {
    	if(trocoTextField.getText().isEmpty()) {
    		alertMessage("ERRO", "Troco", "Sistema não calculou o troco.", AlertType.ERROR);
    	}else {
    		limpaCampos();
    		//Alterando campos da janela caixa
    		caixaController.getListTabelaVendas().clear();
    		caixaController.getValorTotalCompraTextField().clear();
    		caixaController.getTabelaVendas().setItems(caixaController.getListTabelaVendas());	    		
    		caixaController.getListTabelaEstoque().clear();
    		caixaController.getTabelaEstoque().setItems(caixaController.getListTabelaEstoque());	 
    		caixaController.getListTabelaVendas().clear();
    	    		
    		alertMessage("Finalizado", null, "Compra concluida com SUCESSO.", AlertType.INFORMATION);	    		
    		
    		iCarrinho.finalizaCompra(gerenciadorDeJanelas.getUsuarioLogado());
    		
    		caixaController.atualizarOnMouseClicked();
    		gerenciadorDeJanelas.changeScreen(Tela.CAIXA.getTela());
			primaryStage = gerenciadorDeJanelas.getPrimaryStage();
			primaryStage.setTitle("Caixa");
			primaryStage.centerOnScreen();
    	}    	
    }
    
    private String trocaVirgulaPorPonto(String valor) {
		return valor.replace(',', '.');
	}
	
	private void alertMessage(String titulo, String header, String conteudo, AlertType alertType) {
		gerenciadorDeJanelas.getLoginController().alertMessage(titulo, header, conteudo, alertType);
	}
			
	private void calculaTroco() {
		Double valorRecebido, valorCompra;
		try {
			valorRecebido = Double.parseDouble(trocaVirgulaPorPonto(valorRecebidoTextField.getText()));
			valorCompra = caixaController.atualizaValorTotalCompra();
			
			if(valorRecebido < valorCompra) {
				alertMessage("ERRO", "Valor Menor", "O valor recebido e menor que o valor da compra.", 
							AlertType.ERROR);
				valorRecebidoTextField.requestFocus();
    		}else {
    			Double troco = valorRecebido - valorCompra;
    			trocoTextField.setText("R$ " + String.format("%.2f", troco));
    			concluirButton.requestFocus();
    		}
			
		} catch (NumberFormatException e) {
			alertMessage("ERRO", "Valor Recebido.", e.getMessage(), AlertType.ERROR);
			valorRecebidoTextField.requestFocus();
		}
    	
	}		
	
	private void limpaCampos() {
		numeroCartaoTextField.clear();
    	valorRecebidoTextField.clear();
    	valorCompraTextField.clear();
    	trocoTextField.clear();
	}
}