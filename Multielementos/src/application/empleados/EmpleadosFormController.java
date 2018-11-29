package application.empleados;


import java.net.URL;
import java.util.ResourceBundle;

import application.dal.Empleado;
import application.model.EmpleadoModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class EmpleadosFormController implements Initializable {
	@FXML	
	protected Button btnAccept;
	@FXML
	protected Button btnCancel;
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
	
	private EmpleadoModel model = new EmpleadoModel();

	public EmpleadoModel getModel() {
		return model;
	}
	public void setModel(EmpleadoModel model) {
		this.model.copyModel(model);
	}
	public void setModel(Empleado item) {
		this.model.copyEntity(item);
	}
	public void setCommand(EventHandler<ActionEvent> accept, EventHandler<ActionEvent> cancel) {
		if(btnAccept != null) btnAccept.setOnAction(accept);
		if(btnCancel != null) btnCancel.setOnAction(cancel);
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
