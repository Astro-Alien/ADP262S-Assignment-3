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
import java.util.ArrayList;
import java.util.Collections;


/**
 *
 * @author Charles Moses Lemmert 
 * Student No: 220498385
 */
public class ReadSerFileSupplier {

    FileInputStream supplierInput;
    ObjectInputStream supplierFile;

    FileWriter wFile;
    PrintWriter pWriter;
    //--------------------------------------------------------------------------Opening file
    public void FileOpened(String nameF) {

        try {
            wFile = new FileWriter(new File(nameF));
            pWriter = new PrintWriter(wFile);
            //remove later
            System.out.println("***The file " + nameF + " was Created***");

        } catch (IOException ioe) {
            ioe.printStackTrace();
            System.exit(1);
        }
    }
    //--------------------------------------------------------------------------ArrayList
    private ArrayList<Supplier> ListOfSuppliers() {
        ArrayList<Supplier> supplierInfo = new ArrayList<>();

        try {
            supplierInput = new FileInputStream(new File("stakeholder.ser"));
            supplierFile = new ObjectInputStream(supplierInput);

            // throws an EOFException 
            while (true) {
                Object obj = supplierFile.readObject();

                if (obj instanceof Supplier) {
                    supplierInfo.add((Supplier) obj);
                }
            }

        } catch (EOFException eofe) {

        } catch (IOException e) {
            System.out.println("***File closed due to: " + e.getMessage() + "***");
        } catch (ClassNotFoundException e) {
            System.out.println("***File closed due to: " + e.getMessage() + "***");

        } finally {
            closeUp();
        }

        if (!supplierInfo.isEmpty()) {

            Collections.sort(supplierInfo, (Supplier Sup1, Supplier Sup2) -> Sup1.getStHolderId().compareTo(Sup2.getStHolderId())
            );
        }

        return supplierInfo;
    }
    //--------------------------------------------------------------------------Method to close FileInputStream and ObjectInputstream
    public void closeUp() {

        try {
            supplierInput.close();
            supplierFile.close();

        } catch (IOException ex) {
            System.out.println("***File closed due to: " + ex.getMessage() + "***");
        }

    }

    //--------------------------------------------------------------------------Writting supplier details from the ser file to a txt file
    public void supplierDetailsTxt() {

        try {
            pWriter.print("=========================== SUPPLIERS =====================================\n");
            pWriter.printf("%s\t%-16s\t%-16s\t%-16s\n", "ID", "Name", "Surname",
                    "Description");
            pWriter.print("===========================================================================\n");

            for (int x = 0; x < ListOfSuppliers().size(); x++) {
                pWriter.printf("%s\t%-16s\t%-16s\t%-16s\n", ListOfSuppliers().get(x).getStHolderId(), ListOfSuppliers().get(x).getName(),
                        ListOfSuppliers().get(x).getProductType(),ListOfSuppliers().get(x).getProductDescription());
                
            }
            
            pWriter.print("===========================================================================\n");
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
    public void runCodeS() {
        ReadSerFileSupplier supplierF = new ReadSerFileSupplier();

        supplierF.FileOpened("supplierOutFile.txt");
        supplierF.supplierDetailsTxt();
        supplierF.closeFileWriter();
     
    }
}
