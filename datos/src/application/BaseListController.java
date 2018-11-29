package application;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public abstract class BaseListController<M> implements Initializable, IBaseListController<M> {
	@FXML
	protected ButtonBase btnAdd;
	@FXML
	protected ButtonBase btnModify;
	@FXML
	protected ButtonBase btnView;
	@FXML
	protected ButtonBase btnRemove;
	@FXML
	protected ListView<M> lbListado;
	@FXML
	protected TableView<M> dataGrid;

	protected ObservableList<M> listado;
	protected SimpleObjectProperty<M> elemento;

	public BaseListController() {
		super();
	}

	/* (non-Javadoc)
	 * @see application.IBaseListController#getListado()
	 */
	@Override
	public ObservableList<M> getListado() {
		return listado;
	}

	/* (non-Javadoc)
	 * @see application.IBaseListController#setListado(javafx.collections.ObservableList)
	 */
	@Override
	public void setListado(ObservableList<M> listado) {
		this.listado = listado;
		if (lbListado != null)
			lbListado.setItems(this.listado);
		if (dataGrid != null)
			dataGrid.setItems(this.listado);
	}

	/* (non-Javadoc)
	 * @see application.IBaseListController#getElemento()
	 */
	@Override
	public M getElemento() {
		return elemento.get();
	}

	/* (non-Javadoc)
	 * @see application.IBaseListController#setCommand(javafx.event.EventHandler, javafx.event.EventHandler, javafx.event.EventHandler, javafx.event.EventHandler)
	 */
	@Override
	public void setCommand(EventHandler<ActionEvent> add, EventHandler<ActionEvent> modify,
			EventHandler<ActionEvent> view, EventHandler<ActionEvent> remove) {
		if (btnAdd != null)
			if(add != null)
				btnAdd.setOnAction(add);
			else 
				btnAdd.setVisible(false);
		if (btnModify != null)
			if(modify != null)
				btnModify.setOnAction(modify);
			else 
				btnModify.setVisible(false);
		if (btnView != null)
			if(view != null)
				btnView.setOnAction(view);
			else 
				btnView.setVisible(false);
		if (btnRemove != null)
			if(remove != null)
				btnRemove.setOnAction(remove);
			else 
				btnRemove.setVisible(false);
	}

    protected class EditingCell<T> extends TableCell<T, String> {
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