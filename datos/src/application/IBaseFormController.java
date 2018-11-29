package application;

import application.dal.IEntity;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public interface IBaseFormController<M, E extends IEntity> {

	M getModel();

	void setModel(M model);

	void setEntity(E item);

	void setCommand(EventHandler<ActionEvent> accept, EventHandler<ActionEvent> cancel);

}