package br.superdia.controle;

import java.io.IOException;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.com.interfacebean.IAutentica;
import br.com.modelo.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
    private FXMLLoader janelaCaixaFXMLLoader;
	private Parent janelaCaixaParent;
	private Scene janelaCaixaScene;
	private Stage primaryStage;
	private CaixaController caixaController;
	private Alert alert;
	
	@FXML
    private void initialize() {
		alert = new Alert(null);
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
    	Usuario usuario = new Usuario();
    	usuario.setUsuario(usuarioTextField.getText());
    	usuario.setSenha(senhaPasswordField.getText());
    	usuario = iAutentica.autentica(usuario);
    	System.out.println(usuario);
    	if(usuario == null) {    		
    		alert.setAlertType(AlertType.ERROR);
    		alert.setTitle("Erro - Autenticação");
    		alert.setHeaderText("Usuário Inexistente.");
    		alert.show();
    	}else if(usuario.getPerfil().equalsIgnoreCase("Caixa")) {
    		System.out.println("Ir para o caixa realizar a compra");
    		try {
				janelaCaixaFXMLLoader = new FXMLLoader(getClass().getResource("/br/superdia/views/Caixa.fxml"));
				janelaCaixaParent = (Parent) janelaCaixaFXMLLoader.load();
				janelaCaixaScene = new Scene(janelaCaixaParent);
				janelaCaixaScene.getStylesheets().add(getClass().getResource("/br/superdia/views/css/Caixa.css").toExternalForm());
				
				caixaController = (CaixaController) janelaCaixaFXMLLoader.getController();
				caixaController.getOperadorTextField().setText(usuario.getUsuario());
				
				primaryStage = (Stage) usuarioTextField.getScene().getWindow();
				primaryStage.setScene(janelaCaixaScene);
				primaryStage.setTitle("Caixa");
				primaryStage.centerOnScreen();
				primaryStage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}    		
    	}else {
    		alert.setAlertType(AlertType.ERROR);
    		alert.setTitle("Erro - Autenticação");
    		alert.setHeaderText("Perfil Inválido.");
    		alert.setContentText("ERRO: O usuário de perfil (" + usuario.getPerfil() + ") NÃO pode ter acesso ao sistema.");
    		alert.show();
    	}
    	
    }
    
    @FXML
    protected void cancelarButtonOnAction() {
    	limparCampos();
    }
    
    private void limparCampos() {
    	usuarioTextField.clear();
    	senhaPasswordField.clear();
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

	public FXMLLoader getJanelaCaixaFXMLLoader() {
		return janelaCaixaFXMLLoader;
	}

	public void setJanelaCaixaFXMLLoader(FXMLLoader janelaCaixaFXMLLoader) {
		this.janelaCaixaFXMLLoader = janelaCaixaFXMLLoader;
	}

	public Parent getJanelaCaixaParent() {
		return janelaCaixaParent;
	}

	public void setJanelaCaixaParent(Parent janelaCaixaParent) {
		this.janelaCaixaParent = janelaCaixaParent;
	}

	public Scene getJanelaCaixaScene() {
		return janelaCaixaScene;
	}

	public void setJanelaCaixaScene(Scene janelaCaixaScene) {
		this.janelaCaixaScene = janelaCaixaScene;
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public CaixaController getCaixaController() {
		return caixaController;
	}

	public void setCaixaController(CaixaController caixaController) {
		this.caixaController = caixaController;
	}

	public Alert getAlert() {
		return alert;
	}

	public void setAlert(Alert alert) {
		this.alert = alert;
	}
	
	
}