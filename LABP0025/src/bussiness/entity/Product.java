package bussiness.entity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author PHAT
 */
public class Product extends Items {
    private String name;
    private int quantity;
    private Date  manufacturingDate;
    private Date  expirationDate;
    
    public Product(String code, String name, int quantity, Date manufacturingDate, Date expirationDate) {
        super(code);        
        this.name = name;
        this.quantity = quantity;
        this.manufacturingDate = manufacturingDate;
        this.expirationDate = expirationDate;
    }
    
    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    
    public Date getManufacturingDate() {
        return manufacturingDate;
    }

    public void setManufacturingDate(Date manufacturingDate) {
        this.manufacturingDate = manufacturingDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
    
    @Override
    public String toString() {
        return "code = " + code + ", name = " + name + ", quatity = " + quantity + ", manufacturingDate = " + formatDateToString(manufacturingDate) + ", expirationDate = " + formatDateToString(expirationDate);
    }
    
    public String toRawData() {
        return code + ", " + name + ", " + quantity + ", " + formatDateToString(manufacturingDate) + ", " + formatDateToString(expirationDate);
    }
    
    private String formatDateToString(Date date) { 
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
      
        return dateFormat.format(date);
    }
}
