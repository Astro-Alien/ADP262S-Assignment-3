/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.cput.assignment3;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

/**
 *
 * @author Charles Moses Lemmert
 * Student No: 220498385
 */
public class ReadingSerFileCustomer {
   
    FileInputStream customerInput;
    ObjectInputStream customerFile;
    FileWriter wFile;
    PrintWriter pWriter;
    //--------------------------------------------------------------------------Opening file
    public void FileOpened(String nameF) {

        try {
            wFile = new FileWriter(new File(nameF));
            pWriter = new PrintWriter(wFile);
            //remove later
            System.out.println("***The file "+ nameF + " was Created***");

        } catch (IOException ioe) {
            System.out.println("***Error due to: " + ioe.getMessage() + "***");
        }
    }
 //--------------------------------------------------------------------------ArrayList
    private ArrayList<Customer> ListOfCustomers() {
        //new array to save customer ser input to array
        ArrayList<Customer> customerInfo = new ArrayList<>();
        
        try {
            customerInput = new FileInputStream(new File("stakeholder.ser"));
            customerFile = new ObjectInputStream(customerInput);

            // throws an EOFException 
            while (true) {
                Object obj = customerFile.readObject();

                if(obj instanceof Customer) {
                    customerInfo.add((Customer) obj);
                }
            }

        } catch (EOFException eofe) {
            
        } catch (IOException e){
            System.out.println("***File closed due to: " + e.getMessage() + "***");
        }
                
         catch(ClassNotFoundException e) {
            System.out.println("***File closed due to: " + e.getMessage() + "***");

        } finally {
            closeUp();
        }

        if (!customerInfo.isEmpty()) {

            Collections.sort(customerInfo,(Customer k1, Customer k2)-> k1.getStHolderId().compareTo(k2.getStHolderId())
            );
        }

        return customerInfo;
    }
    //--------------------------------------------------------------------------Method to close FileInputStream and ObjectInputstream
    public void closeUp(){
    
        try {
                customerInput.close();
                customerFile.close();

            } catch (IOException ex) {
            System.out.println("***File closed due to: " + ex.getMessage() + "***");    
            }
    
    }
    //--------------------------------------------------------------------------Calculating customer agewith LocalDateTime Object
    public int customerAge(String dateOB) {

        LocalDate conDate = LocalDate.parse(dateOB);
        int YearOfBirth = conDate.getYear();
        LocalDateTime currentdate = LocalDateTime.now();
        int yr = currentdate.getYear();
        int possibleAge = yr - YearOfBirth;

        return possibleAge;

    }
    
     //--------------------------------------------------------------------------Converting date To e.g 14 Jan 1999
    public String convertDate(String dateOB) {

        DateTimeFormatter convert = DateTimeFormatter.ofPattern("dd MM yyyy", Locale.ENGLISH);
        LocalDate conDate = LocalDate.parse(dateOB);
        return conDate.format(convert);

    }

    //--------------------------------------------------------------------------Checking if a customer can rent 
    public int RentC() {

        int RentC = 0;

        for (int x = 0; x < ListOfCustomers().size(); x++) {
            if (ListOfCustomers().get(x).getCanRent()) {
           
                RentC = RentC + 1;
             }
        }

        return RentC;

    }
    //--------------------------------------------------------------------------Checking if a customer cannot rent 
    public int RentCN() {

        int RentCN = 0;
        for (int y = 0; y < ListOfCustomers().size(); y++) {
            if (!ListOfCustomers().get(y).getCanRent()) {
                RentCN = RentCN + 1;
            }
        }
        return RentCN;
    }
    //--------------------------------------------------------------------------Writting customer details from the ser file to a txt file
        public void customerDetailsTxt() {
        

        try {
            pWriter.print("======================= CUSTOMERS =========================\n");
            pWriter.printf("%s\t%-10s\t%-10s\t%-10s\t%-10s\n", "ID", "Name", "Surname",
                    "Date Of Birth", "Age");
            pWriter.print("===========================================================\n");

            for (int x = 0; x < ListOfCustomers().size(); x++) {
                pWriter.printf("%s\t%-10s\t%-10s\t%-10s\t%-10s\n", ListOfCustomers().get(x).getStHolderId(), ListOfCustomers().get(x).getFirstName(),
                        ListOfCustomers().get(x).getSurName(), convertDate(ListOfCustomers().get(x).getDateOfBirth()),customerAge(ListOfCustomers().get(x).getDateOfBirth())
                );
            }
            pWriter.println("\nNumber of customers who can rent: "+ RentC());
            pWriter.println("\nNumber of customers who cannot rent: "+ RentCN());
            pWriter.print("===========================================================\n");
        } catch (Exception ex) {

           System.out.println("***File closed due to: " + ex.getMessage() + "***");

        }

    }
    //--------------------------------------------------------------------------Closing the FileWriter Object and PrintWriter Object
    public void closeFileWriter() {

        try {
            wFile.close();
            pWriter.close();
             
        } catch (IOException ex) {
            System.out.println("***File closed due to: " + ex.getMessage() + "***");
        }

    }
    //--------------------------------------------------------------------------Creating a function that can be called by the main to run 
    public void runCodeC() {
        ReadingSerFileCustomer customer = new ReadingSerFileCustomer();

        customer.FileOpened("customerOutFile.txt");
        customer.customerDetailsTxt();
        customer.closeFileWriter();
       
    }
}
