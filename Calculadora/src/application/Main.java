package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("calculadora.fxml"));
			Scene scene = new Scene(root,450,470);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			//scene.getStylesheets().add(getClass().getResource("calculadora.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Calculadora");
			primaryStage.getIcons().add(new Image("calculator.png"));
			primaryStage.setMinHeight(470);
			primaryStage.setMinWidth(300);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
