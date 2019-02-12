/*
 * Copyright (c) 2019 DGFiP - Tous droits réservés
 * 
 */
package fr.d2factory.libraryapp.library;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.d2factory.libraryapp.book.Book;
import fr.d2factory.libraryapp.book.BookRepository;
import fr.d2factory.libraryapp.member.Member;
import fr.d2factory.libraryapp.member.Resident;
import fr.d2factory.libraryapp.member.Student;

/**
 * The library class is in charge of stocking the books and managing the return delays and members
 *
 * The books are available via the {@link fr.d2factory.libraryapp.book.BookRepository}
 */
public class LibraryImpl implements Library
{
    /** Constant : logger. */
    private static final Logger log = LoggerFactory.getLogger(LibraryImpl.class);
    
    /** The repository */
    BookRepository repo = BookRepository.getInstance();

    /**
     * 
     * (methode de remplacement)
     * {@inheritDoc}
     * @see fr.d2factory.libraryapp.library.Library#borrowBook(long, fr.d2factory.libraryapp.member.Member, java.time.LocalDate)
     */
    @Override
    public Book borrowBook(long isbnCode, Member member, LocalDate borrowedAt)
    {
    	// Check if the member is late
        if (isMemberLate(member))
        {
            log.info("The member has late books");
            throw new HasLateBooksException("The member has late books");
        }
        
        // Get the book
        Book book = repo.findBook(isbnCode);
        
        if (book != null)
        {
            repo.saveBookBorrow(member, book, borrowedAt);
        }
            
        return book;
    }

    /**
     * Check if the member is late.
     * 
     * @param member
     * @return
     */
    private boolean isMemberLate(Member member) {
    	if (repo.getBorrowedBooks().get(member.getId()) != null)
    	{
            for (Book book : repo.getBorrowedBooks().get(member.getId()))
            {
                if (member instanceof Resident && ChronoUnit.DAYS.between(book.getBorrowingDate(), LocalDate.now()) > Resident.NB_OF_DAYS_WITHOUT_LATE)
                {
                    return true;
                }
                else if (member instanceof Student && ChronoUnit.DAYS.between(book.getBorrowingDate(), LocalDate.now()) > Student.NB_OF_DAYS_WITHOUT_LATE)
                {
                    return true;
                }
            }
    	}
    	
        return false;
	}

	/**
     * 
     * (methode de remplacement)
     * {@inheritDoc}
     * @see fr.d2factory.libraryapp.library.Library#returnBook(fr.d2factory.libraryapp.book.Book, fr.d2factory.libraryapp.member.Member)
     */
    @Override
    public void returnBook(Book book, Member member)
    {
    	Book borrowedBook = repo.findBorrowedBookDate(member, book);
    	
    	if (borrowedBook != null)
    	{
        	repo.saveBookAvailable(member, borrowedBook);
        	member.payBook((int) ChronoUnit.DAYS.between(borrowedBook.getBorrowingDate(),LocalDate.now()));
    	}
    }

}
