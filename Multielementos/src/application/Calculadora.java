package application;


import java.text.DecimalFormatSymbols;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Clase simple para realizar calculos acumulados.
 *
 * @author Javier
 */
public class Calculadora {

	/**
	 * Lista de las operaciones soportadas
	 */
	public static final String OPERACIONES_SOPORTADAS = "+-*/=";
	/**
	 * Simbolo separador decimal localizado
	 */
	private static final char DecimalSymbols = DecimalFormatSymbols.getInstance().getDecimalSeparator();
	/**
	 * Resultado parcial hasta el momento
	 */
	private double acumulado = 0;
	/**
	 * Operación pendiente
	 */
	private char operacion = '+';
	/**
	 * Controla si se está visualizando un resultado en pantalla
	 */
	private boolean limpia = true;

	/**
	 * Pantalla de la calculadora
	 */
	private StringProperty pantalla = new SimpleStringProperty("0");

	public String getPantalla() {
		return pantalla.get();
	}

	public void setPantalla(String pantalla) {
		this.pantalla.set(pantalla);
	}

	public StringProperty PantallaProperty() {
		return pantalla;
	}

	private void setPantalla(double rslt) {
		String s = Double.toString(rslt);
		if (s.endsWith(".0")) {
			setPantalla(s.replace(".0", ""));
		} else {
			setPantalla(java.text.NumberFormat.getInstance().format(rslt));
		}
	}

	private double getPantallaDouble() {
		String operando2 = getPantalla();
		if (operando2.endsWith(Character.toString(DecimalSymbols))) {
			operando2 += "0";
		}
		return Double.parseDouble(operando2.replace(DecimalSymbols, '.'));
	}

	/**
	 * Resumen de las operaciones realizadas
	 */
	private StringProperty resumen = new SimpleStringProperty("");

	public String getResumen() {
		return resumen.get();
	}

	public void setResumen(String resumen) {
		this.resumen.set(resumen);
	}

	public StringProperty ResumenProperty() {
		return resumen;
	}

	/**
	 * Constructor por defecto
	 */
	public Calculadora() {
		inicializa();
	}

	/**
	 * Restaura la calculadora
	 * 
	 * @param acumulado Valor inicial del acumulado
	 * @param operacion Operación pendiente
	 */
	public Calculadora(double acumulado, char operacion) {
		this.acumulado = acumulado;
		this.operacion = operacion;
	}

	/**
	 * Restaura el valor inicial para calcular la siguiente secuencia
	 */
	public void inicializa() {
		acumulado = 0;
		operacion = '+';
		this.limpia = false;
		setPantalla("0");
		setResumen("");
	}

	/**
	 * Añade el dígito a la pantalla
	 * @param d Nuevo dígito
	 * @throws CalculadoraException No es un digito.
	 */
	public void ponDigito(String d) throws CalculadoraException {
		String s = getPantalla();
		if (d == null || d.length() != 1 || "0".compareTo(d) == 1 || "9".compareTo(d) == -1)
			throw new CalculadoraException("No es un digito.");
		if (s.equals("0") || limpia) {
			s = "";
			limpia = false;
		}
		setPantalla(s + d);
	}

	/**
	 * Pone el separador decimal
	 */
	public void ponSeparadorDecimal() {
		if (limpia) {
			setPantalla("0,");
			limpia = false;
		} else if (getPantalla().indexOf(DecimalSymbols) == -1) {
			setPantalla(getPantalla() + DecimalSymbols);
		}
	}

	/**
	 * Cambia el signo del acumulado para continuar la operación
	 */
	public void cambiaSigno() {
		setPantalla(-1 * getPantallaDouble());
		if (limpia)
			acumulado = -acumulado;
	}

	/**
	 * Borra el último dígito introducido o inicializa la pantalla a 0
	 */
	public void borra() {
		String s = getPantalla();
		if (limpia || s.length() == 1 || s.equals("0,")) {
			setPantalla("0");
			limpia = false;
		} else {
			setPantalla(s.substring(0, s.length() - 1));
		}
	}

	/**
	 * Lanza el calculo pendiente y cahea el nuevo operador
	 * @param op Nueva operación
	 * @throws CalculadoraException El operando no tienen un formato númerico valido.
	 */
	public void hazOperacion(char op) throws CalculadoraException {
		String s = getResumen() + getPantalla() + op;
        try {
        	setPantalla(calcula(getPantallaDouble(), op));
        } catch (NumberFormatException ex) {
            throw new CalculadoraException(
                    "El operando no tienen un formato númerico valido.", 
                    ex);
        }
		setResumen('=' == op ? "" : s);
		limpia = true;
	}

	/**
	 * Realiza la operacion pendiente una vez obtenido el segundo operador y guarda
	 * la nueva operación pendiente
	 * 
	 * @param operando2      segundo operador
	 * @param nuevaOperacion la nueva operación pendiente
	 * @return el total acumulado hasta el momento
	 * @throws Exception Cuando el simbolo de operacion no esta soportado
	 */
	public double calcula(double operando2, char nuevaOperacion) throws CalculadoraException {
		if (OPERACIONES_SOPORTADAS.indexOf(nuevaOperacion) == -1) {
			throw new CalculadoraException("Operación no soportada.");
		}
		switch (operacion) {
		case '+':
			acumulado += operando2;
			break;
		case '-':
			acumulado -= operando2;
			break;
		case '*':
			acumulado *= operando2;
			break;
		case '/':
			acumulado /= operando2;
			break;
		case '=':
			acumulado = operando2;
			break;
		default:
			throw new CalculadoraException("Operación no soportada.");
		}
		this.operacion = nuevaOperacion;
		return acumulado;
	}
}