package application;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.stage.Stage;

public class CDialogoController {
	private Stage stage;

	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	public void Aceptar(Event ev) {
		stage.close();
	}
	
	public void Cancelar(Event ev) {
		stage.close();
	}

}
