package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class PrMain extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Editorial.fxml"));
			Scene scene = new Scene(root,650,500);
			scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
			Image image = new Image("icon/comic.png");
			primaryStage.setScene(scene);
			primaryStage.setTitle("Editoriales");
			primaryStage.getIcons().add(image);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
