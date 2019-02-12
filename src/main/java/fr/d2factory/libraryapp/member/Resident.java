package fr.d2factory.libraryapp.member;

/**
 * Resident is a type of member.
 */
public class Resident extends Member
{

    /**
     *  The price of a book by day.
     */
	public static final float PRICE_BY_DAY = 0.10f;

    /**
     *  The price of a book by day in late.
     */
    public static final float PRICE_BY_DAY_IN_LATE = 0.20f;
    
    /**
     *  The number of days with the normal price.
     */
    public static final int NB_OF_DAYS_WITHOUT_LATE = 60;

    /** Constructor. */
    public Resident(long id)
    {
    	this.id = id;
    }

    /**
     * (methode de remplacement) {@inheritDoc}
     * 
     * @see fr.d2factory.libraryapp.member.Member#payBook(int)
     */
    @Override
    public void payBook(int numberOfDays)
    {
    	float borrowingPrice = calculationBorrowingPrice(numberOfDays, PRICE_BY_DAY, PRICE_BY_DAY_IN_LATE, NB_OF_DAYS_WITHOUT_LATE);
    	this.wallet -= borrowingPrice;
    }
}