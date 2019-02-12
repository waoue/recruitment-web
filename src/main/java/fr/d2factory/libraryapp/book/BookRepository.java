package fr.d2factory.libraryapp.book;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.d2factory.libraryapp.member.Member;

/**
 * The book repository emulates a database via 2 HashMaps
 */
public class BookRepository {
	private Map<ISBN, Book> availableBooks = new HashMap<>();

	private Map<Long, List<Book>> borrowedBooks = new HashMap<>();

	/** Instance unique pré-initialisée */
	private static final BookRepository INSTANCE = new BookRepository();

	/** Point d'accès pour l'instance unique du singleton */
	public static final BookRepository getInstance() {
		return INSTANCE;
	}

	/**
	 * Add books to the library.
	 *
	 * @param books
	 *            - books to add
	 */
	public void addBooks(List<Book> books) {
		for (Book book : books) {
			availableBooks.put(book.getIsbn(), book);
		}
	}

	/**
	 * Delete books from the library.
	 *
	 * @param books
	 *            - books to delete
	 */
	public void deleteBooks(List<Book> books) {
		for (Book book : books) {
			availableBooks.remove(book.getIsbn());
		}
	}

	/**
	 * Find a book with his id.
	 *
	 * @param isbnCode
	 *            - id of a book
	 * @return
	 */
	public Book findBook(long isbnCode) {
		return availableBooks.get(new ISBN(isbnCode));
	}

	/**
	 * saving the book to borrow
	 *
	 * @param member
	 *            - member who borrow book
	 * @param book
	 *            - books to borrow
	 * @param borrowedAt
	 *            - borrowing date of the book
	 */
	public void saveBookBorrow(Member member, Book book, LocalDate borrowedAt) {
		book.setBorrowingDate(borrowedAt);

		// Add borrowed book
		if (borrowedBooks.get(member.getId()) == null) {
			borrowedBooks.put(member.getId(), new ArrayList<Book>(Arrays.asList(book)));
		} else {
			borrowedBooks.get(member.getId()).add(book);
		}

		// Remove available book
		availableBooks.remove(book.getIsbn());
	}

	/**
	 * saving the book to render
	 *
	 * @param member
	 *            - member who borrow book
	 * @param book
	 *            - book to render
	 */
	public void saveBookAvailable(Member member, Book book) {
		boolean removeSucces = false;

		// Remove the book from the member
		if (borrowedBooks.get(member.getId()) != null) {
			removeSucces = borrowedBooks.get(member.getId()).remove(book);
		}

		// The book will be available if remove succes
		if (removeSucces) {
			book.setBorrowingDate(null);
			availableBooks.put(book.getIsbn(), book);
		}

	}

	/**
	 * Find the borrowing date of a book.
	 *
	 * @param member
	 *            - member who borrow book
	 * @param book
	 *            - borrowed book
	 * @return
	 */
	public Book findBorrowedBookDate(Member member, Book book) {

		if (borrowedBooks.get(member.getId()) != null)
		{
			for (Book borrowedbook : borrowedBooks.get(member.getId()))
			{
				if (borrowedbook.getIsbn().equals(book.getIsbn())) {
					return borrowedbook;
				}
			}
		}
		return null;
	}

	/**
	 * @return the borrowedBooks
	 */
	public Map<Long, List<Book>> getBorrowedBooks() {
		return borrowedBooks;
	}
}
