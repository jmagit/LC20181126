package application.model;

import application.dal.Empleado;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EmpleadoModel implements ModelCopiable<EmpleadoModel, Empleado> {
	private SimpleStringProperty idEmpleado = new SimpleStringProperty();
	private SimpleStringProperty nombre = new SimpleStringProperty();
	private SimpleStringProperty apellidos = new SimpleStringProperty();
	private SimpleStringProperty delegacion = new SimpleStringProperty();
	private SimpleBooleanProperty conflictivo = new SimpleBooleanProperty();
	
	public EmpleadoModel() { }
	public EmpleadoModel(Empleado item) { copyEntity(item); }
	
	public int getIdEmpleado() {
		return Integer.parseInt(idEmpleado.get());
	}
	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado.set(Integer.toString(idEmpleado));
	}
	public SimpleStringProperty IdEmpleadoProperty() {
		return idEmpleado;
	}
	
	public String getNombre() {
		return nombre.get();
	}
	public void setNombre(String nombre) {
		this.nombre.set(nombre);
	}
	public SimpleStringProperty NombreProperty() {
		return nombre;
	}

	public String getApellidos() {
		return apellidos.get();
	}
	public void setApellidos(String apellidos) {
		this.apellidos.set(apellidos);
	}
	public SimpleStringProperty ApellidosProperty() {
		return apellidos;
	}

	public String getDelegacion() {
		return delegacion.get();
	}
	public void setDelegacion(String delegacion) {
		this.delegacion.set(delegacion);
	}
	public SimpleStringProperty DelegacionProperty() {
		return delegacion;
	}

	public boolean isConflictivo() {
		return conflictivo.get();
	}
	public void setConflictivo(boolean conflictivo) {
		this.conflictivo.set(conflictivo);
	}
	public SimpleBooleanProperty ConflictivoProperty() {
		return conflictivo;
	}

	/* (non-Javadoc)
	 * @see application.model.ModelCopiable#copy(application.dal.Empleado)
	 */
	@Override
	public EmpleadoModel copyEntity(Empleado item) {
		setIdEmpleado(item.getIdEmpleado());
		setNombre(item.getNombre());
		setApellidos(item.getApellidos());
		setDelegacion(item.getDelegacion());
		setConflictivo(item.isConflictivo());
		return this;
	}
	/* (non-Javadoc)
	 * @see application.model.ModelCopiable#copy(application.model.EmpleadoModel)
	 */
	@Override
	public EmpleadoModel copyModel(EmpleadoModel item) {
		setIdEmpleado(item.getIdEmpleado());
		setNombre(item.getNombre());
		setApellidos(item.getApellidos());
		setDelegacion(item.getDelegacion());
		setConflictivo(item.isConflictivo());
		return this;
	}
	public Empleado getEntity() {
		Empleado rslt = new Empleado();
		rslt.setIdEmpleado(getIdEmpleado());
		rslt.setNombre(getNombre());
		rslt.setApellidos(getApellidos());
		rslt.setDelegacion(getDelegacion());
		rslt.setConflictivo(isConflictivo());
		return rslt;
	}
	
	ObservableList<String> delegaciones = FXCollections.observableArrayList(
			"Madrid", "A Coruña", "Pontevedra", "Lugo", "Orense");
	public ObservableList<String> getDelegaciones() { return delegaciones; }

}
