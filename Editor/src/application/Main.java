package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
	private static Stage stage;

	public static Stage getStage() {
		return stage;
	}

	@Override
	public void start(Stage primaryStage) {
		stage = primaryStage;
		try {
			// BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("editor.fxml"));
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("editor.fxml"));
			Parent root = (Parent) loader.load();
			EditorController controller = (EditorController) loader.getController();
			controller.setStage(primaryStage);
			
			Scene scene = new Scene(root, 800, 600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Mi Editor HTML");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
