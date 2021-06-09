/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.cput.assignment3;

/**
 *
 * @author Charles
 */
public class RunCode {

    public static void main(String[] args) {
        //calling class in main class
        ReadingSerFileCustomer customerData = new ReadingSerFileCustomer();
        ReadSerFileSupplier supplierData = new ReadSerFileSupplier();
        
        customerData.runCodeC();
        supplierData.runCodeS();
    }
}
