
package bussiness.entity;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author PHAT
 */
public class Receipt extends Items {
//fileds------------------------------------------------
    private Date receiptTime;
    private ArrayList<Product> listItems;
 
//contructor--------------------------------------------
    public Receipt(String receiptCode, Date receiptTime, ArrayList<Product> Items) {
       super(receiptCode);
        this.receiptTime = receiptTime;
        this.listItems = Items;
    }
    
//get and set---------------------------------------------
    @Override
    public String getCode() {
        return code;
     }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    public Date getReceiptTime() {
        return receiptTime;
    }

    public void setReceiptTime(Date receiptTime) {
        this.receiptTime = receiptTime;
    }

    public ArrayList<Product> getItems() {
        return listItems;
    }

    public void setItems(ArrayList<Product> Items) {
        this.listItems = Items;
    }
    
//toString-----------------------------------------------------
    @Override
    public String toString() {
        return  "ReceiptCode = " + code + " , receiptTime = " + receiptTime + ", listItems = " + listItems + '}';
    }    
}
