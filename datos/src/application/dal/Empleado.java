package application.dal;

import java.io.Serializable;

public class Empleado implements Serializable, IEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idEmpleado;
	private String nombre, apellidos, delegacion;
	private boolean conflictivo;
	
	public Empleado() { }
	
	public Empleado(int idEmpleado, String nombre, String apellidos, String delegacion, boolean conflictivo) {
		super();
		this.idEmpleado = idEmpleado;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.delegacion = delegacion;
		this.conflictivo = conflictivo;
	}
	
	public int getIdEmpleado() {
		return idEmpleado;
	}
	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getDelegacion() {
		return delegacion;
	}
	public void setDelegacion(String delegacion) {
		this.delegacion = delegacion;
	}
	public boolean isConflictivo() {
		return conflictivo;
	}
	public void setConflictivo(boolean conflictivo) {
		this.conflictivo = conflictivo;
	}
	
	/* (non-Javadoc)
	 * @see application.dal.IEntity#isValid()
	 */
	@Override
	public boolean isValid() {
		return true;
	}
	/* (non-Javadoc)
	 * @see application.dal.IEntity#isInvalid()
	 */
	@Override
	public boolean isInvalid() {
		return !isValid();
	}
	/* (non-Javadoc)
	 * @see application.dal.IEntity#errors()
	 */
	@Override
	public String[] errors() {
		return null;
	}

}
