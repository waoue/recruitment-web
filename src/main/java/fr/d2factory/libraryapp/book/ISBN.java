package fr.d2factory.libraryapp.book;

/**
 * International Standard Book Number
 */
public class ISBN {
	
	/**
	 * Code represent a International Standard Book Number
	 */
    private long isbnCode;


    /** Constructor. */
    public ISBN() {
        this.setIsbnCode((long) (Math.random() * 100));
    }

    /** Constructor. */
    public ISBN(long isbnCode) {
        this.isbnCode = isbnCode;
    }


	/**
	 * @return the isbnCode
	 */
	public long getIsbnCode() {
		return isbnCode;
	}


	/**
	 * @param isbnCode the isbnCode to set
	 */
	public void setIsbnCode(long isbnCode) {
		this.isbnCode = isbnCode;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (isbnCode ^ (isbnCode >>> 32));
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ISBN other = (ISBN) obj;
		if (isbnCode != other.isbnCode)
			return false;
		return true;
	}
}