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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    private Label msgErroLabel;

    @FXML
    private TextField usuarioTextField;
    
    private InitialContext ic;
    private IAutentica iAutentica = null; 
    private FXMLLoader janelaCaixaFXMLLoader;
	private Parent janelaCaixaParent;
	private Scene janelaCaixaScene;
	private Stage primaryStage;
	private CaixaController caixaController;
	
	@FXML
    private void initialize() {		
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
    	if(usuario == null)
    		msgErroLabel.setText("ERRO: Usuário Inexistente.");
    	else if(usuario.getPerfil().equalsIgnoreCase("Caixa")) {
    		System.out.println("Ir para o caixa realizar a compra");
    		try {
				janelaCaixaFXMLLoader = new FXMLLoader(getClass().getResource("/br/superdia/views/Caixa.fxml"));
				janelaCaixaParent = (Parent) janelaCaixaFXMLLoader.load();
				janelaCaixaScene = new Scene(janelaCaixaParent);
				janelaCaixaScene.getStylesheets().add(getClass().getResource("/br/superdia/views/css/Caixa.css").toExternalForm());
				
				caixaController = (CaixaController) janelaCaixaFXMLLoader.getController();
				caixaController.getOperadorTextField().setText(usuario.getUsuario());
				
				primaryStage = (Stage) msgErroLabel.getScene().getWindow();
				primaryStage.setScene(janelaCaixaScene);
				primaryStage.setTitle("Caixa");
				primaryStage.centerOnScreen();
				primaryStage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}    		
    	}else {
    		msgErroLabel.setText("ERRO: O usuário de perfil (" + usuario.getPerfil() + ") NÃO pode ter acesso ao sistema.");
    	}
    	
    }
    
    @FXML
    protected void cancelarButtonOnAction() {
    	limparCampos();
    }
    
    private void limparCampos() {
    	usuarioTextField.clear();
    	senhaPasswordField.clear();
    	msgErroLabel.setText("");
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

	public Label getMsgErroLabel() {
		return msgErroLabel;
	}

	public void setMsgErroLabel(Label msgErroLabel) {
		this.msgErroLabel = msgErroLabel;
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
	
}