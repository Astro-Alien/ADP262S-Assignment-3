/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.cput.assignment3;

/**
 *
 * @author BURGERR
 */
public class Customer extends Stakeholder{
    private String firstName;
    private String surName;
    private String address;
    private String dateOfBirth;
    private double credit;
    private boolean canRent;   //true - can rent a car; false - not allowed to rent because they have not returned a previously rented car

    public Customer()    {
        super();
    }

    public Customer(String stHolderId, String firstName, String surName, String addr, String dob, double cred, boolean canRent)    {
    	super(stHolderId);
        setFirstName(firstName);
        setSurName(surName);
        setAddress(addr);
        setDateOfBirth(dob);
        setCredit(cred);
        setCanRent(canRent);
    }
  
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public void setSurName(String surName)   {
        this.surName = surName;
    }

    public void setAddress(String addr)    {
        this.address = addr;
    }

    public void setDateOfBirth(String dob) { this.dateOfBirth = dob; }

    public void setCredit(double cred)    {
        this.credit = cred;
    }

	public void setCanRent(boolean canRent)  {
		this.canRent = canRent;
	}

    public String getFirstName()     {
        return firstName;
    }

    public String getSurName()    {
        return surName;
    }

    public String getAddress()    {
        return address;
    }

    public String getDateOfBirth() { return dateOfBirth; }

    public double getCredit()    {
        return credit;
    }

	public boolean getCanRent()     {
        return canRent;
    }

    @Override
    public String toString()      {
        return String.format("%-10s\t%-10s\t%-10s\t%-15s\t%-10s\tR%-10.2f\t%-10s", super.toString(), getFirstName(), getSurName(),
                getAddress(), getDateOfBirth(), getCredit(), getCanRent());
    }
}