package application;

import application.dal.Empleado;
import application.dal.EmpleadosDAO;
import application.model.EmpleadoModel;

public class EmpleadosMntView extends BaseCRUDController<EmpleadoModel, Empleado, EmpleadosDAO> {
	public EmpleadosMntView() { // "EmpleadosTable.fxml"
		super(Empleado.class, EmpleadoModel.class, EmpleadosDAO.class, 
				"EmpleadosList.fxml", "EmpleadosForm.fxml", "EmpleadosViewx.fxml");
	}
}
