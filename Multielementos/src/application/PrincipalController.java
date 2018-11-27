package application;

import java.io.IOException;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PrincipalController {
	@FXML
	private BorderPane root;
	
	private Object cargarFXML(String plantilla) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource(plantilla));
		try {
			root.setCenter(loader.load());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return loader.getController();
	}
	
	public void calculadora(Event ev) {
		cargarFXML("calculadora.fxml");
	}
	
	public void editor(Event ev) {
		EditorController controller = (EditorController) cargarFXML("editor.fxml");
		controller.setStage(Main.getStage());
	}
	
	public void cambiaEscena(Event ev) {
		Main.goDashboard();
	}
	
	public void alerta(Event ev) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText(null);
		alert.setContentText("I have a great message for you!");

		alert.showAndWait();
	}
	public void ventanaModal(Event ev) throws IOException {
		Stage dialogStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("CDialogo.fxml"));
		Parent ventana = (Parent) loader.load();
		((CDialogoController) loader.getController()).setStage(dialogStage);
		Scene scene = new Scene(ventana, 400, 400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		dialogStage.setTitle("Cuadro de dialogo");
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.initOwner(Main.getStage());
		dialogStage.setScene(scene);
		dialogStage.showAndWait();
		// En okHandle o cancelHandle: dialogStage.close();
	}
	public void ventanaModaless(Event ev) throws IOException {
		Stage dialogStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("ventana.fxml"));
		Parent ventana = (Parent) loader.load();
		Scene scene = new Scene(ventana, 400, 400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		dialogStage.setTitle("Ventana con padre");
		dialogStage.initModality(Modality.NONE);
		dialogStage.initOwner(Main.getStage());
		dialogStage.setScene(scene);
		dialogStage.show();
	}
	public void ventanaIndependiente(Event ev) throws IOException {
		Stage dialogStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("ventana.fxml"));
		Parent ventana = (Parent) loader.load();
		Scene scene = new Scene(ventana, 400, 400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		dialogStage.setTitle("Ventana independiente");
		dialogStage.initModality(Modality.NONE);
		dialogStage.initOwner(null);
		dialogStage.setScene(scene);
		dialogStage.show();
	}
}
