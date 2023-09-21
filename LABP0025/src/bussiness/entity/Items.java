package bussiness.entity;

/**
 *
 * @author PHAT
 */
public abstract class Items {
//fileds------------------------------------------------------------------------
    protected String code;
    
//contructor--------------------------------------------------------------------
    public Items(String code) {
        this.code = code;
    }
    

//get and set-------------------------------------------------------------------
    public abstract String getCode();

    public abstract void setCode(String code);

    

}
