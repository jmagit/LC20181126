package application;

import javafx.beans.property.SimpleStringProperty;

public class Persona {
	private SimpleStringProperty nombre = new SimpleStringProperty("Pepito");

	public String getNombre() {
		return nombre.get();
	}

	public void setNombre(String nombre) {
		this.nombre.set(nombre);
	}
	public SimpleStringProperty nombreProperty() {
		return nombre;
	}
}
