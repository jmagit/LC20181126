package application;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import application.dal.Empleado;
import application.dal.EmpleadosDAO;
import application.model.EmpleadoModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.StackPane;

public class EmpleadosMntView implements Initializable {
	@FXML
	protected StackPane root;

	protected Parent listPane = null, formPane = null, viewPane = null;
	protected EmpleadosListController listController;
	protected EmpleadosFormController formController;
	protected EmpleadosViewController viewController;
	protected EventHandler<ActionEvent> addHandler;
	protected EventHandler<ActionEvent> viewHandler;
	protected EventHandler<ActionEvent> modifyHandler;
	protected EventHandler<ActionEvent> removeHandler;
	protected EventHandler<ActionEvent> acceptAddHandler;
	protected EventHandler<ActionEvent> acceptModifyHandler;
	protected EventHandler<ActionEvent> cancelHandler;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		FXMLLoader loader = new FXMLLoader();
//		loader.setLocation(getClass().getResource("EmpleadosList.fxml"));
		loader.setLocation(getClass().getResource("EmpleadosTable.fxml"));
		try {
			listPane = (Parent) loader.load();
			listController = (EmpleadosListController) loader.getController();
		} catch (IOException e) {
			e.printStackTrace();
		}
		loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("EmpleadosForm.fxml"));
		try {
			formPane = (Parent) loader.load();
			formController = (EmpleadosFormController) loader.getController();
			formPane.setVisible(false);
		} catch (IOException e) {
			e.printStackTrace();
		}
		loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("EmpleadosView.fxml"));
		try {
			viewPane = (Parent) loader.load();
			viewController = (EmpleadosViewController) loader.getController();
			viewPane.setVisible(false);
		} catch (IOException e) {
			e.printStackTrace();
		}
		root.getChildren().addAll(listPane, formPane, viewPane);

		acceptAddHandler = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				try {
					if (formController.getModel().getEntity().isValid()) {
						add(formController.getModel());
						load();
						cancelHandler.handle(null);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		acceptModifyHandler = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				try {
					if (formController.getModel().getEntity().isValid()) {
						modify(formController.getModel());
						load();
						cancelHandler.handle(null);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		cancelHandler = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				listPane.setVisible(true);
				formPane.setVisible(false);
				viewPane.setVisible(false);
			}
		};

		addHandler = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				formController.setModel(new Empleado());
				formController.setCommand(acceptAddHandler, cancelHandler);
				listPane.setVisible(false);
				formPane.setVisible(true);
			}
		};
		modifyHandler = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				try {
					if (listController.getElemento() == null)
						return;
					formController.setModel(load(listController.getElemento().getIdEmpleado()));
					formController.setCommand(acceptModifyHandler, cancelHandler);
					listPane.setVisible(false);
					formPane.setVisible(true);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		viewHandler = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				try {
					if (listController.getElemento() == null)
						return;
					viewController.setModel(load(listController.getElemento().getIdEmpleado()));
					viewController.setCommand(null, cancelHandler);
					listPane.setVisible(false);
					viewPane.setVisible(true);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		removeHandler = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				if (listController.getElemento() != null)
					try {
						Alert alert = new Alert(AlertType.CONFIRMATION);
						alert.setTitle("Pedir confirmacion");
						alert.setHeaderText(null);
						alert.setContentText("¿Seguro?");

						Optional<ButtonType> result = alert.showAndWait();
						if (result.get() == ButtonType.OK) {
							remove(listController.getElemento().getIdEmpleado());
							load();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
			}
		};

		listController.setCommand(addHandler, modifyHandler, viewHandler, removeHandler);
		load();
	}

	protected void load() {
		EmpleadosDAO srv = new EmpleadosDAO();
		ObservableList<EmpleadoModel> lst = FXCollections.observableArrayList();
		for (Empleado emp : srv.getAll()) {
			lst.add(new EmpleadoModel(emp));
		}
		listController.setListado(lst);
	}

	public Empleado load(int id) throws IOException {
		EmpleadosDAO srv = new EmpleadosDAO();
		return srv.get(id);
	}

	public void add(EmpleadoModel item) throws IOException {
		EmpleadosDAO srv = new EmpleadosDAO();
		if (item == null)
			throw new IOException("Datos invalidos");
		srv.insert(item.getEntity());
	}

	public void modify(EmpleadoModel item) throws IOException {
		EmpleadosDAO srv = new EmpleadosDAO();
		if (item == null)
			throw new IOException("Datos invalidos");
		srv.update(item.getEntity());
	}

	public void remove(int id) throws IOException {
		EmpleadosDAO srv = new EmpleadosDAO();
		srv.delete(id);
	}

}
