package br.superdia.controle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

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
    
    @FXML
    private void entrarButtonOnAction() {}
    
    @FXML
    private void cancelarButtonOnAction() {}
    
    
}