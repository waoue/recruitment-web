package fr.d2factory.libraryapp.library;

/**
 * This exception is thrown when a member who owns late books tries to borrow another book
 */
public class HasLateBooksException extends RuntimeException {

	public HasLateBooksException(String string) {
		super(string);
	}
	
	public HasLateBooksException() {
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 8859592170741410965L;
}
