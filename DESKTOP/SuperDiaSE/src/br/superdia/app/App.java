package br.superdia.app;
	
import java.io.IOException;
import java.util.ArrayList;

import br.com.modelo.Usuario;
import br.superdia.controle.CaixaController;
import br.superdia.controle.LoginController;
import br.superdia.controle.PagamentoController;
import br.superdia.enumeracoes.Tela;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class App extends Application {
	
	public static Stage primaryStage;
	public static Scene loginScene, caixaScene, pagamentoScene;
	public static LoginController loginController;
	public static CaixaController caixaController;
	public static PagamentoController pagamentoController;
	
	public static Usuario usuarioLogado;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			this.primaryStage = primaryStage;
			janelasApp();		
			
			primaryStage.setScene(loginScene);
			primaryStage.setTitle("Autenticação - SuperDia");
			primaryStage.setResizable(false);
			primaryStage.centerOnScreen();
			primaryStage.show();			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}	
	
	public static void changeScreen(String screen, Object userData) {
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
			break;
		}
	}
		
	public static void changeScreen(String screen) {
		changeScreen(screen, null);
	}
	
	private void janelasApp() {		
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
	
	public static void main(String[] args) {
		launch(args);
	}

	public static Stage getPrimaryStage() {
		return primaryStage;
	}
	
	//-----------------------------
	
	private static ArrayList<OnChangeScreen> listeners = new ArrayList<>();
	
	public static interface OnChangeScreen{
		void onScreenChanged(String newScreen, Object userData);
	}
	
	public static void addOnChangeScreenListener(OnChangeScreen newListener) {
		listeners.add(newListener);		
	}
	
	private static void notifyAllListeners(String newScreen, Object userData) {
		for(OnChangeScreen trocaTela : listeners)
			trocaTela.onScreenChanged(newScreen, userData);		
	}
}
