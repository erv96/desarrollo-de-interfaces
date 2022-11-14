package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class EjercicioController {
	@FXML
	private TextField nombre;
	
	@FXML
	private Button btnEntrar;
	
	@FXML
	private PasswordField password;
	
	@FXML
	private Label lblMensaje;
	
	@FXML
	public void mostrarNombre(ActionEvent event) {
		lblMensaje.setText(nombre.getText());
	}
	
	public void mostrarBienvenida(ActionEvent event) {
		lblMensaje.setText("Bienvenid@ "+nombre.getText());
		nombre.clear();
		password.clear();
	}
	
	
}
