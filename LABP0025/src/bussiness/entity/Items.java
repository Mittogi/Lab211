package bussiness.entity;

/**
 *
 * @author PHAT
 */
public abstract class Items {
    protected String code;
    
    public Items(String code) {
        this.code = code;
    }
    
    public abstract String getCode();

    public abstract void setCode(String code);

    

}
