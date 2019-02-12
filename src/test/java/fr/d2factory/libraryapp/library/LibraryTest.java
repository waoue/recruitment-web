package fr.d2factory.libraryapp.library;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.d2factory.libraryapp.book.Book;
import fr.d2factory.libraryapp.book.BookRepository;
import fr.d2factory.libraryapp.book.ISBN;
import fr.d2factory.libraryapp.member.Resident;
import fr.d2factory.libraryapp.member.Student;

public class LibraryTest {
    private Library library ;
    private BookRepository bookRepository;

    @Before
    public void setup(){
    	library = new LibraryImpl();
    	bookRepository = BookRepository.getInstance();
    	List<Book> books = null;
    	        
        
        ObjectMapper mapper = new ObjectMapper();
        InputStream is = Book.class.getResourceAsStream("/books.json");
        try {
            books = mapper.readValue(is, mapper.getTypeFactory().constructCollectionType(List.class, Book.class));
        } catch (IOException e) {
            e.printStackTrace();
        }

        books.get(2).setBorrowingDate(LocalDate.now());
        books.get(1).setBorrowingDate(LocalDate.now().minusDays(61));
        bookRepository.getBorrowedBooks().put((long) 1, new ArrayList<Book>(Arrays.asList(books.get(2))));
        bookRepository.getBorrowedBooks().put((long) 2, new ArrayList<Book>(Arrays.asList(books.get(1))));
        books.remove(2);
        books.remove(1);
    	bookRepository.addBooks(books);
    }

    @Test
    public void member_can_borrow_a_book_if_book_is_available(){
    	Book book = library.borrowBook(46578964513L, new Resident(1), LocalDate.now());
    	assertNotNull(book);
    	assertEquals("Harry Potter", book.getTitle());
    	assertEquals("J.K. Rowling", book.getAuthor());
    	assertEquals(46578964513L, book.getIsbn().getIsbnCode());
    }

    @Test
    public void borrowed_book_is_no_longer_available(){
    	Book book = library.borrowBook(968787565445L, new Resident(1), LocalDate.now());
    	assertNull(book);
    }

    @Test
    public void residents_are_taxed_10cents_for_each_day_they_keep_a_book(){
    	Resident resident = new Resident(1);
    	resident.payBook(34);
    	Assert.assertEquals(-3.4f, resident.getWallet(), 0.1);
    }

    @Test
    public void students_pay_10_cents_the_first_30days(){
    	Student student = new Student(1, 2);
    	student.payBook(30);
    	Assert.assertEquals(-3.0f, student.getWallet(), 0.1);
    }

    @Test
    public void students_in_1st_year_are_not_taxed_for_the_first_15days(){
    	Student student = new Student(1, 1);
    	student.payBook(15);
    	Assert.assertEquals(0.0f, student.getWallet(), 0.1);
    }

    @Test
    public void students_pay_15cents_for_each_day_they_keep_a_book_after_the_initial_30days(){
    	Student student = new Student(1, 2);
    	student.payBook(34);
    	Assert.assertEquals(-3.6f, student.getWallet(), 0.1);
    }

    @Test
    public void residents_pay_20cents_for_each_day_they_keep_a_book_after_the_initial_60days(){
    	Resident resident = new Resident(1);
    	resident.payBook(64);
    	Assert.assertEquals(-6.8f, resident.getWallet(), 0.1);
    }

    @Test(expected=HasLateBooksException.class)
    public void members_cannot_borrow_book_if_they_have_late_books(){
    	library.borrowBook(465789453149L, new Resident(2), LocalDate.now());
    }

    @Test
    public void member_return_book(){
    }
}
