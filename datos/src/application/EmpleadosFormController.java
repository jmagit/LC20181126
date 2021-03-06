package application;


import java.net.URL;
import java.util.ResourceBundle;

import application.dal.Empleado;
import application.model.EmpleadoModel;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class EmpleadosFormController extends BaseFormController<EmpleadoModel, Empleado> {
	@FXML
	protected TextField txtIdEmpleado;
	@FXML
	protected TextField txtNombre;
	@FXML
	protected TextField txtApellidos;
	@FXML
	protected ComboBox<String> cbDelegacion;
	@FXML
	protected CheckBox ckConflictivo;
	
	public EmpleadosFormController() {
		super(new EmpleadoModel());
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		txtIdEmpleado.textProperty().bindBidirectional(model.IdEmpleadoProperty());
		txtNombre.textProperty().bindBidirectional(model.NombreProperty());
		txtApellidos.textProperty().bindBidirectional(model.ApellidosProperty());
		cbDelegacion.valueProperty().bindBidirectional(model.DelegacionProperty());
		ckConflictivo.selectedProperty().bindBidirectional(model.ConflictivoProperty());
		cbDelegacion.setItems(model.getDelegaciones());
	}


}
