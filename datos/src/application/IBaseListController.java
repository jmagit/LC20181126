package application;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public interface IBaseListController<M> {

	ObservableList<M> getListado();

	void setListado(ObservableList<M> listado);

	M getElemento();

	void setCommand(EventHandler<ActionEvent> add, EventHandler<ActionEvent> modify, EventHandler<ActionEvent> view,
			EventHandler<ActionEvent> remove);

}