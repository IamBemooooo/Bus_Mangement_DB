package Interface;

import java.lang.reflect.Array;
import java.util.ArrayList;

public interface DaoInterface<T> {
    public boolean insert(T t);
    public ArrayList<T> getAll();
    public boolean update(T t);
    public T getById(int id);
    public ArrayList<T> getByCondition(String condition);
}
