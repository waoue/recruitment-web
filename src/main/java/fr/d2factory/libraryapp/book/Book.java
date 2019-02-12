package fr.d2factory.libraryapp.book;

import java.time.LocalDate;

/**
 * A simple representation of a book
 */
public class Book
{
    /**
     * title of this book.
     */
    private String title;

    /**
     * author of this book.
     */
    private String author;

    /**
     * identity of this book.
     */
    private ISBN isbn;
    
    /**
     * borrowing date of this book.
     */
    private LocalDate borrowingDate;

    /** Constructor. */
    public Book()
    {
    }

    /** Constructor. */
    public Book(String title, String author, ISBN isbn)
    {
        this.setTitle(title);
        this.setAuthor(author);
        this.setIsbn(isbn);
    }

    /**
     * Accesseur de title
     *
     * @return title
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * Mutateur de title
     *
     * @param title title
     */
    public void setTitle(String title)
    {
        this.title = title;
    }

    /**
     * Accesseur de author
     *
     * @return author
     */
    public String getAuthor()
    {
        return author;
    }

    /**
     * Mutateur de author
     *
     * @param author author
     */
    public void setAuthor(String author)
    {
        this.author = author;
    }

    /**
     * Accesseur de borrowingDate
     *
     * @return borrowingDate
     */
    public LocalDate getBorrowingDate()
    {
        return borrowingDate;
    }

    /**
     * Mutateur de borrowingDate
     *
     * @param borrowingDate borrowingDate
     */
    public void setBorrowingDate(LocalDate borrowingDate)
    {
        this.borrowingDate = borrowingDate;
    }

	/**
	 * @return the isbn
	 */
	public ISBN getIsbn() {
		return isbn;
	}

	/**
	 * @param isbn the isbn to set
	 */
	public void setIsbn(ISBN isbn) {
		this.isbn = isbn;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (isbn == null) {
			if (other.isbn != null)
				return false;
		} else if (!isbn.equals(other.isbn))
			return false;
		return true;
	}
}
