package application.calculadora;


import java.net.URL;
import java.text.DecimalFormatSymbols;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class CalculadoraController implements Initializable {
	private Calculadora calculadora = new Calculadora();
	
	@FXML
	private Label txtPantalla;
	@FXML
	private Label txtResumen;
	@FXML
	private Button btnDecimal;

	public void DigitoPulsado(ActionEvent e) throws CalculadoraException {
		try {
			calculadora.ponDigito((String) ((Button) e.getSource()).getText());
		} catch (CalculadoraException ex) {
			muestraExcepcion(ex);
		}
	}

	public void OperacionPulsada(ActionEvent e) {
		try {
			calculadora.hazOperacion(((Button) e.getSource()).getText().charAt(0));
		} catch (CalculadoraException ex) {
			muestraExcepcion(ex);
		}
	}

	public void btnIni(ActionEvent e) {
		calculadora.inicializa();
	}

	public void btnDecimal(ActionEvent e) {
		calculadora.ponSeparadorDecimal();
	}

	public void btnSigno(ActionEvent e) {
		calculadora.cambiaSigno();
	}

	public void btnBorra(ActionEvent e) {
		calculadora.borra();
	}

	private void muestraExcepcion(CalculadoraException ex) {
		Alert dlg = new Alert(AlertType.ERROR, ex.getMessage());
		dlg.showAndWait();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// Para activarlos: Run Configurations > Arguments > VM arguments: -ea
		assert txtPantalla != null : "fx:id=\"txtPantalla\" was not injected.";
		assert txtResumen != null : "fx:id=\"txtResumen\" was not injected.";
		txtPantalla.textProperty().bindBidirectional(calculadora.PantallaProperty());
		txtResumen.textProperty().bindBidirectional(calculadora.ResumenProperty());
		if(btnDecimal != null)
			btnDecimal.setText(Character.toString(DecimalFormatSymbols.getInstance().getDecimalSeparator()));
	}

}