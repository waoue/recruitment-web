package fr.d2factory.libraryapp.member;

import fr.d2factory.libraryapp.library.Library;

/**
 * A member is a person who can borrow and return books to a {@link Library} A member can be either a student or a
 * resident
 */
public abstract class Member
{
    /**
     * identity
     */
    protected long id;

	/**
     * An initial sum of money the member has
     */
    // Perte de precision en utilisant le float
    protected float wallet;

	/**
     * The member should pay their books when they are returned to the library
     *
     * @param numberOfDays the number of days they kept the book
     */
    public abstract void payBook(int numberOfDays);

    /**
     * Calculation of the borrowing price without any discount
     * 
     * @param numberOfDays - The number of days they kept the book
     * @param priceByDay - The price of a book by day
     * @param priceByDayInLate - The price of a book by day in late
     * @param nbOfDaysWithoutLate - The number of days with the normal price
     * @return
     */
    protected float calculationBorrowingPrice(int numberOfDays, float priceByDay, float priceByDayInLate, int nbOfDaysWithoutLate)
    {
        // normal price calculation
        if (numberOfDays <= nbOfDaysWithoutLate)
        {
            return numberOfDays * priceByDay;
        }
        // late price calculation
        else
        {
        	return (nbOfDaysWithoutLate * priceByDay) + ((numberOfDays - nbOfDaysWithoutLate) * priceByDayInLate);
        }
    }

    /**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

    /**
	 * @return the wallet
	 */
	public float getWallet() {
		return wallet;
	}
}
