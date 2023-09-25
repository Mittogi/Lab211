package bussiness.entity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author PHAT
 */
public class Receipt extends Items {

    private Date receiptTime;
    private ArrayList<Product> listItems;
    private String type;

    public Receipt(String receiptCode, String type, Date receiptTime, ArrayList<Product> Items) {
        super(receiptCode);
        this.type = type;
        this.receiptTime = receiptTime;
        this.listItems = Items;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    
    @Override
    public String toString() {
        return "ReceiptCode = " + code + ", type = " + type + ", receiptTime = " + formatDateToString(receiptTime) + ", listItems = " + listItems;
    }

    public String toRawData() {
        String string = code + ", " + type + ", " + formatDateToString(receiptTime);
        for (Product item : listItems) {
            string = string + ", " + item.getCode();
        }
        
        return string;
    }
    
    private String formatDateToString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        return dateFormat.format(date);
    }
}
