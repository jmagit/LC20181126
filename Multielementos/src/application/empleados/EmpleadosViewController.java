package application.empleados;

import java.net.URL;
import java.util.ResourceBundle;

import application.dal.Empleado;
import application.model.EmpleadoModel;
import javafx.application.Platform;
import javafx.concurrent.Task;
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

public class EmpleadosViewController implements Initializable {
	@FXML
	protected Button btnAccept;
	@FXML
	protected Button btnCancel;
	@FXML
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

	private EmpleadoModel model = new EmpleadoModel();

	public EmpleadoModel getModel() {
		return model;
	}

	public void setModel(EmpleadoModel model) {
		this.model.copyModel(model);
	}

	public void setModel(Empleado item) {
//		Task<Void> task = new Task<Void>() {
//			@Override
//			protected Void call() throws Exception {
//				Image img = new Image("http://placeimg.com/480/640/people");
//				Platform.runLater(new Runnable() {
//					@Override
//					public void run() {
//						foto.setImage(img);
//					}
//				});
//				return null;
//			}
//		};
//		(new Thread(task)).start();
		foto.setImage(new Image("http://placeimg.com/480/640/people"));
		this.model.copyEntity(item);
	}

	public void setCommand(EventHandler<ActionEvent> accept, EventHandler<ActionEvent> cancel) {
		if (btnCancel != null)
			btnCancel.setOnAction(cancel);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// txtIdEmpleado.textProperty().bindBidirectional(model.IdEmpleadoProperty());
		txtNombre.textProperty().bindBidirectional(model.NombreProperty());
		txtApellidos.textProperty().bindBidirectional(model.ApellidosProperty());
		cbDelegacion.textProperty().bindBidirectional(model.DelegacionProperty());
		ckConflictivo.visibleProperty().bind(model.ConflictivoProperty());
	}

}
