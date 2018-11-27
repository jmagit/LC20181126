package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
//			Button btn = new Button("Saluda");
//			Label rslt = new Label("Hola");
//			rslt.setFont(Font.font("Arial", FontWeight.BOLD, 14));
//			TextField txt = new TextField("Mundo");
//			btn.setOnAction(e -> { rslt.setText("Hola " + txt.getText()); });
//			VBox root = new VBox();
//			root.getChildren().addAll(txt, btn, rslt);
//			root.setSpacing(8);
//			root.setPadding(new Insets(10));
//			primaryStage.setScene(new Scene(root,400,400));

			Parent root = (Parent)FXMLLoader.load(getClass().getResource("Sample.fxml"));
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Demos del curso");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
