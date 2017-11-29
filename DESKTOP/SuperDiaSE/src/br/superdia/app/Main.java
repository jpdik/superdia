package br.superdia.app;
	
import java.io.IOException;
import java.util.ArrayList;

import br.com.modelo.Usuario;
import br.superdia.enumeracoes.Tela;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;


public class Main extends Application {
	
	private static Stage primaryStage;
	private static Scene loginScene;
	private static Scene caixaScene;
	private static Scene pagamentoScene;
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
			/*sceneOnKeyPressed(loginScene);*/
			break;
		case "caixa":
			primaryStage.setScene(caixaScene);
			notifyAllListeners(Tela.CAIXA.getTela(), userData);
			/*sceneOnKeyPressed(caixaScene);*/
			break;
		case "pagamento":
			primaryStage.setScene(pagamentoScene);
			notifyAllListeners(Tela.PAGAMENTO.getTela(), userData);
			/*sceneOnKeyPressed(pagamentoScene);*/
			break;
		}
	}
	
	public static void sceneOnKeyPressed(Scene scene) {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				System.out.println("TEste: " + event.getCode());
			}
		});
	}
	
	public static void changeScreen(String screen) {
		changeScreen(screen, null);
	}
	
	private void janelasApp() {		
		try {
			Parent fxmlLogin = FXMLLoader.load(getClass().getResource("/br/superdia/views/Login.fxml"));
			loginScene = new Scene(fxmlLogin,350,350);
			loginScene.getStylesheets().add(getClass().getResource("/br/superdia/views/css/Login.css").toExternalForm());
			
			Parent fxmlCaixa = FXMLLoader.load(getClass().getResource("/br/superdia/views/Caixa.fxml"));
			caixaScene = new Scene(fxmlCaixa);
			caixaScene.getStylesheets().add(getClass().getResource("/br/superdia/views/css/Caixa.css").toExternalForm());
			
			Parent fxmlPagamento = FXMLLoader.load(getClass().getResource("/br/superdia/views/Pagamento.fxml"));
			pagamentoScene = new Scene(fxmlPagamento);
			pagamentoScene.getStylesheets().add(getClass().getResource("/br/superdia/views/css/Pagamento.css").toExternalForm());
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
	
	//------------------------------
	
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
