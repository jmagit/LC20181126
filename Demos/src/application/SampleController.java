package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SampleController implements Initializable {
	@FXML
	private Label rslt;
	@FXML
	private TextField txt;
	@FXML
	private Button btn;
	@FXML
	private Label nombre;

	@FXML
	private EventHandler<Event> saluda = null;
	private EventHandler<Event> despide = null;
	private Persona persona = new Persona();
	
	public void onClick(Event e) {
		rslt.setText("Hola " + txt.getText());
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		saluda = (e) -> {
			rslt.setText("Hola " + txt.getText());
			btn.removeEventHandler(ActionEvent.ACTION, saluda);
			btn.addEventHandler(ActionEvent.ACTION, despide);
			persona.setNombre("Fulanito");
			btn.setText("Despide");
		};
		despide = (e) -> {
			rslt.setText("Adios " + txt.getText());
			btn.removeEventHandler(ActionEvent.ACTION, despide);
			btn.addEventHandler(ActionEvent.ACTION, saluda);
			persona.setNombre("Menganito");
			btn.setText("Saluda");
		};
		btn.addEventHandler(ActionEvent.ACTION, saluda);
		btn.setText("Saluda");
		txt.textProperty().bindBidirectional(persona.nombreProperty());
		// saluda = (e) -> { rslt.setText("UPSSSSSSS!!!"); };
		nombre.textProperty().bind(persona.nombreProperty());
	}
}
