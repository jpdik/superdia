package br.superdia.controle;

import javax.naming.InitialContext;

import br.com.interfacebean.IAutentica;
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
	private Stage primaryStage;/*
	private CaixaController caixaController;*/
    
    @FXML
    private void entrarButtonOnAction() {}
    
    @FXML
    private void cancelarButtonOnAction() {}

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
}