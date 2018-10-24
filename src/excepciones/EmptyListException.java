package excepciones;

public class EmptyListException extends Exception {

	public EmptyListException() {
		super();
	}

	public EmptyListException(String arg0) {
		super(arg0);
	}

	public EmptyListException(Throwable arg0) {
		super(arg0);
	}

	public EmptyListException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public EmptyListException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}
