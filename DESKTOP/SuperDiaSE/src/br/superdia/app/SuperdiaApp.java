package br.superdia.app;
	
import br.superdia.app.utils.GerenciadorDeJanelas;
import javafx.application.Application;
import javafx.stage.Stage;


public class SuperdiaApp extends Application {
	
	public static GerenciadorDeJanelas gerenciadorDeJanelas;	
	private Stage primaryStage;	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			SuperdiaApp.gerenciadorDeJanelas = new GerenciadorDeJanelas(primaryStage);
			this.primaryStage = gerenciadorDeJanelas.getPrimaryStage();
			gerenciadorDeJanelas.janelasApp();
			
			primaryStage.setScene(gerenciadorDeJanelas.getLoginScene());
			primaryStage.setTitle("Autenticação - SuperDia");
			primaryStage.setResizable(false);
			primaryStage.centerOnScreen();
			primaryStage.show();			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}	
	
	public static void main(String[] args) {
		launch(args);
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static GerenciadorDeJanelas getGerenciadorDeJanelas() {
		return gerenciadorDeJanelas;
	}	
}
