package application;


import java.net.URL;
import java.util.ResourceBundle;

import application.dal.Empleado;
import application.dal.IEntity;
import application.model.EmpleadoModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class EmpleadosViewController extends BaseFormController<EmpleadoModel, Empleado> {
	protected Text txtIdEmpleado;
	@FXML
	protected Text txtNombre;
	@FXML
	protected Text txtApellidos;
	@FXML
	protected Text cbDelegacion;
	@FXML
	protected Text ckConflictivo;
	@FXML
	protected ImageView foto;
	
	public EmpleadosViewController() {
		super(new EmpleadoModel());
	}
	
	@Override
	public void setEntity(Empleado item) {
		foto.setImage(new Image("http://placeimg.com/480/640/people"));
		super.setEntity(item);
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//txtIdEmpleado.textProperty().bindBidirectional(model.IdEmpleadoProperty());
		txtNombre.textProperty().bindBidirectional(model.NombreProperty());
		txtApellidos.textProperty().bindBidirectional(model.ApellidosProperty());
		cbDelegacion.textProperty().bindBidirectional(model.DelegacionProperty());
		ckConflictivo.visibleProperty().bind(model.ConflictivoProperty());
	}


}
