package rikkei.academy.service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IGenericService <T>{
//    List<T> findAll();
    void save(T t,  HttpServletRequest request);
    void deleteById(int id);
    T findById(int id);
    List<T> findAll(int start,int total);
}
