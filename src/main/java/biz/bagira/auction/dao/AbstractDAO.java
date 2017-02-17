package biz.bagira.auction.dao;

import java.util.Collection;

/**
 * Created by Dmitriy on 18.01.2017.
 */
public interface AbstractDAO<T> {
     void create(T entity);

     void delete(T entity);

     void delete(Integer id);

     void edit(T entity);

     T getById(Integer id);

      Collection<T> getAll();
}
