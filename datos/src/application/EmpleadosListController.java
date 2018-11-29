package application;

import java.net.URL;
import java.util.ResourceBundle;

import application.model.EmpleadoModel;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;

public class EmpleadosListController extends BaseListController<EmpleadoModel> implements Initializable {
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
		    col.setCellFactory(TextFieldTableCell.<EmpleadoModel>forTableColumn());
//		    col.setCellFactory(cellFactory);
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
		    TableColumn<EmpleadoModel, String> newCol = new TableColumn<>("Delegación");
		    newCol.setCellValueFactory(new PropertyValueFactory<>("Delegacion"));
		    dataGrid.getColumns().add(newCol);
			elemento.bind(dataGrid.getSelectionModel().selectedItemProperty());
		}
	}

}
