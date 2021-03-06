package application.dal;

import java.io.IOException;
import java.util.Collection;

public interface Repository<T extends IEntity> {

	Collection<T> getAll();

	T get(int key) throws IOException;

	T insert(T item) throws IOException;

	T update(T item) throws IOException;

	T delete(int key) throws IOException;

}