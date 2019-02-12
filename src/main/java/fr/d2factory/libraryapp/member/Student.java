/*
 * Copyright (c) 2019 DGFiP - Tous droits réservés
 * 
 */
package fr.d2factory.libraryapp.member;

/**
 * Student is a type of member.
 */
public class Student extends Member
{

    /**
     *  The price of a book by day.
     */
	public static final float PRICE_BY_DAY = 0.10f;

    /**
     *  The price of a book by day in late.
     */
	public static final float PRICE_BY_DAY_IN_LATE = 0.15f;
    
    /**
     *  The number of days with the normal price.
     */
	public static final int NB_OF_DAYS_WITHOUT_LATE = 30;
    
    /**
     *  The number of years of study with privilege price.
     */
	public static final int YEAR_OF_PRIVILEGE = 1;
    
    /**
     *  The number of days with privilege price.
     */
	public static final int DAY_OF_PRIVILEGE = 15;
    
    /**
     * Number of years of study
     */
    private int year;

    /** Constructor. */
    public Student(long id, int year)
    {
    	this.id = id;
        this.setYear(year);
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
    	
        // Check if the student is his first year
        if (this.year == YEAR_OF_PRIVILEGE)
        {
            if (numberOfDays <= DAY_OF_PRIVILEGE)
            {
                return;
            }
            else
            {
            	// Apply the reduction
            	borrowingPrice -= DAY_OF_PRIVILEGE * PRICE_BY_DAY;
            }
        }
        
    	this.wallet -= borrowingPrice;
    }

    /**
     * Accesseur de year
     *
     * @return year
     */
    public int getYear()
    {
        return year;
    }

    /**
     * Mutateur de year
     *
     * @param year year
     */
    public void setYear(int year)
    {
        this.year = year;
    }

}
