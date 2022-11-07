package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Ejercicio1_Y_2 extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			/*StackPane panel = new StackPane();

			Rectangle r1 = new Rectangle(400, 400, Color.DARKGREEN);
			Rectangle r2 = new Rectangle(300, 300, Color.GREEN);
			Rectangle r3 = new Rectangle(200, 200, Color.LIGHTGREEN);

			panel.getChildren().addAll(r1, r2, r3); // si invertimos el orden de apilación es decir r3,r2,r1 solo se
													// verá el cuadrado más grande ya que machaca a los demás.
			*/
			
			BorderPane panel = new BorderPane();
			
			Rectangle rCentro = new Rectangle(300,300,Color.BLUE);
			Rectangle rArriba = new Rectangle(400,50,Color.DARKGREEN);
			Rectangle rAbajo = new Rectangle(400,50,Color.DARKGREEN);
			Rectangle rIzquierda = new Rectangle(50,300,Color.LIGHTGREEN);
			Rectangle rDerecha = new Rectangle(50,300,Color.ALICEBLUE);
			
			panel.setCenter(rCentro);
			panel.setTop(rArriba);
			panel.setBottom(rAbajo);
			panel.setRight(rDerecha);
			panel.setLeft(rIzquierda);
			
			
			
			Scene scene = new Scene(panel, 400, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
