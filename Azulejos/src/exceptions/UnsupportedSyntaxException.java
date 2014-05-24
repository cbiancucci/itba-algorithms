package exceptions;

public class UnsupportedSyntaxException extends UnsupportedOperationException {

	private static final long serialVersionUID = 1L;

	public UnsupportedSyntaxException(){
		super("Error en la sintaxis de linea de comando. Verifique los argumentos para ejecutar el programa.");
	}

}
