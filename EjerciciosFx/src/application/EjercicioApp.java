package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EjercicioApp extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Panel principal

		VBox panel = new VBox(15);
		panel.setPadding(new Insets(15));
		Label nombre = new Label("Nombre");
		TextField txt_nombre = new TextField();
		Label contraseña = new Label("Contraseña");
		PasswordField psw_contraseña = new PasswordField();
		Button btn = new Button("Entrar");
		Label lbl_bienvenida = new Label();
		
		panel.getChildren().addAll(nombre,txt_nombre,contraseña,psw_contraseña,btn,lbl_bienvenida);
		
		btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				lbl_bienvenida.setText("Bienvenid@ "+txt_nombre.getText());
				
			}
		});
		
	

		Scene scene = new Scene(panel, 500, 300);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
