package application.dal;

import java.io.IOException;
import java.util.Collection;

public interface Repository<T> {

	Collection<T> getAll();

	T get(int key) throws IOException;

	T insert(Empleado item) throws IOException;

	T update(Empleado item) throws IOException;

	T delete(int key) throws IOException;

}