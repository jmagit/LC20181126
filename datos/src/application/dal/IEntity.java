package application.dal;

public interface IEntity {

	boolean isValid();

	boolean isInvalid();

	String[] errors();

}