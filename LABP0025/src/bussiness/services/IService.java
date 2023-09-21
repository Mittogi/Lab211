package bussiness.services;

import bussiness.entity.Product;
import java.util.Date;
import java.util.List;

/**
 *
 * @author PHAT
 */
public interface IService<T> {
    //Repository      
    void printList() throws Exception ;   
    List<T> getList() throws Exception;    
    void add(T obj) throws Exception;
    

}
