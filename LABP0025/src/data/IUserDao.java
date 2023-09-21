package data;

import java.util.List;

/**
 *
 * @author PHAT
 * @param <T>
 */
public interface IUserDao<T>{
    void addNew(T obj) throws Exception;
    List<T> getList();
}
