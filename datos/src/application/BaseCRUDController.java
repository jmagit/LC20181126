package application;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import application.dal.IEntity;
import application.dal.Repository;
import application.model.ModelCopiable;
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

public class BaseCRUDController<M extends ModelCopiable<M, E>, E extends IEntity, D extends Repository<E>>
		implements Initializable {

	@FXML
	protected StackPane root;
	protected Parent listPane = null;
	protected Parent formPane = null;
	protected Parent viewPane = null;
	protected String listFXML = null;
	protected String formFXML = null;
	protected String viewFXML = null;
	protected IBaseListController<M> listController;
	protected IBaseFormController<M, E> formController;
	protected IBaseFormController<M, E> viewController;
	protected EventHandler<ActionEvent> addHandler;
	protected EventHandler<ActionEvent> viewHandler;
	protected EventHandler<ActionEvent> modifyHandler;
	protected EventHandler<ActionEvent> removeHandler;
	protected EventHandler<ActionEvent> acceptAddHandler;
	protected EventHandler<ActionEvent> acceptModifyHandler;
	protected EventHandler<ActionEvent> cancelHandler;

	public BaseCRUDController(Class<E> classEntity, Class<M> classModel, Class<D> classDAO, String listFXML,
			String formFXML, String viewFXML) {
		classE = classEntity;
		classM = classModel;
		classD = classDAO;
		this.listFXML = listFXML;
		this.formFXML = formFXML;
		this.viewFXML = viewFXML;
	}

	private Class<E> classE;

	public E getEntityInstance() {
		try {
			return classE.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Class<M> classM;

	public M getModelInstance() {
		try {
			return classM.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Class<D> classD;

	public D getDAOInstance() {
		try {
			return classD.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	public BaseCRUDController() {
		super();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		FXMLLoader loader = new FXMLLoader();
		if (listFXML != null && !"".equals(listFXML))
			try {
				loader.setLocation(getClass().getResource(listFXML));
				listPane = (Parent) loader.load();
				listController = (IBaseListController<M>) loader.getController();
				root.getChildren().add(listPane);
			} catch (Exception e) {
				e.printStackTrace();
			}
		if (formFXML != null && !"".equals(formFXML))
			try {
				loader = new FXMLLoader();
				loader.setLocation(getClass().getResource(formFXML));
				formPane = (Parent) loader.load();
				formController = (IBaseFormController<M, E>) loader.getController();
				formPane.setVisible(false);
				root.getChildren().add(formPane);
			} catch (Exception e) {
				e.printStackTrace();
			}
		if (viewFXML != null && !"".equals(viewFXML))
			try {
				loader = new FXMLLoader();
				loader.setLocation(getClass().getResource(viewFXML));
				viewPane = (Parent) loader.load();
				viewController = (IBaseFormController<M, E>) loader.getController();
				viewPane.setVisible(false);
				root.getChildren().add(viewPane);
			} catch (Exception e) {
				e.printStackTrace();
			}
		// root.getChildren().addAll(listPane, formPane, viewPane);

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
				if (formPane != null)
					formPane.setVisible(false);
				if (viewPane != null)
					viewPane.setVisible(false);
			}
		};
		if (formPane != null) {
			addHandler = new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {
					formController.setEntity(getEntityInstance());
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
						formController.setEntity(load(listController.getElemento().getPrimaryKey()));
						formController.setCommand(acceptModifyHandler, cancelHandler);
						listPane.setVisible(false);
						formPane.setVisible(true);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			};
		}
		if (viewPane != null)
			viewHandler = new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {
					try {
						if (listController.getElemento() == null)
							return;
						viewController.setEntity(load(listController.getElemento().getPrimaryKey()));
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
							remove(listController.getElemento().getPrimaryKey());
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
		D srv = getDAOInstance();
		ObservableList<M> lst = FXCollections.observableArrayList();
		for (E entity : srv.getAll()) {
			lst.add(getModelInstance().copyEntity(entity));
		}
		listController.setListado(lst);
	}

	public E load(int id) throws IOException {
		D srv = getDAOInstance();
		return srv.get(id);
	}

	public void add(M item) throws IOException {
		if (item == null)
			throw new IOException("Datos invalidos");
		D srv = getDAOInstance();
		srv.insert(item.getEntity());
	}

	public void modify(M item) throws IOException {
		if (item == null)
			throw new IOException("Datos invalidos");
		D srv = getDAOInstance();
		srv.update(item.getEntity());
	}

	public void remove(int id) throws IOException {
		D srv = getDAOInstance();
		srv.delete(id);
	}

}