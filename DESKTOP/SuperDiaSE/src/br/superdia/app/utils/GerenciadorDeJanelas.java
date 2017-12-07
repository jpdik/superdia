package br.superdia.app.utils;

import java.io.IOException;
import java.util.ArrayList;

import br.com.modelo.Usuario;
import br.superdia.controle.CaixaController;
import br.superdia.controle.LoginController;
import br.superdia.controle.PagamentoController;
import br.superdia.enumeracoes.Tela;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GerenciadorDeJanelas {

	private Stage primaryStage;
	private Scene loginScene, caixaScene, pagamentoScene;
	private LoginController loginController;
	private CaixaController caixaController;
	private PagamentoController pagamentoController;	
	private Usuario usuarioLogado;
	private ArrayList<OnChangeScreen> listeners;
	
	public GerenciadorDeJanelas(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.listeners = new ArrayList<>();
	}
	
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public Scene getLoginScene() {
		return loginScene;
	}

	public Scene getCaixaScene() {
		return caixaScene;
	}

	public Scene getPagamentoScene() {
		return pagamentoScene;
	}

	public LoginController getLoginController() {
		return loginController;
	}

	public CaixaController getCaixaController() {
		return caixaController;
	}

	public PagamentoController getPagamentoController() {
		return pagamentoController;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public ArrayList<OnChangeScreen> getListeners() {
		return listeners;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
	
	public void changeScreen(String screen, Object userData) {
		switch (screen) {		
		case "login":
			primaryStage.setScene(loginScene);
			notifyAllListeners(Tela.LOGIN.getTela(), userData);
			break;
		case "caixa":
			primaryStage.setScene(caixaScene);
			notifyAllListeners(Tela.CAIXA.getTela(), userData);
			break;
		case "pagamento":
			primaryStage.setScene(pagamentoScene);
			notifyAllListeners(Tela.PAGAMENTO.getTela(), userData);
			pagamentoController.getValorRecebidoTextField().requestFocus();
			break;
		}
	}
		
	public void changeScreen(String screen) {
		changeScreen(screen, null);
	}
	
	public void janelasApp() {		
		try {
			FXMLLoader loginFXMLLoader = new FXMLLoader(getClass().getResource("/br/superdia/views/Login.fxml"));
			Parent fxmlLogin = loginFXMLLoader.load();
			loginScene = new Scene(fxmlLogin,350,350);
			loginScene.getStylesheets().add(getClass().getResource("/br/superdia/views/css/Login.css").toExternalForm());
			loginController = (LoginController) loginFXMLLoader.getController();
			
			FXMLLoader caixaFXMLLoader = new FXMLLoader(getClass().getResource("/br/superdia/views/Caixa.fxml"));
			Parent fxmlCaixa = caixaFXMLLoader.load();
			caixaScene = new Scene(fxmlCaixa);
			caixaScene.getStylesheets().add(getClass().getResource("/br/superdia/views/css/Caixa.css").toExternalForm());
			caixaController = (CaixaController) caixaFXMLLoader.getController();
			
			FXMLLoader pagamentoFXMLLoader = new FXMLLoader(getClass().getResource("/br/superdia/views/Pagamento.fxml"));
			Parent fxmlPagamento = pagamentoFXMLLoader.load();
			pagamentoScene = new Scene(fxmlPagamento);
			pagamentoScene.getStylesheets().add(getClass().getResource("/br/superdia/views/css/Pagamento.css").toExternalForm());
			pagamentoController = (PagamentoController) pagamentoFXMLLoader.getController();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		
	}	
	
	public void addOnChangeScreenListener(OnChangeScreen newListener) {
		listeners.add(newListener);
	}
	
	private void notifyAllListeners(String newScreen, Object userData) {
		for(OnChangeScreen trocaTela : listeners)
			trocaTela.onScreenChanged(newScreen, userData);		
	}
}
