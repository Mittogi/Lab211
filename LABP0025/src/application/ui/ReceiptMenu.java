package application.ui;

import bussiness.entity.Receipt;
import bussiness.services.receiptservice.IReceiptService;

/**
 *
 * @author PHAT
 */
public class ReceiptMenu {
    IReceiptService<Receipt> service;
    
//contructor--------------------------------------------------------------------
   public ReceiptMenu(IReceiptService<Receipt> service) {
        this.service = service;
    }
    
//receipt menu------------------------------------------------------------------
   public void processMenuForReceipt() {
       boolean quit = false;
       
       try {
           do {
               Menu.print("******Product Management******|1.Create an import receipt.|2.Create an export receipt|3.Print all receipt|4. Back to main menu|Select: ");
               
               int choice = Menu.getUserChoice();
               
               switch (choice) {
                    case 1: //Create an import receipt
                       
                        break;
                       
                    case 2: //create an export receipt 
                        
                        break;
                       
                    case 3: //print all receipt 
                        System.out.println("Receipt list:");
                        printAllReceipt();
                        break;
                            
                    case 4:
                       quit = true;
                       break;
                       
                   default:
                       System.out.println("Choice invalid");
                       break;
               }
           }while (quit == false);
       } catch (Exception e) {
           e.getMessage();
       }
   }
   
//case 3------------------------------------------------------------------------
   public void printAllReceipt() {
       try {
           service.printList();
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
   }
}
