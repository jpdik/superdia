package br.superdia.controle;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.com.interfacebean.IAutentica;
import br.com.modelo.Usuario;
import br.superdia.app.SuperdiaApp;
import br.superdia.app.utils.GerenciadorDeJanelas;
import br.superdia.app.utils.OnChangeScreen;
import br.superdia.enumeracoes.Tela;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginController {
	@FXML
    private AnchorPane janelaLoginAnchorPane;

    @FXML
    private PasswordField senhaPasswordField;

    @FXML
    private Button entrarButton, cancelarButton;

    @FXML
    private TextField usuarioTextField;
    
    private InitialContext ic;
    private IAutentica iAutentica = null; 
    private Stage primaryStage;
    private GerenciadorDeJanelas gerenciadorDeJanelas;
		
	public LoginController() {
		gerenciadorDeJanelas = SuperdiaApp.getGerenciadorDeJanelas();
		primaryStage = gerenciadorDeJanelas.getPrimaryStage();
	}
	
	public AnchorPane getJanelaLoginAnchorPane() {
		return janelaLoginAnchorPane;
	}

	public PasswordField getSenhaPasswordField() {
		return senhaPasswordField;
	}

	public Button getEntrarButton() {
		return entrarButton;
	}

	public Button getCancelarButton() {
		return cancelarButton;
	}

	public TextField getUsuarioTextField() {
		return usuarioTextField;
	}

	public InitialContext getIc() {
		return ic;
	}

	public IAutentica getiAutentica() {
		return iAutentica;
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public GerenciadorDeJanelas getGerenciadorDeJanelas() {
		return gerenciadorDeJanelas;
	}

	@FXML
    private void initialize() {		
		gerenciadorDeJanelas.addOnChangeScreenListener(new OnChangeScreen() {			
			@Override
			public void onScreenChanged(String newScreen, Object userData) {
				if(newScreen.equals(Tela.LOGIN.getTela()))
					System.out.println("Tela Login: " + newScreen + ", " + userData);				
			}
		});		
		
    	try {
    		ic = new InitialContext();
    		iAutentica = (IAutentica) ic.lookup("br.com.interfacebean.IAutentica");
    	} catch (NamingException e) {
    		System.err.println(e.getMessage());
    		System.exit(0);
    	}
	}
	
    @FXML
    private void entrarButtonOnAction() {
    	fazerLogin();
    }
    
    @FXML
    private void cancelarButtonOnAction() {
    	limparCampos();
    }
    
    @FXML
    private void entrarOnKeyPressed(KeyEvent event) {
    	if(event.getCode() == KeyCode.ENTER) {
    		fazerLogin();
    	}else if(event.getCode() == KeyCode.ESCAPE) {
    		cancelarButton.requestFocus();
    	}else if(event.getCode() == KeyCode.S) {
    		senhaPasswordField.requestFocus();
    	}else if(event.getCode() == KeyCode.U) {
    		usuarioTextField.requestFocus();
    	}
    }
    
    @FXML
    private void cancelarOnKeyPressed(KeyEvent event) {
    	if(event.getCode() == KeyCode.ENTER) {
			limparCampos();
    	}else if(event.getCode() == KeyCode.ESCAPE) {
    		entrarButton.requestFocus();
    	}
    }
    
    @FXML
    private void usuarioOnKeyPressed(KeyEvent event){
    	if(event.getCode() == KeyCode.ENTER) {
			entrarButton.requestFocus();
    	}else if(event.getCode() == KeyCode.ESCAPE) {
    		cancelarButton.requestFocus();
    	}
    }
    
    @FXML
    private void senhaOnKeyPressed(KeyEvent event){
    	if(event.getCode() == KeyCode.ENTER) {
			entrarButton.requestFocus();
    	}else if(event.getCode() == KeyCode.ESCAPE) {
    		cancelarButton.requestFocus();
    	}
    }
	
	protected void alertMessage(String titulo, String header, String conteudo, AlertType alertType) {
		Alert alert = new Alert(alertType);
		alert.setTitle(titulo);
		alert.setHeaderText(header);
		alert.setContentText(conteudo);
		alert.getModality();    		
		alert.showAndWait();
	}
    
    private void limparCampos() {
    	usuarioTextField.clear();
    	senhaPasswordField.clear();
    }
    
    private void fazerLogin() {
    	Usuario usuario = new Usuario();
    	usuario.setUsuario(usuarioTextField.getText());
    	usuario.setSenha(senhaPasswordField.getText());
    	usuario = iAutentica.autentica(usuario);
    	if(usuario == null) {
    		alertMessage("Erro - Autenticação", "Usuário Inexistente.", null, AlertType.ERROR);
    	}else if(usuario.getPerfil().equalsIgnoreCase("Caixa")) {
    		gerenciadorDeJanelas.setUsuarioLogado(usuario);
    		gerenciadorDeJanelas.changeScreen(Tela.CAIXA.getTela());
    		gerenciadorDeJanelas.getCaixaController().getOperadorTextField().setText(usuario.getUsuario());
    		gerenciadorDeJanelas.getCaixaController().getTabelaEstoque().requestFocus();
			primaryStage.setTitle("Caixa");
			primaryStage.centerOnScreen();				
    	}else {
    		alertMessage("Erro - Autenticação", "Perfil Inválido.",
    				"ERRO: O usuário de perfil (" + usuario.getPerfil() + ") "
    				+ "NÃO pode ter acesso ao sistema.", AlertType.ERROR);
    	} 
    }
}