package biz.bagira.auction.dao;

import java.util.Set;

/**
 * Created by Dmitriy on 18.01.2017.
 */
public interface AbstractDAO<T> {
     T create(T entity);

     void delete(T entity);

     T edit(T entity);

     T getById(Integer id);

      Set<T> getAll();
}
