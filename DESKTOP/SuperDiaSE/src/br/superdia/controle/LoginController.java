package br.superdia.controle;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.com.interfacebean.IAutentica;
import br.com.modelo.Usuario;
import br.superdia.app.App;
import br.superdia.enumeracoes.Tela;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginController {
	@FXML
    private AnchorPane janelaLoginAnchorPane;

    @FXML
    private PasswordField senhaPasswordField;

    @FXML
    private Button entrarButton;

    @FXML
    private Button cancelarButton;

    @FXML
    private TextField usuarioTextField;
    
    private InitialContext ic;
    private IAutentica iAutentica = null; 
	private Stage primaryStage;
		
	public LoginController() {}

	@FXML
    private void initialize() {
		App.addOnChangeScreenListener(new App.OnChangeScreen() {			
			@Override
			public void onScreenChanged(String newScreen, Object userData) {
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
    protected void entrarButtonOnAction() {
    	fazerLogin();
    }
    
    @FXML
    protected void cancelarButtonOnAction() {
    	limparCampos();
    }
    
    @FXML
    protected void entrarOnKeyPressed(KeyEvent event) {
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
    protected void cancelarOnKeyPressed(KeyEvent event) {
    	if(event.getCode() == KeyCode.ENTER) {
			limparCampos();
    	}else if(event.getCode() == KeyCode.ESCAPE) {
    		entrarButton.requestFocus();
    	}
    }
    
    @FXML
    protected void usuarioOnKeyPressed(KeyEvent event){
    	if(event.getCode() == KeyCode.ENTER) {
			entrarButton.requestFocus();
    	}else if(event.getCode() == KeyCode.ESCAPE) {
    		cancelarButton.requestFocus();
    	}
    }
    
    @FXML
    protected void senhaOnKeyPressed(KeyEvent event){
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
    	System.out.println(usuario);
    	if(usuario == null) {
    		alertMessage("Erro - Autenticação", "Usuário Inexistente.", null, AlertType.ERROR);
    	}else if(usuario.getPerfil().equalsIgnoreCase("Caixa")) {
    		System.out.println("Ir para o caixa realizar a compra");
    		App.usuarioLogado = usuario;
    		App.changeScreen(Tela.CAIXA.getTela());
    		App.caixaController.getOperadorTextField().setText(usuario.getUsuario());
    		App.caixaController.getTabelaEstoque().requestFocus();
			primaryStage = App.getPrimaryStage();
			primaryStage.setTitle("Caixa");
			primaryStage.centerOnScreen();				
    	}else {
    		alertMessage("Erro - Autenticação", "Perfil Inválido.",
    				"ERRO: O usuário de perfil (" + usuario.getPerfil() + ") "
    				+ "NÃO pode ter acesso ao sistema.", AlertType.ERROR);
    	} 
    }

	public AnchorPane getJanelaLoginAnchorPane() {
		return janelaLoginAnchorPane;
	}

	public void setJanelaLoginAnchorPane(AnchorPane janelaLoginAnchorPane) {
		this.janelaLoginAnchorPane = janelaLoginAnchorPane;
	}

	public PasswordField getSenhaPasswordField() {
		return senhaPasswordField;
	}

	public void setSenhaPasswordField(PasswordField senhaPasswordField) {
		this.senhaPasswordField = senhaPasswordField;
	}

	public Button getEntrarButton() {
		return entrarButton;
	}

	public void setEntrarButton(Button entrarButton) {
		this.entrarButton = entrarButton;
	}

	public Button getCancelarButton() {
		return cancelarButton;
	}

	public void setCancelarButton(Button cancelarButton) {
		this.cancelarButton = cancelarButton;
	}

	public TextField getUsuarioTextField() {
		return usuarioTextField;
	}

	public void setUsuarioTextField(TextField usuarioTextField) {
		this.usuarioTextField = usuarioTextField;
	}

	public InitialContext getIc() {
		return ic;
	}

	public void setIc(InitialContext ic) {
		this.ic = ic;
	}

	public IAutentica getiAutentica() {
		return iAutentica;
	}

	public void setiAutentica(IAutentica iAutentica) {
		this.iAutentica = iAutentica;
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
}