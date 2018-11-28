package application.dal;

import java.io.Serializable;

public class Empleado implements Serializable {
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
	
	public boolean isValid() {
		return true;
	}
	public boolean isInvalid() {
		return !isValid();
	}
	public String[] errors() {
		return null;
	}
	

}
