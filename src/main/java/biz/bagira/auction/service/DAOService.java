package biz.bagira.auction.service;

import java.util.Collection;

/**
 * Created by Dmitriy on 20.01.2017.
 */
public interface DAOService<T> {
    void create(T entity);

    void delete(T entity);

    void edit(T entity);

    T getById(Integer id);

    Collection<T> getAll();
}
