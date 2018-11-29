package application.dal;

import java.io.IOException;
import java.util.Collection;
import java.util.Hashtable;


public class EmpleadosDAO implements Repository<Empleado> {
	private static Hashtable<Integer, Empleado> listado = new Hashtable<Integer, Empleado>();
	static {
		listado.put(1, new Empleado(1, "Pepito", "Grillo", "Madrid", true));
		listado.put(2, new Empleado(2, "Carmelo", "Coton", "A Coruña", false));
	}
	
	/* (non-Javadoc)
	 * @see application.dal.Repository#getAll()
	 */
	@Override
	public Collection<Empleado> getAll() {
		return listado.values();
	}
	
	/* (non-Javadoc)
	 * @see application.dal.Repository#get(int)
	 */
	@Override
	public Empleado get(int key) throws IOException {
		if(0>key || key>listado.size() )
			throw new IOException("No encontrado.");
		Empleado rslt = listado.get(key);
		if(rslt == null)
			throw new IOException("No encontrado.");
		return rslt;
	}
	/* (non-Javadoc)
	 * @see application.dal.Repository#insert(application.dal.Empleado)
	 */
	@Override
	public Empleado insert(Empleado item) throws IOException {
		if(item == null)
			throw new IOException("Argumentos invalidos.");
		Empleado rslt = listado.get(item.getIdEmpleado());
		if(rslt != null)
			throw new IOException("Clave duplicada.");
		if(item.isInvalid())
			throw new IOException("Error en datos.");
		listado.put(item.getIdEmpleado(), item);
		return rslt;
	}
	/* (non-Javadoc)
	 * @see application.dal.Repository#update(application.dal.Empleado)
	 */
	@Override
	public Empleado update(Empleado item) throws IOException {
		Empleado rslt = listado.get(item.getIdEmpleado());
		if(rslt == null)
			throw new IOException("No encontrado.");
		if(item.isInvalid())
			throw new IOException("Error en datos.");
		listado.put(item.getIdEmpleado(), item);
		return rslt;
	}
	/* (non-Javadoc)
	 * @see application.dal.Repository#delete(int)
	 */
	@Override
	public Empleado delete(int key) throws IOException {
		Empleado rslt = listado.get(key);
		if(rslt == null)
			throw new IOException("No encontrado.");
		listado.remove(key);
		return rslt;
	}
}
