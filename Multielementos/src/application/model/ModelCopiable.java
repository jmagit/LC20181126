package application.model;

public interface ModelCopiable<M, E> {
	M copyEntity(E item);
	M copyModel(M item);
	E getEntity();
}