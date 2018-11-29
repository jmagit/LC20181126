package application.empleados;

import java.net.URL;
import java.util.ResourceBundle;

import application.model.EmpleadoModel;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;

public class EmpleadosListController implements Initializable {
	@FXML
	protected ButtonBase btnAdd;
	@FXML
	protected ButtonBase btnModify;
	@FXML
	protected ButtonBase btnView;
	@FXML
	protected ButtonBase btnRemove;
	@FXML
	protected ListView<EmpleadoModel> lbListado;
	@FXML
	protected TableView<EmpleadoModel> dataGrid;
	
	protected ObservableList<EmpleadoModel> listado;
	protected SimpleObjectProperty<EmpleadoModel> elemento;
	
	public ObservableList<EmpleadoModel> getListado() {
		return listado;
	}
	public void setListado(ObservableList<EmpleadoModel> listado) {
		this.listado = listado;
		if (lbListado != null)
			lbListado.setItems(this.listado);
		if (dataGrid != null)
			dataGrid.setItems(this.listado);
	}
	public EmpleadoModel getElemento() {
		return elemento.get();
	}
	public void setCommand(EventHandler<ActionEvent> add, EventHandler<ActionEvent> modify,
			EventHandler<ActionEvent> view, EventHandler<ActionEvent> remove) {
		if (btnAdd != null)
			btnAdd.setOnAction(add);
		if (btnModify != null)
			btnModify.setOnAction(modify);
		if (btnView != null)
			btnView.setOnAction(view);
		if (btnRemove != null)
			btnRemove.setOnAction(remove);
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		elemento = new SimpleObjectProperty<EmpleadoModel>();
		if (lbListado != null) {
			lbListado.setCellFactory(new Callback<ListView<EmpleadoModel>, ListCell<EmpleadoModel>>() {
				@Override
				public ListCell<EmpleadoModel> call(ListView<EmpleadoModel> param) {
					return new ListCell<EmpleadoModel>() {
						@Override
						protected void updateItem(EmpleadoModel item, boolean empty) {
							super.updateItem(item, empty);
							if (item != null) {
								setText(String.format("%s %s (%d)", item.getNombre(), item.getApellidos(), item.getIdEmpleado()));
							} else {
								setText(null);
							}
						}
					};
				}
			});
			elemento.bind(lbListado.getSelectionModel().selectedItemProperty());
		}
		if (dataGrid != null) {
	        Callback<TableColumn<EmpleadoModel, String>, TableCell<EmpleadoModel, String>> cellFactory = 
	        		(TableColumn<EmpleadoModel, String> p) -> new EditingCell<EmpleadoModel>();
			TableColumn<EmpleadoModel, String> col = (TableColumn<EmpleadoModel, String>)dataGrid.getColumns().get(0);
		    //col.setCellFactory(TextFieldTableCell.<EmpleadoModel>forTableColumn());
		    col.setCellFactory(cellFactory);
		    col.setOnEditCommit(
		            (CellEditEvent<EmpleadoModel, String> t) -> {
		                ((EmpleadoModel) t.getTableView().getItems().get(
		                        t.getTablePosition().getRow())
		                        ).IdEmpleadoProperty().set(t.getNewValue());
		        });
			col = (TableColumn<EmpleadoModel, String>)dataGrid.getColumns().get(1);
		    //col.setCellFactory(TextFieldTableCell.<EmpleadoModel>forTableColumn());
		    col.setCellFactory(cellFactory);
		    col.setOnEditCommit(
		            (CellEditEvent<EmpleadoModel, String> t) -> {
		                ((EmpleadoModel) t.getTableView().getItems().get(
		                        t.getTablePosition().getRow())
		                        ).setNombre(t.getNewValue());
		        });
			col = (TableColumn<EmpleadoModel, String>)dataGrid.getColumns().get(2);
		    //col.setCellFactory(TextFieldTableCell.<EmpleadoModel>forTableColumn());
		    col.setCellFactory(cellFactory);
		    col.setOnEditCommit(
		            (CellEditEvent<EmpleadoModel, String> t) -> {
		                ((EmpleadoModel) t.getTableView().getItems().get(
		                        t.getTablePosition().getRow())
		                        ).setApellidos(t.getNewValue());
		        });
			elemento.bind(dataGrid.getSelectionModel().selectedItemProperty());
		}
	}

    class EditingCell<T> extends TableCell<T, String> {
        private TextField textField;
 
        public EditingCell() {
        }
 
        @Override
        public void startEdit() {
            if (!isEmpty()) {
                super.startEdit();
                createTextField();
                setText(null);
                setGraphic(textField);
                textField.selectAll();
            }
        }
 
        @Override
        public void cancelEdit() {
            super.cancelEdit();
 
            setText((String) getItem());
            setGraphic(null);
        }
 
        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
 
            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                if (isEditing()) {
                    if (textField != null) {
                        textField.setText(getString());
                    }
                    setText(null);
                    setGraphic(textField);
                } else {
                    setText(getString());
                    setGraphic(null);
                }
            }
        }
 
        private void createTextField() {
            textField = new TextField(getString());
            textField.setMinWidth(this.getWidth() - this.getGraphicTextGap()* 2);
            textField.focusedProperty().addListener(
                (ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) -> {
                    if (!arg2) {
                        commitEdit(textField.getText());
                    }
            });
        }
 
        private String getString() {
            return getItem() == null ? "" : getItem().toString();
        }
    }

}
